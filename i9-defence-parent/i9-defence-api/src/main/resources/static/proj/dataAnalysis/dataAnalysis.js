var dataAnalysisNgModule=angular.module('dataAnalysisNgModule',['ngAnimate','ui.bootstrap','app']);
var dataAnalysisService = dataAnalysisNgModule.factory('dataAnalysisService',
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
var dataAnalysisNgControl=dataAnalysisNgModule.controller('dataAnalysisNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,dataAnalysisService,httpService){
	/*$scope.option={
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
	            type:'bar',
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
	            type:'bar',
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
	}*/
	$scope.chartsStatus = false;
    $scope.getDate = function (index){
	    var date = new Date(); //当前日期
	    var newDate = new Date();
	    newDate.setDate(date.getDate() + index);//官方文档上虽然说setDate参数是1-31,其实是可以设置负数的
	    var time = newDate.getFullYear()+"/"+(newDate.getMonth()+1)+"/"+newDate.getDate();
	    return time;
	}
	$scope.startTime = $scope.getDate(-7);
	$scope.endTime = $scope.getDate(0);
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
	$scope.pageInit = function (){
    	if ($scope.projectName != null) {
			$scope.projectId =$scope.projectName.id;
		}else{
			$scope.projectId = null;
		}
    	
    	
    	var Ids = [];
    	if ($scope.projectId != null) {
    		Ids.push($scope.projectId);
    	}
    	
		var pageParam = {
				projectId:Ids,
				startTime:$scope.startTime,
				endTime:$scope.endTime,
				/*projectName : text,
				projectAddress : text,*/
			};
		console.log(pageParam)
		httpService.post({url:'./equipment/selectMonthData',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.projectInfo = data.data.data;
			$scope.projectTime = [];
			$scope.projectWarning = [];
			$scope.projectHidden = [];
			if($scope.projectInfo!= null){
				$scope.chartsStatus = true;
				for(i in $scope.projectInfo.months){
					$scope.projectTime.push($scope.projectInfo.months[i]);
				}
				for(i in $scope.projectInfo.hiddenData){
					$scope.projectHidden.push($scope.projectInfo.hiddenData[i]);
				}
				for(i in $scope.projectInfo.warningData){
					$scope.projectWarning.push($scope.projectInfo.warningData[i]);
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
				        textStyle:{
				            color:'#fff',
				        },
				        data:['报警','故障',]
				    },
				    xAxis:{
				        axisLabel: {        
				            show: true,
				            textStyle: {
				                color: '#fff',
				            }
				        },
				        data:$scope.projectTime,
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
				            type:'bar',
				            name:'报警',
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
				            data:$scope.projectWarning,
				        },
				        
				        {
				            type:'bar',
				            name:'故障',
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
				            data:$scope.projectHidden
				        },
				    ],
				}
			}else{
				$scope.chartsStatus = false;
			}
			
		})
	};
	$scope.pageInit();
	// 地域
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
	$scope.searchBtn = function () {
		$scope.pageInit();
	}
})
