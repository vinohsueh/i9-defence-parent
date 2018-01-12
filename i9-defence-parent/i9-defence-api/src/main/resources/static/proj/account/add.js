var accountEditNgModule = angular.module('accountEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var accountEditCtrl = accountEditNgModule.controller('accountEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		account,httpService) {
	
	$scope.account = account;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.account.username ==null ||$scope.account.username ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "账户名不能为空!"
			});
			return false;
		}
		httpService.post({url:'./account/addAccount',data:$scope.project,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
