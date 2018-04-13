var confirmCheckNgModule = angular.module('confirmCheckNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var confirmCheckCtrl = confirmCheckNgModule.controller('confirmCheckCtrl', function($scope,
		$rootScope,$modal, $modalInstance, $cookieStore, $http, $window, toaster, httpService,param) {

    $scope.id = param;
    /*httpService.post({url:'./agency/allotAgency',data:$scope.id,showSuccessMsg:false}).then(function(data) {
    	

    });*/

	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
        $scope.checkList($scope.id);
	};

	// 确认添加
	$scope.confirmAdd = function() {
		// httpService.post({url:'./agency/addAgency',data:$scope.agency,showSuccessMsg:true}).then(function(data) {
			$modalInstance.dismiss('cancel');
		// })
	};
    
    $(document).unbind( "click" ).on('click','.list>li',function () {
        $(this).siblings().removeClass('active');
        $(this).toggleClass('active');
    });
    $scope.checkList = function (id) {
        var param = {
            id:id,
            statu:'child'
        }
        var modalInstance = $modal.open({  
            templateUrl: 'proj/agency/checkList.html',
            controller: 'checkListCtrl',
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
                deps : ['$ocLazyLoad',function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name : 'checkListNgModule',
                        insertBefore : '#ng_load_plugins_before',
                        files : [
                                 'proj/agency/checkList.js',
                        ]
                    });
                }],
                param: function () {
                    return param;
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数

        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
            // $scope.pageInit();
        });
         
    };
});
