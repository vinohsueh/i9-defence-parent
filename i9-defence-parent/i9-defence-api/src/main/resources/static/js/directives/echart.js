angular.module('app').directive('eChart', function($parse, $interval){
	return {
		restrict: 'AE',
		replace: true,
		scope: {
			options: '=',
			height: '@',
			width: '@'
		},
		link: function(scope, element, attrs, ctrl) {
			var wrap = $('<div></div>').css({
				width: scope.width||'100%',
				height: scope.height||'100%'
			});
			$(element).css({
				display:'block',
				width: scope.width||'100%',
				height: scope.height||'100%'
			});
			var myChart = echarts.init(element[0]);
			window.addEventListener('resize',function(){
                    myChart.resize();//监测图表自适应
                })

			scope.$watch('options', function(n, o){
				if (typeof(n)=='object') {
					myChart.setOption(scope.options);
					
				};
			});
			
		}
	};
});