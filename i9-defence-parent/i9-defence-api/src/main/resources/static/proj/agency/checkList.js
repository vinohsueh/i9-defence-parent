var checkListNgModule = angular.module('checkListNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var checkListCtrl = checkListNgModule.controller('checkListCtrl', function($scope,
		$rootScope,$modal, $modalInstance, $cookieStore, $http, $window, toaster, httpService,param) {

    $scope.id = param.id;
    $scope.statu = param.statu;
    /*httpService.post({url:'./agency/allotAgency',data:$scope.id,showSuccessMsg:false}).then(function(data) {
    	

    });*/

	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	};

	// 确认添加
	$scope.confirmAdd = function() {
		// httpService.post({url:'./agency/addAgency',data:$scope.agency,showSuccessMsg:true}).then(function(data) {
			$modalInstance.dismiss('cancel');
            if($scope.statu != 'child'){
                $scope.confirmCheck($scope.id);
            }
            
		// })
	};
    
    $(document).unbind( "click" ).on('click','.list>li',function () {
        $(this).siblings().removeClass('active');
        $(this).toggleClass('active');
    });
    $scope.confirmCheck = function (id) {
        var modalInstance = $modal.open({  
            templateUrl: 'proj/agency/confirm.html',
            controller: 'confirmCheckCtrl',
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
                deps : ['$ocLazyLoad',function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name : 'confirmCheckNgModule',
                        insertBefore : '#ng_load_plugins_before',
                        files : [
                                 'proj/agency/confirm.js',
                        ]
                    });
                }],
                param: function () {
                    return id;
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数

        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
            // $scope.pageInit();
        });
         
    };
});
