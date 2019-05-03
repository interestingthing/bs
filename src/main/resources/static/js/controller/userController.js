//控制层
app.controller('userController',
    ['$scope', '$timeout', 'userService',
        function ($scope, $controller, userService) {
            //注册信息校验
            $("input[name='username']").focus(function () {
                $scope.register.username.$error.unique = false;
            });
            $("input[name='phone']").focus(function () {
                $scope.register.phone.$error.unique = false;
            });
            $("input[name='password']").focus(function () {
                $scope.register.password.$error.unique = false;
            });

            $("input[name='username'],input[name='phone']").blur(function () {
                userService.findUserByUsername($("input[name='username']").val(), $("input[name='phone']").val()).success(
                    function (data) {
                        if (data.flag == '1')
                            $scope.register.username.$error.unique = true;
                        else if (data.flag != '2') {
                            $scope.register.username.$error.unique = false;
                        }
                        else if (data.flag == '2')
                            $scope.register.phone.$error.unique = true;
                        else
                            $scope.register.phone.$error.unique = false;

                    });
            });

            $("input[name='password']").blur(function () {
                //比较两次输入的密码是否一致
                if ($scope.password != $scope.entity.password) {
                    $scope.$apply(function () {
                        $scope.register.password.$error.unique = true;
                    });
                } else
                    $scope.$apply(function () {
                        $scope.register.password.$error.unique = false;
                    });
            })

            //注册用户
            $scope.reg = function () {
                //新增
                console.log("用户注册!");
                userService.add($scope.entity).success(
                    function (response) {
                        if (response.success === false)
                            alert(response.message);
                        else if (response.success === true)
                            alert("注册成功");
                        $(location).attr('href', 'http://localhost:8080/user/login.html');
                    }
                );

            }

            //登录
            $scope.log = function () {
                userService.login($scope.user).success(function (result) {
                    if (result.success == true)
                        $(location).attr('href', 'http://localhost:8080/user/index.html');
                    else {
                        $("#loginMsg").css("display", "block");
                        $("#loginMsg").text(result.message);
                    }
                });
            }

            //读取列表数据绑定到表单中
            $scope.findAll = function () {
                userService.findAll().success(
                    function (response) {
                        $scope.list = response;
                    }
                );
            }

            //分页
            $scope.findPage = function (page, rows) {
                userService.findPage(page, rows).success(
                    function (response) {
                        $scope.list = response.rows;
                        $scope.paginationConf.totalItems = response.total;//更新总记录数
                    }
                );
            }

            //查询实体
            $scope.findOne = function (id) {
                userService.findOne(id).success(
                    function (response) {
                        $scope.entity = response;
                    }
                );
            };

            //保存
            $scope.save = function () {
                var serviceObject;//服务层对象
                if ($scope.entity.id != null) {//如果有ID
                    serviceObject = userService.update($scope.entity); //修改
                } else {
                    serviceObject = userService.add($scope.entity);//增加
                }
                serviceObject.success(
                    function (response) {
                        if (response.success) {
                            //重新查询
                            $scope.reloadList();//重新加载
                        } else {
                            alert(response.message);
                        }
                    }
                );
            }


            //批量删除
            $scope.dele = function () {
                //获取选中的复选框
                userService.dele($scope.selectIds).success(
                    function (response) {
                        if (response.success) {
                            $scope.reloadList();//刷新列表
                            $scope.selectIds = [];
                        }
                    }
                );
            }

            $scope.searchEntity = {};//定义搜索对象

//搜索
            $scope.search = function (page, rows) {
                userService.search(page, rows, $scope.searchEntity).success(
                    function (response) {
                        $scope.list = response.rows;
                        $scope.paginationConf.totalItems = response.total;//更新总记录数
                    }
                );
            }
        }
    ]
);
