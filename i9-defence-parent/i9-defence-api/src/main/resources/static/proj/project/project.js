var projectNgModule=angular.module('projectNgModule',['ngAnimate','ui.bootstrap','app']);
var projectService = projectNgModule.factory('projectService',
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
var projectNgControl=projectNgModule.controller('projectNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,projectService,httpService){
	//分页条件
	$scope.pageSize = 6;
	$scope.currentPage = 1;
	//初始化
	$scope.initTable = function (){
		var text = $scope.searchText;
		if($scope.selected == null || $scope.selected == ''){
			$scope.selected ={
				name : ''
			}
		}
		if($scope.selected2 == null || $scope.selected2 == ''){
			$scope.selected2 ={
				name : ''
			}
		}
		if($scope.selected3 == null || $scope.selected3 == ''){
			$scope.selected3 ={
				value : ''
			}
		}
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				projectName : text,
				projectAddress : text,
				projectProvince : $scope.selected.name,
				projectCity : $scope.selected2.name,
				projectCounty : $scope.selected3.value,
				orderByClause : "projectDate desc"
			};
		//console.log(JSON.stringify(pageParam));
		httpService.post({url:'./project/pageProject',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.projects = data.data.data.pageList;
			console.log(JSON.stringify($scope.projects));
			// console.log(JSON.stringify($scope.projects));
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.projects.length;
			$scope.pages = data.data.data.loopPageNum;
			$scope.currentPage = pageParam.currentPage;
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
	
	$scope.add = function () {  
		httpService.post({url:'./project/getClientByDistributorId',showSuccessMsg:false}).then(function(data) {
			$scope.clientList = data.data.clientList;
			var modalInstance = $modal.open({  
	            templateUrl: 'proj/project/add.html',  
	            controller: 'projectEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load('ui.select').then(
	        			    function(){
	        			        return $ocLazyLoad.load('proj/project/select.js');
	        			    }
	        			);
	        			
	        		}],
	        		project: function () {  
	                    return {};  
	                },
	                clientList: function () {  
	                    return $scope.clientList;  
	                },
	                safeList: function () {  
	                    return {}; 
	                },
	            }  
	        }); 
	        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
		})     
    };  
    //编辑
    $scope.edit = function (ids) { 
    	var projectId = ids.split(',')[0];
    	var distributorId = ids.split(',')[1];
    	var projectGetDto = {
    			projectId:projectId,
    			distributorId:distributorId
    	}
    	httpService.post({url:'./project/getProject',data:JSON.stringify(projectGetDto),showSuccessMsg:false}).then(function(data) {  
    		$scope.project = data.data.project;
    		$scope.clientList = data.data.clientList;
    		$scope.safeList = data.data.safeList;
    		//console.log(JSON.stringify($scope.clientList));
			var modalInstance = $modal.open({  
	            templateUrl: 'proj/project/add.html',  
	            controller: 'projectEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	            		return $ocLazyLoad.load('ui.select').then(
	        			    function(){
	        			        return $ocLazyLoad.load('proj/project/select.js');
	        			    }
		        		);
	        		}],
	            	project: function () {  
	                    return $scope.project;  
	                },
	                clientList: function () {  
	                    return $scope.clientList;  
	                },
	                safeList: function () {  
	                    return $scope.safeList;  
	                },
	            }  
	        });
			modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(reason){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
    	})
    };  
    
    
    //邀请码
    $scope.shareCode = function () { 
    	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	if ($scope.delArray.length != 1){
    		$.toaster({
				title : "Error",
				priority : "danger",
				message : "请选择一个!"
			});
    		return false;
    	}
    	httpService.post({url:'./project/getCode',data:$scope.delArray[0],showSuccessMsg:false}).then(function(data) {  
    		$scope.code = data.data.data;
			var modalInstance = $modal.open({  
	            templateUrl: 'proj/project/code.html',  
	            controller: 'projectCodeCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'projectCodeNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/project/code.js',
	        				]
	        			});
	        		}],
	            	code: function () {  
	                    return $scope.code;  
	                },
	            }  
	        });
    	})
    };  
    
    
    $scope.del = function(){
    	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	confirm("确定删除吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./project/applyDelProject',data:$scope.delArray,showSuccessMsg:false}).then(function(data) {  
            	$scope.msg=data.data.msg;
            	$.toaster({
            		title : "Success",
					priority : "success",
					message : $scope.msg
            	});
            	$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    }
    
    $scope.changeStatus = function(id,status) {
    	var pageParam = {
    		 id : id,
    		 projectState : status,
    	};
    	httpService.post({url:'./project/updateProject',data:pageParam,showSuccessMsg:false}).then(function(data) {  
    		$scope.initTable();
    	})
	};
    
  //地域
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
	// 窗口适应
	function resizeWin() {
	    var domHeight = $(window).height();
	    var bodyHeight = domHeight-335;
	    $('#myTableBody').height(bodyHeight);
	}
	resizeWin()
	$(window).resize(function () {
	    resizeWin();
	})
})
