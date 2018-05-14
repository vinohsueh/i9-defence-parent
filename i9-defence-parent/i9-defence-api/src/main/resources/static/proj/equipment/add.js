var equipmentEditNgModule = angular.module('equipmentEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var equipmentEditCtrl = equipmentEditNgModule.controller('equipmentEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		equipment,eqSystemCategorys,equCategorys,projects,httpService) {
	
	$scope.equipment = equipment;
	$scope.eqSystemCategorys = eqSystemCategorys;
	$scope.secondData =equCategorys;
	$scope.projects = projects;

	//设备数量修改
	$scope.equipmentNumStatu = false;
	if($scope.equipment.id != null){
		$scope.equipmentNumStatu = true;
	}
//	console.log($scope.equipment);
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	//初始化第二个参数显示状态
	$scope.choiceFirst = false;
	if(equipment.equipmentCategoryId){
		$scope.choiceFirst = true;
	}
	$scope.change = function(){
//		alert($scope.equipment.equipmentCategoryId);
		if($scope.equipment.equipmentCategoryId != null && $scope.equipment.equipmentCategoryId != ''){
			$scope.choiceFirst = true;
			httpService.post({url:'./equipment/findEquipmentSystemCategory2',data:$scope.equipment.equipmentCategoryId,showSuccessMsg:true}).then(function(data) {  
//				console.log(JSON.stringify(data))
				$scope.secondData = data.data.equipmentCategory;
				
			})
		}else{
			$scope.choiceFirst = false;
		}
		
	};
	$scope.change();
	$scope.change2 = function(){
	};
	
	
	/*$scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {  
		$scope.equipment.equipmentCategory = $scope.equipment.equipmentCategory.id;
		console.log($scope.equipment.equipmentCategory);
  
    }); */ 
//	$scope.aa = 1;
	// 确认添加
	$scope.confirmAdd = function() {
//		if ($scope.equipment.systemId ==null ||$scope.equipment.systemId.length ==0) {
//			$.toaster({
//				title : "Error",
//				priority : "danger",
//				message : "设备编号不能为空!"
//			});
//			return false;
//		}
		if ($scope.equipment.loopl ==null ||$scope.equipment.loopl.length ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "回路号不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentPosition ==null ||$scope.equipment.equipmentPosition.length ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "设备地址不能为空!"
			});
			return false;
		}
		if ($scope.equipment.equipmentRemarks ==null ||$scope.equipment.equipmentRemarks.length ==0) {
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
		$scope.equipment.systemId  = $scope.equipment.equipmentCategory.eqCategoryId;
		console.log($scope.equipment.equipmentCategory.id);
		httpService.post({url:'./equipment/addEquipment',data:$scope.equipment,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
