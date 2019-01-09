'use strict';

/* Controllers */

angular.module('app')
  .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window','$cookieStore','$http','$modal',
    function(              $scope,   $translate,   $localStorage,   $window, $cookieStore,$http,$modal) {
      // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      isIE && angular.element($window.document.body).addClass('ie');
      isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

      // config
      $scope.app = {
        name: 'Angulr',
        version: '1.3.2',
        picturePrefix : "../head/",
        // for chart colors
        color: {
          primary: '#7266ba',
          info:    '#23b7e5',
          success: '#27c24c',
          warning: '#fad733',
          danger:  '#f05050',
          light:   '#e8eff0',
          dark:    '#3a3f51',
          black:   '#1c2b36'
        },
        settings: {
          themeID: 1,
          navbarHeaderColor: 'bg-black',
          navbarCollapseColor: 'bg-white-only',
          asideColor: 'bg-black',
          headerFixed: true,
          asideFixed: false,
          asideFolded: false,
          asideDock: false,
          container: false
        }
      }
      
      // save settings to local storage
      if ( angular.isDefined($localStorage.settings) ) {
        $scope.app.settings = $localStorage.settings;
      } else {
        $localStorage.settings = $scope.app.settings;
      }
      $scope.$watch('app.settings', function(){
        if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
          // aside dock and fixed must set the header fixed.
          $scope.app.settings.headerFixed = true;
        }
        // save to local storage
        $localStorage.settings = $scope.app.settings;
      }, true);

      // angular translate
      $scope.lang = { isopen: false };
      $scope.langs = {en:'English', de_DE:'German', it_IT:'Italian'};
      $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
      $scope.setLang = function(langKey, $event) {
        // set the current lang
        $scope.selectLang = $scope.langs[langKey];
        // You can change the language during runtime
        $translate.use(langKey);
        $scope.lang.isopen = !$scope.lang.isopen;
      };
      
      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }

  }]);
app.config([
    '$ocLazyLoadProvider', 
    function($ocLazyLoadProvider) {
        $ocLazyLoadProvider.config({
            // 用来开启debug模式。布尔值，默认是false。开启debug模式时，$ocLazyLoad会打印出所有的错误到console控制台上。 
            debug: true, 
            // 当动态加载了module的时候，$ocLazyLoad会广播相应的事件。布尔值，默认为false。
            events: true, 
            // 用于定义需要动态加载的模块。定义每个模块的名字需要唯一。 modules必须要用数组的形式，其中files也必须以数组的形式存在，哪怕只需要加载一个文件。
           /* modules: [{
                 name: 'stdorganNgModule', 
                 files: [
                     './modules/standard/architecture/stdorganNgCtrl.js'
                 ]
             }] */
        });
    }
]);
/* Setup Layout Part - Sidebar */
app.controller('NavController', ['$scope', '$http','$cookieStore','$cookies','removeElement', function($scope, $http,$cookieStore,$cookies,removeElement) {
    $scope.$on('$includeContentLoaded', function() {
    	
    	/**
         * 获取用户权限和页签
         */
		$http.get('./security/noAllowedAuth').then(function (resp) {
       		$cookieStore.put("noAllowedAuthList",resp.data.data.data);
       		$scope.pages = resp.data.data.urls;
       		$scope.app.user = resp.data.data.user; 
       	})
	    
    });
}]);
app.controller('HeaderController', ['$scope', '$http','$cookieStore', function($scope, $http,$cookieStore) {
    $scope.$on('$includeContentLoaded', function() {
    });
}]);
var httpService = app.factory('httpService', ['$http','$q', '$window', 'toaster', function($http, $q, $window, toaster){
	var service = {};
//	var options = {
//			url : '',
//			data: '',
//			showSuccessMsg: '',
//	};
	service.get = function(options){
		var deferred = $q.defer();
		var accessUrl = options.url;
		$http.get(accessUrl)
			.success(function (data) {
				if (data.result == 1){
					if(options.showSuccessMsg==true){
					$.toaster({
						title : "Success",
						priority : "success",
						message : "成功"
					});
					}
					deferred.resolve(data);
				}else if(data.result == -1){
					$.toaster({
						title : "Error",
						priority : "danger",
						message : data.errorMsg[Object.keys(data.errorMsg)[0]]
					});
				}else{
					$.toaster({
						title : "Error",
						priority : "danger",
						message : data.errorMsg
					});
				}
			})
			.error(function(msg){
				if (msg.exception == "org.apache.shiro.authz.UnauthorizedException") {
					$.toaster({
						title : "Error",
						priority : "danger",
						message : "没有权限！"
					});
				}else{
					$.toaster({
						title : "Error",
						priority : "danger",
						message : "失败！"
					});
				}
				deferred.reject(msg);
			});
		return deferred.promise;
	};
	
	service.post = function(options){
		var deferred = $q.defer();
		var accessUrl = options.url;
		
		$http.post(accessUrl, options.data)
			.success(function (data) {
				if (data.result == 1){
					if(options.showSuccessMsg==true){
						$.toaster({
							title : "Success",
							priority : "success",
							message : "成功"
						});
					}
					deferred.resolve(data);
				}else if(data.result == -1){
					$.toaster({
						title : "Error",
						priority : "danger",
						message : data.errorMsg[Object.keys(data.errorMsg)[0]]
					});
				}else{
					$.toaster({
						title : "Error",
						priority : "danger",
						message : data.errorMsg
					});
				}
				
			})
			.error(function(msg){
				console.log(msg)
				if (msg.exception == "org.apache.shiro.authz.UnauthorizedException") {
					$.toaster({
						title : "Error",
						priority : "danger",
						message : "没有权限！"
					});
				}else{
					$.toaster({
						title : "Error",
						priority : "danger",
						message : "失败！"
					});
				}
				console.log(msg.exception)
				deferred.reject(msg);
			});
		return deferred.promise;
	};
	
	return service;
}]);

/*$http({
    method: 'POST',
    url: './managerApply/ceshi',
    data: $.param($scope.formData), // pass in data as strings
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' } // set the headers so angular passing info as form data (not request payload)
})
.success(function(data) {
    console.log(data);
});*/