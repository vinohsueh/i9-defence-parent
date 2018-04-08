var pageUrlNgModule=angular.module('pageUrlNgModule',['ngAnimate','ui.bootstrap','app']);
var pageUrlNgControl=pageUrlNgModule.controller('pageUrlNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,httpService,$sce){
	$scope.initTable = function(){
		$http.post('./page/getAllFirstPages').then(function (data) {
			  $scope.pages = data.data.data.urls;
			  $scope.table="";
			  angular.forEach($scope.pages,function(data){
				  data = changeToBlank(data);
				  var str = "";
				  if (data.items.length == 0){
					  str = "<tr><td onclick='angular.element(this).scope().edit("+data.id+")'>"+data.name+"</td><td>"+data.code+"</td><td>"+data.icon+"</td><td><Button class='btn m-b-xs w-xs btn-success btn-rounded' onclick='angular.element(this).scope().addChild("+data.id+")'>添加</Button></td></tr>";
				  }else{
					  var child = "";
					  angular.forEach(data.items,function(item){
						  item = changeToBlank(item);
						  var str = "<tr><td onclick='angular.element(this).scope().edit("+item.id+")' class= 'childPage'>"+item.name+"</td><td>"+item.code+"</td><td>"+item.icon+"</td><td></td></tr>";
						  child += str;
					  })
					  str = "<tr>"
					  		+"<td onclick='angular.element(this).scope().edit("+data.id+")'>"+data.name+"</td><td>"+data.code+"</td><td>"+data.icon+"</td><td><Button class='btn m-b-xs w-xs btn-success btn-rounded' onclick='angular.element(this).scope().addChild("+data.id+")'>添加</Button></td></tr>"+child;
				  }
				  $scope.table+=str;
			  })
			  $("#body").html($scope.table)
	    });
	}
	
	
	function changeToBlank(data){
		if (data.name == null) {
		  data.name = "";
	    }
	    if (data.code == null) {
		  data.code = "";
	    }
	    if (data.icon == null) {
		  data.icon = "";
	    }
	    return data;
	}
	$scope.initTable();
	//编辑
    $scope.edit = function (id) {  
    	httpService.post({url:'./page/getPage',data:id,showSuccessMsg:false}).then(function(data) {  
    		$scope.page = data.data.data;
			var modalInstance = $modal.open({  
	            templateUrl: '/proj/page/add.html',  
	            controller: 'pageEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'pageEditNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/page/add.js',
	        				]
	        			});
	        		}],
	        		page: function () {  
	                    return $scope.page;  
	                },
	                parentId : function(){
	                	return null;
	                }
	            }  
	        });
			modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(reason){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
    	})
    };  
    
    $scope.addChild = function (id) {  
			var modalInstance = $modal.open({  
	            templateUrl: '/proj/page/add.html',  
	            controller: 'pageEditCtrl', 
	            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
	            resolve: {  
	            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
	        			return $ocLazyLoad.load({
	        				name : 'pageEditNgModule',
	        				insertBefore : '#ng_load_plugins_before',
	        				files : [
	        				         'proj/page/add.js',
	        				]
	        			});
	        		}],
	        		page: function () {  
	                    return {};  
	                },
	                parentId : function(){
	                	return id;
	                }
	            }  
	        });
			modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
	            $scope.selected = data;
	        },function(reason){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
	        	$scope.initTable();
	        });
    };  
})