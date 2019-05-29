app.service('loginService', function ($http) {
    this.loginName = function () {
        return $http.get('../user/showName');
    }

    this.showOperateName = function () {
        return $http.get('../operate/showName');
    }
    this.showStoreName = function () {
        return $http.get('../store/showName');
    }


});