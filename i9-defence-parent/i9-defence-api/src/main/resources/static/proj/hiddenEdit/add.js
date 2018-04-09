/**
 * 故障add.js
 */
var equipmentFaultEditNgModule = angular.module('equipmentFaultEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var equipmentFaultEditCtrl = equipmentFaultEditNgModule.controller('equipmentFaultEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		equipmentFault,httpService) {
	
	$scope.equipmentFault = equipmentFault;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	$scope.tab = "equipmentFault";
	$scope.changeTab = function(page){
		$scope.tab = page;
	}
	// 确认添加
	$scope.confirmAdd = function() {
		
		$scope.permissionArray = [];
    	angular.forEach(angular.element.find(".ckbox"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.permissionArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	$scope.equipmentFault.permissionIds = $scope.permissionArray;
		if ($scope.equipmentFault.name == null ||$scope.equipmentFault.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障名称不能为空!"
			});
			return false;
		}
		if ($scope.equipmentFault.code == null ||$scope.equipmentFault.code == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障代码不能为空!"
			});
			return false;
		}
		httpService.post({url:'./equipmentFault/updateAndAdd',data:$scope.equipmentFault,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	httpService.post({url:'./eqCategory/serchEqCategory',data:$scope.equipmentCategory,showSuccessMsg:false}).then(function(data) {  
		$scope.equipmentCategory = data.data.data;
	})
});
