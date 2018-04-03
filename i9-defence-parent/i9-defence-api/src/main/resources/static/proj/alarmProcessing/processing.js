var alarmProcessingEditNgModule = angular.module('alarmProcessingEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var alarmProcessingEditCtrl = alarmProcessingEditNgModule.controller('alarmProcessingEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		alarmProcessing,clientList,httpService) {
	
	$scope.alarmProcessing = alarmProcessing;
	$scope.clientList = clientList;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.alarmProcessing.processingInfo ==null ||$scope.alarmProcessing.processingInfo ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "项目名不能为空!"
			});
			return false;
		}
		httpService.post({url:'./alarmProcessing/addalarmProcessing',data:$scope.alarmProcessing,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
