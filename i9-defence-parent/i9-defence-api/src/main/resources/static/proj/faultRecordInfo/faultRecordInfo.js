var faultRecordInfoModule=angular.module('faultRecordInfoModule',['ngAnimate','ui.bootstrap','app']);
var faultRecordInfoControl=faultRecordInfoModule.controller('faultRecordInfoControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	$scope.typeId=$stateParams.typeId;

	$scope.itemNum = 32;
	//初始化
	$scope.initTable = function (){

		var pageParam = {
				count:$scope.itemNum,
				deviceId:$stateParams.id
			};
			console.log(JSON.stringify(pageParam));
		if($scope.typeId){
			if($scope.typeId==1){
				httpService.post({url:'./equipment/selectErrorRecord',data:pageParam,showSuccessMsg:false}).then(function(data) {  
					$scope.warningList = data.data.data.channelData;
					// console.log($scope.warningList)
				})
			}else if($scope.typeId==2){
				httpService.post({url:'./hiddenDangerEdit/selectDangerChannelDtoBySid',data:{deviceId:$stateParams.id,count:16},showSuccessMsg:false}).then(function(data) {  
					$scope.warningList = data.data.data;
					// console.log($scope.warningList)
				})
			}else if($scope.typeId==3){
				httpService.post({url:'./hiddenDangerEdit/selectHiddenDangerChannelDtoBySid',data:{deviceId:$stateParams.id,count:16},showSuccessMsg:false}).then(function(data) {  
					$scope.warningList = data.data.data;
					// console.log($scope.warningList)
				})
			}
		}
	};
	$scope.initTable();
	
	//加载更多
	$scope.countAdd = function () {
		$scope.itemNum = $scope.itemNum+=32;
		$scope.initTable();
	}

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
