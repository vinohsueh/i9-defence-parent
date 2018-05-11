var eqSystemtypeEditNgModule = angular.module('eqSystemtypeEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var eqSystemtypeEditCtrl = eqSystemtypeEditNgModule.controller('eqSystemtypeEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		eqSystemtype,httpService) {
	
	$scope.eqSystemtype = eqSystemtype;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.eqSystemtype.systemType ==null ||$scope.eqSystemtype.systemType.length ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备种类名称不能为空!"
			});
			return false;
		}
		if ($scope.eqSystemtype.systemCategory ==null ||$scope.eqSystemtype.systemCategory.length ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备分类类型不能为空!"
			});
			return false;
		}
//		if ($scope.equipment.equipmentCategory.eqSystemtypeName ==null ||$scope.equipment.equipmentCategory.eqSystemtypeName ==0) {
//			$.toaster({
//				title : "Error",
//				priority : "danger",
//				message : "设备类别不能为空!"
//			});
//			return false;
//		}
		httpService.post({url:'./eqSystemtype/addEqSystemtype',data:$scope.eqSystemtype,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
