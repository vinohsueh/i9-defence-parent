var monitoringChartEditNgModule = angular.module('monitoringChartEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

app.filter('propsFilter', function() {
    return function(items, props) {
        var out = [];

        if (angular.isArray(items)) {
          items.forEach(function(item) {
            var itemMatches = false;

            var keys = Object.keys(props);
            for (var i = 0; i < keys.length; i++) {
              var prop = keys[i];
              var text = props[prop].toLowerCase();
              if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                itemMatches = true;
                break;
              }
            }

            if (itemMatches) {
              out.push(item);
            }
          });
        } else {
          // Let the output be the input untouched
          out = items;
        }

        return out;
    };
})
var monitoringChartEditCtrl = monitoringChartEditNgModule.controller('monitoringChartEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		monitoringChart,clientList,httpService,$timeout) {
	
	$scope.monitoringChart = monitoringChart;
	$scope.clientList = clientList;
  //初始化
  $scope.pageInit = function (){
    var pageParam = {
        id:$scope.monitoringChart,
      };
    console.log(JSON.stringify(pageParam));
    httpService.post({url:'./passageWay/selectPassagewaysByEquipId',data:pageParam,showSuccessMsg:false}).then(function(data) {  
      console.log(JSON.stringify(data));
      /*$scope.passagewayList = data.data.data.pageList;
      $scope.hiddenDanger = data.data;*/

    })
  };
  $scope.pageInit();
  for(i in $scope.passagewayList){
    var modelName = 'hidedenDanger'+$scope.passagewayList[i].passagewayNum;
    $scope[modelName] = $scope.passagewayList[i].hiddenDanger;
  }


	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
		/*if ($scope.monitoringChart.passagewayName ==null ||$scope.monitoringChart.passagewayName ==0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "项目名不能为空!"
			});
			return false;
		}*/
    var passagewayArr = [],
        passagewayObj = {};

    $('#passagewayBody tr').each(function () {
      var thisDom = $(this);
      passagewayObj.id = thisDom.attr('data-id');
      passagewayObj.passagewayNum = thisDom.find('.passagewayNum').text();
      passagewayObj.passagewayName = thisDom.find('.passagewayName input').val();
      passagewayObj.hidedenDanger = $scope['hidedenDanger'+passagewayObj.passagewayNum]
      passagewayArr.push(passagewayObj);
    })

		httpService.post({url:'./monitoringChart/addMonitoringChart',data:passagewayArr,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
});
