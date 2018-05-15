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
		$rootScope, $cookieStore, $window, toaster,httpService,$modal) {
    $scope.project = project;

    $scope.clientList = clientList;
    $scope.safeList = safeList;
    if ($scope.safeList.length > 0){
		$scope.placeHod = "请选安全责任人";
	}else{
		$scope.placeHod = "暂无安全责任人可选";
	}
    
    if ($scope.clientList.length > 0){
		$scope.placeHod2 = "请选责任人";
	}else{
		$scope.placeHod2 = "暂无责任人可选";
	}
    var province={},
    	  city={};
    
    for(i in division){
    	if(division[i].name == $scope.project.projectProvince){
    		$scope.selected =division[i];
    		province = division[i];
//    		console.log(JSON.stringify(province));
    		 for(j in province.child){
    		    	if(province.child[j].name == $scope.project.projectCity){
    		    		$scope.selected2 =province.child[j];
    		    		city = province.child[j];
    		    		//console.log(JSON.stringify(city));
    		    		for(x in city.child){
    		    	    	if(city.child[x].value == $scope.project.projectCounty){
    		    	    		$scope.selected3 =city.child[x];
    		    	    	}
    		    	    }
    		    	}
    		    }
    	}
    }
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
        JSON.stringify($scope.project.clientIds);
        var idArr2 = [];
        var idStr2;
        $('#charge2 .ui-select-multiple>div>.ui-select-match>.ng-scope').each(function () {
            idStr2 = $(this).find('.checkItem').attr('data-id');
            idArr2.push(idStr2);
        });
        $scope.project.safeIds = idArr2;
        JSON.stringify($scope.project.safeIds);
        if($scope.selected == null || $scope.selected == ''){
    		$scope.selected ={
    			name : ''
    		}
    	}
    	if($scope.selected2 == null || $scope.selected2 == ''){
    		$scope.selected2 ={
    			name : ''
    		}
    	}
    	if($scope.selected3 == null || $scope.selected3 == ''){
    		$scope.selected3 ={
    			value : ''
    		}
    	}
    	
		$scope.project.projectProvince = $scope.selected.name;
		$scope.project.projectCity = $scope.selected2.name;
		$scope.project.projectCounty = $scope.selected3.value;
		
		$scope.project.projectStartDate=$("#projectStartDate").val();
		$scope.project.projectEndDate=$("#projectEndDate").val();
		console.log(JSON.stringify($scope.project))
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
    //编辑页面默认选中的负责人
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
    };
  //编辑页面默认选中的安全责任人
    $scope.safeNewList = [];
    var safeIds = $scope.project.safeIds;
    if(safeIds != null){
    	if(safeIds.length > 0){
            for (var i = 0; i <  safeIds.length; i++) {
            	for(c in $scope.safeList){
            		if($scope.safeList[c].id == safeIds[i]){
            			 $scope.safeNewList.push($scope.safeList[c]);
            		}
            	}
            }
        }
    };
    
  //地域
	$scope.error = {};
	$scope.division = division;
	$scope.c = function () {
	   $scope.error.province = false;
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected2 = "";
	   $scope.selected3 = "";
	};
	$scope.c2 = function () {       
	   $scope.error.city = false;
	   $scope.error.area = false;
	   $scope.selected3 = "";
	};
	$scope.c3 = function () {
	   $scope.error.area = false;
	};
    //地图选择
    $scope.map = function () {  
        var modalInstance = $modal.open({  
            templateUrl: 'proj/project/map.html',  
            controller: 'mapCtrl', 
            backdrop:"static",//但点击模态窗口之外时，模态窗口不关闭
            resolve: {  
                deps : ['$ocLazyLoad',function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name : 'mapNgModule',
                        insertBefore : '#ng_load_plugins_before',
                        files : [
                                 'proj/project/map.js',
                                 'http://webapi.amap.com/maps?v=1.4.5&key=491782deb46aec33a67744f583836895&plugin=AMap.Autocomplete'
                        ]
                    });
                }],
                project: function () {  
                    return   
                },
                clientList: function () {  
                    return {}; 
                },
            }  
        }); 
        modalInstance.result.then(function(data){//$modalInstance.close()正常关闭后执行的函数
            // $scope.coordinate = data;
            var coordinateAtt = data.split(',');
            $scope.project.projectLongitude = coordinateAtt[0];
            $scope.project.projectLatitude = coordinateAtt[1];
        },function(){//$modalInstance.dismiss('cancel')后执行的函数，取消或退出执行的函数
           
        });
         
    };

});