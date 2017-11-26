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
        <!-- start content -->

        <div class="col-md-10 col-md-offset-1">
          <h3 align="center" class="sub-header">查询图书信息</h3>
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
              <div class="col-md-4" style="text-align: right">
                <label class="control-label">按类别检索：</label>
              </div>
              <div class="col-md-8">
                <select id="bookType" name="type" class="form-control">
                  <option value="0">所有</option>
                </select>
              </div>
            </div>
            <div class="col-md-3"></div>
          </div><br>
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
              <div class="col-md-4" style="text-align: right">
                <label class="control-label">按部分书名检索：</label>
              </div>
              <div class="col-md-8">
                <div class="input-group" >
                  <input type="text" class="form-control" id="bookName" placeholder="请输入部分书名">
                  <span class="input-group-btn">
                  <button class="btn btn-default" onclick="selectBookByName()" type="button">查询</button>
                </span>
                </div>
              </div>
            </div>
            <div class="col-md-3"></div>
          </div><br>
          <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
              <div class="col-md-4" style="text-align: right">
                <label class="control-label">按ISBN编号检索：</label>
              </div>
              <div class="col-md-8">
                <div class="input-group" >
                  <input type="text" class="form-control" id="isbn" placeholder="请输入ISBN编号">
                  <span class="input-group-btn">
                  <button class="btn btn-default" onclick="selectBookByISBN()" type="button">查询</button>
                </span>
                </div>
              </div>
            </div>
            <div class="col-md-3"></div>
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
              </tbody>
            </table>
          </div>
        </div>
        <!-- end content -->
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
    <script src="/assets/js/bootbox.min.js"></script>

    <script type="text/javascript">
      function getBookTypeSet() {
          $.ajax({
              url: "/getBookType",
              type: "POST",
              success: function (res) {
//                  console.log(res);
                  for(var i=0; i<res.data.length; i++) {
                    $("#bookType").append("<option value=\""+res.data[i].no+"\">"+res.data[i].name+"</option>");
                  }
              },
              error: function (res) {
                  console.log(res);
              }

          });
      }
      getBookTypeSet();

      // 展示图书信息
      function showBook(data) {
        $("#memberContent").empty();
        for(var i=0; i<data.length; i++) {
          $("#memberContent").append("<tr>" +
            "<td>"+data[i].no+"</td>" +
            "<td>"+data[i].name+"</td>" +
            "<td>"+data[i].type+"</td>" +
            "<td>"+data[i].author+"</td>" +
            "<td>"+data[i].publicer+"</td>" +
            "<td>"+data[i].price+"</td>" +
            "<td>"+data[i].inventory+"</td></tr>");
        }
      }
      selectBookByType();
      // 根据类别查询书目信息
      function selectBookByType() {
        $.ajax({
          url: "/selectBookByType",
          type: "post",
          data: {
            typeNo: $("#bookType")[0].value
          },
          success: function (res) {
//            console.log(res);
            showBook(res.data);
          }
        });
      }

      // 监听类别变化
      $("#bookType").change(function() {
//        console.log("选择的类别: "+$("#bookType")[0].value);
        selectBookByType();
      });
      
      // 根据书名查询
      function selectBookByName() {
        var name = trim($("#bookName").val());
        if(name == ""){
          bootbox.alert({
            size: "small",
            title: "提示消息",
            message: "书名不能为空"
          });
          return;
        }
        $.ajax({
          url: "/selectBookByName",
          type: "post",
          data: {
            bookName: name
          },
          success: function (res) {
//            console.log(res);
            showBook(res.data);
          }
        });
      }
      
      // 根据ISBN编码查询
      function selectBookByISBN() {
        var isbn = trim($("#isbn").val());
        if(isbn == ""){
          bootbox.alert({
            size: "small",
            title: "提示消息",
            message: "ISBN编号不能为空"
          });
          return;
        }
        $.ajax({
          url: "/selectBookByISBN",
          type: "post",
          data: {
            isbn: isbn
          },
          success: function (res) {
//            console.log(res);
            $("#memberContent").empty();
            $("#memberContent").append("<tr>" +
              "<td>"+res.data.no+"</td>" +
              "<td>"+res.data.name+"</td>" +
              "<td>"+res.data.type+"</td>" +
              "<td>"+res.data.author+"</td>" +
              "<td>"+res.data.publicer+"</td>" +
              "<td>"+res.data.price+"</td>" +
              "<td>"+res.data.inventory+"</td></tr>");
          }
        });

      }

      // 删除左右两端的空格
      function trim(str){
        return str.replace(/(^\s*)|(\s*$)/g, "");
      }

    </script>

  </body>
</html>