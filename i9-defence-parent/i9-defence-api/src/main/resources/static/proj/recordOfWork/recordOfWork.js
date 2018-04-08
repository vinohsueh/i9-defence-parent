var recordOfWorkNgModule=angular.module('recordOfWorkNgModule',['ngAnimate','ui.bootstrap','app']);
var recordOfWorkService = recordOfWorkNgModule.factory('recordOfWorkService',
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
var recordOfWorkNgControl=recordOfWorkNgModule.controller('recordOfWorkNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,recordOfWorkService,httpService){
	//日期
	var myDate = new Date(2000,00,00);
	var dYear = myDate.getFullYear();
	var dMonth = myDate.getMonth()+1;
	var dDay = myDate.getDate();
	$scope.startTime = dYear+'/'+dMonth+'/'+dDay;

	$scope.change = function () {
		var myStartDate = this.startTime;
		var dYear = myStartDate.getFullYear();
		var dMonth = myStartDate.getMonth()+1;
		var dDay = myStartDate.getDate();
		$scope.startCheckTime = dYear+'/'+dMonth+'/'+dDay;
		console.log(this.startCheckTime);
	}
	
	// $scope.endTime = this.startCheckTime;

	// 地域
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
