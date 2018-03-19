var equipmentEditNgModule = angular.module('equipmentEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var equipmentEditCtrl = equipmentEditNgModule.controller('equipmentEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		equipment,httpService) {
	
	$scope.equipment = equipment;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	httpService.post({url:'./eqCategory/serchEqCategory',showSuccessMsg:false}).then(function(data) {  
		$scope.equCategorys = data.data.data;
		console.log(JSON.stringify(data));
	}) 
	httpService.post({url:'./project/getAllProject',showSuccessMsg:false}).then(function(data) {  
		$scope.projects = data.data.data;
		console.log(JSON.stringify(data));
	}) 
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.equipment.equipmentName ==null ||$scope.equipment.equipmentName ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备名称不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentNum ==null ||$scope.equipment.equipmentNum ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "数量不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentIdentifier ==null ||$scope.equipment.equipmentIdentifier ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备编号不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentPosition ==null ||$scope.equipment.equipmentPosition ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "位置不能为空!"
			});
			return false;
		}
//		if ($scope.equipment.equipmentDate ==null ||$scope.equipment.equipmentDate ==0) {
//			$.toaster({
//				title : "Error",
//				priority : "danger",
//				message : "时间不能为空!"
//			});
//			return false;
//		}
		if ($scope.equipment.equipmentRemarks ==null ||$scope.equipment.equipmentRemarks ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "备注不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentName ==null ||$scope.equipment.equipmentName ==0) {
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
		
		httpService.post({url:'./equipment/addEquipment',data:$scope.equipment,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
