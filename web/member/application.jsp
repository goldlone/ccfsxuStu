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

    <title>CSP预报名</title>

    <!-- Bootstrap core CSS -->
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../assets/css/my/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
	
	<jsp:include page="../all/header.jsp"></jsp:include>

		<!-- start content -->
		<div class="container-fluid">
			<h2 align="center">CSP预报名</h2><hr>
			<div class="col-md-5 col-sm-offset-4">
				<div class="alert alert-info" role="alert">初次参加CSP认证需提供一张一寸照片<br>非第一次不必上传</div>
			</div>
			<div class="col-sm-7 col-md-10 col-sm-offset-4 main">
				<form class="form-horizontal" id="applicateForm">
					<div class="form-group">
						<label class="col-md-1 control-label">考试名称</label>
						<div class="col-md-4">
							<select class="form-control" id="certSet" required name="certNo">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">考生姓名</label>
						<div class="col-md-4">
							<input class="form-control" id="name" name="name" value="程宁">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">会员号</label>
						<div class="col-md-4">
							<input class="form-control" name="memberNo" value="65535G" >
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">身份证号</label>
						<div class="col-md-4">
							<input class="form-control" name="id" placeholder="请输入身份证号" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">手机号</label>
						<div class="col-md-4">
							<input type="number" name="phone" class="form-control" placeholder="请输入手机号" required>
						</div>
					</div>
					<div class="">
						<label class="col-md-1 control-label">邮箱</label>
						<div class="col-md-4">
							<input class="form-control" name="email" value="857353825@qq.com" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">编程语言</label>
						<div class="col-md-4">
							<select class="form-control" name="language">
								<option value="C/C++">C/C++</option>
								<option value="Java">Java</option>
						</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">考生身份</label>
						<div class="col-md-4">
							<select name="degree" class="form-control">
							<option value="大一">大一</option>
							<option value="大二">大二</option>
							<option value="大三">大三</option>
							<option value="大四">大四</option>
							<option value="研一">研一</option>
							<option value="研二">研二</option>
							<option value="研三">研三</option>
							<option value="在读博士">在读博士</option>
							<option value="其他">其他</option>
						</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">认证目的</label>
						<div class="col-md-4">
							<select class="form-control" name="purpose" id="purpose" required onchange="showkaoyanInput()">
								<option value="就业">就业</option>
								<option value="考研">考研</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="col-md-2">
							<input name="school" id="kaoyan" type="text" style="display: none;" class="form-control" placeholder="考研意向学校">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">CSP账号</label>
						<div class="col-md-4">
							<input type="text" name="username" class="form-control" placeholder="请输入登录账号，没有先注册" required>
						</div>
						<div class="col-md-1"><a class="btn btn-info" target="_blank" href="http://www.cspro.org/lead/application/ccf/login.jsp">登录测试</a></div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">登录密码</label>
						<div class="col-md-4">
							<input type="password" name="password" class="form-control" placeholder="请输入登录密码" required>
						</div>
					</div>
					<div class="col-md-5">
						<button class="btn btn-lg btn-primary btn-block" type="button" onclick="showTips()">提交</button>
					</div>
				</form>
			</div>
			<div class="col-sm-7 col-md-10 col-sm-offset-4 main">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="exampleInputFile" class="col-md-1 control-label">上传照片</label>
						<div class="col-md-4 control-label">
							<input type="file" id="exampleInputFile">
						</div>
					</div>
					<div class="col-md-5">
						<button  id="fileInput" class="btn btn-lg btn-primary btn-block" type="button" onclick="uploadPhoto()">确认上传</button>
					</div>
				</form>
			</div>
			<!-- end content -->
	</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
    <script type="text/javascript" src="../assets/js/vendor/jquery-3.2.1.min.js"></script>
    <!-- <script>window.jQuery || document.write('<script src="./assets/js/vendor/jquery.min.js"><\/script>')</script> -->
    <script src="../dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
		<script src="../assets/js/bootbox.min.js"></script>

  	<script text="text/javascript">
      getCertSetNotStart();
			// 获取未开始认证考试的集合
			function getCertSetNotStart() {
				$.ajax({
					type:"POST",
					url:"getCertSetNotStart",
					success: function (res) {
						var tempStr;
						for(var i=0; i<res.data.length; i++) {
							tempStr = "<option value=\""+res.data[i].no+"\">"+res.data[i].name+"</option>";
						}
						$("#certSet").append(tempStr);
					},
					fail: function (res) {
						console.log(res);
					}
				});
			}
			// 选择考研后出现填写意向学校
			function showkaoyanInput() {
				if($("#purpose").val() == "考研") {
					$("#kaoyan").show();
				} else {
							$("#kaoyan").hide();
				}
			}

			// 报名
			function applicate() {
				$.ajax({
					type:"POST",
					url:"applicate",
					data: $("#applicateForm").serialize(),
					success: function (res) {
					  if(res.ret)
            	bootbox.alert("报名成功");
					  else
              bootbox.alert("报名失败，请联系工作人员");
//            console.log(res);
					},
					fail: function (res) {
					  bootbox.alert("报名失败，请联系工作人员");
						console.log(res);
					}
				});
			}
			// 弹出提示框
			function showTips() {
			  var obj = $("#applicateForm").serializeJson();
				if(obj.certNo==null || obj.id == ""
					|| obj.degree=="" || obj.email==""
					|| obj.language=="" || obj.memberNo==""
					|| obj.name=="" || obj.password==""
					|| obj.phone=="" || obj.purpose==""
					|| obj.username=="") {
          bootbox.alert({
            size: "small",
            title: "提示消息",
            message: "您的信息填写不完整!",
            callback: function(){  }
          });
				  return;
				}
				bootbox.confirm({
					title: "请核实你的报名信息？",
					message: "若报名信息无误，请准备缴纳保证金。",
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
              applicate();
						}
					}
				});
			}
      ( function ($){
        $.fn.serializeJson= function (){
          var serializeObj={};
          $( this .serializeArray()).each( function (){
            serializeObj[ this .name]= this .value;
          });
          return serializeObj;
        };
      })(jQuery);
      (function($){
        $.fn.serializeJson=function(){
          var serializeObj={};
          $(this.serializeArray()).each(function(){
            serializeObj[this.name]=this.value;
          });
          return serializeObj;
        };
      })(jQuery);

      var xhr = new XMLHttpRequest();
      // 上传照片
			function uploadPhoto() {
        var fileObj = $("#exampleInputFile")[0].files[0];
        var FileController = "uploadPhoto";
        // FormData 对象
        var form = new FormData();
        form.append("photo", fileObj);// 文件对象
				form.append("memberNo", document.getElementsByName("memberNo").item(0).value);
        // XMLHttpRequest 对象
        xhr.open("post", FileController, true);
        xhr.onload = function () {
//           alert("上传完成!");
        };
        xhr.send(form);
        xhr.onreadystatechange = callbackUpload;
      }
      function callbackUpload() {
        if(xhr.readyState == 4 && xhr.status == 200) {
//					var temp = xhr.responseText;
//					console.log(temp);
					var obj = jQuery.parseJSON(xhr.responseText);
//					console.log(obj);
					if(obj.ret) {
						bootbox.alert({
							size: "small",
							title: "提示信息",
							message: "提交成功!",
							callback: function(){  }
						});
					} else {
						bootbox.alert({
							size: "small",
							title: "提交失败",
							message: "请在填写报名信息后再提交照片!",
							callback: function(){  }
						});
					}
      	}
      }
		</script>

  </body>
</html>