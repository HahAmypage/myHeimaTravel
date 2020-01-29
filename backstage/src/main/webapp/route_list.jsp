<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>线路管理-黑马旅游后台</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

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
                <li class="active">
                    <a href="RouteServlet?action=findByPage"><i class="icon-chevron-right"></i> 线路管理</a>
                </li>

            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content">

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">线路管理</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div class="table-toolbar">
                                <div class="btn-group">
                                    <a href="RouteServlet?action=addRouteUI" class="btn btn-success">添加旅游线路<i class="icon-plus icon-white"></i></a>

                                </div>
                                <form class="form-horizontal" id="searchForm" method="post" action="RouteServlet">
                                    <fieldset>
                                        <div class="control-group">
                                            <div class="controls" style="float:right;">
                                                <input type="hidden" name="action" value="findByPage" >
                                                <input type="hidden" name="curPage" value="1">
                                                <input class="input-xlarge focused" id="rname" name="rname"  value="${rname}" type="text" placeholder="请输入线路名称..." style="width:300px;">
                                                <button type="submit" class="btn" style="width:100px"><i class="icon-search"></i>&nbsp;查询</button>
                                                <button type="button"  class="btn btn-danger"  onclick="batchDel();" style="width:100px"><i class="icon-trash icon-white"></i> 批量删除</button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        <form id="listForm" action="RouteServlet?action=batchDelRoutes" method="post">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example2">
                                <thead>
                                <tr>
                                    <th><i class="icon-check"></i> 勾选</th>
                                    <th>编号</th>
                                    <th>图片</th>
                                    <th width="50%">线路名称</th>
                                    <th>线路价格</th>
                                    <th>收藏数量</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageBean.dataList}" var="route">
                                    <tr class="odd">
                                        <td><input  type="checkbox" name="itemSelect" value="${route.rid}"></td>
                                        <td>${route.rid}</td>
                                        <td class="center" style="text-align: center;">
                                            <img src="${route.rimage}" width="99px"/></td>
                                        <td style="word-break:break-all; word-wrap:break-word;">${route.rname}</td>
                                        <td>${route.price}</td>
                                        <td class="center">${route.count}</td>
                                        <td style="text-align:center;">
                                            <a class="btn btn-primary" href="RouteServlet?action=findRouteByRid&rid=${route.rid}"><i class="icon-pencil icon-white"></i> 修改</a>
                                            <a class="btn btn-danger" onclick="delRoute(${route.rid})"><i class="icon-remove icon-white"></i> 删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </form>
                            <div class="pagination">
                                <ul>
                                    <li><a href="RouteServlet?action=findByPage&curPage=1&rname=${rname}">首页</a></li>
                                    <c:if test="${pageBean.curPage > 1}">
                                        <li><a href="RouteServlet?action=findByPage&curPage=${pageBean.prePage}&rname=${rname}">上一页</a></li>
                                    </c:if>

                                    <c:forEach begin="${pageBean.beginPage}" end="${pageBean.endPage}" var="pageNo">
                                        <li ${pageBean.curPage == pageNo ? 'class="active"':''}>
                                            <a href="RouteServlet?action=findByPage&curPage=${pageNo}&rname=${rname}">${pageNo}</a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${pageBean.curPage<pageBean.totalPage}">
                                    <li><a href="RouteServlet?action=findByPage&curPage=${pageBean.nextPage}&rname=${rname}">下一页</a></li>
                                    </c:if>
                                    <li><a href="RouteServlet?action=findByPage&curPage=${pageBean.totalPage}&rname=${rname}">末页</a></li>
                                </ul>
                                <ul>
                                    <li><a style="border:0px;font-weight:bold;">共${pageBean.totalPage}页</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>
        </div>
    </div>
    <footer>
         <p style="text-align:center;">
            传智播客黑马程序员 &copy;版权所有Copyright 2006-2019, All Rights Reserved 技术支持，联系电话：400-618-4000
        </p>
    </footer>
</div>
<!--/.fluid-container-->
<script src="vendors/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

    function delRoute(rid) {
        if (confirm("您是否要删除该记录？")) {
            location.href="RouteServlet?action=delRoute&rid="+rid;
        }
    }
    
    function batchDel() {
        //获取勾选的复选框个数
        var length = $("input[name='itemSelect']:checked").length;
        //判断个数大于0提示是否删除
        if (length > 0){
            if (confirm("你是否要删除"+length+"条数据？")){
                $("#listForm").submit();
            }
        } else {
            //判断个数为0提示用户需要勾选才能操作
            alert("你还没有勾选线路");
        }

    }

</script>

</body>

</html>