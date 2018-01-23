var agencyEditNgModule = angular.module('agencyEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var agencyEditCtrl = agencyEditNgModule.controller('agencyEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		agency,httpService) {
	
	$scope.agency = agency;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.agency.username ==null ||$scope.agency.username ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "账户名不能为空!"
			});
			return false;
		}
		httpService.post({url:'./agency/addAgency',data:$scope.agency,showSuccessMsg:true}).then(function(data) {
			$modalInstance.dismiss('cancel')
		})
	};
	
});
