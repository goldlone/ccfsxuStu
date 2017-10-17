<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./favicon.ico">

    <title>CCFSXU会员管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="./dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./assets/css/my/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
  	<div class="container-fluid">
      <div class="row">
		
		<!-- start left nav -->
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor: default;">
            	<h4 class="sub-header">会员信息</h4></a>
            </li>
            <li><a href="./main.jsp" >查询会员信息</a></li>
            <li><a href="./insertMember.jsp">录入会员信息</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor:default;">
            	<h4 class="sub-header">CSP管理</h4></a>
            </li>
            <li><a href="./csp.jsp">查询CSP成绩</a></li>
            <li><a href="./insertScore.jsp">录入CSP成绩</a></li>
            <li><a href="./application.jsp">CSP预报名</a></li>
            <li><a href="./analy.jsp">会员进步状况分析</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor:default;">
            	<h4 class="sub-header">图书馆</h4></a>
            </li>
            <li class="active"><a href="./book.jsp">查询图书信息</a></li>
            <li><a href="./borrowBook.jsp">借书</a></li>
            <li><a href="./backBook.jsp">还书</a></li>
          </ul>
        </div>
		<!-- end left nav -->
        
        <!-- start content -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">图书馆</h1>
          <h4 class="sub-header">查询图书信息</h4>
          <div class="row">
            <div class="col-lg-2">
              <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">类别<span class="caret"></span></button>
                <ul class="dropdown-menu">
                  <li><a href="#">Java</a></li>
                  <li><a href="#">Python</a></li>
                  <li><a href="#">机器学习</a></li>
                  <li><a href="#">操作系统</a></li>
                </ul>
              </div>
            </div>
            <div class="col-lg-3">
              <div class="input-group" >
                <form id="selectMemberByNoForm">
                  <input type="text" class="form-control" name="no" placeholder="请输入部分书名">
                </form>
                  <span class="input-group-btn">
                    <button class="btn btn-default" onclick="selectMemberByNo()" type="button">查询</button>
                  </span>
              </div>
            </div>
          </div>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>ISBN编号</th>
                  <th>书名</th>
                  <th>类别</th>
                  <th>作者</th>
                  <th>出版社</th>
                  <th>价格</th>
                  <th>库存</th>
                </tr>
              </thead>
              <tbody id="memberContent">
                <tr>
                  <td>9787512335264</td>
                  <td>计算机操作系统原理</td>
                  <td>操作系统</td>
                  <td>张霞</td>
                  <td>中国电力出版社</td>
                  <td>35.0</td>
                  <td>1</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!-- end content -->
        
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
    <script type="text/javascript" src="./assets/js/vendor/jquery-3.2.1.min.js"></script>
    <!-- <script>window.jQuery || document.write('<script src="./assets/js/vendor/jquery.min.js"><\/script>')</script> -->
    <script src="./dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="./assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./assets/js/ie10-viewport-bug-workaround.js"></script>
    
  </body>
</html>