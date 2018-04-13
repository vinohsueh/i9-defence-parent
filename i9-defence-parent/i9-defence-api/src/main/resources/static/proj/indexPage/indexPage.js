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
	$scope.pageInit = function(){
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
		};
		
		httpService.post({url:'./project/selectProject',data:pageParam,showSuccessMsg:false}).then(function(data) { 
			// $scope.projectList = data.data.data;
			console.log(JSON.stringify(data));
		})
	}
	$scope.pageInit();

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
	
	var myData = {'mark':[
	        {'positionX':'116.368904','positionY':'39.923423','text':'提示内容1'},
	        {'positionX':'116.382122','positionY':'39.921176','text':'提示内容2'},
	        {'positionX':'116.387271','positionY':'39.922501','text':'提示内容3'},
	        {'positionX':'116.398258','positionY':'39.914600','text':'提示内容4'}
	    ]}
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
	    oHtml = "<div class='info'><div class='infoTitle'>"+myData.mark[i].text+"<span class='closeInfo' onclick='angular.element(this).scope().closeInfoWindow()'>x</span></div><table class='infoBody' cellspacing='0'><tr><th><i class='mIcon icon-address'></i>地址</th><th><i class='mIcon icon-floorArea'></i>建筑面积</th><th><i class='mIcon icon-device'></i>消防设备</th></tr><tr><td>华苑产业园华天道2号</td><td>2000平米</td><td><p>A设备:200台</p><p>B设备:200台</p><p>C设备:200台</p></td></tr><tr><th><i class='mIcon icon-clients'></i>所属客户</th><th><i class='mIcon icon-charges'></i>安全负责人</th><th><i class='mIcon icon-phone'></i>联系电话</th></tr><tr><td>国创中心</td><td>李某</td><td><p>022-28978989</p><p>022-22222222</p></td></tr></table><div class='mIcon icon-arrowDown'></div></div>";
	    marker = new AMap.Marker({
	        position: [myData.mark[i].positionX,myData.mark[i].positionY],
	        zIndex: 101,
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
	$(function () {
	    // 右侧样式
	    var mainWidth = parseInt($('#selfMain').width()*0.03);
	    var rightWidth = parseInt($('#selfMain').width()*0.2);
	    // $('#projectList,#pieChart').css({'right':mainWidth+'px','width':rightWidth+'px'});
	    $('#projectList').css({'right':mainWidth+'px','width':rightWidth+'px'});
	    // var projectListHeight = parseInt($('#selfMain').height()*0.7-136);
	    var projectListHeight = parseInt($('#selfMain').height()-100);
	    $('#projectList .projectNameList').css('height',projectListHeight+'px');
	    
	    // 图表样式
	    /*var chartBodyHeight = parseInt($('#selfMain').height()*0.3-40);
	    $('#chartData .chartBody,#pieChart .chartBody').css('height',chartBodyHeight+'px');*/



	    // 加载更多
	    $('#loadMore').click(function () {
	        var listHeight = parseInt($('#selfMain').height());
	        $('.projectNameList').css('height',listHeight+'px');
	        $('#loadMore').hide();
	    });

	    //图标日期选择
	    $('#chartData .tools span').click(function () {
	        $(this).addClass('active').siblings().removeClass('active');
	    });
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
	$scope.pieOption = {
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
