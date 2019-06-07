//控制层 
app.controller('goodsController', function ($scope, $http, $controller, $location, goodsService,
                                            uploadService, itemCatService, typeTemplateService) {


        $controller('baseController', {
            $scope: $scope
        });// 继承

        $scope.deleteImgFromBack = function (index, index1) {
            console.log("#deleteImgFromBack" + index + "   " + index1);
            // $scope.entity.itemList[index].imgUrl =
            $scope.entity.itemList[index].imgUrl.splice(index1, 1);
            $(this).parent().parent().remove();
        }

        //多图片上传
        // $scope.imgResult = [];
        $scope.renderFinish = function () {
            //下面是在table render完成后执行的js
            console.log("#1");

            var input = $("input[id^='file_input']");
            console.log($("input[id^='file_input']").length);
            //$("tr").css({"background-color":"red","border":"2px solid red"});
            console.log("多图片上传初始化");
            var oSelect = $("button[id^='selectMain']");
            var oAdd = $("button[id^='add']");
            var oSubmit = $("button[id^='submit']");
            var formdata = new FormData();//表单数据
            var dataArray = [];

            //读取文件
            function read() {
                // console.log($(this).prop('files'));
                var file_input = $(this);
                console.log(file_input);
                var iLen = file_input.prop('files').length;
                // console.log(iLen);

                for (var i = 0; i < iLen; i++) {

                    dataArray.push(file_input.prop('files')[i]);
                    var reader = new FileReader();
                    //转成base64
                    reader.readAsDataURL(file_input.prop('files')[i]);
                    reader.fileName = this.files[i].name;
                    reader.onload = function (e) {
                        var result =
                            '<div class="delete">delete</div>' +
                            '<div class="result">' +
                            '<img class="subPic" width="100px" height="100px" ' +
                            'src="' + this.result + '" alt="' + this.fileName + '"/>' +
                            '</div>';
                        var div = document.createElement('div');

                        div.innerHTML = result;
                        div['className'] = 'float';
                        //插入dom树
                        //.css({"color":"red","border":"2px solid red"})
                        file_input.next().append(div);
                        // file_input.next("div").find("div img").css({"color":"red","border":"2px solid red"});
                        // img.onload = function () {
                        //     var nowHeight = ReSizePic(this); //设置图片大小
                        //     this.parentNode.style.display = 'block';
                        //     var oParent = this.parentNode;
                        //     if (nowHeight) {
                        //         oParent.style.paddingTop = (oParent.offsetHeight - nowHeight) / 2 + 'px';
                        //     }
                        // };
                        div.onclick = function () {
                            var alt = $(this).find("img").prop("alt");
                            for (var i = 0; i < dataArray.length; i++) {
                                if (dataArray[i].name === alt) {
                                    console.log("删除" + dataArray[i].name);
                                    dataArray.splice(i, 1);
                                    break;
                                }
                            }
                            // 在页面中删除该图片元素
                            $(this).remove();
                        };
                    }
                }
            }

            //初始化
            if (typeof FileReader === 'undefined') {
                alert("抱歉，你的浏览器不支持 FileReader");
                input.each(function (index, element) {
                    element.prop('disabled', 'disabled');
                });
            } else {
                console.log("#2");
                console.log(input);
                input.each(function (index, element) {
                    console.log("#2.5");
                    //console.log(input[index].prop("id"));
                    element.addEventListener('change', read, false);
                    // $(element).change(
                    //     read()
                    // );
                    //
                });

                console.log("#3");

                oSelect.each(function (index, element) {
                    console.log("#4");
                    $(element).click(function () {
                        console.log("#5");
                        input[index].value = "";
                        // 先将input值清空，否则选择图片与上次相同时change事件不会触发
                        //清空已选图片
                        formdata.delete("file");
                        dataArray = [];
                        // $('.float').remove();
                        $scope.entity.itemList[index].imgUrlShow = [];
                        $scope.$apply();
                        $(input[index]).next().children().remove();
                        input[index].click();
                    });
                });

                oAdd.each(function (index, element) {
                    $(element).click(function () {
                        //console.log(input.value);
                        input[index].value = "";
                        // 先将input值清空，否则选择图片与上次相同时change事件不会触发
                        input[index].click();
                    });
                });

                oSubmit.each(function (index, element) {
                    console.log("oSubmit");

                    $(element).click(function (event) {
                        if (dataArray.length === 0) {
                            return alert('请先选择文件');
                        }
                        upload().success(function (result) {
                            //console.log(result['message']);
                            // $(event.target).next().val(result['message']);
                            // var index = $(event.target).next().prop("id");
                            // console.log(index);
                            var pattern = /[\[\]\s]/g;
                            $scope.entity.itemList[index].imgUrl = result['message'].replace(pattern, "").split(",");
                            $(element).next("h7").remove();
                            $(element).after("<h7 id=\"loginMsg\" style=\"\n" +
                                "                            position: relative;\n" +
                                "                            background: #ffebeb;\n" +
                                "                            color: #e4393c;\n" +
                                "                            border: 1px solid #faccc6;\n" +
                                "                            padding: 3px 10px 3px 40px;\n" +
                                "                            line-height: 15px;\n" +
                                "                            height: auto;\n" +
                                "                            \">保存成功\n" +
                                "                            </h7>");
                        });
                        //处理结果
                        // $scope.imgResult = result;
                    });
                });
            }


            //上传图片
            function upload() {
                formdata.delete("file");
                for (var i = 0; i < dataArray.length; i++)
                    formdata.append('file', dataArray[i]);

                return $http({
                    url: '../upload',
                    method: 'post',
                    data: formdata,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                });

            }
        };

        function ReSizePic(ThisPic) {
            var RePicWidth = 200; //这里修改为您想显示的宽度值

            var TrueWidth = ThisPic.width; //图片实际宽度
            var TrueHeight = ThisPic.height; //图片实际高度

            if (TrueWidth > TrueHeight) {
                //宽大于高
                var reWidth = RePicWidth;
                ThisPic.width = reWidth;
                //垂直居中
                var nowHeight = TrueHeight * (reWidth / TrueWidth);
                return nowHeight;  //将图片修改后的高度返回，供垂直居中用
            } else {
                //宽小于高
                var reHeight = RePicWidth;
                ThisPic.height = reHeight;
            }
        }

        $scope.itemCatList = [];//商品分类列表
        //加载商品分类列表
        $scope.findItemCatList = function () {
            itemCatService.findAll().success(
                function (response) {
                    for (var i = 0; i < response.length; i++) {
                        $scope.itemCatList[response[i].id] = response[i].name;
                    }
                }
            );
        }

        //商品状态
        $scope.status = ['未审核', '审核中', '已审核', '审核未通过', '关闭'];

        // 审核的方法:
        $scope.updateStatus = function (status) {
            goodsService.updateStatus($scope.selectIds, status).success(function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                } else {
                    alert(response.message);
                }
            });
        }

        // 读取列表数据绑定到表单中
        $scope.findAll = function () {
            goodsService.findAll().success(function (response) {
                $scope.list = response;
            });
        }

        // 分页
        $scope.findPage = function (page, rows) {
            goodsService.findPage(page, rows).success(function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;// 更新总记录数
            });
        }

        // // 查询实体
        // $scope.findOne = function (id) {
        //     var id = $location.search()['id'];
        //     goodsService.findOne(id).success(function (response) {
        //         $scope.entity = response;
        //     });
        // }

        //查询实体
        $scope.findOne = function () {
            var id = $location.search()['id'];//获取参数值
            if (id == null) {
                return;
            }
            goodsService.findOne(id).success(
                function (response) {
                    $scope.entity = response;

                    // 调用处理富文本编辑器：
                    editor.html($scope.entity.goodsDesc.introduction);

                    // 处理图片列表，因为图片信息保存的是JSON的字符串，让前台识别为JSON格式对象
                    $scope.entity.goodsDesc.itemImages = JSON.parse($scope.entity.goodsDesc.itemImages);

                    // 处理扩展属性:
                    $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.entity.goodsDesc.customAttributeItems);

                    // 处理规格
                    $scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems);

                    // 遍历SKU的集合:
                    for (var i = 0; i < $scope.entity.itemList.length; i++) {
                        $scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec);
                        var pattern = /[\[\]\s]/g;
                        $scope.entity.itemList[i].imgUrlShow = $scope.entity.itemList[i].imgUrl.replace(pattern, "").split(",");
                        $scope.entity.itemList[i].imgUrl = $scope.entity.itemList[i].imgUrlShow;
                    }
                }
            );
        }

        // 增加商品
        $scope.add = function () {
            var serviceObject;//服务层对象
            // for (var i = 0; i < $scope.entity.itemList.length; i++) {
            //     $scope.entity.itemList[i].imgUrl = $scope.entity.itemList[i].imgUrlShow;
            // }
            // for (var i = 0; i < $scope.entity.itemList.length; i++) {
            //     $scope.entity.itemList[i].imgUrl = $scope.entity.itemList[i].imgUrl.join(",")+"]";
            // }
            if ($scope.entity.goods.id != null) {//如果有ID
                serviceObject = goodsService.update($scope.entity); //修改
            } else {
                serviceObject = goodsService.add($scope.entity);//增加
            }
            serviceObject.success(
                function (response) {
                    if (response.flag) {
                        //重新查询
                        alert(response.message);
                        location.href = "goods.html";
                    } else {
                        alert(response.message);
                    }
                }
            );
        }

        // 批量删除
        $scope.dele = function () {
            // 获取选中的复选框
            goodsService.dele($scope.selectIds).success(function (response) {
                if (response.success) {
                    $scope.reloadList();// 刷新列表
                    $scope.selectIds = [];
                }
            });
        }

        $scope.searchEntity = {};// 定义搜索对象

        // 搜索
        $scope.search = function (page, rows) {
            goodsService.search(page, rows, $scope.searchEntity).success(
                function (response) {
                    $scope.list = response.rows;
                    $scope.paginationConf.totalItems = response.total;// 更新总记录数
                });
        }

        // 上传图片
        $scope.uploadFile = function ($event) {
            uploadService.uploadFile($event).success(function (response) {
                if (response.success) {
                    $scope.image_entity.imgUrl = response.message;
                } else {
                    alert(response.message);
                }
            });
        }

        // window.onload = function () {
        //     var input = document.getElementById("file_input");
        //     var result;
        //     var dataArr = []; // 储存所选图片的结果(文件名和base64数据)
        //     var fd;  //FormData方式发送请求
        //     var oSelect = document.getElementById("select");
        //     var oAdd = document.getElementById("add");
        //     var oSubmit = document.getElementById("submit");
        //     var oInput = document.getElementById("file_input");
        //
        //     if (typeof FileReader === 'undefined') {
        //         alert("抱歉，你的浏览器不支持 FileReader");
        //         input.setAttribute('disabled', 'disabled');
        //     } else {
        //         input.addEventListener('change', readFile, false);
        //     }
        //     //handler
        //
        //
        //     function readFile() {
        //         fd = new FormData();
        //         var iLen = this.files.length;
        //         for (var i = 0; i < iLen; i++) {
        //             if (!input['value'].match(/.jpg|.gif|.png|.jpeg|.bmp/i)) {　　//判断上传文件格式
        //                 return alert("上传的图片格式不正确，请重新选择");
        //             }
        //             var reader = new FileReader();
        //             fd.append(i, this.files[i]);
        //             reader.readAsDataURL(this.files[i]);  //转成base64
        //             reader.fileName = this.files[i].name;
        //
        //             reader.onload = function (e) {
        //                 var imgMsg = {
        //                     name: this.fileName,
        //                     //获取文件名
        //                     base64: this.result
        //                     //reader.readAsDataURL方法执行完后，base64数据储存在reader.result里
        //                 }
        //                 dataArr.push(imgMsg);
        //                 result = '<div class="delete">delete</div><div class="result"><img class="subPic" src="' + this.result + '" alt="' + this.fileName + '"/></div>';
        //                 var div = document.createElement('div');
        //                 div.innerHTML = result;
        //                 div['className'] = 'float';
        //                 document.getElementsByTagName('body')[0].appendChild(div);  　　//插入dom树
        //                 var img = div.getElementsByTagName('img')[0];
        //                 img.onload = function () {
        //                     var nowHeight = ReSizePic(this); //设置图片大小
        //                     this.parentNode.style.display = 'block';
        //                     var oParent = this.parentNode;
        //                     if (nowHeight) {
        //                         oParent.style.paddingTop = (oParent.offsetHeight - nowHeight) / 2 + 'px';
        //                     }
        //                 }
        //                 div.onclick = function () {
        //                     $(this).remove();                  // 在页面中删除该图片元素
        //                 }
        //             }
        //         }
        //     }
        //
        //
        //     function send() {
        //         var submitArr = [];
        //         $('.subPic').each(function () {
        //                 submitArr.push({
        //                     name: $(this).attr('alt'),
        //                     base64: $(this).attr('src')
        //                 });
        //             }
        //         );
        //         $.ajax({
        //             url: 'http://123.206.89.242:9999',
        //             type: 'post',
        //             data: JSON.stringify(submitArr),
        //             dataType: 'json',
        //             //processData: false,   用FormData传fd时需有这两项
        //             //contentType: false,
        //             success: function (data) {
        //                 console.log('返回的数据：' + JSON.stringify(data))
        //             }
        //         })
        //     }
        //
        //
        //     // oSelect.onclick = function () {
        //     //     oInput.value = "";
        //     //     // 先将oInput值清空，否则选择图片与上次相同时change事件不会触发
        //     //     //清空已选图片
        //     //     $('.float').remove();
        //     //     oInput.click();
        //     // }
        //     //
        //     //
        //     // oAdd.onclick = function () {
        //     //     oInput.value = "";
        //     //     // 先将oInput值清空，否则选择图片与上次相同时change事件不会触发
        //     //     oInput.click();
        //     // }
        //
        //
        //     oSubmit.onclick = function () {
        //         if (!dataArr.length) {
        //             return alert('请先选择文件');
        //         }
        //         send();
        //     }
        // }


        $scope.entity = {goods: {}, goodsDesc: {itemImages: [], specificationItems: []}};
        // 将当前上传的图片实体存入图片列表
        $scope.add_image_entity = function () {
            $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
        }

        // 移除图片
        $scope.remove_image_entity = function (index) {
            $scope.entity.goodsDesc.itemImages.splice(index, 1);
        }


        //展示
        // 查询一级商品分类列表
        $scope.selectItemCat1List = function () {
            itemCatService.findByParentId(0).success(function (response) {
                $scope.itemCat1List = response;
            });
        }

        // 查询二级商品分类列表
        $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
            if (newValue != null && newValue != '')
                itemCatService.findByParentId(newValue).success(function (response) {
                    $scope.itemCat2List = response;
                });
        });

        // 读取模板ID
        $scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {
            if (newValue != null && newValue != '')
                itemCatService.findOne(newValue).success(function (response) {
                    $scope.entity.goods.typeTemplateId = response.templateId;
                });
        });

        //读取模板ID后，扩展属性  规格列表
        $scope.$watch('entity.goods.typeTemplateId', function (newValue, oldValue) {
            if (newValue != null && newValue != '') {
                if ($location.search()['id'] == null) {
                    typeTemplateService.findOne(newValue).success(
                        function (response) {
                            $scope.typeTemplate = response;// 模板对象
                            //扩展属性
                            $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.extendAttributes);
                        }
                    );
                }
                //读取规格
                typeTemplateService.findSpecList(newValue).success(
                    function (response) {
                        $scope.specList = response;
                    }
                );
            }

        });

        $scope.updateSpecAttribute = function ($event, name, value) {

            var object = $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems, 'attributeName', name);

            if (object != null) {
                if ($event.target.checked) {
                    object.attributeValue.push(value);
                } else {//取消勾选
                    object.attributeValue.splice(object.attributeValue.indexOf(value), 1);
                    //移除选项
                    //如果选项都取消了，将此条记录移除
                    if (object.attributeValue.length == 0) {
                        $scope.entity.goodsDesc.specificationItems.splice(
                            $scope.entity.goodsDesc.specificationItems.indexOf(object), 1);
                    }

                }
            } else {
                $scope.entity.goodsDesc.specificationItems.push({
                    "attributeName": name,
                    "attributeValue": [value]
                });
            }

        }

        //根据规格名称和选项名称返回是否被勾选
        <!--{"options":[{"id":6,"orders":1,"specId":1,"value":"黑色"},{"id":7,"orders":3,"specId":1,"value":"蓝色"}],"id":1,"text":"颜色"}-->
        $scope.checkAttributeValue = function (specName, optionName) {
            var items = $scope.entity.goodsDesc.specificationItems;
            var object = $scope.searchObjectByKey(items, 'attributeName', specName);
            if (object == null) {
                return false;
            } else {
                if (object.attributeValue.indexOf(optionName) >= 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }


//创建SKU列表
        $scope.createItemList = function () {
            $scope.entity.itemList = [{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}];//列表初始化
            var items = $scope.entity.goodsDesc.specificationItems;
            for (var i = 0; i < items.length; i++) {
                $scope.entity.itemList = addColumn($scope.entity.itemList, items[i].attributeName, items[i].attributeValue);
            }
            console.log($scope.entity.itemList);

        }

        addColumn = function (list, columnName, columnValues) {
            var newList = [];
            for (var i = 0; i < list.length; i++) {
                var oldRow = list[i];
                for (var j = 0; j < columnValues.length; j++) {
                    var newRow = JSON.parse(JSON.stringify(oldRow));//深克隆
                    newRow.spec[columnName] = columnValues[j];
                    newList.push(newRow);
                }
            }
            return newList;
        }

    }
);
