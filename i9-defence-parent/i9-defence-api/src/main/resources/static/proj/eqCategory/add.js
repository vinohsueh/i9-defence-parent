var eqCategoryEditNgModule = angular.module('eqCategoryEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var eqCategoryEditCtrl = eqCategoryEditNgModule.controller('eqCategoryEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		eqCategory,httpService) {
	
	$scope.eqCategory = eqCategory;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.eqCategory.eqCategoryName ==null ||$scope.eqCategory.eqCategoryName.length ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备种类名称不能为空!"
			});
			return false;
		}
		if ($scope.eqCategory.eqCategoryId ==null ||$scope.eqCategory.eqCategoryId.length ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备种类编号不能为空!"
			});
			return false;
		}
//		if ($scope.equipment.equipmentCategory.eqCategoryName ==null ||$scope.equipment.equipmentCategory.eqCategoryName ==0) {
//			$.toaster({
//				title : "Error",
//				priority : "danger",
//				message : "设备类别不能为空!"
//			});
//			return false;
//		}
		httpService.post({url:'./eqCategory/addEqCategory',data:$scope.eqCategory,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
