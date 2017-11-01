<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="assets/img/favicon.ico">

    <title>登录CCFSXU会员管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="./dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./assets/css/my/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="./assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">
      <h1 align="center">登录CCFSXU会员管理系统</h1><br><br><br>
      <form action="" method="POST" class="form-signin" id="loginForm">
      
        <label for="inputEmail" class="sr-only">邮箱</label>
        <input name="email" type="email" id="inputEmail" class="form-control" placeholder="邮箱" required autofocus>
        
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="passwd" type="password" id="inputPassword" class="form-control" placeholder="密码" required>

        <div id="warnInfo" class="alert alert-danger" role="alert"></div>
        <a class="btn btn-lg btn-primary btn-block" onclick="login()">登录</a>
      </form>
    </div>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./assets/js/ie10-viewport-bug-workaround.js"></script>
    <script type="text/javascript" src="./assets/js/vendor/jquery-3.2.1.min.js"></script>

    <script type="text/javascript">
      $("#warnInfo").hide();
      function login() {
          if(isEmail($("#inputEmail").val())) {
              $.ajax({
                  type: "post",
                  url: "login",
                  data: $('#loginForm').serialize(),
                  error: function (res) {
                      console.log(res);
                  },
                  success: function (res) {
                      console.log(res);
                      if(res.ret) {
                          // 登录成功，跳转
                          window.location.href='master/main.jsp';
                      }else {
                          switch (res.code) {
                              case 10002:// 密码错误
                                  $("#warnInfo").empty();
                                  $("#warnInfo").append("<strong>密码错误</strong>");
                                  $("#warnInfo").show();
                                  break;
                              case 10003:// 未注册
                                  $("#warnInfo").empty();
                                  $("#warnInfo").append("<strong>未注册</strong>");
                                  $("#warnInfo").show();
                                  break;
                              case 10004:// 邮箱为空
                                  $("#warnInfo").empty();
                                  $("#warnInfo").append("<strong>邮箱不能为空</strong>");
                                  $("#warnInfo").show();
                                  break;
                              case 10005:// 密码为空
                                  $("#warnInfo").empty();
                                  $("#warnInfo").append("<strong>密码不能为空</strong>");
                                  $("#warnInfo").show();
                                  break;
                          }
                      }
                  }
              });
          } else {
              $("#warnInfo").empty();
              $("#warnInfo").append("<strong>请填入正确的邮箱</strong>");
              $("#warnInfo").show();
          }

      }

       // 校验邮箱合法性
       function isEmail(str){
         var reg = /^([a-zA-Z0-9])+@([a-zA-Z0-9])+(.[a-zA-Z0-9])+/;
         return reg.test(str);
       }
    </script>
  </body>
</html>