var monitoringChartEditNgModule = angular.module('monitoringChartEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var monitoringChartEditCtrl = monitoringChartEditNgModule.controller('monitoringChartEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		monitoringChart,clientList,httpService) {
	
	$scope.monitoringChart = monitoringChart;
	$scope.clientList = clientList;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.monitoringChart.monitoringChartName ==null ||$scope.monitoringChart.monitoringChartName ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "项目名不能为空!"
			});
			return false;
		}
		httpService.post({url:'./monitoringChart/addMonitoringChart',data:$scope.monitoringChart,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
