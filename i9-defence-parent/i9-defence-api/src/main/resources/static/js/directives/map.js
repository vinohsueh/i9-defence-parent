angular.module('app').directive(
				'gaodeMap',
				[ function() {

					return {

						restrict : 'E',

						replace : true,

						template : '<div id="selfMap"></div>',

						scope : {

							options : '='

						},

						link : function($scope, elem, attr, $state) {
							console.log(JSON.stringify($scope.options));
							var windowHeight = document.body.clientHeight,mapZoom=4;
							/*if(windowHeight<720){
							    mapZoom=3;
							}else if(windowHeight>1300 && windowHeight<2700){
							    mapZoom = 5;
							}else if(windowHeight>=2700){
							    mapZoom = 6;
							}else{
							    mapZoom = 4;
							}*/

							/*var map = new AMap.Map("selfMap", {
								resizeEnable : true,
								mapStyle: 'amap://styles/9ef8e635e3bae06c3f40563eee898c64',//样式URL
								zoom:4,
								// zooms:[mapZoom,mapZoom],
							});*/
								
							var infoWindow = new AMap.InfoWindow({
							    isCustom:true,
							    autoMove:true,
							    // closeWhenClickMap:true,
							    offset: new AMap.Pixel(0, -50),
							});
							

							$scope.$watch("options", function(newValue,
									oldValue) {
								if($scope.options.project){
									if ($scope.options.project.length>=1 && $scope.options.project[0].lng && $scope.options.project[0].lat) {
										var map = new AMap.Map("selfMap", {
											resizeEnable : true,
											mapStyle: 'amap://styles/9ef8e635e3bae06c3f40563eee898c64',//样式URL
											zoom:4,
											// zooms:[mapZoom,mapZoom],
										});
										map.setCenter([ $scope.options.project[0].lng,
												$scope.options.project[0].lat ]);
										if($scope.options.choiceItemId){
											for(var i=0;i<$scope.options.project.length;i++){
												if($scope.options.project[i].warningCount>0){
													marker = new AMap.Marker({
														position: [$scope.options.project[i].lng,$scope.options.project[i].lat],
												        zIndex: 101,
												        icon:new AMap.Icon({            
												            // size: new AMap.Size(40, 50),  //图标大小
												            // image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
												            image: "./images/mark.png",
												            // imageOffset: new AMap.Pixel(0, -60)
												        }),
												        map: map
												    });
													marker.content = $scope.options.project[i].content;
													marker.on('click', markerClick);
													if($scope.options.choiceItemId == $scope.options.project[i].id){
														infoWindow.setContent($scope.options.project[i].content);
													    infoWindow.open(map, [$scope.options.project[i].lng,$scope.options.project[i].lat]);
													}else{
														infoWindow.setContent($scope.options.project[i].content);
													    infoWindow.open(map, [117.238581,39.105172]);
														// infoWindow.open(map, [117.238581,39.105172]);
													}
												}else{
													marker = new AMap.Marker({
														position: [$scope.options.project[i].lng,$scope.options.project[i].lat],
												        zIndex: 101,
												        /*icon:new AMap.Icon({            
												            // size: new AMap.Size(40, 50),  //图标大小
												            // image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
												            image: "./images/mark.png",
												            // imageOffset: new AMap.Pixel(0, -60)
												        }),*/
												        map: map
												    });
													marker.content = $scope.options.project[i].content;
													marker.on('click', markerClick);
													if($scope.options.project[i].projectName == '万达中心'){
														infoWindow.setContent($scope.options.project[i].content);
													    infoWindow.open(map, [117.238581,39.105172]);
														// infoWindow.open(map, [117.238581,39.105172]);
													}
												}
												
												
											}
										}else{
											for(var i=0;i<$scope.options.project.length;i++){
												if($scope.options.project[i].warningCount>0){
													marker = new AMap.Marker({
														position: [$scope.options.project[i].lng,$scope.options.project[i].lat],
												        zIndex: 101,
												        icon:new AMap.Icon({            
												            // size: new AMap.Size(40, 50),  //图标大小
												            // image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
												            image: "./images/mark.png",
												            // imageOffset: new AMap.Pixel(0, -60)
												        }),
												        map: map
												    });
													marker.content = $scope.options.project[i].content;
													marker.on('click', markerClick);
													if($scope.options.project[i].projectName == '万达中心'){
														infoWindow.setContent($scope.options.project[i].content);
													    infoWindow.open(map, [117.238581,39.105172]);
														// infoWindow.open(map, [117.238581,39.105172]);
													}
												}else{
													marker = new AMap.Marker({
														position: [$scope.options.project[i].lng,$scope.options.project[i].lat],
												        zIndex: 101,
												        /*icon:new AMap.Icon({            
												            // size: new AMap.Size(40, 50),  //图标大小
												            // image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
												            image: "./images/mark.png",
												            // imageOffset: new AMap.Pixel(0, -60)
												        }),*/
												        map: map
												    });
													marker.content = $scope.options.project[i].content;
													marker.on('click', markerClick);
													if($scope.options.project[i].projectName == '万达中心'){
														infoWindow.setContent($scope.options.project[i].content);
													    infoWindow.open(map, [117.238581,39.105172]);
														// infoWindow.open(map, [117.238581,39.105172]);
													}
												}
												
												
											}
										}
										
										
										function markerClick(e) {
										    infoWindow.setContent(e.target.content);
										    infoWindow.open(map, e.target.getPosition());
										};
										//关闭信息窗体
										$scope.closeInfoWindow =  function () {
										   	map.clearInfoWindow();
										}
										//跳转项目页面
										$scope.goTo = function(id){
											var thisHref = window.location.href;
											var thisHrefApp = thisHref.slice(0,thisHref.lastIndexOf('app/'));
											window.location.href=thisHrefApp+"app/monitoringChart?id="+id;
											// $state.go('app.monitoringChart',{id:id});
										}
									}else{
										var map = new AMap.Map("selfMap", {
											resizeEnable : true,
											mapStyle: 'amap://styles/9ef8e635e3bae06c3f40563eee898c64',//样式URL
											zoom:4,
											// zooms:[mapZoom,mapZoom],
										});
										map.setCenter([116.39,39.9]);
									}
								}
								

							}, true);

						}

					}

				} ]);
