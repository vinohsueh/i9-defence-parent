/**
 * 客户add.js
 */
var clientSendNgModule = angular.module('clientSendNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var clientSendCtrl = clientSendNgModule.controller('clientSendCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		client,httpService) {
	$scope.client = client;
	$scope.templateNumber = '';
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	//初始化
	/*httpService.post({url:'./client/getAllClientById',showSuccessMsg:false}).then(function(data) {  
		$scope.dataList = data.data.allManger;
	})*/
	// 确认添加
	$scope.confirmSend = function() {
		$scope.permissionArray = [];
    	angular.forEach(angular.element.find(".clientList .clientItem"), function(dom){
    		if(angular.element(dom).find(".o-checks").prop("checked") == true){
    			$scope.permissionArray.push(angular.element(dom).find(".o-checks").attr("data-id"))
    		}
		});
    	var param={
    			clientIdList:$scope.permissionArray,
    			templateNum:$scope.templateNumber
    	}
    	console.log(param)
		if ($scope.permissionArray.length<1) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "群发人数不能为空"
			});
			return false;
		}else if ($scope.templateNumber.length==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "模板编号不能为空"
			});
			return false;
		}else{
			/*httpService.post({url:'./client/updateAndAdd',data:param,showSuccessMsg:true}).then(function(data) {  
				$modalInstance.dismiss('cancel')
			})*/
			alert('success');
		}
		
	};
});
