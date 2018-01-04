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
    <meta name="author" content="CN">
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

    <!-- HTML5 shim and Respond.js r IEfo8 support of HTML5 elements and media queries -->
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
            <li class="active"><a href="/master/selectMemberInfo" >查询会员信息</a></li>
            <li><a href="/master/insertMemberInfo">录入会员信息</a></li>
            <li><a href="/master/updateMemberInfo">修改会员信息</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="#" style="color: #000;cursor:default;">
              <h4 class="sub-header">CSP管理</h4></a>
            </li>
            <li><a href="/master/selectCSPScore">查询CSP成绩</a></li>
            <li><a href="/master/insertCSPScore">录入CSP成绩</a></li>
            <li><a href="/master/managerCSPApplication">CSP报名管理</a></li>
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

          <h1 class="page-header">会员信息</h1>
         	<div id="memberDetail"></div>

          <h4 class="sub-header">查询会员信息&nbsp;&nbsp;<span class="label label-success" id="totalNum">当前显示：0</span></h4>
          <div class="table-responsive">
          	<!-- 筛选条件 -->
          	<form class="form-inline" id="queryForm">
          		<div class="form-group">
						    <label>年级</label>
						    <select name="grade" class="form-control" id="gradeSet">
						    	<option value="0">所有</option>
						    </select>
						  </div>
          		<div class="form-group">
						    <label>是否过期</label>
						    <select name="endTime" class="form-control">
						    	<option value="all">所有</option>
						    	<option value="1">过期</option>
						    	<option value="0">未过期</option>
						    </select>
						  </div>
          		<div class="form-group">
						    <label>会员号</label>
						    <input type="text" class="form-control" name="no" placeholder="请输入会员号">
						  </div>
							<a class="btn btn-default" onclick="queryMember()">查询</a>
          	</form>
          	<br>
          	<!-- 显示会员信息内容 -->
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>会员号</th>
                  <th>姓名</th>
                  <th>邮箱</th>
                  <th>年级</th>
                  <th>过期时间</th>
                  <th>...</th>
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
        // 存储全部会员信息
    	var datas;
        getGradeSet();
    	// 获取年级集合
    	function getGradeSet() {
            $.ajax({
                type:"post",
                url:"/getGradeSet",
                error: function (res) {
                    console.log("年级集合请求出错");
                    console.log(res);
                },
                success: function (res) {
                    addGrageSet(res.data);
                }
            });
        }
    	// 展示年级信息
    	function addGrageSet(data) {
    		var str = "";
    		for(var i=0; i<data.length; i++) {
    			str += "<option value=\""+data[i]+"\">"+data[i]+"</option>";
    		}
    		$("#gradeSet").append(str);
    	}

    	// 展示信息
    	function showData(data) {
        $("#totalNum").empty();
        $("#totalNum").append("当前显示："+data.length)
    		$("#memberContent").empty();
    		for(var i=0; i<data.length; i++) {
    			var str = "";
    			if(data[i].addScore>0){
    				str = "<tr style=\"background-color: #FFFF33\"><td>"+data[i].no+"</td><td>";
    			}else {
    				str = "<tr><td>"+data[i].no+"</td><td>";
    			}
    			str = str+
                data[i].name+"</td><td>"+
                data[i].email+"</td><td>"+
                data[i].grade+"</td><td>"+
                data[i].endTime+"</td><td><a href=\""+
                "#"+"\" onclick=\"showDetail("+i+")\">更多信息</a></td></tr>"
               $("#memberContent").append(str);
     		}
    	}

    	// 查询会员信息
    	function queryMember() {
    		$.ajax({
    			type:"post",
                url:"/queryMember",
                data: $('#queryForm').serialize(),
                error: function (res) {
                    console.log(res);
                },
                success: function (res) {
                    console.log(res);
                    datas = res.data;
                    showData(res.data);
                }
    		});
    	}
    	queryMember();

    	// 查看会员详细信息
    	function showDetail(num) {
    		// console.log(datas);
    		$("#memberDetail").empty();
    		var str = "<h4 class=\"sub-header\">会员信息详情</h4><table class=\"table table-bordered\">"+
                "<tbody><tr><td><b>姓名</b></td><td>"+datas[num].name+"</td><td><b>年级</b></td><td>"+datas[num].grade+"</td></tr>"+
                "<tr><td><b>会员号</b></td><td>"+datas[num].no+"</td><td><b>学号</b></td><td>"+datas[num].stuNo+"</td></tr>"+
                "<tr><td><b>手机</b></td><td>"+datas[num].phone+"</td><td><b>邮箱</b></td><td>"+datas[num].email+"</td></tr>"+
                "<tr><td><b>班级</b></td><td>"+datas[num].classNum+"</td><td><b>专业</b></td><td>"+datas[num].discipline+"</td></tr>"+
                "<tr><td><b>学历</b></td><td>"+datas[num].degree+"</td><td><b>会员类型</b></td><td>"+datas[num].memberType+"</td></tr>"+
                "<tr><td><b>生效时间</b></td><td>"+datas[num].startTime+"</td><td><b>过期时间</b></td><td>"+datas[num].endTime+"</td></tr></tbody></table>";
				$("#memberDetail").append(str);
    	}
    </script>
  </body>
</html>