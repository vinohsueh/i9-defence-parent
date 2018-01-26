var accountEditNgModule = angular.module('accountEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var accountEditCtrl = accountEditNgModule.controller('accountEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		account,httpService) {
	
	$scope.account = account;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	$scope.roles = ["主管领导","管理人员","值班人员"];
	// 确认添加
	
	httpService.post({url:'./project/findAllProjectById',dshowSuccessMsg:false}).then(function(data) {
		$scope.projects = data.data.data;
	})
	$scope.confirmAdd = function() {
		httpService.post({url:'./account/addAccount',data:$scope.account,showSuccessMsg:true}).then(function(data) {
			$modalInstance.dismiss('cancel')
		})
	};
	
});
