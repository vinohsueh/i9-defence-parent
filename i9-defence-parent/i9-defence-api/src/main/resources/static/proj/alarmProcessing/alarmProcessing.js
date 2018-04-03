var alarmProcessingNgModule=angular.module('alarmProcessingNgModule',['ngAnimate','ui.bootstrap','app']);
var alarmProcessingService = alarmProcessingNgModule.factory('alarmProcessingService',
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
var alarmProcessingNgControl=alarmProcessingNgModule.controller('alarmProcessingNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,alarmProcessingService,httpService){
	$scope.error = {};
	$scope.division = division;
	$scope.c = function () {
	   $scope.error.province = false;
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected2 = "";
	   $scope.selected3 = "";
	};
	$scope.c2 = function () {       
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected3 = "";
	};
	$scope.c3 = function () {
	   $scope.error.area = false;
	};
	$scope.processing = function () {  
        var modalInstance = $modal.open({  
            templateUrl: 'proj/alarmProcessing/processing.html',  
            controller: 'alarmProcessingEditCtrl', 
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name : 'alarmProcessingEditNgModule',
        				insertBefore : '#ng_load_plugins_before',
        				files : [
        				         'proj/alarmProcessing/processing.js',
        				]
        			});
        		}],
        		alarmProcessing: function () {  
                    return {};  
                },
                clientList: function () {  
                    return {}; 
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
            $scope.selected = data;
        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
        	$scope.initTable();
        });
         
    };
})
