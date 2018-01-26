var projectCodeNgModule = angular.module('projectCodeNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var projectCodeCtrl = projectCodeNgModule.controller('projectCodeCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		code) {
	
	$scope.code = code;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
});
