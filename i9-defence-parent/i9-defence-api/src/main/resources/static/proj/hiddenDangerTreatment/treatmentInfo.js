var treatmentInfoNgModule = angular.module('treatmentInfoNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var treatmentInfoEditCtrl = treatmentInfoNgModule.controller('treatmentInfoEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		treatmentInfo,clientList,httpService) {
	
	$scope.treatmentInfo = treatmentInfo;
	$scope.clientList = clientList;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.treatmentInfo.text ==null ||$scope.treatmentInfo.text ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "项目名不能为空!"
			});
			return false;
		}
		httpService.post({url:'./treatmentInfo/addtreatmentInfo',data:$scope.treatmentInfo,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
	
});
