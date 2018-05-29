var connectLogNgModule=angular.module('connectLogNgModule',['ngAnimate','ui.bootstrap','app']);
var connectLogService = connectLogNgModule.factory('connectLogService',
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
var connectLogNgControl=connectLogNgModule.controller('connectLogNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,connectLogService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	//初始化
	$scope.initTable = function (){
		var text = $scope.searchText;
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				status : $scope.status,
				orderByClause : "createTime desc"
			};
		
		httpService.post({url:'./connectLog/pageConnectLog',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.connectLogs = data.data.data.pageList;
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.connectLogs.length;
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
	
	 $scope.changeType = function(){
	    	if($scope.status){
	    		$scope.status = $scope.status;
	    	}else{
	    		$scope.status = "";
	    	}
	    	$scope.initTable();
	    }
	
	$scope.add = function () {
		httpService.post({url:'./connectLog/findconnectLogSystemCategory',showSuccessMsg:false}).then(function(data) {  
			$scope.eqSystemCategorys = data.data.eqSystemCategory;
			$scope.eqCategorys = data.data.eqCategory;
			$scope.projects = data.data.projects;
			var modalInstance = $modal.open({ 
	            templateUrl: 'proj/connectLog/add.html',  
	            controller: 'connectLogEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'connectLogEditNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/connectLog/add.js',
	        				]
	        			});
	        		}],
	        		connectLog: function () {  
	                    return {};  
	                },
	                eqSystemCategorys: function () {  
	                    return $scope.eqSystemCategorys;  
	                },
	                equCategorys: function () {  
	                    return null;  
	                },
	                eqCategorys: function () {  
	                    return $scope.eqCategorys;  
	                },
	                projects: function () {  
	                    return $scope.projects;  
	                },
	            }  
	        }); 
	        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
		});
	};
    //删除
    $scope.del = function(){
    	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	confirm("确定删除吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./connectLog/applyDelconnectLog',data:$scope.delArray,showSuccessMsg:false,msg:true}).then(function(data) {  
            	$scope.msg=data.data.msg;
//            	console.log($scope.msg);
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
    //编辑
    $scope.edit = function (id) { 
    	httpService.post({url:'./connectLog/getconnectLog',data:id,showSuccessMsg:false}).then(function(data) {  
    		$scope.connectLog = data.data.data;
    		$scope.equCategorys = data.data.equCategory;
//    		console.log(JSON.stringify($scope.equCategorys));
//    		console.log(JSON.stringify(data.data.data));
    		httpService.post({url:'./connectLog/findconnectLogSystemCategory',showSuccessMsg:false}).then(function(data) {  
    			$scope.eqSystemCategorys = data.data.eqSystemCategory;
    			$scope.projects = data.data.projects;
			    var modalInstance = $modal.open({  
	            templateUrl: 'proj/connectLog/add.html',  
	            controller: 'connectLogEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'connectLogEditNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/connectLog/add.js',
	        				]
	        			});
	        		}],
	        		connectLog: function () {  
	                    return $scope.connectLog;  
	                },
	                equCategorys: function () {  
	                    return $scope.equCategorys;  
	                },
	                eqSystemCategorys: function () {  
	                    return $scope.eqSystemCategorys;  
	                },
	                projects: function () {  
	                    return $scope.projects;  
	                },
	            }  
	        });
			modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(reason){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
    	})
      });
    };  
    // 窗口适应
    function resizeWin() {
        var domHeight = $(window).height();
        var bodyHeight = domHeight-280;
        $('#myTableBody').height(bodyHeight);
    }
    resizeWin()
    $(window).resize(function () {
        resizeWin();
    })
})
