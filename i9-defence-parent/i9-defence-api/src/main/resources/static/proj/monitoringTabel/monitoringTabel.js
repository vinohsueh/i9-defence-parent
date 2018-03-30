var monitoringTabelNgModule=angular.module('monitoringTabelNgModule',['ngAnimate','ui.bootstrap','app']);
var monitoringTabelService = monitoringTabelNgModule.factory('monitoringTabelService',
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
var monitoringTabelNgControl=monitoringTabelNgModule.controller('monitoringTabelNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,monitoringTabelService,httpService){
	
	
})
