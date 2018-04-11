var monitoringTabelNgModule=angular.module('monitoringTabelNgModule',['ngAnimate','ui.bootstrap','app']);
var monitoringTabelService = monitoringTabelNgModule.factory('monitoringTabelService',
		['$resource', function($resource){
			//指定url格式:../模块名/服务名/方法名?参数
			var path = '../rest/:moduleName/:serviceName/:methodName?rnd=:random';
			//service忠的方法
			var resource = $resource(path, {}, {
				save : {
					method : 'POST',
					params : {
						moduleName : 'proj',
						serviceName : 'info',
						methodName : 'save',
						//role : '@role',
						//rp : '@rp',
						random : Math.random()
					}
				},
			});
			return resource;
	}]);
var monitoringTabelNgControl=monitoringTabelNgModule.controller('monitoringTabelNgControl',function($rootScope,$timeout, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,monitoringTabelService,httpService){
	$scope.getDate = function (index){
	    var date = new Date(); //当前日期
	    var newDate = new Date();
	    newDate.setDate(date.getDate() + index);//官方文档上虽然说setDate参数是1-31,其实是可以设置负数的
	    var time = newDate.getFullYear()+"/"+(newDate.getMonth()+1)+"/"+newDate.getDate();
	    return time;
	}

	$scope.startTime = $scope.getDate(-7);
	$scope.endTime = $scope.getDate(0);

	//地域
	$scope.error = {};
	$scope.division = division;
	$scope.c = function () {
	   $scope.error.province = false;
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected2 = "";
	   $scope.selected3 = "";
	};
	$scope.c2 = function () {       
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected3 = "";
	};
	$scope.c3 = function () {
	   $scope.error.area = false;
	};

	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	//初始化
	$scope.pageInit = function (){
		var text = $scope.searchText;
		if($scope.selected == null || $scope.selected == ''){
			$scope.selected ={
				name:''
			}
		}
		if($scope.selected2 == null || $scope.selected2 == ''){
			$scope.selected2 ={
				name:''
			}
		}
		if($scope.selected3 == null || $scope.selected3 == ''){
			$scope.selected3 ={
				value:''
			}
		}
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				projectName : text,
				projectAddress : text,
				projectProvince:$scope.selected.name,
				projectCity:$scope.selected2.name,
				projectCounty:$scope.selected3.value,
			};
		
		httpService.post({url:'./hiddenDangerEdit/pageHiddenDangerEdit',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			console.log(JSON.stringify(data.data.data.pageList));
			$scope.projects = data.data.data.pageList;
			for(i in $scope.projects){
				if($scope.projects[i].hiddeCount>0){
					$scope.projects[i].status = 'warning';
					$scope.projects[i].statusText = '隐患';
				}else if($scope.projects[i].warningCount>0){
					$scope.projects[i].status = 'danger';
					$scope.projects[i].statusText = '报警';
				}else{
					$scope.projects[i].status = ''
					$scope.projects[i].statusText = '正常';
				}
			}

			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.projects.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;

			$scope.passagewayInit($scope.projects[0].id);
		})
	};
	$scope.pageInit();
	$scope.passagewayInit = function (idNum){
		var text = $scope.searchText;
		var pageParam = {
				equipmentId:idNum,
				startDateString:$scope.startTime,
				endDateString:$scope.endTime,
				/*projectName : text,
				projectAddress : text,*/
			};
		
		httpService.post({url:'./equipment/selectEquipInfoAndData',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			console.log(JSON.stringify(data));
			$scope.equipmentInfo = data.data.data;
		})
	};
	$scope.passagewayInit(52);

	//修改分页大小
	$scope.changePageSize = function(){
		$scope.currentPage = 1;
		$scope.pageInit();
	}
	//上一页
	$scope.lastPage = function(){
		if ($scope.hasPrevious){
			$scope.currentPage -=1;
			$scope.pageInit();
		}
	}
	//下一页
	$scope.nextPage = function (){
		if ($scope.hasNext){
			$scope.currentPage +=1;
			$scope.pageInit();
		}
	}
	//跳转
	$scope.pageTo = function(page){
		$scope.currentPage = page;
		$scope.pageInit();
	}
		$scope.option={
		    title:{
		        show:false,
		    },
		    toolbox:{
		        show:false,
		    },
		    grid:{
		        top:10,
		        left:60,
		        right:120,
		        bottom:30,
		        borderColor:'#566c93',
		    },
		    tooltip:{
		        trigger:'axis'
		    },
		    dataZoom:{
	            type: 'inside',
	            realtime: true,
	            start: 90,
	            end: 100,
	            // xAxisIndex: [0, 1]
		    },
		    legend:{
		        right:0,
		        top:0,
		        orient:'vertical',
		        inactiveColor:'#666',
		        selectedMode:'single',
		        textStyle:{
		            color:'#fff',
		        },
		        data:['系列1','系列2',]
		    },
		    xAxis:{
		    	// type:'time',
		        axisLabel: {        
		            show: true,
		            textStyle: {
		                color: '#fff',
		            }
		        },
		        data:['信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12','信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12']
		    },
		    yAxis:{
		        axisLabel: {        
		            show: true,
		            textStyle: {
		                color: '#fff',
		            }
		        },
		        splitLine:{
		            show:true,
		            lineStyle:{
		                color:'#4960bf',
		                type:'dashed'
		            }
		        },
		    },
		    series:[
		        {
		            type:'line',
		            name:'系列1',
		            stack:'10',
		            showAllSymbol: true,
		            symbol: 'emptyCircle',
		            symbolSize: 10,
		            itemStyle:{
		                normal:{
		                    color:'#ab56dc',
		                }
		            },
		            lineStyle:{
		                normal:{
		                    color:'#ab56dc',
		                }
		            },
		            
		            data:[40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36,40,20,10,75,30,20,78,55,51,31,46,36],
		            /*markLine:{
		            	lineStyle:{
		            		color:'#fff',
		            		type:'solid'
		            	},
		            	data: [{
		                    yAxis: 10,
		                }, {
		                    yAxis: 50,
		                }]
		            },*/
		        },
		        
		        {
		            type:'line',
		            name:'系列2',
		            showAllSymbol: true,
		            symbol: 'emptyCircle',
		            symbolSize: 10,
		            itemStyle:{
		                normal:{
		                    color:'#ab56dc',
		                }
		            },
		            lineStyle:{
		                normal:{
		                    color:'#ab56dc',
		                }
		            },
		            data:[70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56,70,30,20,15,40,50,28,35,71,21,16,56]
		        },
		    ],
		}
})
