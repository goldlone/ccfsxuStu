<%--
  Created by IntelliJ IDEA.
  User: CN
  Date: 2017/11/1
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
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
  <link rel="icon" href="../assets/img/favicon.ico">

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
          <li><a href="/master/updateMemberInfo">修改会员信息</a></li>
        </ul>
        <ul class="nav nav-sidebar">
          <li><a href="#" style="color: #000;cursor:default;">
            <h4 class="sub-header">CSP管理</h4></a>
          </li>
          <li><a href="/master/selectCSPScore">查询CSP成绩</a></li>
          <li><a href="/master/insertCSPScore">录入CSP成绩</a></li>
          <li class="active"><a href="/master/managerCSPApplication">CSP报名管理</a></li>
          <li><a href="/master/analysisCSPScore">会员进步状况分析</a></li>
        </ul>
        <ul class="nav nav-sidebar">
          <li><a href="#" style="color: #000;cursor:default;">
            <h4 class="sub-header">图书馆</h4></a>
          </li>
          <li><a href="/master/borrowBook">借书</a></li>
          <li><a href="/master/backBook">还书</a></li>
          <li><a href="/master/insertBook">导入图书</a></li>
        </ul>
      </div>
      <!-- end left nav -->

      <!-- start content -->
      <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header">CSP管理</h1>

        <h4 class="sub-header">下载CSP报名信息表</h4>
        <div class="table-responsive">
          <form class="form-inline" id="downloadForm">
            <label>考试名称</label>
            <select id="certName" name="certNo" class="form-control">
              <option value="0">所有</option>
            </select>
            <a class="btn btn-default" onclick="queryApplication()">下载</a>
          </form>
        </div>

        <br>
        <h4 class="sub-header">添加CSP认证考试</h4>
        <div class="table-responsive">
          <form class="form-horizontal col-md-12" id="addCertForm" role="form">
            <div class="form-group">
              <label class="col-md-2 control-label" style="text-align: center">认证名称</label>
              <div class="col-md-4">
                <input class="form-control" name="name" type="text" value="第*次CCF计算机软件能力认证" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label" style="text-align: center">认证开始时间</label>
              <div class="col-md-4">
                <input class="form-control" name="startTime" type="datetime-local" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label" style="text-align: center">认证结束时间</label>
              <div class="col-md-4">
                <input class="form-control" name="endTime" type="datetime-local" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label" style="text-align: center" >会员报名费</label>
              <div class="col-md-4">
                <input class="form-control" name="memberFee" type="number" value="180" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-2 control-label" style="text-align: center">非会员报名费</label>
              <div class="col-md-4">
                <input class="form-control" name="notMemberFee" type="number" value="300" />
              </div>
            </div>

            <a  class="btn btn-md btn-primary col-md-2 col-md-offset-2"  onclick="addCert()">添加</a><br><br>
          </form>
          <br><br><br><br><br><br>
        </div>
      </div>
      <!-- end content -->

    </div>
  </div>

  <!-- Bootstrap core JavaScript
  ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
  <script src="/assets/js/vendor/jquery-3.2.1.min.js"></script>
  <!-- <script>window.jQuery || document.write('<script src="./assets/js/vendor/jquery.min.js"><\/script>')</script> -->
  <script src="/dist/js/bootstrap.min.js"></script>
  <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
  <script src="/assets/js/vendor/holder.min.js"></script>
  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
  <script src="/assets/js/bootbox.min.js"></script>

  <script type="text/javascript">
    // 获取考试集合
    function getCertSet() {
      $.ajax({
        url: "/getCertSet",
        type: "post",
        success: function (res) {
//          console.log(res);
          for(var i=0; i<res.data.length; i++)
            $("#certName").append("<option value=\""+res.data[i].no+"\">"+res.data[i].name+"</option>");

        },
        error: function (res) {
          console.log(res);
        }
      });
    }
    getCertSet();

    console.log();
    // 告诉系统可以下载了
    function queryApplication() {
//      window.open("/excel/applicate.xls", '', '', false);
      $.ajax({
        url: "/getApplication",
        type: "post",
        data: $("#downloadForm").serialize(),
        success: function (res) {
          console.log(res);
          if(res.ret) {
            var temp = window.location.href;
            temp = temp.slice(0, temp.indexOf("/master"));
            window.open(temp+"/excel/applicate.xls", '', '', false);
          } else {
            bootbox.alert("导出失败");
          }
        },
        fail: function (res) {
          bootbox.alert("导出失败");
        }
      });
    }

    // 添加CSP认证
    function addCert() {
      bootbox.confirm({
        title: "请核实填写的信息？",
        message: "请确认认证考试信息填写完整，*使用中文数字代替。",
        buttons: {
          cancel: {
            label: '<i class="fa fa-times"></i> 取消'
          },
          confirm: {
            label: '<i class="fa fa-check"></i> 确认'
          }
        },
        callback: function (result) {
          if(result) {
            add();
          }
        }
      });

      function add() {
        $.ajax({
          url:"/addCertification",
          type: "post",
          data: $("#addCertForm").serialize(),
          success: function (res) {
            if(res.ret)
              bootbox.alert("添加成功");
            else
              bootbox.alert("添加失败，请检查信息是否填写完整和准确，或者该认证已被添加");
          },
          fail: function (res) {
            console.log(res);
            bootbox.alert("添加失败，服务器内部故障");
          }
        });
      }
    }
  </script>

</body>
</html>

