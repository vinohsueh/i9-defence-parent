var applyaccountEditNgModule = angular.module('applyaccountEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var applyaccountEditCtrl = applyaccountEditNgModule.controller('applyaccountEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		ids,content,edit,httpService) {
	
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	if (content != null) {
		$scope.content = content;
	}
	$scope.edit = edit;
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.content ==null ||$scope.content.length ==0 ||$scope.content.length>200) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "拒绝理由不能为空且在200字内!"
			});
			return false;
		}
		var applyRefuseDto = {
			ids : ids,
			content : $scope.content
		};
		confirm("确定拒绝吗?", "", function (isConfirm) {
	        if (isConfirm) {
	        	httpService.post({url:'./managerApply/refuseManagerApply',data:applyRefuseDto,showSuccessMsg:true}).then(function(data) {  
	        		$modalInstance.dismiss('cancel');
	        	})
	        } else {
	        }
	    }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
	};
	
});
