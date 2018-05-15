/**
 * 故障add.js
 */
var equipmentFaultEditNgModule = angular.module('equipmentFaultEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var equipmentFaultEditCtrl = equipmentFaultEditNgModule.controller('equipmentFaultEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		equipmentFault,eqSystemCategorys,httpService) {
	
	$scope.equipmentFault = equipmentFault;
	$scope.eqSystemCategorys = eqSystemCategorys;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	$scope.tab = "equipmentFault";
	$scope.changeTab = function(page){
		$scope.tab = page;
	}
	// 确认添加
	$scope.confirmAdd = function() {
		console.log($scope.equipmentFault)
		if ($scope.equipmentFault.name == null ||$scope.equipmentFault.name.length == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障名称不能为空!"
			});
			return false;
		}
		if ($scope.equipmentFault.code == null ||$scope.equipmentFault.code.length == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障代码不能为空!"
			});
			return false;
		}
		
		if ($scope.equipmentFault.type == null ||$scope.equipmentFault.type.length == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障类型不能为空!"
			});
			return false;
		}
		httpService.post({url:'./equipmentFault/updateAndAdd',data:$scope.equipmentFault,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
//	httpService.post({url:'./eqCategory/serchEqCategory',data:$scope.equipmentCategory,showSuccessMsg:false}).then(function(data) {  
//		$scope.equipmentCategory = data.data.data;
//	})
});
