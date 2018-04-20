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
	
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	$scope.queryProjects = function(){
		if($scope.selected == null || $scope.selected == ''){
			$scope.selected ={
				name:null
			}
		}
		if($scope.selected2 == null || $scope.selected2 == ''){
			$scope.selected2 ={
				name:''
			}
		}
		if($scope.selected3 == null || $scope.selected3 == ''){
			$scope.selected3 ={
				value:''
			}
		}
		
		var pageParam = {
			projectProvince:$scope.selected.name,
			projectCity:$scope.selected2.name,
			projectCounty:$scope.selected3.value,
		};
		
		httpService.post({url:'./project/selectProject',data:pageParam,showSuccessMsg:false}).then(function(data) { 
			$scope.projectss  = data.data.data;
		})
	}
	$scope.queryProjects();
	//初始化
	$scope.searchText = '';
	$scope.initTable = function (){
		if ($scope.projectName != null) {
			$scope.searchText =$scope.projectName.id;
		}else{
			$scope.searchText = null;
		}
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				projectId : $scope.searchText
			};
		
		httpService.post({url:'./equipment/selectErrorEquipment',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.equipments = data.data.data.pageList;
			console.log($scope.equipments)
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.equipments.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
		})
	};
	$scope.initTable();

	//地域
	$scope.error = {};
	$scope.division = division;
	$scope.c = function () {
	   $scope.error.province = false;
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected2 = "";
	   $scope.selected3 = "";
	   $scope.queryProjects();
	};
	$scope.c2 = function () {       
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected3 = "";
	   $scope.queryProjects();
	};
	$scope.c3 = function () {
	   $scope.error.area = false;
	   $scope.queryProjects();
	};
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
	
	$scope.ifshow = false; 
	$scope.deviceId;
    // 处理详情
    $scope.handleInfo = function (idNum) {
    	var param = {
    		'equipmentId':idNum
    	}
    	httpService.post({url:'./equipment/selectEquipInfoAndData',data:param,showSuccessMsg:false}).then(function(data) {
    		$scope.ifshow = true;
    		$scope.deviceId = idNum;
    		$scope.projectInfo = data.data;
    	});
    }
    //查看记录
    $scope.faultRecord = function (idNum) {
    	$state.go('app.faultRecordInfo',{id:idNum,typeId:1});
    }
    //详情提交
    $scope.confirmBtn = function () {
    	var handleCon = $('#handleCon').val();
    	var param = {
    		eqId:$scope.deviceId,
    		handleCon:handleCon,
    		eqType:1
    	}
    	if(handleCon.replace(/(^\s*)|(\s*$)/g,"").length!=0){
	    	httpService.post({url:'./errHandle/handlingErrors',data:param,showSuccessMsg:false}).then(function(data) {
	    		$scope.ifshow = false;
	    		$('#handleCon').val('');
	    		$scope.initTable();
	    	});
    	}else{
    		alert('请输入内容！');
    	}
    	
    }


    /*$scope.confirmAdd = function(){
		var dealStatusDtos = [];
		angular.forEach(angular.element.find(".error"), function(dom){
    		var a =  {
    			id :angular.element(dom).find(".id").attr("data-aa"),
    			dealStatus : angular.element(dom).find(".delType").find("select").val(),
    			dealDetail : angular.element(dom).find(".content").find("textarea").val()
    		}
    		dealStatusDtos.push(a)
		});
		httpService.post({url:'./hiddenDangerEdit/updateDealStatus',data:dealStatusDtos,showSuccessMsg:true}).then(function(data) {  
    		$scope.ifshow = false;
    	})
	}*/
})
