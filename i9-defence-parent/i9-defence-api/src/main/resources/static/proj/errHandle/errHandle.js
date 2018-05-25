var errHandleModule=angular.module('errHandleModule',['ngAnimate','ui.bootstrap','app']);
var warningInfoService = errHandleModule.factory('warningInfoService',
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
var errHandleControl=errHandleModule.controller('errHandleControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,warningInfoService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
//	初始化底部信息
	$scope.ifshow = false;
//	初始化id
	$scope.deviceId=0;
	//初始化
	$scope.initTable = function (){
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				orderByClause: "handleDate desc",
				handleState: 0 ,
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
	//项目选择
	$scope.choiceItem = function(idNum){
		$scope.ifshow = true;
		$scope.id = idNum;
		console.log(idNum);
	}
	
	   //详情提交
    $scope.confirmBtn = function () {
    	var handleCon = $('#handleCon').val();
    	var param = {
    		id:$scope.id,
    		handleCon:handleCon,
    		handleState:1
    	}
    	console.log(param);
    	if(handleCon.replace(/(^\s*)|(\s*$)/g,"").length!=0){
	    	httpService.post({url:'./errHandle/errHandleEdit',data:param,showSuccessMsg:false}).then(function(data) {
	    		$scope.ifshow = false;
	    		$('#handleCon').val('');
	    		$scope.initTable();
	    	});
    	}else{
    		alert('请输入内容！');
    	}
    	
    }
})
