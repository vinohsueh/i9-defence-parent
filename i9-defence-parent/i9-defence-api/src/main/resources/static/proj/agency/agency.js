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
			//经销商等级  0 管理员     1 2 3 分别对应经销商
			var grade = data.data.grade;
			var agencys = $scope.agencys;
			console.log(grade)
			var oHtml = "";
			if(agencys.length > 0){
                angular.forEach(agencys,function(manager1){
                    oHtml='';
                    var button0 = "<p style='height:34px;'></p>";
                    console.log(grade)
                    if (grade == 0) {
                    	button0 = "<button class='btn m-b-xs w-xs btn-success btn-rounded' onclick='angular.element(this).scope().allot("+manager1.id+")'>管理</button>";
                    }
                    
                    oHtml="<tr class='oneLever'><td>1</td><td>"+manager1.username+"</td><td>"+manager1.name+"</td><td>"+manager1.area+"</td><td>"+manager1.address+"</td><td>"+manager1.phone+"</td><td>"+button0+"</td></tr>";
                    $('#agencyTable').append(oHtml);
                    if(manager1.agencyList.length > 0){
                        angular.forEach(manager1.agencyList,function(manager2){
                            oHtml='';
                            //2级经销商的2级经销商   是总的三级经销商  
                            var button1 = "<p style='height:34px;'></p>";
                            if (grade < 1) {
                            	button1 = "<button class='btn m-b-xs w-xs btn-success btn-rounded' onclick='angular.element(this).scope().allot("+manager2.id+")'>管理</button>";
                            }
                            oHtml="<tr class='twoLever'><td>2</td><td>"+manager2.username+"</td><td>"+manager2.name+"</td><td>"+manager2.area+"</td><td>"+manager2.address+"</td><td>"+manager2.phone+"</td><td>"+button1+"</td></tr>";
                            $('#agencyTable').append(oHtml);
                            if(manager2.agencyList.length > 0){
                                angular.forEach(manager2.agencyList,function(manager3){
                                	//3级经销商  不可编辑
                                    var button2 = "<p style='height:34px;'></p>";
                                    oHtml='';
                                    oHtml="<tr class='threeLever'><td>3</td><td>"+manager3.username+"</td><td>"+manager3.name+"</td><td>"+manager3.area+"</td><td>"+manager3.address+"</td><td>"+manager3.phone+"</td><td>"+button2+"</td></tr>";
                                    $('#agencyTable').append(oHtml);
                                });
                            }
                        });
                    }
                });
			}else{
                oHtml='经销商列表为空';
                $('#agencyTable').append(oHtml);
			}
		});
	};
	$scope.initTable();
	
	$scope.allot = function (id) {
        $scope.id = id;
        var modalInstance = $modal.open({  
            templateUrl: 'proj/agency/allot.html',
            controller: 'agencyEditCtrl',
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
            	deps : ['$ocLazyLoad',function($ocLazyLoad) {
        			return $ocLazyLoad.load({
        				name : 'agencyEditNgModule',
        				insertBefore : '#ng_load_plugins_before',
        				files : [
        				         'proj/agency/allot.js',
        				]
        			});
        		}],
                param: function () {
                    return $scope.id;
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数

        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
            $('#agencyTable').html("");
			$scope.initTable();
        });
         
    };
})
