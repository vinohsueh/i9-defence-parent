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
				handleState: 1 ,
				eqDeviceId:$stateParams.id
			};
		httpService.post({url:'./errHandle/pageErrHandle',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.warningLists = data.data.data.pageList;
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.warningLists.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
//			for(i in $scope.warningLists){
//				console.log("----"+JSON.stringify($scope.warningLists[i].handleState));
//				if($scope.warningLists[i].handleState = 0){
//					$scope.warningLists[i].statusText = '未处理';
//				}else if($scope.warningLists[i].handleState = 1){
//					$scope.warningLists[i].statusText = '已处理';
//				}
//			}
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
