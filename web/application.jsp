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
            <li class="active"><a href="./application.jsp">CSP预报名</a></li>
            <li><a href="./analy.jsp">会员进步状况分析</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor:default;">
            	<h4 class="sub-header">图书馆</h4></a>
            </li>
            <li><a href="./book.jsp">查询图书信息</a></li>
            <li><a href="./borrowBook.jsp">借书</a></li>
            <li><a href="./backBook.jsp">还书</a></li>
          </ul>
        </div>
		<!-- end left nav -->
        
        <!-- start content -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">CSP管理</h1>
          <h4 class="sub-header">CSP预报名</h4>
			<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-md-1 control-label">考试名称</label>
			    <div class="col-md-4">
			      <select class="form-control" required>
						  <option>第十二次软件能力认证</option>
						  <option>2017 CCF CCSP</option>
						</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">考生姓名</label>
			    <div class="col-md-4">
			    	<span class="form-control">程宁</span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">会员号</label>
			    <div class="col-md-4">
			    	<span class="form-control">65535G</span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">身份证号</label>
			    <div class="col-md-4">
			    	<span class="form-control">1421212121212121212</span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">手机号</label>
			    <div class="col-md-4">
			    	<input type="text" class="form-control" placeholder="请输入手机号" required>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">邮箱</label>
			    <div class="col-md-4">
			    	<span class="form-control">857353825@qq.com</span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">编程语言</label>
			    <div class="col-md-4">
			      <select class="form-control">
						  <option>C/C++</option>
						  <option>Java</option>
						</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">考生身份</label>
			    <div class="col-md-4">
			      <select class="form-control">
						  <option>大一</option>
						  <option>大二</option>
						  <option>大三</option>
						  <option>大四</option>
						  <option>研一</option>
						  <option>研二</option>
						  <option>在读博士</option>
						  <option>其他</option>
						</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">所在单位</label>
			    <div class="col-md-4">
			    	<span class="form-control">山西大学</span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">认证目的</label>
			    <div class="col-md-4">
			      <select class="form-control" id="purpose" required>
						  <option value="就业">就业</option>
						  <option value="考研">考研</option>
						  <option value="其他">其他</option>
						</select>
			    </div>
					<div class="col-md-4">
						<input id="kaoyan" type="text" style="display: none;" class="form-control" placeholder="请输入考研意向学校">
					</div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">CSP账号</label>
			    <div class="col-md-4">
			    	<input type="text" class="form-control" placeholder="请输入登录账号，没有先注册" required>
			    </div>
			    <div class="col-md-1"><a class="btn btn-info" target="_blank" href="http://www.cspro.org/lead/application/ccf/login.jsp">登录尝试</a></div>
			  </div>
			  <div class="form-group">
			    <label class="col-md-1 control-label">登录密码</label>
			    <div class="col-md-4">
			    	<input type="password" class="form-control" placeholder="请输入登录密码" required>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="exampleInputFile" class="col-md-1 control-label">上传照片</label>
			    <div class="col-md-4 control-label">
			    	<input type="file" id="exampleInputFile">
			    </div>
			  </div>
			  <div class="col-md-5">
			  	<button class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
				</div>
			</form>
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