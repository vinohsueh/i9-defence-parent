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
		if ($scope.hiddendangerInfo.type == null) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "阀值类型不能为空!"
			});
			return false;
		}
		if ($scope.hiddendangerInfo.hid == null ||$scope.hiddendangerInfo.hid == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "隐患类型名称不能为空!"
			});
			return false;
		}
		if ($scope.hiddendangerInfo.name== null ||$scope.hiddendangerInfo.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "最高低值不能为空!"
			});
			return false;
		}
		httpService.post({url:'./hiddenDangerInfo/addHiddenDangerInfo',data:$scope.hiddendangerInfo,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	 httpService.post({url:'./hiddendanger/getAllHiddendanger'}).then(function(data){
		 $scope.hiddendangers=data.data.data;
	 })
	
});
