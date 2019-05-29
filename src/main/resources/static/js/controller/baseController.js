//品牌控制层
app.controller('baseController', function ($scope) {

    //重新加载列表 数据
    $scope.reloadList = function () {
        //切换页码
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }


    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };

    $scope.selectIds = [];//选中的ID集合

    //更新复选
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {//如果是被选中,则增加到数组
            $scope.selectIds.push(id);
        } else {
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除 
        }
    }

    $scope.updateSelectionByStoreId = function ($event, id) {
        if ($event.target.checked) {//如果是被选中,则增加到数组
            $scope.selectIds = [];
            console.log("开始1");
            //var goods = $(this).closest(".one-shop").find(".GoodsCheck");
            $(this).closest(".cart-item-list").find("input").each(function () {
                $(this).checked = true;
                $scope.selectIds.push(Number("111"));
            });
            // $scope.selectIds.push(Number($(this).find("li")[1].text()));

            console.log("结束1");

        } else {
            console.log("开始2");
            $(this).parent().next().children().each(function () {
                $(this).find("input[type='checkbox']").checked = false;
            });
            $scope.selectIds = [];
            console.log("结束2");
        }
    }

    $scope.isCheckAll = false;
    $scope.swapCheck = function () {
        if ($scope.isCheckAll) {
            $("td input[type='checkbox']").each(function () {
                this.checked = false;
            });
            $("li input[type='checkbox']").each(function () {
                this.checked = false;
            });
            $scope.selectIds = [];
            $scope.isCheckAll = false;
        } else {
            $scope.selectIds = [];
            $("td input[type='checkbox']").each(function () {
                this.checked = true;
                $scope.selectIds.push(Number($(this).parent().next().text()));
            });
            $("li input[type='checkbox']").each(function () {
                this.checked = true;
                $scope.selectIds.push(Number($(this).parent().next().text()));
            });
            $scope.isCheckAll = true;
        }
    }

    $scope.getValueFromJsonByKey = function (jsonString, key) {
        // console.log(typeof jsonString);
        var json = JSON.parse(jsonString);
        return json[key];
    }

// $scope.getValueFromJsonArrayByKey = function (jsonString, key) {
//     console.log(typeof jsonString);
//     var json = JSON.parse(jsonString);
//     return json[key];
// }

    $scope.jsonArrayToString = function (jsonString, key) {

        var json = JSON.parse(jsonString);
        var value = "";

        for (var i = 0; i < json.length; i++) {
            if (i > 0) {
                value += ",";
            }
            value += json[i][key];
        }

        return value;
    }

    //在list集合中根据某key的值查询对象
    $scope.searchObjectByKey = function (list, key, keyValue) {

        for (var i = 0; i < list.length; i++) {
            if (list[i][key] == keyValue) {
                return list[i];
            }
        }
        return null;
    }
});