var monitoringChartNgModule=angular.module('monitoringChartNgModule',['ngAnimate','ui.bootstrap','app']);
var monitoringChartService = monitoringChartNgModule.factory('monitoringChartService',
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
var monitoringChartNgControl=monitoringChartNgModule.controller('monitoringChartNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,monitoringChartService,httpService){
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
	        data:['系列1','系列2']
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
	//添加通道
	$scope.add = function () {  
        var modalInstance = $modal.open({  
            templateUrl: 'proj/monitoringChart/add.html',  
            controller: 'monitoringChartEditCtrl', 
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name : 'monitoringChartEditNgModule',
        				insertBefore : '#ng_load_plugins_before',
        				files : [
        				         'proj/monitoringChart/add.js',
        				]
        			});
        		}],
        		monitoringChart: function () {  
                    return {};  
                },
                clientList: function () {  
                    return {}; 
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
            $scope.selected = data;
        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
        	$scope.initItem();
        });
         
    };

    $scope.getDate = function (index){
	    var date = new Date(); //当前日期
	    var newDate = new Date();
	    newDate.setDate(date.getDate() + index);//官方文档上虽然说setDate参数是1-31,其实是可以设置负数的
	    var time = newDate.getFullYear()+"/"+(newDate.getMonth()+1)+"/"+newDate.getDate();
	    return time;
	}
	$scope.startTime = $scope.getDate(-7);
	$scope.endTime = $scope.getDate(0);

    
    //分页条件
    $scope.pageSize = 10;
    $scope.currentPage = 1;
    //故障类型
    $scope.type=1;
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
    			/*projectName : text,
    			projectAddress : text,*/
    		};
    	
    	httpService.post({url:'./hiddenDangerEdit/pageHiddenDangerEdit',data:pageParam,showSuccessMsg:false}).then(function(data) {  
    		$scope.projects = data.data.data.pageList;
    		for(i in $scope.projects){
    			if($scope.projects[i].warningCount>0){
    				$scope.projects[i].status = 'dangerLabel';
    			}else if($scope.projects[i].hiddeCount>0){
    				$scope.projects[i].status = 'faultLabel';
    			}else{
    				$scope.projects[i].status = ''
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

    //日期
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

    $scope.changeType = function (num) {
    	$scope.type = num;
    }
})
