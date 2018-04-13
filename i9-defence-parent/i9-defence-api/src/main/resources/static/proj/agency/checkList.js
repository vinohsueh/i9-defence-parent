var checkListNgModule = angular.module('checkListNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var checkListCtrl = checkListNgModule.controller('checkListCtrl', function($scope,
		$rootScope,$modal, $modalInstance, $cookieStore, $http, $window, toaster, httpService,param) {

	//此ID为操作对象的ID 二级经销商的
    $scope.id = param.id;
    $scope.statu = param.statu;
  //初始化 一级经销商
    $scope.pageInit1 = function () {
        httpService.post({url:'./agency/selectAagency',showSuccessMsg:false}).then(function(data) {
            var agencyList = data.data.data;
            var oHtml= "";
            //初始化 一级经销商
            angular.forEach(agencyList,function(manager){
            	oHtml+="<li><span class='name' data-id='"+manager.id+"'>"+manager.username+"</span></li>";             
            });
            $('#checkList').html(oHtml);
        });
    }
  //初始化 二级经销商
    $scope.pageInit2 = function () {
        httpService.post({url:'./agency/selectBagency',showSuccessMsg:false}).then(function(data) {
            var agencyList = data.data.data;
            var oHtml= "";
            //初始化 二级经销商
            angular.forEach(agencyList,function(manager){
            	oHtml+="<li><span class='name' data-id='"+manager.id+"'>"+manager.username+"</span></li>";             
            });
            $('#checkList').html(oHtml);
        });
    }
    //为father的时候查询的是全部的一级经销商
    if($scope.statu == 'father'){
        $scope.pageInit1();
    }else{
    	$scope.pageInit2();
    }
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	};

	// 确认添加
	$scope.confirmAdd = function() {
			//此ID可能为一级可能为二级
			var checkId = $("#checkList").find('li.active>span').attr('data-id');
            if($scope.statu != 'child'){
            	//  是否确认连带 二级换一级 $scope.id 二级  checkId 一级
            	var parme = {
            			managerId : $scope.id,
            			parentId : checkId
            	}
            	httpService.post({url:'./agency/updateBagency',data:parme,showSuccessMsg:true}).then(function(data) {})
                $scope.confirmCheck($scope.id);
            }else{
            	//给三级 换个二级 先根据二级ID把全部的三级ID们查出来
            	var parme = {
            			//新二级ID
            			parentId : checkId,
            			//老二级ID
            			spareId : $scope.id
            	}
            	httpService.post({url:'./agency/updateCagency',data:parme,showSuccessMsg:true}).then(function(data) {})
            }
            $modalInstance.dismiss('cancel');
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
