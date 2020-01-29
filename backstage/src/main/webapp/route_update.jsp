<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>修改线路-黑马旅游后台</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <style>
        .row-fluid input[type='radio'].span6{width:5%;}
		.controls span{
            display: none;
        }
    </style>
</head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse collapse" style="line-height:60px;">
                <img src="images/logo.png">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> 个人信息 <i class="caret"></i>

                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="#">个人中心</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a tabindex="-1" href="login.html">注销</a>
                            </li>
                        </ul>
                    </li>
                </ul>

            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href="index.jsp"><i class="icon-chevron-right"></i> 首页</a>
                </li>
                <li>
                    <a href="#"><i class="icon-chevron-right"></i> 类别管理</a>
                </li>
                <li>
                    <a href="#"><i class="icon-chevron-right"></i> 商家管理</a>
                </li>
                <li  class="active">
                    <a href="RouteServlet?action=findByPage"><i class="icon-chevron-right"></i> 线路管理</a>
                </li>

            </ul>
        </div>
        <div class="span9" id="content">

            <!-- validation -->
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" style="font-weight:bold;font-size:20px;color:#08c;">线路修改操作</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <!-- BEGIN FORM-->
                            <form action="RouteServlet?action=updateRoute" enctype="multipart/form-data" method="post" id="form_sample_1" class="form-horizontal">
                                <input type="hidden" name="rid" value="${route.rid}">
                                <fieldset>
                                    <div class="alert alert-error hide">
                                        <button class="close" data-dismiss="alert"></button>
                                        You have some form errors. Please check below.
                                    </div>
                                    <div class="alert alert-success hide">
                                        <button class="close" data-dismiss="alert"></button>
                                        Your form validation is successful!
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">线路名字<span class="required">*</span></label>
                                        <div class="controls">
                                            <textarea type="text" id="rname" name="rname"  data-required="1"  class="span6 m-wrap">${route.rname}</textarea>
                                            <span id="rnameMsg" class="alert alert-error"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">价格<span class="required">*</span></label>
                                        <div class="controls">
                                            <input id="price" name="price" type="text" value="${route.price}" class="span6 m-wrap"/>
                                            <span id="priceMsg" class="alert alert-error"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">介绍<span class="required">*</span></label>
                                        <div class="controls">
                                            <textarea id="routeIntroduce" name="routeIntroduce"   type="text" class="span6 m-wrap">${route.routeIntroduce}</textarea>
                                            <span id="routeIntroduceMsg" class="alert alert-error"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">是否上架<span class="required">*</span></label>
                                        <div class="controls">
                                            <input name="rflag" type="radio" ${route.rflag == '1' ? 'checked':''} checked="checked" value="1" class="span6" />是
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <input name="rflag" type="radio" ${route.rflag == '0' ? 'checked':''} value="0" class="span6"/>否
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">图片<span class="required">*</span></label>
                                        <div class="controls">
                                            <img id="rimagePreView" data-src="holder.js/299x169" alt="299x169" style="width: 299px; height: 169px;"
                                                 src="${route.rimage}">
                                            <br/><br/>
                                            <input name="rimage" type="file" onchange="changeImg(this)" class="span6 m-wrap"/>
                                        </div>
                                        <script type="text/javascript">
                                            function changeImg(file) {
                                                //获取上传文件对象
                                                var obj=file.files[0];
                                                //根据上传文件图片对象在浏览器内存里创建预览图片
                                                var wuc=window.URL.createObjectURL(obj);
                                                //将预览图片设置到预览位置显示
                                                $("#rimagePreView").attr('src',wuc);
                                                //当预览图片显示加载后，释放内存预览图片对象
                                                $("#rimagePreView").load(function (){
                                                    window.URL.revokeObjectURL(wuc);//当图片加载后，释放内存空间
                                                });
                                            }
                                        </script>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">类别<span class="required">*</span></label>
                                        <div class="controls">
                                            <select class="span6 m-wrap" name="cid">
                                                <option value="">请选择...</option>
                                                <c:forEach items="${categoryList}" var="category">
                                                    <option value="${category.cid}" ${category.cid == route.cid ? 'selected':''}>${category.cname}</option>
                                                </c:forEach>
                                            </select>
                                            <span id="cidMsg" class="alert alert-error"></span>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-primary">提交修改</button>
                                        <a type="button" class="btn" href="javascript:history.back();">撤销返回</a>
										<!--放错误消息标签-->
                                        <c:if test="${not empty errorMsg}">
										    <span class="alert alert-error">${errorMsg}</span>
                                        </c:if>
                                    </div>
                                </fieldset>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>
            <!-- /validation -->


        </div>
    </div>
    <hr>
    <footer>
         <p style="text-align:center;">
            传智播客黑马程序员 &copy;版权所有Copyright 2006-2019, All Rights Reserved 技术支持，联系电话：400-618-4000
        </p>
    </footer>
</div>
<script src="vendors/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

</body>

</html>