var agencyEditNgModule = angular.module('agencyEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var agencyEditCtrl = agencyEditNgModule.controller('agencyEditCtrl', function($scope,
		$rootScope,$modal, $modalInstance, $cookieStore, $http, $window, toaster, httpService,param) {

    $scope.id = param;
    //初始化
    $scope.pageInit = function () {
        httpService.post({url:'./agency/allotAgency',data:$scope.id,showSuccessMsg:false}).then(function(data) {
            var agencyLeftList = data.data.data1;
            var agencyRightList = data.data.data2;
            var oHtmlLeft = "";
            var oHtmlRight = "";
            //未被分配的经销商  无建立关系的经销商
            if(agencyLeftList.length > 0){
                angular.forEach(agencyLeftList,function(manager1){
                    oHtmlLeft+="<li><span class='name' data-id='"+manager1.id+"'>"+manager1.username+"</span></li>";             
                });
            }else{
                oHtmlLeft = "未被分配的经销商  无建立关系的经销商 为空";
            }
            $('#leftTab').html(oHtmlLeft);
            //当前经销商的下属
            if(agencyRightList.agencyList.length > 0){
                angular.forEach(agencyRightList.agencyList,function(manager1){
                    oHtmlRight+="<li><span class='name' data-id='"+manager1.id+"'>"+manager1.username+"</span></li>";
                });
            }else{
                oHtmlRight="此经销商为三级，无下级";
            }
            $('#rightTab').html(oHtmlRight)
        });
    }
    $scope.pageInit();

	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	};

	// 确认添加
	$scope.confirmAdd = function() {
		httpService.post({url:'./agency/addAgency',data:$scope.agency,showSuccessMsg:true}).then(function(data) {
			$modalInstance.dismiss('cancel');
		})
	};
    //备选已选
    $(document).unbind( "click" ).on('click','.choice-list>li',function () {
        if($(this).parent().prop('id') == 'rightTab'){
            $(this).siblings().removeClass('active');
        }
        $(this).toggleClass('active');
    });

    $(document).on('click','.ico-select-m',function () {
        var arrays = [];
        $('.choice-list-n>li').each(function () {
            if($(this).hasClass('active')){
                arrays.push($(this).find("span").attr('data-id'));   
                $(this).appendTo($('.choice-list-y')).removeClass('active');  
            }
        })
        var params = {
            'managerIdS' : arrays,
            'parentId' : param,
       };      
        httpService.post({url:'./agency/insertAgency',data:params,showSuccessMsg:true}).then(function(data) {
        	$modalInstance.dismiss('cancel');
            console.log(JSON.stringify(data.data.data));
        });
    });

    $(document).on('click','.ico-cancle-m',function () {
        $('.choice-list-y>li').each(function () {
            if($(this).hasClass('active')){
                var managerId = $(this).find("span").attr('data-id');
                //先判断 此操作对象 是否为三级  是的话  不走以下方法  /agency/deleteAgencyById',d
                httpService.post({url:'./agency/selectByAgencyId',data:managerId,showSuccessMsg:true}).then(function(data) {
                	var manager = data.data.data;
                	var agencyList = manager.agencyList;
                	if(agencyList.length > 0){
                		//说明此二级经销商下 有三级经销商  设计到三级怎么分配问题  
                		$scope.checkList(managerId);
                	}else{
                		//说明此二级经销商下  没有三级经销商   直接撤销此 经销商
                		var params = {
	                        'managerId' : managerId,
	                        'parentId' : param,
	                    };
                		httpService.post({url:'./agency/deleteAgencyById',data:params,showSuccessMsg:true}).then(function(data) {
                            console.log(JSON.stringify(data.data.data));
                        });
	                    $(this).appendTo($('.choice-list-n')).removeClass('active');
                	}
                });
            }
        }) 
    });

    $scope.checkList = function (id) {
    	//father 第一种情况  给此操作对象换个一级 
        var param = {
            id:id,
            statu:'father'
        }
        var modalInstance = $modal.open({  
            templateUrl: 'proj/agency/checkList.html',
            controller: 'checkListCtrl',
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
                deps : ['$ocLazyLoad',function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name : 'checkListNgModule',
                        insertBefore : '#ng_load_plugins_before',
                        files : [
                                 'proj/agency/checkList.js',
                        ]
                    });
                }],
                param: function () {
                    return param;
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数

        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
            $scope.pageInit();
        });
         
    };
});
