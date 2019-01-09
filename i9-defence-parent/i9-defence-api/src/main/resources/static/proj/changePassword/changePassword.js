var changePasswordNgModule=angular.module('changePasswordNgModule',['ngAnimate','ui.bootstrap','app']);
var changePasswordService = changePasswordNgModule.factory('changePasswordService',
		['$resource', function($resource){
			//指定url格式:../模块名/服务名/方法名?参数
			var path = '../rest/:moduleName/:serviceName/:methodName?rnd=:random';
			//service忠的方法
			var resource = $resource(path, {}, {
				save : {
					method : 'POST',
					params : {
						moduleName : 'proj',
						serviceName : 'info',
						methodName : 'save',
						//role : '@role',
						//rp : '@rp',
						random : Math.random()
					}
				},
			});
			return resource;
	}]);
var changePasswordNgControl=changePasswordNgModule.controller('changePasswordNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,changePasswordService,httpService){
	$scope.oldPas = '';
    $scope.newPas = '';
    $scope.configPas = '';
    //初始化
    $scope.submitF = function (){
        var pageParam = {
                oldPassword:$scope.oldPas,
                password:$scope.newPas,
                confirmPwd : $scope.configPas
            };
        if(!$scope.oldPas){
            $.toaster({
                title : "Error",
                priority : "danger",
                message : "请输入旧密码!"
            });
            return;
        }if(!$scope.newPas){
            $.toaster({
                title : "Error",
                priority : "danger",
                message : "请输入新密码!"
            });
            return;
        }else if($scope.newPas.length<5){
            $.toaster({
                title : "Error",
                priority : "danger",
                message : "请输入5-20位新密码!"
            });
            return;
        }
        if(!$scope.configPas){
            $.toaster({
                title : "Error",
                priority : "danger",
                message : "请输入确认密码!"
            });
            return;
        }else if($scope.configPas != $scope.newPas){
            $.toaster({
                title : "Error",
                priority : "danger",
                message : "请保持两次新密码输入一致!"
            });
            return;
        }
        console.log(pageParam);
        httpService.post({url:'./managerApply/updateManagerPwd',data:pageParam,showSuccessMsg:false}).then(function(data) {  
            $.toaster({
                title : "Success",
                priority : "success",
                message : "修改密码成功",
            });
            setTimeout(function(){
            	httpService.get({url:'./logout',showSuccessMsg:false}).then(function(data) {  
                })
            },1000)
        })
    };
})
