/**
 * 事件查看js
 */
var eventModule=angular.module('eventModule',['ngAnimate','ui.bootstrap','app']);
var eventService = eventModule.factory('eventService',
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
var eventControl=eventModule.controller('eventControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,eventService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	
	
    $scope.dateToString = function(d){
    	var date = new Date(d);
    	return date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
    }
	
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
			$scope.searchText = "";
		}
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				projectId : $scope.searchText,
				projectAddress : $scope.searchText,
				eqCategoryName : $scope.eqCategoryName,
				orderByClause: 'warningCount desc'
			};
		httpService.post({url:'./hiddenDangerEdit/pageHiddenDangerEdit2',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.hiddenEdits = data.data.data.pageList;
			$scope.equipmentCategorys = data.data.equipmentCategory;
			$scope.projects = data.data.project;
			for(i in $scope.hiddenEdits){
				if($scope.hiddenEdits[i].warningCount>0){
					$scope.hiddenEdits[i].status = 'danger';
					$scope.hiddenEdits[i].statusText = '报警';
				}else if($scope.hiddenEdits[i].hiddeCount>0){
					$scope.hiddenEdits[i].status = 'warning';
					$scope.hiddenEdits[i].statusText = '隐患';
				}else if ($scope.hiddenEdits[i].status == 0){
    				$scope.hiddenEdits[i].status = 'lineOutLabel';
    				$scope.hiddenEdits[i].statusText = '离线';
    			}
			}
			
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.hiddenEdits.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
    		if($scope.hiddenEdits.length>0){
				$scope.idNum = $scope.hiddenEdits[0].id;
				$scope.passagewayInit();
			}else{
				$scope.projectInfo = {};
				$scope.chartsStatus = false;
			}
		})
	};
	
	    $scope.passagewayInit = function (){
	    	var pageParam = {
	    			equipmentId:$scope.idNum,
	    			startDateString:$scope.dateToString($("#startTime").val()),
	    			endDateString:$scope.dateToString($("#endTime").val()),
	    			/*projectName : text,
	    			projectAddress : text,*/
	    		};
	    	
	    	httpService.post({url:'./equipment/selectEquipInfoAndData',data:pageParam,showSuccessMsg:false}).then(function(data) {  
	    		$scope.equipmentInfo = data.data.data;
	    		$scope.equipment = data.data.equip;
	    		$scope.dataAndManager = data.data.dataAndManager;
	    		$scope.equipmentCheckArr = [];
	            $scope.equipmentItemArr = [];

	            
	            if($scope.equipmentInfo!= null){
	                $scope.chartsStatus = true;
	                for(i in $scope.equipmentInfo.channelData){
	                    /*$scope.equipmentItemObj = {
	                        type:'line',
	                        stack:'10',
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
	                    };*/
	                    if ($scope.equipmentInfo.channelData[i].name!=null && $scope.equipmentInfo.channelData[i].name != ""){
	                        $scope.equipmentCheckArr.push($scope.equipmentInfo.channelData[i].name);
	                        // $scope.equipmentItemObj.name=$scope.equipmentInfo.channelData[i].name;
	                    }else{
	                        $scope.equipmentCheckArr.push('通道'+$scope.equipmentInfo.channelData[i].channelNumber);
	                        // $scope.equipmentItemObj.name='通道'+$scope.equipmentInfo.channelData[i].channelNumber;
	                    }
	                    /*$scope.equipmentItemObj.data=$scope.equipmentInfo.channelData[i].value;
	                    $scope.equipmentItemArr.push($scope.equipmentItemObj);*/
	                }
	                $scope.passageway='0';
	                $scope.chartData = $scope.equipmentInfo.channelData[0].value;
	                $scope.changeLine = function () {
	                    var chengeIndex = parseInt($scope.passageway);
	                    $scope.chartData = $scope.equipmentInfo.channelData[chengeIndex].value;
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
	                            right:60,
	                            bottom:30,
	                            borderColor:'#566c93',
	                        },
	                        tooltip:{
	                            trigger:'axis'
	                        },
	                        /*dataZoom:{
	                            type: 'inside',
	                            realtime: true,
	                            start: 90,
	                            end: 100,
	                            // xAxisIndex: [0, 1]
	                        },*/
	                        /*legend:{
	                            type:'scroll',
	                            right:0,
	                            top:0,
	                            bottom:10,
	                            width:30,
	                            pageButtonItemGap:5,
	                            pageButtonGap:5,
	                            pageButtonPosition:'end',
	                            pageFormatter:{
	                                current:1,
	                                total:5
	                            },
	                            orient:'vertical',
	                            inactiveColor:'#666',
	                            selectedMode:'single',
	                            textStyle:{
	                                color:'#fff',
	                            },
	                            data:$scope.equipmentCheckArr,
	                            // data:['通道0','通道1','通道2','通道3','通道4','通道5','通道6','通道7'],
	                        },*/
	                        xAxis:{
	                            // type:'time',
	                            axisLabel: {        
	                                show: true,
	                                textStyle: {
	                                    color: '#fff',
	                                }
	                            },
	                            data:$scope.equipmentInfo.date,
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
	                        // series:$scope.equipmentItemArr,
	                        series:[{

	                            type:'line',
	                            stack:'10',
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
	                            data:$scope.chartData,
	                        }]
	                    }
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
	                        right:60,
	                        bottom:30,
	                        borderColor:'#566c93',
	                    },
	                    tooltip:{
	                        trigger:'axis'
	                    },
	                    /*dataZoom:{
	                        type: 'inside',
	                        realtime: true,
	                        start: 90,
	                        end: 100,
	                        // xAxisIndex: [0, 1]
	                    },*/
	                    /*legend:{
	                        type:'scroll',
	                        right:0,
	                        top:0,
	                        bottom:10,
	                        width:30,
	                        pageButtonItemGap:5,
	                        pageButtonGap:5,
	                        pageButtonPosition:'end',
	                        pageFormatter:{
	                            current:1,
	                            total:5
	                        },
	                        orient:'vertical',
	                        inactiveColor:'#666',
	                        selectedMode:'single',
	                        textStyle:{
	                            color:'#fff',
	                        },
	                        data:$scope.equipmentCheckArr,
	                        // data:['通道0','通道1','通道2','通道3','通道4','通道5','通道6','通道7'],
	                    },*/
	                    xAxis:{
	                        // type:'time',
	                        axisLabel: {        
	                            show: true,
	                            textStyle: {
	                                color: '#fff',
	                            }
	                        },
	                        data:$scope.equipmentInfo.date,
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
	                    // series:$scope.equipmentItemArr,
	                    series:[{

	                        type:'line',
	                        stack:'10',
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
	                        data:$scope.chartData,
	                    }]
	                }
	            }else{
	                $scope.chartsStatus = false;
	            }
	    		
	    	})
	    };
	
	
	
	
	$scope.initTable();
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
	$scope.search = function(){
		$scope.initTable();
	}
	
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
    //查看记录
    $scope.faultRecord = function (idNum2,statuText) {
    	if(statuText=='报警'){
    		$state.go('app.faultRecordInfo',{id:idNum2,typeId:2});
    	}else if(statuText=='隐患'){
    		$state.go('app.faultRecordInfo',{id:idNum2,typeId:3});
    	}
    }
//	$scope.add = function () {  
//			$scope.equipmentCategory = data.data.equipmentCategory;
//			//$modalInstance.dismiss('cancel')
//			  var modalInstance = $modal.open({  
//		            templateUrl: 'proj/hiddenEdit/add.html',  
//		            controller: 'hiddenEditEditNgCtrl', 
//		            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
//		            resolve: {  
//		            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
//		        			return $ocLazyLoad.load({
//		        				name : 'hiddenEditEditNgModule',
//		        				insertBefore : '#ng_load_plugins_before',
//		        				files : [
//		        				         'proj/hiddenEdit/add.js',
//		        				]
//		        			});
//		        		}],
//		        		hiddenEdit: function () {  
//		                    return {};  
//		                },
//		            }  
//		        }); 
//		        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
//		            $scope.selected = data;
//		        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
//		        	$scope.initTable();
//		        });
//    };  
    //编辑
    $scope.edit = function (idNum) { 
    	$scope.idNum = idNum;
    	$scope.passagewayInit();    	 
    };  
    
})
