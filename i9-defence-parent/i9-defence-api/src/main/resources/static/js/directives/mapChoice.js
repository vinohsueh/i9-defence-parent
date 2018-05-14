angular.module('app').directive(
				'mapChoice',
				[ function() {

					return {

						restrict : 'E',

						replace : true,

						template : "<div id='map'><div id='choiceMap'><table><tr><td><label>按关键字搜索：</label></td><td class='column2'><label>左击获取经纬度：</label></td></tr><tr><td><input type='text' placeholder='请输入关键字进行搜索' id='tipinput'></td><td class='column2'><input type='text' readonly='true' id='lnglat'></td></tr></table></div></div>",

						scope : {

							options : '='

						},

						link : function($scope, elem, attr, $state) {
							var map = new AMap.Map("map", {
							    resizeEnable: true
							});
							
							//为地图注册click事件获取鼠标点击出的经纬度坐标
							var clickEventListener = map.on('click', function(e) {
							    document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
							});
							var auto = new AMap.Autocomplete({
							    input: "tipinput"
							});
							AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
							function select(e) {
							    if (e.poi && e.poi.location) {
							        map.setZoom(15);
							        map.setCenter(e.poi.location);
							    }
							}

						}

					}

				} ]);
