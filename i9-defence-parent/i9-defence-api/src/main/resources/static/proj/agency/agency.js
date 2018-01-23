var agencyNgModule=angular.module('agencyNgModule',['ngAnimate','ui.bootstrap','app']);
var agencyService = agencyNgModule.factory('agencyService',
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
var agencyNgControl=agencyNgModule.controller('agencyNgControl',function($rootScope, $scope,$stateParams,  $log, $http, $window, $state,$modal, toaster,agencyService,httpService){
	//初始化
	$scope.initTable = function (){
		
		httpService.post({url:'./agency/pageAgency',showSuccessMsg:false}).then(function(data) {
			$scope.agencys = data.data.data;
			var agencys = $scope.agencys;
			var oHtml = "";
            angular.forEach(agencys,function(manager1){
                oHtml='';
                oHtml="<tr class='oneLever'><td>1</td><td>"+manager1.username+"</td><td>"+manager1.name+"</td><td>"+manager1.area+"</td><td>"+manager1.address+"</td><td>"+manager1.phone+"</td></tr>";
                $('#agencyTable').append(oHtml);
                if(manager1.agencyList != null){
                    angular.forEach(manager1.agencyList,function(manager2){
                        oHtml='';
                        oHtml="<tr class='twoLever'><td>2</td><td>"+manager2.username+"</td><td>"+manager2.name+"</td><td>"+manager2.area+"</td><td>"+manager2.address+"</td><td>"+manager2.phone+"</td></tr>";
                        $('#agencyTable').append(oHtml);
                        if(manager2.agencyList != null){
                            angular.forEach(manager2.agencyList,function(manager3){
                                oHtml='';
                                oHtml="<tr class='threeLever'><td>3</td><td>"+manager3.username+"</td><td>"+manager3.name+"</td><td>"+manager3.area+"</td><td>"+manager3.address+"</td><td>"+manager3.phone+"</td></tr>";
                                $('#agencyTable').append(oHtml);
                            });
                        }
					});
				}
            });

		});
	};
	$scope.initTable();

	
	$scope.add = function () {  
        var modalInstance = $modal.open({  
            templateUrl: 'proj/agency/add.html',
            controller: 'agencyEditCtrl',
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name : 'agencyEditNgModule',
        				insertBefore : '#ng_load_plugins_before',
        				files : [
        				         'proj/agency/add.js',
        				]
        			});
        		}],
        		agency: function () {
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
})
