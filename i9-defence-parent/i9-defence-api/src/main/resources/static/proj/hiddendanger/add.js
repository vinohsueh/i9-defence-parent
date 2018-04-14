var hiddendangerEditNgModule = angular.module('hiddendangerEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var hiddendangerEditCtrl = hiddendangerEditNgModule.controller('hiddendangerEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		hiddendanger,eqCategorys,httpService) {
	
	$scope.hiddendanger = hiddendanger;
	$scope.eqCategorys=eqCategorys;
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
		if ($scope.hiddendanger.hiddenMax == null ||$scope.hiddendanger.hiddenMax == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "隐患最高阀值不能为空!"
			});
			return false;
		}
		if ($scope.hiddendanger.hiddenMin == null ||$scope.hiddendanger.hiddenMin == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "隐患最低阀值不能为空!"
			});
			return false;
		}
		if ($scope.hiddendanger.warningMax == null ||$scope.hiddendanger.warningMax == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "报警最高阀值不能为空!"
			});
			return false;
		}
		if ($scope.hiddendanger.warningMax <  $scope.hiddendanger.hiddenMax) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "报警最高阀值不能小于隐患最高阀值!"
			});
			return false;
		}
		if ($scope.hiddendanger.warningMin == null ||$scope.hiddendanger.warningMin == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "报警最低阀值不能为空!"
			});
			return false;
		}
		if ($scope.hiddendanger.warningMin >  $scope.hiddendanger.hiddenMin) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "报警最低阀值不能大于隐患最低阀值!"
			});
			return false;
		}
		httpService.post({url:'./hiddendanger/addHiddendanger',data:$scope.hiddendanger,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
