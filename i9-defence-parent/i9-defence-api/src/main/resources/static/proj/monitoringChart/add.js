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
  console.log($scope.monitoringChart);
	$scope.clientList = clientList;
  //初始化
  $scope.pageInit = function (){
    var pageParam = {
        id:$scope.monitoringChart,
      };
    
    
    httpService.post({url:'./passageWay/selectPassagewaysByEquipId',data:pageParam,showSuccessMsg:false}).then(function(data) {  
      $scope.passagewayHave = data.data.data;
      $scope.hiddenDanger = data.data.dangers;
      $scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
        $scope.mName = [];  
        
        for(i in $scope.passagewayList){
          if ($scope.passagewayList[i].hiddenDangerId) {
            $scope.mName.push($scope.passagewayList[i].hiddenDangerId.id)
          }else{
             $scope.mName.push(0);
          }
          
        }
        
      });
      $scope.passagewayList = [];
      $scope.passagewayData = [];
      for(i in $scope.passagewayHave){
        $scope.passagewayData.push($scope.passagewayHave[i].channel);
      }
      for(var j=0;j<16;j++){
        if($.inArray(j,$scope.passagewayData)>-1){
          for(i in $scope.passagewayHave){
            if($scope.passagewayHave[i].channel == j){
              $scope.passagewayList.push($scope.passagewayHave[i]);
            }
          }
        }else{
          $scope.passagewayList.push({'channel':j})
        }
      }
      console.log(JSON.stringify($scope.passagewayList)); 
    })
  };
  $scope.pageInit();
  


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

    $('#passagewayBody tr').each(function (i) {
      passagewayObj = {}
      var thisDom = $(this);
      passagewayObj.channel = thisDom.find('.passagewayNum').text();
      passagewayObj.name = thisDom.find('.passagewayName input').val();
      passagewayObj.hiddenDangerId = $scope.mName[i];
      passagewayArr.push(passagewayObj);
    })
    var passagewayDto = {
      passageways:passagewayArr,
      equipmentId:$scope.monitoringChart
    }
    console.log(JSON.stringify(passagewayDto));
		httpService.post({url:'./passageWay/addPassageway',data:passagewayDto,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
});
