/**
 * 类型故障js
 */
var equipmentFaultModule=angular.module('equipmentFaultModule',['ngAnimate','ui.bootstrap','app']);
var equipmentFaultService = equipmentFaultModule.factory('equipmentFaultService',
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
var equipmentFaultControl=equipmentFaultModule.controller('equipmentFaultControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,equipmentFaultService,httpService){
	//分页条件
	$scope.pageSize = 10;
	$scope.currentPage = 1;
	//初始化
	$scope.initTable = function (){
		var text = $scope.searchText;
		var pageParam = {
				pageSize:$scope.pageSize,
				currentPage:$scope.currentPage,
				//username : $scope.searchText
				name : text,
				code : text,
			};
		console.log(pageParam)
		httpService.post({url:'./equipmentFault/pageEquipmentFault',data:pageParam,showSuccessMsg:false}).then(function(data) {  
			$scope.equipmentFaults = data.data.data.pageList;
			$scope.hasPrevious = data.data.data.hasPrevious;
			$scope.currentPage = data.data.data.currentPage;
			$scope.hasNext = data.data.data.hasNext;
			$scope.total = data.data.data.totalSize;
			$scope.start = data.data.data.offset+1;
			$scope.end = data.data.data.offset+$scope.equipmentFaults.length;
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
		httpService.post({url:'./equipment/findEquipmentSystemCategory',showSuccessMsg:false}).then(function(data) {  
			$scope.eqSystemCategorys = data.data.eqSystemCategory;
			//$modalInstance.dismiss('cancel')
			  var modalInstance = $modal.open({  
		            templateUrl: 'proj/equipmentFault/add.html',  
		            controller: 'equipmentFaultEditCtrl', 
		            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
		            resolve: {  
		            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
		        			return $ocLazyLoad.load({
		        				name : 'equipmentFaultEditNgModule',
		        				insertBefore : '#ng_load_plugins_before',
		        				files : [
		        				         'proj/equipmentFault/add.js',
		        				]
		        			});
		        		}],
		        		equipmentFault: function () {  
		                    return {};  
		                },
		                eqSystemCategorys: function () {  
		                    return $scope.eqSystemCategorys;  
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
    $scope.edit = function (id) { 
    	httpService.post({url:'./equipmentFault/getById',data:id,showSuccessMsg:false}).then(function(data) {  
    		$scope.equipmentFault = data.data.data;
    		$scope.equipmentFault.activationOneStr = data.data.activationOneStr;
    		$scope.equipmentFault.activationTwoStr = data.data.activationTwoStr;
    		httpService.post({url:'./equipment/findEquipmentSystemCategory',showSuccessMsg:false}).then(function(data) {  
    			$scope.eqSystemCategorys = data.data.eqSystemCategory;
	    		//$scope.equipmentCategory = data.data.equipmentCategory;
				var modalInstance = $modal.open({  
		            templateUrl: 'proj/equipmentFault/add.html',  
		            controller: 'equipmentFaultEditCtrl', 
		            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
		            resolve: {  
		            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
		        			return $ocLazyLoad.load({
		        				name : 'equipmentFaultEditNgModule',
		        				insertBefore : '#ng_load_plugins_before',
		        				files : [
		        				         'proj/equipmentFault/add.js',
		        				]
		        			});
		        		}],
		        		equipmentFault: function () {  
		                    return $scope.equipmentFault;  
		                },
		                eqSystemCategorys: function () {  
		                    return $scope.eqSystemCategorys;  
		                },
	//	                equipmentCategory: function () {  
	//	                    return $scope.equipmentCategory;  
	//	                },
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
    $scope.del = function(){
    	$scope.delArray = [];
    	angular.forEach(angular.element.find(".o-checks"), function(dom){
    		if(angular.element(dom).prop("checked") == true){
    			$scope.delArray.push(angular.element(dom).attr("data-id"))
    		}
		});
    	confirm("确定删除吗?", "", function (isConfirm) {
            if (isConfirm) {
            	httpService.post({url:'./equipmentFault/deleteBatch',data:$scope.delArray,showSuccessMsg:true}).then(function(data) {  
            		$scope.initTable();
            	})
            } else {
            }
        }, {confirmButtonText: '确定', cancelButtonText: '取消', width: 400});
    }
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
