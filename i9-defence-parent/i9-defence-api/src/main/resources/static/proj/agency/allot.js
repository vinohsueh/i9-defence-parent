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
                    oHtmlLeft = "";
                    oHtmlLeft+="<li><span class='name' data-id='"+manager1.id+"'>"+manager1.username+"</span></li>";
                    // $('#leftTab').append(oHtmlLeft);
                });
            }else{
                oHtmlLeft = "未被分配的经销商  无建立关系的经销商 为空";
                // $('#leftTab').append(oHtmlLeft);
            }
            $('#leftTab').html(oHtmlLeft);
            //当前经销商的下属
            if(agencyRightList.agencyList.length > 0){
                angular.forEach(agencyRightList.agencyList,function(manager1){
                    oHtmlRight = "";
                    oHtmlRight+="<li><span class='name' data-id='"+manager1.id+"'>"+manager1.username+"</span></li>";
                    // $('#rightTab').append(oHtmlRight);
                });
            }else{
                oHtmlRight="<li><span class='name'>此经销商为三级，无下级</span></li>";
                // $('#rightTab').append(oHtmlRight);
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
        if($(this).parent().prop('id') == 'leftTab'){
            $(this).siblings().removeClass('active');
        }
        $(this).toggleClass('active');
    });

    $(document).on('click','.ico-select-m',function () {
        var arrays = [];
        $('.choice-list-n>li').each(function () {
            if($(this).hasClass('active')){
                arrays.push($(this).find("span").attr('data-id'));
                var params = {
                    'managerIdS' : arrays,
                    'parentId' : param,
                };
                console.log(JSON.stringify(params));
                httpService.post({url:'./agency/insertAgency',data:params,showSuccessMsg:true}).then(function(data) {
                    console.log(JSON.stringify(data.data.data));
                });

                $(this).appendTo($('.choice-list-y')).removeClass('active');
                
            }
        })
    });

    $(document).on('click','.ico-cancle-m',function () {
        $('.choice-list-y>li').each(function () {
            if($(this).hasClass('active')){
                var managerId = $(this).find("span").attr('data-id');
                /*var params = {
                    'managerId' : managerId,
                    'parentId' : param,
                };
                httpService.post({url:'./agency/deleteAgencyById',data:params,showSuccessMsg:true}).then(function(data) {
                    console.log(JSON.stringify(data.data.data));
                });
                $(this).appendTo($('.choice-list-n')).removeClass('active');*/
                $scope.checkList(managerId);
            }
        })
    });

    $scope.checkList = function (id) {
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
