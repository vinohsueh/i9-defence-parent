/**
 * 客户add.js
 */
var clientEditNgModule = angular.module('clientEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var clientEditCtrl = clientEditNgModule.controller('clientEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		client,httpService) {
	
	$scope.client = client;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	$scope.tab = "client";
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
    	$scope.client.permissionIds = $scope.permissionArray;
		if ($scope.client.name == null ||$scope.client.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "角色名称不能为空!"
			});
			return false;
		}
		if ($scope.client.phone == null ||$scope.client.phone == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "联系方式不能为空!"
			});
			return false;
		}
		if ($scope.client.address == null ||$scope.client.address == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "地址不能为空!"
			});
			return false;
		}
		if ($scope.client.mail == null ||$scope.client.mail == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "邮箱不能为空!"
			});
			return false;
		}
		if ($scope.client.territory == null ||$scope.client.territory == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "地域不能为空!"
			});
			return false;
		}
		httpService.post({url:'./client/updateAndAdd',data:$scope.client,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
});
