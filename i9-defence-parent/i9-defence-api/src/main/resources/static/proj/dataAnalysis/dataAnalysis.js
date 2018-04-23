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
var dataAnalysisNgControl=dataAnalysisNgModule.controller('dataAnalysisNgControl',function($rootScope, $scope,$stateParams,$interval,  $log, $http, $window, $state,$modal, toaster,dataAnalysisService,httpService){
	//时间插件
    // Disable weekend selection
    $scope.disabled = function(date, mode) {
      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function() {
      $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope.opened = true;
    };
    $scope.dateOptions = {
      formatYear: 'yy',
      startingDay: 1,
      class: 'datepicker'
    };

    $scope.initDate = new Date('2016-15-20');
    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[1];
	
    $scope.dateToString = function(d){
    	var date = new Date(d);
    	return date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
    }

	$scope.chartsStatus = false;
    $scope.getDate = function (index){
	    var date = new Date(); //当前日期
	    var newDate = new Date();
	    newDate.setDate(date.getDate() + index);//官方文档上虽然说setDate参数是1-31,其实是可以设置负数的
	    var time = newDate.getFullYear()+"/"+(newDate.getMonth()+1)+"/"+newDate.getDate();
	    return time;
	}
	$scope.startTime = $scope.getDate(-180);
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
				startTime:$scope.dateToString($("#startTime").val()),
				endTime:$scope.dateToString($("#endTime").val()),
				/*projectName : text,
				projectAddress : text,*/
			};
		console.log(JSON.stringify(pageParam))
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
				                    color:'#f05050',
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
				                    color:'#fad733',
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
	setTimeout(function () {
		$scope.pageInit();
	  }, 100);
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
