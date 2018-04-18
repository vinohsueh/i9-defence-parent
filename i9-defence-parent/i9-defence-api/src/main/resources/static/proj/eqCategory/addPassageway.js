var addPassagewayEditNgModule = angular.module('addPassagewayEditNgModule', [ 'ngResource',
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
var addPassagewayEditCtrl = addPassagewayEditNgModule.controller('addPassagewayEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		addPassageway,clientList,httpService,$timeout) {
	
	$scope.addPassageway = addPassageway;
	$scope.clientList = clientList;
  //初始化
  $scope.pageInit = function (){
    var pageParam = {
        id:$scope.addPassageway,
      };    
    httpService.post({url:'./passageWay/selectPassagewaysByCategoryId',data:pageParam,showSuccessMsg:false}).then(function(data) {  
      $scope.passagewayList = data.data.data;
      $scope.hiddenDanger = data.data.dangers;
      $scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
        $scope.mName = [];  
        for(i in $scope.passagewayList){
          if ($scope.passagewayList[i].hiddenDangerId) {
            $scope.mName.push($scope.passagewayList[i].hiddenDangerId.id)
          }
        }
      });
      console.log($scope.mName)
    })
  };
  $scope.pageInit();
  


	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	// 确认添加
	$scope.confirmAdd = function() {
    var passagewayArr=[];
    var passagewayObj = {};

    var thisDom = $('#passagewayBody tr');
    for(i in thisDom){
      passagewayObj.channel = thisDom.find('.passagewayNum input').val();
      passagewayObj.name = thisDom.find('.passagewayName input').val();
      passagewayObj.hiddenDangerId = thisDom.find('.hidedenDanger select').val();
      passagewayObj.categoryId = $scope.addPassageway;
    }
    

    console.log(JSON.stringify(passagewayObj));
		httpService.post({url:'./passageWay/addPassageway',data:passagewayObj,showSuccessMsg:true}).then(function(data) {  
			// $modalInstance.dismiss('cancel')
      thisDom.remove();
      $scope.pageInit();
		})
	};
  // 确认删除
  $scope.confirmDel = function(them) {
    var passagewayObj = {};

    var thisDom = $(them.target).closest('tr');
    passagewayObj.channel = thisDom.find('.passagewayNum input').val();
    passagewayObj.categoryId = $scope.addPassageway;

    console.log(JSON.stringify(passagewayObj));
    httpService.post({url:'./passageWay/delPassageway',data:passagewayObj,showSuccessMsg:true}).then(function(data) {  
      // $modalInstance.dismiss('cancel')
      // thisDom.remove();
      $scope.pageInit();
      console.log(1);
    })
  };
  // 确认移除
  $scope.confirmRemove = function(them) {
    var thisDom = $(them).closest('tr');
    thisDom.remove();
  };
  //添加新通道
  $scope.addRoad = function () {
    $scope.mName.push[0];
    var oOption = '';
    for(i in $scope.hiddenDanger){
      oOption += "<option value='"+$scope.hiddenDanger[i].id+"'>"+$scope.hiddenDanger[i].name+"</option>"
    }
    var oHtml = "<tr on-finish><td class='passagewayNum'><input type='text' class='form-control' placeholder='请输入通道号'></td><td class='passagewayName'><input type='text' class='form-control' placeholder='请输入通道名称'></td><td class='hidedenDanger'><select class='form-control'><option value = ''>请选择</option>"+oOption+"</select></td><td><button type='button' class='btn btn-danger' onclick='angular.element(this).scope().confirmRemove(this)'>删除</button></td></tr>";
    angular.element("#passagewayBody").append(oHtml);
  }
});
