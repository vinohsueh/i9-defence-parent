var errHandleModule=angular.module('errHandleModule',['ngAnimate','ui.bootstrap','app']);
var warningInfoService = errHandleModule.factory('warningInfoService',
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
var errHandleControl=errHandleModule.controller('errHandleControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,warningInfoService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	$scope.hour =0;
//	初始化底部信息
	$scope.ifshow = false;
//	初始化id
	$scope.deviceId=0;
	//初始化
	$scope.initTable = function (){
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				orderByClause: "handleDate desc",
				handleState: 0 ,
				eqDeviceId:$stateParams.id
			};
		httpService.post({url:'./errHandle/pageErrHandle',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.warningLists = data.data.data.pageList;
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.warningLists.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
			if($scope.warningLists.length > 0){
				$scope.idNum =$scope.warningLists[0].equipmentId;
				$scope.passagewayInit();
			}
//			for(i in $scope.warningLists){
//				console.log("----"+JSON.stringify($scope.warningLists[i].handleState));
//				if($scope.warningLists[i].handleState = 0){
//					$scope.warningLists[i].statusText = '未处理';
//				}else if($scope.warningLists[i].handleState = 1){
//					$scope.warningLists[i].statusText = '已处理';
//				}
//			}
		})
	};
	
	$scope.dateToString = function(d){
    	var date = new Date(d);
    	return date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
    }
	
    $scope.passagewayInit = function (){
    	if($("#hour").val()==24){
	    	$scope.startDateString=$scope.dateToString($("#startTime").val())+" "+$scope.hour;
			$scope.endDateString=$scope.dateToString($("#startTime").val())+" "+(parseInt($scope.hour)+24);
	    }else{
	    	$scope.startDateString=$scope.dateToString($("#startTime").val())+" "+$("#hour").val();
	    	$scope.endDateString=$scope.dateToString($("#startTime").val())+" "+(parseInt($("#hour").val())+1);
	    }
    	var pageParam = {
    			equipmentId:$scope.idNum,
    			startDateString:$scope.startDateString,
    			endDateString:$scope.endDateString,
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
    
    $scope.dealEq = function() { 
       	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	var handleCon = $('#handleCon').val();
    	var pageParam = {
    			handleCon :handleCon,
    			eqIds  :  $scope.delArray,
    	}
    	httpService.post({url:'./errHandle/errHandleEdit',data:pageParam,showSuccessMsg:true}).then(function(data) {
    		$('#handleCon').val("");
    		$scope.initTable();
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
	//项目选择
	$scope.choiceItem = function(idNum){
		$scope.ifshow = true;
		$scope.id = idNum;
	}
	
	   //详情提交
    $scope.confirmBtn = function () {
    	var handleCon = $('#handleCon').val();
    	var param = {
    		id:$scope.id,
    		handleCon:handleCon,
    		handleState:1
    	}
    	if(handleCon.replace(/(^\s*)|(\s*$)/g,"").length!=0){
	    	httpService.post({url:'./errHandle/errHandleEdit',data:param,showSuccessMsg:false}).then(function(data) {
	    		$scope.ifshow = false;
	    		$('#handleCon').val('');
	    		$scope.initTable();
	    	});
    	}else{
    		alert('请输入内容！');
    	}
    	
    }
    
    //编辑
    $scope.edit = function (idNum) { 
    	$scope.idNum = idNum;
    	$scope.passagewayInit();    	 
    };  
    // 窗口适应
    function resizeWin() {
        var domHeight = $(window).height();
        var bodyHeight = domHeight-410;
        $('#myTableBody').height(bodyHeight);
    }
    resizeWin()
    $(window).resize(function () {
        resizeWin();
    })
    
    //查看记录
    $scope.faultRecord = function (idNum2,status) {
    	if(status==2){
    		$state.go('app.faultRecordInfo',{id:idNum2,typeId:2});
    	}else if(status==3){
    		$state.go('app.faultRecordInfo',{id:idNum2,typeId:3});
    	}
    }
    
})
