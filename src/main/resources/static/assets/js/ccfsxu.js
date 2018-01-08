var myUrl = "http://127.0.0.1:8080";

/***  工具方法  ***/
// 校验邮箱合法性
function isEmail(str){
  var reg = /^([a-zA-Z0-9])+@([a-zA-Z0-9])+(.[a-zA-Z0-9])+/;
  return reg.test(str);
}
// 检测Input是否为空
function isNull(val) {
  var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
  if (str == '' || str == undefined || str == null) {
    return true;
  } else {
    return false;
  }
}
// 删除左右两端的空格
function trim(str){
  return str.replace(/(^\s*)|(\s*$)/g, "");
}


/***  index.html  ***/
// 展示登录还是注销
function initHeadView() {
  $.ajax({
    url: myUrl+"/isLogin",
    type: "post",
    success: function (res) {
      if(res) {
        $("#login-link").empty();
        $("#login-link").append("<li><a href=\"login.html\" onclick='logout()'>注销</a></li>");
        $("#update-password-link").show();
      }
      else {
        $("#login-link").empty();
        $("#login-link").append("<li><a href=\"login.html\">登录</a></li>");
      }
    }
  });
}

// 注销登录
function logout() {
  $.ajax({
    url: myUrl+"/logout",
    type: "post",
    success: function (res) {
      window.location.href = "/login.html";
    }
  });
}

/***  login.html  ***/
// 登录
function login() {
  if(isEmail($("#inputEmail").val())) {
    if(!isNull($("#inputPassword").val())) {
      $.ajax({
        type: "post",
        url: myUrl+"/login",
        data: $('#loginForm').serialize(),
        error: function (res) {
          console.log(res);
        },
        success: function (res) {
          console.log(res);
          if(res.code==0) {    // 登录成功，跳转
            window.location.href=myUrl+'/master/main.html';
          }else {
            $("#warnInfo").empty();
            $("#warnInfo").append("<strong>"+res.msg+"</strong>");
            $("#warnInfo").show();
          }
        }
      });
    } else {
      $("#warnInfo").empty();
      $("#warnInfo").append("<strong>密码不能为空</strong>");
      $("#warnInfo").show();
    }
  } else {
    $("#warnInfo").empty();
    $("#warnInfo").append("<strong>请填入正确的邮箱</strong>");
    $("#warnInfo").show();
  }
}

/***  book.html  ***/
// 获取图书类别
function getBookTypeSet() {
  $.ajax({
    url: myUrl+"/book/getBookType",
    type: "POST",
    success: function (res) {
      console.log(res);
      for(var i=0; i<res.data.length; i++) {
        $("#bookType").append("<option value=\""+res.data[i].no+"\">"+res.data[i].name+"</option>");
      }
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 展示图书信息
function showBookInfo(data) {
  $("#bookInfoContent").empty();
  for(var i=0; i<data.length; i++) {
    $("#bookInfoContent").append("<tr>" +
      "<td>"+data[i].no+"</td>" +
      "<td>"+data[i].name+"</td>" +
      "<td>"+data[i].type+"</td>" +
      "<td>"+data[i].author+"</td>" +
      "<td>"+data[i].publicer+"</td>" +
      "<td>"+data[i].price+"</td>" +
      "<td>"+data[i].inventory+"</td></tr>");
  }
}
// 监听类别变化
$("#bookType").change(function() {
  // console.log("选择的类别: "+$("#bookType")[0].value);
  selectBookByType();
});
// 根据类别查询书目信息
function selectBookByType() {
  $.ajax({
    url: myUrl+"/book/selectBookByType",
    type: "post",
    data: {
      typeNo: $("#bookType")[0].value
    },
    success: function (res) {
      console.log(res);
      showBookInfo(res.data);
    }
  });
}
// 根据书名查询
function selectBookByName() {
  var name = trim($("#bookName").val());
  if(isNull(name)){
    bootbox.alert({
      size: "small",
      title: "提示消息",
      message: "书名不能为空"
    });
    return;
  }
  $.ajax({
    url: myUrl+"/book/selectBookByName",
    type: "post",
    data: {
      bookName: name
    },
    success: function (res) {
      console.log(res);
      showBookInfo(res.data);
      if(res.data.length<1) {
        bootbox.alert({
          size: "small",
          title: "提示消息",
          message: "无查询结果"
        });
      }
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 根据ISBN编码查询
function selectBookByISBN() {
  var isbn = trim($("#isbn").val());
  if(isNull(isbn)){
    bootbox.alert({
      size: "small",
      title: "提示消息",
      message: "ISBN编号不能为空"
    });
    return;
  }
  $.ajax({
    url: myUrl+"/book/selectBookByISBN",
    type: "post",
    data: {
      isbn: isbn
    },
    success: function (res) {
      console.log(res);
      if(res.data == null) {
        bootbox.alert({
          size: "small",
          title: "提示消息",
          message: "无查询结果"
        });
        return;
      }
      $("#bookInfoContent").empty();
      $("#bookInfoContent").append("<tr>" +
        "<td>"+res.data.no+"</td>" +
        "<td>"+res.data.name+"</td>" +
        "<td>"+res.data.type+"</td>" +
        "<td>"+res.data.author+"</td>" +
        "<td>"+res.data.publicer+"</td>" +
        "<td>"+res.data.price+"</td>" +
        "<td>"+res.data.inventory+"</td></tr>");
    },
    error: function (res) {
      console.log(res);
    }
  });
}
