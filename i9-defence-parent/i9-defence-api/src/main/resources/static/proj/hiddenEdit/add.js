var hiddenEditNgModule = angular.module('hiddenEditNgModule', [ 'ngResource',
        'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var hiddenEditNgCtrl = hiddenEditNgModule.controller('hiddenEditNgCtrl', function($scope,
        $rootScope,$modal, $modalInstance, $cookieStore, $http, $window, toaster, httpService,hiddenEdit) {
	
	$scope.hiddenEdit = hiddenEdit;
    //页面初始化
    /*$scope.pageInit = function (data) {
        console.log(JSON.stringify(data));
        var oHtml = "";
        for(i in data){
            oHtml += "<tr><td>"+data[i].name+"</td></tr>"
            for(j in data[i].data){
                oHtml += "<tr><td>"+data[i].data[j].value+"</td><td >"+data[i].data[j].dateTimeStr+"</td><td ><select class='form-control'><option value='0'>未处理</option><option value='1'>标记已处理</option><option value='2'>标记误报</option><option value='3'>标记故障</option></select></td><td ><input type='text' class='input-sm form-control' placeholder='处理详情'></td></tr>"
            }
        }
        $('#tableBody').html(oHtml);
        console.log(angular.element('#tableBody'));
    }
    $scope.pageInit($scope.hiddenEdit);*/



	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
    
   
	/*$scope.check = function(statu){
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
    		dealStatus : statu
//    		dealDetail:$scope.editTextArray
    	}
    	console.log('--------'+statu);
    	console.log('--------'+$scope.delArray);
//    	console.log('--------'+$scope.editTextArray);
    	console.log('--------'+JSON.stringify(dealStatus));
    	confirm("确定处理吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./hiddenDangerEdit/updateDealStatus',data:dealStatus,showSuccessMsg:true}).then(function(data) {  
            		$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    
    }*/
	
});
