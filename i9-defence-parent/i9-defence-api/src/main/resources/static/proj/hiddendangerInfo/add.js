var hiddendangerInfoEditNgModule = angular.module('hiddendangerInfoEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var hiddendangerInfoEditCtrl = hiddendangerInfoEditNgModule.controller('hiddendangerInfoEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		hiddendangerInfo,httpService) {
	
	$scope.hiddendangerInfo = hiddendangerInfo;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.hiddendangerInfo.type == null ||$scope.hiddendangerInfo.type == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "隐患类型名称不能为空!"
			});
			return false;
		}
		httpService.post({url:'./hiddendangerInfo/addHiddenDangerInfo',data:$scope.hiddendangerInfo,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
