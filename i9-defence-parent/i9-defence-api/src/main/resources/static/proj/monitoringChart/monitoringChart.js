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
	/*//时间插件
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
      $('.dropdown-menu').css('bottom','34px');
    };

    $scope.dateOptions = {
      formatYear: 'yy',
      startingDay: 1,
      class: 'datepicker'
    };

    $scope.initDate = new Date('2016-15-20');
    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[1];*/
	
    $scope.dateToString = function(d){
    	var date = new Date(d);
    	return date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
    }
    
    
	//首页跳转过来的项目id
    $scope.projectId=$stateParams.id;

	$scope.getDate = function (index){
	    var date = new Date(); //当前日期
	    var newDate = new Date();
	    newDate.setDate(date.getDate() + index);//官方文档上虽然说setDate参数是1-31,其实是可以设置负数的
	    var time = newDate.getFullYear()+"/"+(newDate.getMonth()+1)+"/"+newDate.getDate();
	    return time;
	}
	$scope.startTime = $scope.getDate(0);
	$("#hour").val(new Date().getHours());
    
    //分页条件
    $scope.pageSize = 10;
    $scope.currentPage = 1;
    //类型切换
    $scope.type = 0;
    //图表显示隐藏状态
    $scope.chartsStatus = true;
    $scope.idNum = 0;
    $scope.serchEqCategoryId = null;

    $scope.changeTimeStatu = 1;
    
   /* $scope.serchEqCategory = function(){      
        var pageParam = {};
        httpService.post({url:'./eqCategory/serchEqCategory',data:pageParam,showSuccessMsg:false}).then(function(data) { 
            $scope.serchEqCategory  = data.data.data;
            $scope.serchEqNums = data.data.eqNums;
        })
    }
    $scope.serchEqCategory();*/

    //项目下拉选择列表
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
    //初始化
    $scope.pageInit = function (){
    	if ($scope.projectId != null) {
    		$scope.searchText = $scope.projectId;
    	}else{
            $scope.searchText = '';
        }
    	var pageParam = {
    			/*pageSize:$scope.pageSize,
    			currentPage:$scope.currentPage,*/
    			projectId : $scope.searchText,
				projectAddress : $scope.searchText,
                systemId:$scope.serchEqCategoryId,
    			/*projectName : text,
    			projectAddress : text,*/
    		};
            console.log(JSON.stringify(pageParam));
		httpService.post({url:'./equipment/selectTotalEquipmentDto',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.totalCount = data.data.data;
		});
		
        httpService.post({url:'./eqCategory/serchEqCategory',data:pageParam,showSuccessMsg:false}).then(function(data) { 
            $scope.serchEqCategory  = data.data.data;
            $scope.serchEqNums = data.data.eqNums;
        })
    	
    	httpService.post({url:'./hiddenDangerEdit/selectAllHiddenDangerEdit',data:pageParam,showSuccessMsg:false}).then(function(data) {  
    		$scope.projects = data.data.data;
    		for(i in $scope.projects){
    			if($scope.projects[i].status == 0 || $scope.projects[i].status == 2){
    				$scope.projects[i].status = 'lineOutLabel';
    				
    			}else{
    				if($scope.projects[i].dataStatus==0){
        				$scope.projects[i].status = '';
        			}else if ($scope.projects[i].dataStatus == 1){
        				$scope.projects[i].status = 'dangerLabel';
        			}else{
        				$scope.projects[i].status = 'faultLabel';
        			}
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

    		if($scope.projects.length>0){
				$scope.idNum = $scope.projects[0].id;
				$scope.passagewayInit();
			}else{
				// $scope.projectInfo = {};
                $scope.equipment = {};
				$scope.chartsStatus = false;
			}
    	})
    };
    $scope.pageInit();
    var myInterval = setInterval(function () {
        $scope.pageInit();
    },10000);
    $scope.$on("$destroy", function() {
        clearInterval(myInterval);
        myInterval = undefined;
    });
    $scope.passagewayInit = function (){
    	var text = $scope.searchText;
    	var pageParam = {
    			equipmentId:$scope.idNum,
    			startDateString:$scope.dateToString($("#startTime").val())+" "+$("#hour").val(),
    			endDateString:$scope.dateToString($("#startTime").val())+" "+(parseInt($("#hour").val())+1),
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
    //日期
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
    //条件搜索
    $scope.subSearch = function () {
    	$scope.pageInit();
    }
    //选择设备
    $scope.checkItem = function (idNum) {
    	$scope.idNum = idNum;
    	$scope.passagewayInit();
    }
    //时间切换
    $scope.changeTime = function () {
    	$scope.changeTimeStatu = $scope.changeTimeStatu+1;
    	if($scope.changeTimeStatu>2){
    		setTimeout(function () {
    			$scope.passagewayInit();
        	  }, 100);
    	}
    }
    function resizeWin() {
        var domHeight = $(window).height();
        var leftHeight = domHeight-405;
        var rightHeight = domHeight-445;
        $('#faultType').height(leftHeight);
        $('#equipmentData').height(rightHeight);
    }
    resizeWin()
    $(window).resize(function () {
        resizeWin();
    })
    
    //设备类型切换
    $scope.changeType = function (idNum) {
        if(idNum === 0){
            $scope.type=idNum;
            $scope.serchEqCategoryId = null;
            $scope.pageInit();
        }else{
            $scope.type=idNum;
            $scope.serchEqCategoryId = idNum;
            $scope.pageInit();
        }
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
                    return $scope.idNum;  
                },
                clientList: function () {  
                    return {}; 
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
            $scope.selected = data;
        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
            $scope.passagewayInit();
        });
         
    };
})
