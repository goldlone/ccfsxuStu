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
    <link rel="icon" href="/assets/img/favicon.ico">

    <title>CCFSXU会员管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/assets/css/my/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	
	<jsp:include page="/all/header.jsp"></jsp:include>
	
  	<div class="container-fluid">
      <div class="row">
        <!-- start left nav -->
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor: default;">
              <h4 class="sub-header">会员信息</h4></a>
            </li>
            <li><a href="/master/selectMemberInfo" >查询会员信息</a></li>
            <li><a href="/master/insertMemberInfo">录入会员信息</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor:default;">
              <h4 class="sub-header">CSP管理</h4></a>
            </li>
            <li class="active"><a href="/master/selectCSPScore">查询CSP成绩</a></li>
            <li><a href="/master/insertCSPScore">录入CSP成绩</a></li>
            <li><a href="/master/managerCSPApplication">CSP报名管理</a></li>
            <li><a href="/master/analysisCSPScore">会员进步状况分析</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor:default;">
              <h4 class="sub-header">图书馆</h4></a>
            </li>
            <li><a href="/master/selectBookInfo">查询图书信息</a></li>
            <li><a href="/master/borrowBook">借书</a></li>
            <li><a href="/master/backBook">还书</a></li>
          </ul>
        </div>
        <!-- end left nav -->
        
        <!-- start content -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">CSP管理</h1>
          <h4 class="sub-header">查询CSP成绩</h4>

          <!-- 筛选条件 -->
          <div class="table-responsive">
            <form class="form-inline" id="queryForm">
              <label>考试名称</label>
              <select id="certName" name="certNo" class="form-control">
                <option value="0">所有</option>
              </select>
              <label>成绩区间</label>
              <input type="number" class="form-control" name="lowScore" placeholder="大于等于" value="0" max="3">
              ~
              <input type="number" class="form-control" name="highScore" placeholder="小于等于" value="500" max="3">
              <a class="btn btn-default" onclick="queryCSP()">查询</a>
            </form><br>
            <form id="selectScoreByNoForm" class="form-inline">
              <label>查询该会员的所有成绩</label>
              <input type="text" class="form-control" name="no" placeholder="请输入会员号">
              <a class="btn btn-default" onclick="getScoreByNo()">查询</a>
            </form>
          </div>

          <!-- 内容展示 -->
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>会员号</th>
                  <th>姓名</th>
                  <th>认证名称</th>
                  <th>总分</th>
                  <th>第一题</th>
                  <th>第二题</th>
                  <th>第三题</th>
                  <th>第四题</th>
                  <th>第五题</th>
                </tr>
              </thead>
              <tbody id="memberContent">
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
    <script type="text/javascript" src="/assets/js/vendor/jquery-3.2.1.min.js"></script>
    <!-- <script>window.jQuery || document.write('<script src="./assets/js/vendor/jquery.min.js"><\/script>')</script> -->
    <script src="/dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="/assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/assets/js/ie10-viewport-bug-workaround.js"></script>

    <script type="text/javascript">
      // 获取考试集合
      function getCertSet() {
          $.ajax({
              url: "/getCertSet",
              type: "post",
              success: function (res) {
                  console.log(res);
                  for(var i=0; i<res.data.length; i++)
                    $("#certName").append("<option value=\""+res.data[i].no+"\">"+res.data[i].name+"</option>");

              },
              error: function (res) {
                  console.log(res);
              }
          });
      }
      getCertSet();

      // 获取CSP成绩列表
      function queryCSP() {
          $.ajax({
              url: "/queryScore",
              type: "post",
              data: $("#queryForm").serialize(),
              success: function (res) {
                  console.log(res);
                  showScore(res.data)
              },
              error: function (res) {
                  console.log(res);
              }
          });
      }
      
      function getScoreByNo() {
          $.ajax({
              url: "/getScoreByNo",
              type: "post",
              data: $("#selectScoreByNoForm").serialize(),
              success: function (res) {
                  console.log(res);
                  showScore(res.data);
              },
              error: function (res) {
                  console.log(res);
              }
          });
      }
      // 展示获取的会员成绩信息
      function showScore(data) {
          $("#memberContent").empty();
          var str = "";
          for(var i=0; i<data.length; i++) {
              str = str + " <tr> <td>"+
                  data[i].memberNo+"</td> <td>"+
                  data[i].memberName+"</td> <td>"+
                  data[i].certName+"</td> <td>"+
                  data[i].all+"</td> <td>"+
                  data[i].fifth+"</td> <td>"+
                  data[i].sencond+"</td> <td>"+
                  data[i].third+"</td> <td>"+
                  data[i].forth+"</td> <td>"+
                  data[i].fifth+"</td> </tr>";
          }
          $("#memberContent").append(str);
      }
    </script>
    
  </body>
</html>
