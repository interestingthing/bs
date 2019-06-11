<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>产品详情页</title>
    <link rel="icon" href="assets/img/favicon.ico">

    <link rel="stylesheet" type="text/css" href="../../static/css/webbase.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/pages-item.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/pages-zoom.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/widget-cartPanelView.css"/>

    <script type="text/javascript" src="../../static/js/plugins/jquery/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="../../static/js/plugins/jquery.jqzoom/jquery.jqzoom.js"></script>
    <script type="text/javascript" src="../../static/js/plugins/jquery.jqzoom/zoom.js"></script>
    <script type="text/javascript" src="../../static/js/plugins/angularjs/angular.min.js"></script>
    <script type="text/javascript" src="../../static/js/base.js"></script>
    <script type="text/javascript" src="../../static/js/controller/baseController.js"></script>
    <script type="text/javascript" src="../../static/js/service/itemService.js"></script>
    <script type="text/javascript" src="../../static/js/controller/itemController.js"></script>
    <script>
        var skuList = [
          <#list itemList as item>
           {
               id:${item.id?c},
               title: '${item.title}',
               price:${item.price?c},
               spec:${item.spec},
               spuId:${item.spuId?c}
           },
          </#list>
        ];

        var img = [
          <#list img as item>
            [
              <#list item as url>
                  '${url}',
              </#list>
            ],
          </#list>
        ];

    </script>
</head>

<body ng-app="wgxcb" ng-controller="itemController" ng-init="num=1;loadSku()">

<!--页面顶部 开始-->
<#include "head.ftl">
<#--图片列表-->
<#--<#list itemList as sku>   -->
<#-- -->
<#--    <#if sku_index == 0>-->
<#-- </#if>-->
<#--   -->
<#--</#list>-->

<#--<#assign imageList=$(itemList[0].imgUrl)?eval>-->
<#--扩展属性-->
<#assign customAttributeList=goodsDesc.customAttributeItems?eval>
<#--规格-->
<#assign specificationList=goodsDesc.specificationItems?eval>

<!--页面顶部 结束-->
<div class="py-container">
    <div id="item">
        <div class="crumb-wrap">
            <ul class="sui-breadcrumb">
                <li>
                    <a href="/user/search.html#?category='${type1.name}">${type1.name}</a>
                </li>
                <li>
                    <a href="/user/search.html#?category='${type2.name}">${type2.name}</a>
                </li>
            </ul>
        </div>
        <!--product-info-->
        <div class="product-info">
            <div class="fl preview-wrap">
                <!--放大镜效果-->
                <div class="zoom">
                    <!--默认第一个预览-->
                    <div id="preview" class="spec-preview">
							<span class="jqzoom">
							        <img jqimg="../../static{{imageList[0]}}"
                                         src="../../static{{imageList[0]}}"
                                         width="400px" height="400px"/>
                        </span>
                    </div>
                    <!--下方的缩略图-->
                    <div class="spec-scroll">
                        <a class="prev">&lt;</a>
                        <!--左右按钮-->
                        <div class="items">
                            <ul>
                                <li ng-repeat="url in imageList" repeat-finish="renderFinish()"><img
                                        src="../../static{{url}}"
                                        bimg="../../static{{url}}"
                                        onmousemove="preview(this)"/></li>
                            </ul>
                        </div>
                        <a class="next">&gt;</a>
                    </div>
                </div>
            </div>
            <div class="fr itemInfo-wrap">
                <div class="sku-name">
                    <h4>{{sku.title}}</h4>
                </div>
            <#--<div class="news"><span>${goods.caption} </span></div>-->
                <div class="summary">
                    <div class="summary-wrap">
                        <div class="fl title">
                            <i>价　　格</i>
                        </div>
                        <div class="fl price">
                            <i>¥</i>
                            <em>{{sku.price}}</em>
                            <span>降价通知</span>
                        </div>
                        <div class="fr remark">
                            <i>累计评价</i><em>{{sku.comment}}</em>
                        </div>
                    </div>
                    <div class="summary-wrap">
                        <div class="fl title">
                            <i>促　　销</i>
                        </div>
                        <div class="fl fix-width">
                            <i class="red-bg">加价购</i>
                            <em class="t-gray">满999.00另加20.00元，或满1999.00另加30.00元，或满2999.00另加40.00元，即可在购物车换
                                购热销商品</em>
                        </div>
                    </div>
                </div>
                <div class="support">
                    <div class="summary-wrap">
                        <div class="fl title">
                            <i>支　　持</i>
                        </div>
                    </div>
                    <div class="summary-wrap">
                        <div class="fl title">
                            <i>配 送 至</i>
                        </div>
                    </div>
                </div>
                <div class="clearfix choose">
                    <div id="specification" class="summary-wrap clearfix">
							<#list specificationList as spec>

                                <dl>
                                    <dt>
                                        <div class="fl title">
                                            <i>${spec.attributeName}</i>
                                        </div>
                                    </dt>
								
								<#list spec.attributeValue as item>
								  <dd><a href="javascript:;"
                                         class="{{isSelected('${spec.attributeName}','${item}')?'selected':''}}"
                                         ng-click="selectSpecification('${spec.attributeName}','${item}')">${item}
                                  </a></dd>
                                </#list>
                                </dl>
                            </#list>

                    </div>

                    <div class="summary-wrap">
                        <div class="fl title">
                            <div class="control-group">
                                <div class="controls">
                                    <input autocomplete="off" ng-model="num" type="text" value="1" minnum="1"
                                           class="itxt"/>
                                    <a href="javascript:void(0)" class="increment plus" ng-click="addNum(1)">+</a>
                                    <a href="javascript:void(0)" class="increment mins" ng-click="addNum(-1)">-</a>
                                </div>
                            </div>
                        </div>
                        <div class="fl">
                            <ul class="btn-choose unstyled">
                                <li>
                                    <a class="sui-btn  btn-danger addshopcar" ng-click="addToCart()">加入购物车</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--product-detail-->
        <div class="clearfix product-detail">
            <div class="fr detail">
                <div class="tab-main intro">
                    <ul class="sui-nav nav-tabs tab-wraped">
                        <li class="active">
                            <a href="#one" data-toggle="tab">
                                <span>商品介绍</span>
                            </a>
                        </li>
                        <li>
                            <a href="#two" data-toggle="tab">
                                <span>规格与包装</span>
                            </a>
                        </li>
                        <li>
                            <a href="#three" data-toggle="tab">
                                <span>售后保障</span>
                            </a>
                        </li>
                        <li>
                            <a href="#four" data-toggle="tab">
                                <span>商品评价</span>
                            </a>
                        </li>

                    </ul>
                    <div class="clearfix"></div>
                    <div class="tab-content tab-wraped">
                        <div id="one" class="tab-pane active">
                            <ul class="goods-intro unstyled">
								   <#list customAttributeList as item>
								      <#if item.value??>
									      <li>${item.text}：${item.value}</li>
                                      </#if>
                                   </#list>
                            </ul>
                            <div class="intro-detail">
                                ${goodsDesc.introduction}
                            </div>
                        </div>
                        <div id="two" class="tab-pane">
                            <p>${goodsDesc.packageList}</p>
                        </div>
                        <div id="three" class="tab-pane">
                            <p>${goodsDesc.saleService}</p>
                        </div>
                        <div id="four" class="tab-pane">
                            <p>商品评价</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--like-->
        <div class="clearfix"></div>
    </div>
</div>
<!-- 底部栏位 -->

<!--页面底部  开始 -->
<#include "foot.ftl">
<!--页面底部  结束 -->
</body>

</html>