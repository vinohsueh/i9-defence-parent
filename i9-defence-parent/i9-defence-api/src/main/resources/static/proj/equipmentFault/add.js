/**
 * 故障add.js
 */
var equipmentFaultEditNgModule = angular.module('equipmentFaultEditNgModule', [ 'ngResource',
		'ngCookies', 'ui.bootstrap', 'toaster', 'app' ]);

var equipmentFaultEditCtrl = equipmentFaultEditNgModule.controller('equipmentFaultEditCtrl', function($scope,
		$rootScope, $modalInstance, $cookieStore, $http, $window, toaster,
		equipmentFault,eqSystemCategorys,httpService) {
	
	//选择状态判断
	$scope.checkMore = false;	//是否选择过温过流



	$scope.equipmentFault = equipmentFault;
	
	if (equipmentFault.jhType == 1){
		$("#checkMore").prop("checked",true);
		$scope.checkMore = true;
	}
	$scope.eqSystemCategorys = eqSystemCategorys;
	$scope.closeBtn = function() {
		$modalInstance.dismiss('cancel');
	}
	$scope.tab = "equipmentFault";
	$scope.changeTab = function(page){
		$scope.tab = page;
	}
	
	$scope.chooseObj1 = [];
	$scope.chooseObj2 = [];
	for (var i=8;i<=15;i++){
		var check = false;
		if ($.inArray(i+"",$scope.equipmentFault.activationOneStr)>=0) {
			var check = true;
		}
		var obj = {
			value : i,
			check : check
		}
		$scope.chooseObj1.push(obj);
	}
	
	for (var i=8;i<=15;i++){
		var check = false;
		if ($.inArray(i+"",$scope.equipmentFault.activationTwoStr)>=0) {
			var check = true;
		}
		var obj = {
			value : i,
			check : check
		}
		$scope.chooseObj2.push(obj);
	}
	
	
	console.log($scope.equipmentFault)
	console.log($scope.chooseObj2)
	
	// 确认添加
	$scope.confirmAdd = function() {
		if ($scope.equipmentFault.name == null ||$scope.equipmentFault.name.length == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障名称不能为空!"
			});
			return false;
		}
		if ($scope.equipmentFault.code == null ||$scope.equipmentFault.code.length == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障代码不能为空!"
			});
			return false;
		}
		
		if ($scope.equipmentFault.type == null ||$scope.equipmentFault.type.length == 0) {
			$.toaster({
				title : "Error",
				priority : "danger",
				message : "故障类型不能为空!"
			});
			return false;
		}
		$scope.equipmentFault.activationOne = '';
		$scope.equipmentFault.activationTwo = '';
		var activationOneArr = [];
		var activationTwoArr = [];
		if($("#checkMore").prop("checked") == true){
			$scope.equipmentFault.jhType=1;		//多选状态
			$('#checkUp>p>label').each(function () {
				if($(this).find('input').prop('checked')==true){
					activationOneArr.push($(this).find('input').val());

				}
			});
			$scope.equipmentFault.activationOne = activationOneArr.join(',');
			$('#checkDown>p>label').each(function () {
				if($(this).find('input').prop('checked')==true){
					activationTwoArr.push($(this).find('input').val());
				}
			});
			$scope.equipmentFault.activationTwo = activationTwoArr.join(',');
		}else{
			$scope.equipmentFault.jhType=0;
			$scope.equipmentFault.activationOne = '';
			$scope.equipmentFault.activationTwo = '';
		}
		
		// console.log(JSON.stringify($scope.equipmentFault));
		httpService.post({url:'./equipmentFault/updateAndAdd',data:$scope.equipmentFault,showSuccessMsg:true}).then(function(data) {  
			$modalInstance.dismiss('cancel')
		})
	};
//	httpService.post({url:'./eqCategory/serchEqCategory',data:$scope.equipmentCategory,showSuccessMsg:false}).then(function(data) {  
//		$scope.equipmentCategory = data.data.data;
//	})
});
