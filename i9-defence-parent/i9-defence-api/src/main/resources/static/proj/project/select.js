// this is a lazy load controller, 
// so start with "app." to register this controller

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
app.controller('projectEditCtrl', function($scope, $http, $timeout,project,clientList,safeList,$modalInstance,
		$rootScope, $cookieStore, $window, toaster,httpService) {
    $scope.project = project;
    $scope.clientList = clientList;
    $scope.safeList = safeList;
    $scope.closeBtn = function() {
        $modalInstance.dismiss('cancel');
    }
    // 确认添加
    $scope.confirmAdd = function() {
        if ($scope.project.projectName ==null ||$scope.project.projectName ==0) {
            $.toaster({
                title : "Error",
                priority : "danger",
                message : "项目名不能为空!"
            });
            return false;
        }
        var idArr = [];
        var idStr;
        $('#charge .ui-select-multiple>div>.ui-select-match>.ng-scope').each(function () {
            idStr = $(this).find('.checkItem').attr('data-id');
            idArr.push(idStr);
        });
        $scope.project.clientIds = idArr;
        //console.log(JSON.stringify(idArr));
        JSON.stringify($scope.project.clientIds);
        httpService.post({url:'./project/addProject',data:$scope.project,showSuccessMsg:true}).then(function(data) {  
            $modalInstance.dismiss('cancel')
        })
    };

    
        $scope.disabled = undefined;
        $scope.searchEnabled = undefined;

        $scope.enable = function() {
        $scope.disabled = false;
        };

        $scope.disable = function() {
        $scope.disabled = true;
        };

        $scope.enableSearch = function() {
        $scope.searchEnabled = true;
        }

        $scope.disableSearch = function() {
        $scope.searchEnabled = false;
        }

        $scope.clear = function() {

        };

        $scope.counter = 0;
        $scope.someFunction = function (item, model){
            $scope.counter++;
            $scope.eventResult = {item: item, model: model};
        };

        $scope.removed = function (item, model) {
            $scope.lastRemoved = {
                item: item,
                model: model
        	};
        };
        //编辑页面默认选中的
        $scope.clientNewList = [];
        var clientIds = $scope.project.clientIds;
        if(clientIds != null){
        	if(clientIds.length > 0){
                //console.log(JSON.stringify(clientIds));
                for (var i = 0; i <  clientIds.length; i++) {
                	for(c in $scope.clientList){
                		if($scope.clientList[c].id == clientIds[i]){
                			 $scope.clientNewList.push($scope.clientList[c]);
                		}
                	}
                }
            }
        }
});