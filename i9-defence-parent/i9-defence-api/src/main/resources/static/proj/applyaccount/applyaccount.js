var applyAccountNgModule=angular.module('applyAccountNgModule',['ngAnimate','ui.bootstrap','app']);
var applyAccountService = applyAccountNgModule.factory('applyAccountService',
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
var applyAccountNgControl=applyAccountNgModule.controller('applyAccountNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,applyAccountService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	//初始化
	$scope.initTable = function (){
		var text = $scope.searchText;
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				roleName : $scope.selectRoleName,
				area : text,
			};
		
		httpService.post({url:'./managerApply/pageManagerApply',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.applyAccounts = data.data.data.pageList;
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.applyAccounts.length;
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
    
    $scope.agree = function(){
    	$scope.agreeArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.agreeArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	confirm("确定同意吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./managerApply/agreeManagerApply',data:$scope.agreeArray,showSuccessMsg:true}).then(function(data) {  
            		$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    }
    
    
    $scope.del = function(){
    	$scope.agreeArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.agreeArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	confirm("确定删除吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./managerApply/delManagerApply',data:$scope.agreeArray,showSuccessMsg:true}).then(function(data) {  
            		$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    }
    
    $scope.refuse = function(){
    	$scope.agreeArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.agreeArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	var modalInstance = $modal.open({  
            templateUrl: 'proj/applyaccount/add.html',  
            controller: 'applyaccountEditCtrl', 
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name : 'applyaccountEditNgModule',
        				insertBefore : '#ng_load_plugins_before',
        				files : [
        				         'proj/applyaccount/add.js',
        				]
        			});
        		}],
        		ids: function () {  
                    return $scope.agreeArray;  
                },
                content: function () {  
                    return null;  
                },
                edit: function () {  
                    return 0;  
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
            $scope.selected = data;
        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
        	$scope.initTable();
        });
    }
    
    $scope.reason = function(id){
    	httpService.post({url:'./managerApply/getManagerApply',data:id,showSuccessMsg:false}).then(function(data) {  
    		var modalInstance = $modal.open({  
                templateUrl: 'proj/applyaccount/add.html',  
                controller: 'applyaccountEditCtrl', 
                backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
                resolve: {  
                	deps : ['$ocLazyLoad',function($ocLazyLoad) {
            			return $ocLazyLoad.load({
            				name : 'applyaccountEditNgModule',
            				insertBefore : '#ng_load_plugins_before',
            				files : [
            				         'proj/applyaccount/add.js',
            				]
            			});
            		}],
            		content: function () {  
                        return data.data.data.refuseContent;  
                    },
                    ids: function () {  
                        return null;  
                    },
                    edit: function () {  
                        return 1;  
                    },
                }  
            });
    		
    	})
    }
})
