/**
 * 事件查看js
 */
var eventModule=angular.module('eventModule',['ngAnimate','ui.bootstrap','app']);
var eventService = eventModule.factory('eventService',
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
var eventControl=eventModule.controller('eventControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,eventService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	$scope.statusText = '';
	$scope.equipId =0;
	$scope.eqType=0;
	
    $scope.dateToString = function(d){
    	var date = new Date(d);
    	return date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
    }
	
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
			$scope.searchText = "";
		}
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				projectId : $scope.searchText,
				projectAddress : $scope.searchText,
				eqCategoryName : $scope.eqCategoryName,
				orderByClause: 'warningCount desc'
			};
		httpService.post({url:'./hiddenDangerEdit/pageHiddenDangerEdit2',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.hiddenEdits = data.data.data.pageList;
			$scope.equipmentCategorys = data.data.equipmentCategory;
			$scope.projects = data.data.project;
			for(i in $scope.hiddenEdits){
				if($scope.hiddenEdits[i].warningCount>0){
					$scope.hiddenEdits[i].status = 'danger';
					$scope.hiddenEdits[i].statusText = '报警';
				}else if($scope.hiddenEdits[i].hiddeCount>0){
					$scope.hiddenEdits[i].status = 'warning';
					$scope.hiddenEdits[i].statusText = '隐患';
				}else if ($scope.hiddenEdits[i].status == 0){
    				$scope.hiddenEdits[i].status = 'lineOutLabel';
    				$scope.hiddenEdits[i].statusText = '离线';
    			}
			}
			
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.hiddenEdits.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
    		if($scope.hiddenEdits.length>0){
				$scope.idNum = $scope.hiddenEdits[0].id;
				$scope.passagewayInit();
			}else{
				$scope.projectInfo = {};
				$scope.chartsStatus = false;
			}
		})
	};
	
	    $scope.passagewayInit = function (){
	    	var pageParam = {
	    			equipmentId:$scope.idNum,
	    			startDateString:$scope.dateToString($("#startTime").val()),
	    			endDateString:$scope.dateToString($("#endTime").val()),
	    			/*projectName : text,
	    			projectAddress : text,*/
	    		};
	    	
	    	httpService.post({url:'./equipment/selectEquipInfoAndData',data:pageParam,showSuccessMsg:false}).then(function(data) {  
	    		$scope.equipmentInfo = data.data.data;
	    		$scope.equipment = data.data.equip;
	    		$scope.dataAndManager = data.data.dataAndManager;
	    		$scope.equipmentCheckArr = [];
	            $scope.equipmentItemArr = [];
	    	})
	    };
	
	    $scope.dealEq = function() { 
	    	$scope.EquipId =$scope.equipId;
	    	var handleCon = $('#handleCon').val();
	    	if('' !=$scope.statusText && null != $scope.statusText){
	    		if('报警' == $scope.statusText){
	    			$scope.eqType = 2;
	    		}else if('隐患' == $scope.statusText){
	    			$scope.eqType = 3;
	    		}
	    	}
	    	var pageParam = {
	    			handleCon :handleCon,
	    			eqId  :  $scope.EquipId,
	    			eqType : $scope.eqType
	    	}
	    	httpService.post({url:'./errHandle/handlingErrors',data:pageParam,showSuccessMsg:true}).then(function(data) {
    		
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
	
	// 地域
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
    //查看记录
    $scope.faultRecord = function (idNum2,statuText) {
    	if(statuText=='报警'){
    		$state.go('app.faultRecordInfo',{id:idNum2,typeId:2});
    	}else if(statuText=='隐患'){
    		$state.go('app.faultRecordInfo',{id:idNum2,typeId:3});
    	}
    }
    //编辑
    $scope.edit = function (idNum,statusText) { 
    	$scope.idNum = idNum;
    	$scope.equipId = idNum;
    	$scope.statusText = statusText;
    	$scope.passagewayInit();    	 
    };  
    
})
