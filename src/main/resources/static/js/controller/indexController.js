app.controller('indexController', function($scope, loginService) {
	
	//显示当前用户名
	$scope.showLoginName = function() {
		loginService.loginName().success(function(response) {
			$scope.loginName = response.username;
            $scope.id = response.userId;
		})
	}
    $scope.showOperateName = function() {
        loginService.showOperateName().success(function(response) {
            $scope.loginName = response.operateName;
            $scope.id = response.operateId;
        })
    }

    $scope.showStoreName = function() {
        loginService.showStoreName().success(function(response) {
            $scope.loginName = response.storeName;
            $scope.id = response.storeId;
        })
    }
});