<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="no-js">

<head>
    <meta charset="UTF-8">
    <title>黑马旅游后台</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
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
                <li class="active">
                    <a href="index.jsp"><i class="icon-chevron-right"></i> 首页</a>
                </li>
                <li>
                    <a href="#"><i class="icon-chevron-right"></i> 类别管理</a>
                </li>
                <li>
                    <a href="#"><i class="icon-chevron-right"></i> 商家管理</a>
                </li>
                <li>
                    <a href="RouteServlet?action=findByPage"><i class="icon-chevron-right"></i> 线路管理</a>
                </li>

            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content"  style="margin-top:12px;">
            <div class="row-fluid">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <h4>欢迎回来</h4>
                    登录成功，请查看各种统计数据
                </div>

            </div>
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">收藏排行榜</div>
                        <div class="pull-right"><span class="badge badge-warning">查看 更多</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span3">
                            <div class="chart" data-percent="83">83%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">国内游</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="65">65%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">港澳游</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="55">55%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">出境游</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="40">40%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">全球自由行</span>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>
            <div class="row-fluid">
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">会员列表</div>
                            <div class="pull-right"><span class="badge badge-info">1,234</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>会员账号</th>
                                    <th>邮箱</th>
                                    <th>手机号</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Mark</td>
                                    <td>itcast@itheima.com</td>
                                    <td>400-618-9090</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacob</td>
                                    <td>itcast@itheima.com</td>
                                    <td>400-618-9090</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Vincent</td>
                                    <td>itcast@itheima.com</td>
                                    <td>400-618-9090</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">收藏记录</div>
                            <div class="pull-right"><span class="badge badge-info">752</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>收藏线路</th>
                                    <th>收藏时间</th>
                                    <th>会员</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Coat</td>
                                    <td>11/11/2018</td>
                                    <td>$25.12</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacket</td>
                                    <td>11/11/2018</td>
                                    <td>$335.00</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Shoes</td>
                                    <td>11/11/2018</td>
                                    <td>$29.99</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>

        </div>
    </div>
    <hr>
    <footer>
         <p style="text-align:center;">
            传智播客黑马程序员 &copy;版权所有Copyright 2006-2019, All Rights Reserved 技术支持，联系电话：400-618-4000
        </p>
    </footer>
</div>
<!--/.fluid-container-->
<script src="vendors/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="vendors/easypiechart/jquery.easy-pie-chart.js"></script>
<script src="assets/scripts.js"></script>
<script>
    $(function() {
        // Easy pie charts
        $('.chart').easyPieChart({animate: 1000});
    });
</script>
</body>

</html>