var roleEditNgModule = angular.module('roleEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var roleEditCtrl = roleEditNgModule.controller('roleEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		role,httpService) {
	
	$scope.role = role;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	console.log(role)
	$scope.tab = "role";
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
    	$scope.role.permissionIds = $scope.permissionArray;
		if ($scope.role.name == null ||$scope.role.name == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "角色名称不能为空!"
			});
			return false;
		}
		if ($scope.role.code ==null ||$scope.role.code ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "角色代码不能为空!"
			});
			return false;
		}
		console.log($scope.role)
		httpService.post({url:'./role/addRole',data:$scope.role,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
	
	httpService.post({url:'./permission/getAll',showSuccessMsg:false}).then(function(data) {  
		var permissionIds = [];
		if (role.id != null) {
			for (var i = 0;i<role.permissions.length;i++){
				permissionIds.push(role.permissions[i].id);
			}
		}
		$scope.permissions = data.data.data;
		for(var i=0;i<$scope.permissions.length;i++){
			if($.inArray($scope.permissions[i].id, permissionIds)>-1){
				$scope.permissions[i].checked = true;
			}else{
				$scope.permissions[i].checked = false;
			}
		}
	})
});
