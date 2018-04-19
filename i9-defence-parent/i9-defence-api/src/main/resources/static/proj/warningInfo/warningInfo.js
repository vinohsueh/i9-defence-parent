var warningInfoModule=angular.module('warningInfoModule',['ngAnimate','ui.bootstrap','app']);
var warningInfoService = warningInfoModule.factory('warningInfoService',
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
var warningInfoControl=warningInfoModule.controller('warningInfoControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,warningInfoService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	//初始化
	$scope.initTable = function (){
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				orderByClause: "handleDate desc",
				eqDeviceId:$stateParams.id
				//state:$scope.selectState,
				//username : $scope.searchText
			};
		console.log(JSON.stringify(pageParam));
		httpService.post({url:'./channel/pageChannel',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			console.log(JSON.stringify(data));
		})
	};
	$scope.initTable();
	
	//修改分页大小
	$scope.changePageSize = function(){
		$scope.currentPage = 1;
		$scope.initTable();
	}
	//上一页
	$scope.lastPage = function(){
		if ($scope.hasPrevious){
			$scope.currentPage -=1;
			$scope.initTable();
		}
	}
	//下一页
	$scope.nextPage = function (){
		if ($scope.hasNext){
			$scope.currentPage +=1;
			$scope.initTable();
		}
	}
	//跳转
	$scope.pageTo = function(page){
		$scope.currentPage = page;
		$scope.initTable();
	}
	$scope.search = function(){
		$scope.initTable();
	}

})
