$(function () {
    $('input,textarea').placeholder();
});


//控制层
// app.controller('userController1',
//     ['$scope', '$timeout', 'userService',
//         function ($scope, $controller, userService, userController) {
//             //注册信息校验
//             $("input[name='username']").focus(function () {
//                 $scope.register.username.$error.unique = false;
//             });
//             $("input[name='phone']").focus(function () {
//                 $scope.register.phone.$error.unique = false;
//             });
//             $("input[name='password']").focus(function () {
//                 $scope.register.password.$error.unique = false;
//             });
//
//             $("input[name='username'],input[name='phone']").blur(function () {
//                 userService.findUserByUsername($("input[name='username']").val(), $("input[name='phone']").val()).success(
//                     function (data) {
//                         if (data.flag == '1')
//                             $scope.register.username.$error.unique = true;
//                         else if (data.flag != '2') {
//                             $scope.register.username.$error.unique = false;
//                         }
//                         else if (data.flag == '2')
//                             $scope.register.phone.$error.unique = true;
//                         else
//                             $scope.register.phone.$error.unique = false;
//
//                     });
//             });
//
//             $("input[name='password']").blur(function () {
//                 //比较两次输入的密码是否一致
//                 if ($scope.password != $scope.entity.password) {
//                     $scope.$apply(function () {
//                         $scope.register.password.$error.unique = true;
//                     });
//                 } else
//                     $scope.$apply(function () {
//                         $scope.register.password.$error.unique = false;
//                     });
//             });
//         }
//     ]
// );
