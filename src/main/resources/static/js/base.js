var app = angular.module('wgxcb', []);

// 定义过滤器
app.filter('trustHtml', ['$sce', function ($sce) {
    return function (data) {//传入参数时被过滤的内容
        return $sce.trustAsHtml(data);//返回的是过滤后的内容（信任html的转换）
    }
}]);

app.directive('repeatFinish', function () {
    return {
        link: function (scope, element, attr) {
            console.log(scope.$index);
            if (scope.$last == true) {
                console.log('ng-repeat执行完毕');
                scope.$eval(attr.repeatFinish);
            }
        }
    }
})



