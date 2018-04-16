var eqCategoryNgModule=angular.module('eqCategoryNgModule',['ngAnimate','ui.bootstrap','app']);
var eqCategoryService = eqCategoryNgModule.factory('eqCategoryService',
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
var eqCategoryNgControl=eqCategoryNgModule.controller('eqCategoryNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,eqCategoryService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	//初始化
	$scope.initTable = function (){
		var text = $scope.searchText;
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				eqCategoryName : text,
			};
		
		httpService.post({url:'./eqCategory/pageEqCategory',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.eqCategorys = data.data.data.pageList;
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.eqCategorys.length;
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
	
	//添加通道
	$scope.addPassageway = function (id) {  
	    var modalInstance = $modal.open({  
	        templateUrl: 'proj/eqCategory/addPassageway.html',  
	        controller: 'addPassagewayEditCtrl', 
	        backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	        resolve: {  
	            deps : ['$ocLazyLoad',function($ocLazyLoad) {
	                return $ocLazyLoad.load({
	                    name : 'addPassagewayEditNgModule',
	                    insertBefore : '#ng_load_plugins_before',
	                    files : [
	                             'proj/eqCategory/addPassageway.js',
	                    ]
	                });
	            }],
	            addPassageway: function () {  
	                return id;  
	            },
	            clientList: function () {  
	                return {}; 
	            },
	        }  
	    }); 
	    modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	        $scope.selected = data;
	    },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        $scope.initTable();
	    });
	     
	};

	$scope.add = function () {  
        var modalInstance = $modal.open({  
            templateUrl: 'proj/eqCategory/add.html',  
            controller: 'eqCategoryEditCtrl', 
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name : 'eqCategoryEditNgModule',
        				insertBefore : '#ng_load_plugins_before',
        				files : [
        				         'proj/eqCategory/add.js',
        				]
        			});
        		}],
        		eqCategory: function () {  
                    return {};  
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
            $scope.selected = data;
        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
        	$scope.initTable();
        });
         
    };  
    //编辑
    $scope.edit = function (id) { 
    	httpService.post({url:'./eqCategory/getEqCategory',data:id,showSuccessMsg:false}).then(function(data) {  
    		$scope.eqCategory = data.data.data;
			var modalInstance = $modal.open({  
	            templateUrl: 'proj/eqCategory/add.html',  
	            controller: 'eqCategoryEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'eqCategoryEditNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/eqCategory/add.js',
	        				]
	        			});
	        		}],
	        		eqCategory: function () {  
	                    return $scope.eqCategory;  
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
    $scope.del = function(){
    	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	console.log(JSON.stringify($scope.delArray));
    	confirm("确定删除吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./eqCategory/delEqCategory',data:$scope.delArray,showSuccessMsg:true}).then(function(data) {  
            		$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    }
})
