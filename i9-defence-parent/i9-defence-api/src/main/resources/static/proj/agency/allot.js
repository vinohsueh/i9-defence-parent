var agencyEditNgModule = angular.module('agencyEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var agencyEditCtrl = agencyEditNgModule.controller('agencyEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster, httpService,param) {

    $scope.id = param;
	console.log($scope.id);
    httpService.post({url:'./agency/allotAgency',data:$scope.id,showSuccessMsg:false}).then(function(data) {
    	var agencyLeftList = data.data.data1;
        var agencyRightList = data.data.data2;
        var oHtmlLeft = "";
        var oHtmlRight = "";
        //未被分配的经销商  无建立关系的经销商
		if(agencyLeftList.length > 0){
            angular.forEach(agencyLeftList,function(manager1){
                oHtmlLeft = "";
                oHtmlLeft="<li><span class='name' data-id='"+manager1.id+"'>"+manager1.username+"</span></li>";
                $('#leftTab').append(oHtmlLeft);
            });
		}else{
			oHtmlLeft = "未被分配的经销商  无建立关系的经销商 为空";
			$('#leftTab').append(oHtmlLeft);
		}

        //当前经销商的下属
		if(agencyRightList.agencyList.length > 0){
			angular.forEach(agencyRightList.agencyList,function(manager1){
				oHtmlRight = "";
				oHtmlRight="<li><span class='name' data-id='"+manager1.id+"'>"+manager1.username+"</span></li>";
				$('#rightTab').append(oHtmlRight);
			});
		}else{
			oHtmlRight="<li><span class='name'>此经销商为三级，无下级</span></li>";
			$('#rightTab').append(oHtmlRight);
		}

    });

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
    $(document).on('click','.choice-list>li',function () {
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else {
            $(this).addClass('active');
        }
    });

    $(document).on('click','.ico-select-m',function () {
        var arrays=[];
        $('.choice-list-n>li').each(function () {
            if($(this).hasClass('active')){
                console.log($(this).find("span").attr('data-id'));
                arrays.push($(this).find("span").attr('data-id'));
                console.log(JSON.stringify(arrays));
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
        var managerId = "";
        $('.choice-list-y>li').each(function () {
            if($(this).hasClass('active')){
                managerId = $(this).find("span").attr('data-id');
                var params = {
                    'managerId' : managerId,
                    'parentId' : param,
                };
                httpService.post({url:'./agency/deleteAgencyById',data:params,showSuccessMsg:true}).then(function(data) {
                    console.log(JSON.stringify(data.data.data));
                });
                $(this).appendTo($('.choice-list-n')).removeClass('active');
            }
        })
    });
});
