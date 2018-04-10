var hiddenEditNgModule = angular.module('hiddenEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var hiddenEditNgCtrl = hiddenEditNgModule.controller('hiddenEditNgCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		hiddenEdit,httpService) {
	
	$scope.hiddenEdit = hiddenEdit;
//	console.log($scope.hiddenEdit);
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	$scope.check = function(statu){
//		var text = $scope.editText;
    	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks1"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
//    	$scope.editTextArray = [];
//    	angular.forEach(angular.element.find(".o-checks1"), function(dom){
//    		if(angular.element(dom).prop("checked") == true){
//    			$scope.delArray.push(angular.element(dom).attr("ng-model"))
//    		}
//		});
    	
    	var dealStatus={
    		ids :$scope.delArray,
    		dealStatus : statu,
//    		editDealText:text
    	}
    	console.log('--------'+statu);
    	console.log('--------'+$scope.delArray);
    	console.log('--------'+$scope.editTextArray);
    	console.log('--------'+JSON.stringify(dealStatus));
    	confirm("确定处理吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./hiddenDangerEdit/updateDealStatus',data:dealStatus,showSuccessMsg:true}).then(function(data) {  
            		$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    
    }
	
});
