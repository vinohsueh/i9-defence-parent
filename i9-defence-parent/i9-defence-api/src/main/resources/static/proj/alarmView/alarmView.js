var alarmViewNgModule=angular.module('alarmViewNgModule',['ngAnimate','ui.bootstrap','app']);
var alarmViewService = alarmViewNgModule.factory('alarmViewService',
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
var alarmViewNgControl=alarmViewNgModule.controller('alarmViewNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,alarmViewService,httpService){
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
})
