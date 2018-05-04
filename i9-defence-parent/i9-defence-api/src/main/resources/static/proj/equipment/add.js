var equipmentEditNgModule = angular.module('equipmentEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var equipmentEditCtrl = equipmentEditNgModule.controller('equipmentEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		equipment,equCategorys,projects,httpService) {
	
	$scope.equipment = equipment;
	$scope.equCategorys = equCategorys;
	$scope.projects = projects;

	//设备数量修改
	$scope.equipmentNumStatu = false;
	if($scope.equipment.id != null){
		$scope.equipmentNumStatu = true;
	}
	console.log($scope.equipment);
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.equipment.loopl ==null ||$scope.equipment.loopl ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "回路号不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentPosition ==null ||$scope.equipment.equipmentPosition ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备地址不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentRemarks ==null ||$scope.equipment.equipmentRemarks ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "备注不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentCategoryId ==null ||$scope.equipment.equipmentCategoryId ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备类别不能为空!"
			});
			return false;
		}
		if ($scope.equipment.projectId ==null ||$scope.equipment.projectId ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "项目不能为空!"
			});
			return false;
		}
		console.log($scope.equipment);
		httpService.post({url:'./equipment/addEquipment',data:$scope.equipment,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
