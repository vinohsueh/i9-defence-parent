var indexPageNgModule=angular.module('indexPageNgModule',['ngAnimate','ui.bootstrap','app']);
var indexPageService = indexPageNgModule.factory('indexPageService',
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
var indexPageNgControl=indexPageNgModule.controller('indexPageNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,indexPageService,httpService){
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
	/*$scope.c3 = function () {
	   $scope.error.area = false;
	   $scope.queryProjects();
	};*/
	$scope.getDate = function (index){
	    var date = new Date(); 
	    var newDate = new Date();
	    newDate.setDate(date.getDate() + index);
	    var time = newDate.getFullYear()+"/"+(newDate.getMonth()+1)+"/"+newDate.getDate();
	    return time;
	}

	$scope.startTime = $scope.getDate(-7);
	$scope.endTime = $scope.getDate(0);

	$scope.pageInit = function(){
		$scope.markArr = [];
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
		var pageParam = {
			projectProvince:$scope.selected.name,
			projectCity:$scope.selected2.name,
			projectName:$scope.searchText,
			projectAddress:$scope.searchText,
		};
		httpService.post({url:'./project/selectProject',data:pageParam,showSuccessMsg:false}).then(function(data) { 
			$scope.projectList = data.data.data;
			var markItem = {};
			
			for(i in $scope.projectList){
				markItem = {}
				markItem.positionX = $scope.projectList[i].projectLongitude;
				markItem.positionY = $scope.projectList[i].projectLatitude;
				markItem.saferList = $scope.projectList[i].saferList;
				markItem.area = $scope.projectList[i].area;
				markItem.address = $scope.projectList[i].address;
				markItem.projectName = $scope.projectList[i].projectName;
				markItem.clientListStr = $scope.projectList[i].clientListStr;
				$scope.markArr.push(markItem);
			}
			var mainHeight = $(window).height()-50;
			$('#selfMain').height(mainHeight);
			var windowHeight = document.body.clientHeight,mapZoom=4;
			if(windowHeight<720){
			    mapZoom=3;
			}else if(windowHeight>1300 && windowHeight<2700){
			    mapZoom = 5;
			}else if(windowHeight>=2700){
			    mapZoom = 6;
			}else{
			    mapZoom = 4;
			}
			
			/*var myData = {'mark':[
			        {'positionX':'116.368904','positionY':'39.923423','text':'提示内容1'},
			        {'positionX':'116.382122','positionY':'39.921176','text':'提示内容2'},
			        {'positionX':'116.387271','positionY':'39.922501','text':'提示内容3'},
			        {'positionX':'116.398258','positionY':'39.914600','text':'提示内容4'}
			    ]}*/
			var myData = {'mark':$scope.markArr};
			    
			var oHtml = "<div class='prompt'><p>提示内容</p></div>"
			var map = new AMap.Map("selfMap", {
			    zoom:mapZoom,
			    zooms:[mapZoom,mapZoom],
			    // center:[117.190182,39.125596],
			    mapStyle: 'amap://styles/9ef8e635e3bae06c3f40563eee898c64',//样式URL
			    resizeEnable:true,
			});
			var infoWindow = new AMap.InfoWindow({
			    isCustom:true,
			    autoMove:true,
			    // closeWhenClickMap:true,
			    offset: new AMap.Pixel(0, -50),
			});
			var oHtml="";
			for(var i=0;i<myData.mark.length;i++){
			    oHtml = "<div class='info'><div class='infoTitle'>"+myData.mark[i].projectName+"<span class='closeInfo' onclick='angular.element(this).scope().closeInfoWindow()'>x</span></div><table class='infoBody' cellspacing='0'><tr><th><i class='mIcon icon-address'></i>地址</th><th><i class='mIcon icon-floorArea'></i>建筑面积</th></tr><tr><td>"+myData.mark[i].address+"</td><td>"+myData.mark[i].area+"</td></tr><tr><th><i class='mIcon icon-clients'></i>项目负责人</th><th><i class='mIcon icon-charges'></i>安全负责人</th></tr><tr><td>"+myData.mark[i].clientListStr+"</td><td>"+myData.mark[i].saferList+"</td></tr></table><div class='mIcon icon-arrowDown'></div></div>";
			    marker = new AMap.Marker({
			        position: [myData.mark[i].positionX,myData.mark[i].positionY],
			        zIndex: 101,
			        /*icon:new AMap.Icon({            
			            // size: new AMap.Size(40, 50),  //图标大小
			            // image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
			            image: "./images/timg.jpg",
			            // imageOffset: new AMap.Pixel(0, -60)
			        }),*/
			        map: map
			    });
			    marker.content = oHtml;
			    marker.on('click', markerClick);
			}
			function markerClick(e) {
			    infoWindow.setContent(e.target.content);
			    infoWindow.open(map, e.target.getPosition());
			}
			//关闭信息窗体
			$scope.closeInfoWindow =  function () {
			   map.clearInfoWindow();
			}
		})
	}
	$scope.pageInit();

	//图标初始化
	// $scope.Ids = [];
	$scope.chartsStatus = false;
	$scope.chartInit = function (){
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
		var pageParam = {
			projectProvince:$scope.selected.name,
			projectCity:$scope.selected2.name,
			projectId:[],
			startTime:$scope.dateToString($scope.startTime),
			endTime:$scope.dateToString($scope.endTime),

		};
		console.log(JSON.stringify(pageParam));
		httpService.post({url:'./equipment/selectMonthData',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.projectInfo = data.data.data;
			console.log($scope.projectInfo);
			$scope.projectTime = [];
			$scope.projectWarning = [];
			$scope.projectHidden = [];
			if($scope.projectInfo!= null){
				if ($scope.projectInfo.hiddenData.length == 0 && $scope.projectInfo.warningData == 0) {
					$scope.chartsStatus = false;
				}else{
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
				}
				
			}else{
				$scope.chartsStatus = false;
			}
			
		})
	};
	$scope.chartInit();
	//时间切换
	$scope.changeTimeStatu = 1;
	$scope.changeTime = function () {
		$scope.changeTimeStatu = $scope.changeTimeStatu+1;
		if($scope.changeTimeStatu>2){
			$scope.chartInit();
		}
	}

	$scope.searchBtn = function () {
		$scope.pageInit();
		$scope.chartInit();
	}

	$(function () {
	    // 右侧样式
	    var mainWidth = parseInt($('#selfMain').width()*0.03);
	    var rightWidth = parseInt($('#selfMain').width()*0.2);
	    // $('#projectList,#pieChart').css({'right':mainWidth+'px','width':rightWidth+'px'});
	    $('#projectList').css({'right':mainWidth+'px','width':rightWidth+'px'});
	    // var projectListHeight = parseInt($('#selfMain').height()*0.7-136);
	    var projectListHeight = parseInt($(window).height()-150);
	    $('#projectList .projectNameList').css('height',projectListHeight+'px');
	    
	    // 图表样式
	    var chartBodyHeight = parseInt($('#selfMain').height()*0.3-40);
	    // $('#chartData .chartBody,#pieChart .chartBody').css('height',chartBodyHeight+'px');
	    $('#chartData .chartBody').css('height',chartBodyHeight+'px');



	    // 加载更多
	    /*$('#loadMore').click(function () {
	        var listHeight = parseInt($('#selfMain').height());
	        $('.projectNameList').css('height',listHeight+'px');
	        $('#loadMore').hide();
	    });*/

	    //图标日期选择
	    /*$('#chartData .tools span').click(function () {
	        $(this).addClass('active').siblings().removeClass('active');
	    });*/

	})

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
	
	
	//跳转项目页面
	$scope.goTo = function(id){
		$state.go('app.monitoringChart',{id:id});
	}
	/*$scope.pieOption = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            inactiveColor:'#666',
            textStyle:{
                color:'#fff',
            },
            data:['正常','报警','隐患']
        },
        series: [
            {
                name:'设备分布',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {
                        value:335, 
                        name:'正常',
                        itemStyle:{
                            normal:{
                                color:'#75c19e',
                            }
                        }
                    },
                    {
                        value:310, 
                        name:'报警',
                        itemStyle:{
                            normal:{
                                color:'#8048b1',
                            }
                        }
                    },
                    {
                        value:234, 
                        name:'隐患',
                        itemStyle:{
                            normal:{
                                color:'#e2d89c',
                            }
                        }
                    },
                ]
            }
        ]
    }*/
	
})
