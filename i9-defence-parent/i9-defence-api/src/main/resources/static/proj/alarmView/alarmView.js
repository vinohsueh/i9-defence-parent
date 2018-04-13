var alarmViewNgModule=angular.module('alarmViewNgModule',['ngAnimate','ui.bootstrap','app']);
var alarmViewService = alarmViewNgModule.factory('alarmViewService',
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
var alarmViewNgControl=alarmViewNgModule.controller('alarmViewNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,alarmViewService,httpService){
	
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	$scope.queryProjects = function(){
		if($scope.selected == null || $scope.selected == ''){
			$scope.selected ={
				name:null
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
			projectProvince:$scope.selected.name,
			projectCity:$scope.selected2.name,
			projectCounty:$scope.selected3.value,
		};
		
		httpService.post({url:'./project/selectProject',data:pageParam,showSuccessMsg:false}).then(function(data) { 
			$scope.projectss  = data.data.data;
		})
	}
	$scope.queryProjects();
	//初始化
	$scope.searchText = '';
	$scope.initTable = function (){
		if ($scope.projectName != null) {
			$scope.searchText =$scope.projectName.id;
		}else{
			$scope.searchText = null;
		}
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				projectId : $scope.searchText
			};
		
		httpService.post({url:'./equipment/selectErrorEquipment',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.equipments = data.data.data.pageList;
			console.log($scope.equipments)
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.equipments.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
		})
	};
	$scope.initTable();
	
	
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
	    legend:{
	        right:0,
	        top:0,
	        orient:'vertical',
	        inactiveColor:'#666',
	        textStyle:{
	            color:'#fff',
	        },
	        data:['系列1','系列2',]
	    },
	    xAxis:{
	        axisLabel: {        
	            show: true,
	            textStyle: {
	                color: '#fff',
	            }
	        },
	        data:['信息1','信息2','信息3','信息4','信息5','信息6','信息7','信息8','信息9','信息10','信息11','信息12',]
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
	            data:[40,20,10,75,30,20,78,55,51,31,46,36]
	        },
	        
	        {
	            type:'line',
	            name:'系列2',
	            showAllSymbol: true,
	            symbol: 'emptyCircle',
	            symbolSize: 10,
	            itemStyle:{
	                normal:{
	                    color:'#e2d89c',
	                }
	            },
	            lineStyle:{
	                normal:{
	                    color:'#e2d89c',
	                }
	            },
	            data:[70,30,20,15,40,50,28,35,71,21,16,56]
	        },
	    ],
	}
	//日期
	var myDate = new Date(2000,00,00);
	var dYear = myDate.getFullYear();
	var dMonth = myDate.getMonth()+1;
	var dDay = myDate.getDate();
	$scope.startTime = dYear+'/'+dMonth+'/'+dDay;


	$scope.change = function () {
		var myStartDate = this.startTime;
		var dYear = myStartDate.getFullYear();
		var dMonth = myStartDate.getMonth()+1;
		var dDay = myStartDate.getDate();
		$scope.startCheckTime = dYear+'/'+dMonth+'/'+dDay;
		console.log(this.startCheckTime);
	}
	
	// $scope.endTime = this.startCheckTime;

	//地域
	$scope.error = {};
	$scope.division = division;
	$scope.c = function () {
	   $scope.error.province = false;
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected2 = "";
	   $scope.selected3 = "";
	   $scope.queryProjects();
	};
	$scope.c2 = function () {       
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected3 = "";
	   $scope.queryProjects();
	};
	$scope.c3 = function () {
	   $scope.error.area = false;
	   $scope.queryProjects();
	};
	//修改分页大小
	$scope.changePageSize = function(){
		$scope.currentPage = 1;
		$scope.initTable();
	}
	//上一页
	$scope.lastPage = function(){
		if ($scope.hasPrevious){
			$scope.currentPage -=1;
			$scope.initTable();
		}
	}
	//下一页
	$scope.nextPage = function (){
		if ($scope.hasNext){
			$scope.currentPage +=1;
			$scope.initTable();
		}
	}
	//跳转
	$scope.pageTo = function(page){
		$scope.currentPage = page;
		$scope.initTable();
	}
	
	
	//编辑
    $scope.edit = function (systemId) { 
    	console.log(systemId)
    	httpService.post({url:'./equipment/selectEquipmentError',data:systemId,showSuccessMsg:false}).then(function(data) {  
    		$scope.hiddenEdit = data.data.data;
    		//$scope.equipmentCategory = data.data.equipmentCategory;
			var modalInstance = $modal.open({  
	            templateUrl: 'proj/alarmView/add.html',  
	            controller: 'warningEditNgCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'warningEditNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/warningEdit/add.js',
	        				]
	        			});
	        		}],
	        		hiddenEdit: function () {  
	                    return $scope.hiddenEdit;  
	                },
	            }  
	        });
			modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(reason){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
    	})
    };  
})
