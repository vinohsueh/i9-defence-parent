var roleEditNgModule = angular.module('roleEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var roleEditCtrl = roleEditNgModule.controller('roleEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		role,httpService) {
	
	$scope.role = role;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.role.name == null ||$scope.role.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "角色名称不能为空!"
			});
			return false;
		}
		if ($scope.role.code ==null ||$scope.role.code ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "角色代码不能为空!"
			});
			return false;
		}
		httpService.post({url:'./role/addRole',data:$scope.role,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
