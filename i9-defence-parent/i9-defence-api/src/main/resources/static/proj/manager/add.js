var managerEditNgModule = angular.module('managerEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var managerEditCtrl = managerEditNgModule.controller('managerEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		manager,httpService) {
	
	$scope.manager = manager;
	$scope.manager.confirmPwd = $scope.manager.password;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.manager.username ==null ||$scope.manager.username ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "用户名不能为空!"
			});
			return false;
		}
		if ($scope.manager.password ==null ||$scope.manager.password ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "密码不能为空!"
			});
			return false;
		}
		httpService.post({url:'./manager/addNetManager',data:$scope.manager,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
