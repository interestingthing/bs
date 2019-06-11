<div id="nav-bottom">
	<!--顶部-->
	<div class="nav-top">
        <div class="top">
            <div class="py-container">
                <div class="shortcut">
                    <ul class="fl">
                        <li class="f-item">欢迎您！&nbsp;&nbsp;</li>
                        <#--<div class="f-item" th:if="${username}">-->
                            <#--<li class="f-item" th:if="${username}" th:text="${username}"></li>-->
                            <#--<li class="f-item">&nbsp;&nbsp;<a th:href="@{/user/logout}" >注销</a>&nbsp;&nbsp;-->
                            <#--</li>-->
                        <#--</div>-->

                        <#--<div class="f-item" th:unless="${username}">-->
                            <#--<li class="f-item">&nbsp;&nbsp;请<a href="/user/login.html" >登录</a>&nbsp;&nbsp;-->
                            <#--</li>-->
                            <#--<span><a href="register.html" >免费注册</a></span>-->
                        <#--</div>-->
                    </ul>
                    <ul class="fr">
                        <li class="f-item"><a href="/user/index.html" >网站首页</a></li>
                        <li class="f-item space"></li>
                        <li class="f-item"><a href="/user/home-index.html" >我的主页</a></li>
                        <li class="f-item space"></li>
                        <li class="f-item"><a href="/operate/login.html" >运营商后台</a></li>
                        <li class="f-item space"></li>
                        <li class="f-item"><a href="/store/login.html" >商家后台</a></li>
                        <!--<li class="f-item" id="service">-->
                        <!--<span>客户服务</span>-->
                        <!--<ul class="service">-->

                        <!--</ul>-->
                        <!--</li>-->
                    </ul>
                </div>
            </div>
        </div>

		<!--头部-->
		<div class="header">
			<div class="py-container">
				<div class="yui3-g Logo">
					<div class="yui3-u Left logoArea">
                            <a href="/user/index" class="fl logo"></a>
					</div>
					<div class="yui3-u Center searchArea">
						<div class="search">
							<form action="" class="sui-form form-inline">
								<!--searchAutoComplete-->
								<div class="input-append">
									<input id="autocomplete" type="text" class="input-error input-xxlarge" />
									<button class="sui-btn btn-xlarge btn-danger" type="button">搜索</button>
								</div>
							</form>
						</div>
					</div>
					<div class="yui3-u Right shopArea">
						<div class="fr shopcar">
							<div class="show-shopcar" id="shopcar">
								<span class="car"></span>
								<a class="sui-btn btn-default btn-xlarge" href="cart.html" >
									<span>我的购物车</span>
									<i class="shopnum">0</i>
								</a>
								<div class="clearfix shopcarlist" id="shopcarlist" style="display:none">
									<p>"啊哦，你的购物车还没有商品哦！"</p>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="yui3-g NavList">
					<div class="yui3-u Left all-sort">
						<h4>全部商品分类</h4>
					</div>
					<div class="yui3-u Center navArea">
						<ul class="nav">
							<li class="f-item">服装城</li>
							<li class="f-item">美妆馆</li>
						</ul>
					</div>
					<div class="yui3-u Right"></div>
				</div>
			</div>
		</div>
	</div>
</div>