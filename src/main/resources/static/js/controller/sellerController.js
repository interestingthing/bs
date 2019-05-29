//控制层
app.controller('sellerController', function ($scope, $controller, sellerService, loginService) {

    $controller('baseController', {$scope: $scope});//继承


    $scope.updateStatus = function (id, status) {
        sellerService.updateStatus(id, status).success(function (response) {
            if (response.success) {
                //重新查询
                $scope.reloadList();//重新加载
            } else {
                alert(response.message);
            }
        });
    }

    $scope.log = function () {
        sellerService.login($scope.store).success(function (result) {
            if (result.success === true)
                $(location).attr('href', 'http://localhost:8080/store/index.html');
            else {
                $("#loginMsg").css("display", "block");
                $("#loginMsg").text(result.message);
            }
        });
    }

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        sellerService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        sellerService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOn = function () {
        loginService.showStoreName().success(function (response) {
            sellerService.findOne(response.storeId).success(
                function (response) {
                    $scope.entity = response;
                }
            );
        });

    }

    $scope.findOne = function (id) {

        sellerService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };


//保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = sellerService.update($scope.entity); //修改
        } else {
            serviceObject = sellerService.add($scope.entity);//增加
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

//新增
    $scope.add = function () {
        sellerService.add($scope.entity).success(
            function (response) {
                if (response.success) {
                    alert("申请成功，等待管理员审核！");
                    //如果注册成功，跳转到登录页
                    location.href = "/store/login.html";
                } else {
                    alert(response.message);
                }
            }
        );
    }

//批量删除
    $scope.dele = function () {
        //获取选中的复选框
        sellerService.dele($scope.selectIds).success(
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
        sellerService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

})
;
