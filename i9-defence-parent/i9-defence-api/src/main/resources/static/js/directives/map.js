angular.module('app').directive(
				'gaodeMap',
				[ function() {

					return {

						restrict : 'E',

						replace : true,

						template : '<div id="container"></div>',

						scope : {

							options : '='

						},

						link : function($scope, elem, attr) {

							var map = new AMap.Map("container", {
								resizeEnable : true,
								mapStyle: 'amap://styles/9ef8e635e3bae06c3f40563eee898c64',//样式URL
								zoom : 17

							});
								
							var infoWindow = new AMap.InfoWindow({
							    isCustom:true,
							    autoMove:true,
							    // closeWhenClickMap:true,
							    offset: new AMap.Pixel(0, -50),
							});
							

							$scope.$watch("options", function(newValue,
									oldValue) {

								if ($scope.options && $scope.options[0].lng
										&& $scope.options[0].lat) {

									map.setCenter([ $scope.options[0].lng,
											$scope.options[0].lat ]);
										
									for(var i=0;i<$scope.options.length;i++){
										marker = new AMap.Marker({
											position: [$scope.options[i].lng,$scope.options[i].lat],
									        zIndex: 101,
									        /*icon:new AMap.Icon({            
									            // size: new AMap.Size(40, 50),  //图标大小
									            // image: "http://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
									            image: "./images/timg.jpg",
									            // imageOffset: new AMap.Pixel(0, -60)
									        }),*/
									        map: map
									    });
										marker.content = $scope.options[i].content;
										marker.on('click', markerClick);
									}
									
									function markerClick(e) {
									    infoWindow.setContent(e.target.content);
									    infoWindow.open(map, e.target.getPosition());
									}
								}

							}, true);

						}

					}

				} ]);
