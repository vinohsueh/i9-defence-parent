var pageEditNgModule = angular.module('pageEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var pageEditCtrl = pageEditNgModule.controller('pageEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		page,parentId,httpService) {
	if (page.id != null){
		$scope.page = page;
	}else{
		page.parentId = parentId;
		$scope.page = page;
	}
	
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.page.name ==null ||$scope.page.name ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "页签名称不能为空!"
			});
			return false;
		}
		httpService.post({url:'./page/savePage',data:$scope.page,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	}
});
