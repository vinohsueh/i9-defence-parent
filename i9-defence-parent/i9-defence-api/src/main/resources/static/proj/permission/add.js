var permissionEditNgModule = angular.module('permissionEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var permissionEditCtrl = permissionEditNgModule.controller('permissionEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		permission,httpService) {
	
	$scope.permission = permission;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.permission.name == null ||$scope.permission.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "权限名称不能为空!"
			});
			return false;
		}
		if ($scope.permission.code ==null ||$scope.permission.code ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "权限代码不能为空!"
			});
			return false;
		}
		httpService.post({url:'./permission/addPermission',data:$scope.permission,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
