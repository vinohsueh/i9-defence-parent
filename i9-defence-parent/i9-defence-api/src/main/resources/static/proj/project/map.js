var mapNgModule = angular.module('mapNgModule', [ 'ngResource',
        'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var mapCtrl = mapNgModule.controller('mapCtrl', function($scope,
        $rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
        project,clientList,httpService,$timeout) {
    
    $scope.closeBtn = function() {
        $modalInstance.dismiss('cancel');
    }
    $scope.confirmAdd = function () {
        
    }
});
