var hiddendangerEditNgModule = angular.module('hiddendangerEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var hiddendangerEditCtrl = hiddendangerEditNgModule.controller('hiddendangerEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		hiddendanger,httpService) {
	
	$scope.hiddendanger = hiddendanger;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.hiddendanger.name == null ||$scope.hiddendanger.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "隐患类型名称不能为空!"
			});
			return false;
		}
		httpService.post({url:'./hiddendanger/addHiddendanger',data:$scope.hiddendanger,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
