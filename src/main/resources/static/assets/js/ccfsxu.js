var myUrl = "http://127.0.0.1:8080";
var xhr = null;

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
// 显示提示框
function showAlertMessage(msg) {
  bootbox.alert({
    size: "small",
    title: "提示消息",
    message: msg
  });
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
// 管理端初始化登录注销
function initHeadViewInMaster() {
  $.ajax({
    url: myUrl+"/isLogin",
    type: "post",
    success: function (res) {
      if(res) {
        $("#login-link").empty();
        $("#login-link").append("<li><a href=\"../login.html\" onclick='logout()'>注销</a></li>");
        $("#update-password-link").show();
      }
      else {
        $("#login-link").empty();
        $("#login-link").append("<li><a href=\"../login.html\">登录</a></li>");
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

// 获取权限
function getPower() {
  $.ajax({
    url: ""
  });
}

/***  login.html  ***/
// 登录
function login() {
  if(isEmail($("#inputEmail").val())) {
    if(!isNull($("#inputPassword").val())) {
      $.ajax({
        type: "post",
        url: myUrl+"/loginSystem",
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



/***  main.html ***/
// 存储全部会员信息
var datas;
// 获取年级集合
function getGradeSet() {
  $.ajax({
    url: myUrl+"/member/getGradeSet",
    type: "post",
    success: function (res) {
      console.log(res);
      addGrageSet(res.data);
    },
    error: function (res) {
      console.log(res);
      console.log("年级集合请求出错");
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
function showMemberInfo(data) {
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
    url: myUrl+"/member/queryMember",
    data: $('#queryForm').serialize(),
    error: function (res) {
      console.log(res);
    },
    success: function (res) {
      console.log(res);
      datas = res.data;
      showMemberInfo(res.data);
    }
  });
}
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

/***  insertMemberInfo  ***/
// 上传会员名录信息
xhr = new XMLHttpRequest();
function uploadMemberInfo() {
  bootbox.alert({
    size: "small",
    title: "提示信息",
    message: "正在上传，请耐心等待上传结果!(上传过程真得很慢，不要刷新页面。)"
  });
  var fileObj = $("#exampleInputFile")[0].files[0];
  var FileController = myUrl+"/member/addByFile";
  var form = new FormData();
  form.append("file", fileObj);
  xhr.open("post", FileController, true);
  xhr.onload = function () {
//           alert("上传完成!");
  };
  xhr.send(form);
  xhr.onreadystatechange = callbackUploadMemberFile;
}
function callbackUploadMemberFile() {
  if(xhr.readyState == 4 && xhr.status == 200) {
    var obj = jQuery.parseJSON(xhr.responseText);
    console.log(obj);
    if(obj.data.length!=0) {
      var str = "";
      for(var i=0; i<obj.data.length; i++)
        str.append(obj.data.data[0]+"，");
      showAlertMessage(obj.msg+"\n以下同学录入出错："+str);
    } else {
      showAlertMessage(obj.msg);
    }
  }
}

// 提交手动录入的会员信息
function submitHandMemberInfo() {
  var forms = $("#form-import")[0];
  if(isNull(forms[0].value) || isNull(forms[1].value)
    || isNull(forms[2].value) || isNull(forms[3].value)
    || isNull(forms[4].value) || isNull(forms[5].value)
    || isNull(forms[11].value) || isNull(forms[12].value)) {
    bootbox.alert({
      size: "small",
      title: "提示信息",
      message: "有必填信息没有填写!"
    });
    return;
  }
  $.ajax({
    url: myUrl+"/member/add",
    data: $("#form-import").serialize(),
    type: "post",
    success: function (res) {
      console.log(res);
      switch (res.code) {
        case 0:
          bootbox.alert({
            size: "small",
            title: "提示信息",
            message: "录入成功!"
          });
          break;
        default:
          bootbox.alert({
            size: "small",
            title: "提示信息",
            message: res.msg
          });
      }
    },
    fail: function (res) {
      console.log(res);
    }
  });
}


/***  updateMemberInfo.html ***/
// 查询要修改的会员信息
function selectMemberInfo() {
  if(isNull($("#memberNo").val())) {
    bootbox.alert({
      size: "small",
      title: "提示信息",
      message: "请输入会员号!",
      callback: function(){  }
    });
    return;
  }
  $.ajax({
    url: myUrl+"/member/queryMember",
    type: "post",
    data: "&no="+$("#memberNo").val(),
    success: function (res) {
      console.log(res);
      if(res.len == 0) {
        bootbox.alert({
          size: "small",
          title: "提示消息",
          message: "会员号有误，无该会员"
        });
        return;
      }
      $("#updateInfo").show();
      $("input[name='no']")[1].value = res.data[0].no;
      $("input[name='name']")[0].value = res.data[0].name;
      $("input[name='email']")[0].value = res.data[0].email;
      $("input[name='stuNo']")[0].value = res.data[0].stuNo;
      $("input[name='phone']")[0].value = res.data[0].phone;
      $("input[name='discipline']")[0].value = res.data[0].discipline;
      $("input[name='grade']")[0].value = res.data[0].grade;
      $("input[name='classNum']")[0].value = res.data[0].classNum;
      $("input[name='id']")[0].value = res.data[0].id;
      $("input[name='startTime']")[0].value = res.data[0].startTime;
      $("input[name='endTime']")[0].value = res.data[0].endTime;
      switch (res.data[0].degree) {
        case "专科":
          $("select[name='degreeNo']")[0].value = 1;
          break;
        case "本科":
          $("select[name='degreeNo']")[0].value = 2;
          break;
        case "硕士":
          $("select[name='degreeNo']")[0].value = 3;
          break;
        case "博士":
          $("select[name='degreeNo']")[0].value = 4;
          break;
      }
      $("select[name='addSocre']")[0].value = res.data[0].addScore;
      if(res.data[0].power!=1) {
        $("select[name='power']")[0].value = res.data[0].power;
      } else {
        $("#identity").hide();
      }
    },
    fail: function (res) {
      console.log(res);
    }
  });
}
// 提交修改
function submitUpdate() {
  bootbox.confirm({
    title: "提示消息",
    message: "确认提交修改？",
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
        $.ajax({
          url: myUrl+"/member/update",
          type: "post",
          data: $("#form-update").serialize(),
          success: function (res) {
            console.log(res);
            showAlertMessage(res.msg);
          },
          fail: function (res) {
            console.log(res);
          }
        });
      }
    }
  });
}


/***  selectCSPScore.html  ***/
// 获取考试集合
function getCertSet() {
  $.ajax({
    url: myUrl+"/csp/certSet",
    type: "post",
    success: function (res) {
      console.log(res);
      if(res.code==0) {
        for(var i=0; i<res.data.length; i++)
          $("#certName").append("<option value=\""+res.data[i].no+"\">"+res.data[i].name+"</option>");
      } else {
        showAlertMessage(res.msg);
      }
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 获取CSP成绩列表
function queryCSP() {
  $.ajax({
    url: myUrl+"/csp/queryScore",
    type: "post",
    data: $("#queryForm").serialize(),
    success: function (res) {
      console.log(res);
      showScore(res.data);
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 获取某个会员的CSP成绩
function getScoreByNo() {
  $.ajax({
    url: myUrl+"/csp/ScoreByMember",
    type: "post",
    data: $("#selectScoreByNoForm").serialize(),
    success: function (res) {
      console.log(res);
      if(res.data!=null) {
        showScore(res.data);
      }
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 展示获取的会员成绩信息
function showScore(data) {
  $("#score-content").empty();
  var str = "";
  for(var i=0; i<data.length; i++) {
    str = str + " <tr> <td>"+
      data[i].memberNo+"</td> <td>"+
      data[i].memberName+"</td> <td>"+
      data[i].certName+"</td> <td>"+
      data[i].all+"</td> <td>"+
      data[i].first+"</td> <td>"+
      data[i].second+"</td> <td>"+
      data[i].third+"</td> <td>"+
      data[i].forth+"</td> <td>"+
      data[i].fifth+"</td> </tr>";
  }
  $("#score-content").append(str);
}

/***  insertCSPScore.html  ***/
xhr = new XMLHttpRequest();
// 上传文件
function uploadCSPScore() {
  bootbox.alert({
    size: "small",
    title: "提示信息",
    message: "正在上传，请耐心等待上传结果!",
    callback: function(){  }
  });
  var fileObj = $("#exampleInputFile")[0].files[0];
  var FileController = "/receiveCSPScoreFile";
  // FormData 对象
  var form = new FormData();
  form.append("file", fileObj);// 文件对象
  form.append("certNo", $("#certName").val());
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
    console.log(obj);
    if(obj.ret) {
      bootbox.alert({
        size: "small",
        title: "提示信息",
        message: "录入成功!",
        callback: function(){  }
      });
    } else {
      bootbox.alert({
        size: "small",
        title: "提交失败",
        message: "录入失败!",
        callback: function(){  }
      });
    }
  }
}


/***  managerCSPApplication.html  ***/
// 告诉系统可以下载了
function downloadApplication() {
  window.open(myUrl + "/csp/downLoadApplication?" + $("#downloadForm").serialize());
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
        $.ajax({
          url: myUrl+"/csp/addCertification",
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
  });
}


/***  borrowBook.html  ***/
// var counts = 0;
// $("#isbn").change(function () {
//   // console.log(this.value);
//   if(this.value.length>=13) {
//     searchInventory();
//   }
// });
// 查询库存
// function searchInventory() {
//   counts = 0;
//   if($("#isbn").val() == "") {
//     bootbox.alert({
//       size: "small",
//       title: "提示消息",
//       message: "请输入合法的ISBN编号"
//     });
//     return;
//   }
//   $.ajax({
//     url: myUrl+"/book/searchInventory",
//     type: "post",
//     data: {
//       isbn: $("#isbn").val()
//     },
//     success: function (res) {
//       console.log(res);
//       counts = res.data;
//       if(res.code==0) {
//         bootbox.alert({
//           size: "size",
//           title: "提示消息",
//           message: "剩余库存量："+res.num
//         });
//       } else {
//         showAlertMessage(res.msg);
//       }
//       if(res.num == 0) {
//         $("#memberNo")[0].disabled = true;
//       } else {
//         $("#memberNo")[0].disabled = false;
//       }
//     },
//     error: function (res) {
//       console.log(res);
//     }
//   });
// }
// 提交借书请求
function submitBorrow() {
  // if(counts<1) {
  //   bootbox.alert({
  //     size: "small",
  //     title: "提示信息",
  //     message: "请先查询图书的库存量"
  //   });
  //   return;
  // }
  if(isNull($("#isbn").val())) {
    bootbox.alert({
      size: "small",
      title: "提示信息",
      message: "ISBN编号不能为空"
    });
    return;
  }
  if(isNull($("#memberNo").val())) {
    bootbox.alert({
      size: "small",
      title: "提示信息",
      message: "会员号不能为空"
    });
    return;
  }
  bootbox.confirm({
    title: "提示信息",
    message: "请确认借阅信息无误?",
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
        $.ajax({
          url: myUrl+"/book/borrowBook",
          type: "post",
          data: {
            isbn: $("#isbn").val(),
            memberNo: $("#memberNo").val()
          },
          success: function (res) {
            console.log(res);
            showAlertMessage(res.msg);
          }
        });
      }
    }
  });
}


/***  backBook.html ***/
//      $("#isbn").change(function () {
//        if(this.value.length>=13) {
//          searchBorrowOrder();
//        }
//      });

// 查询借阅情况
function searchBorrowOrder() {
  $("#no").empty();
  if(isNull($("#isbn").val())) {
    bootbox.alert({
      size: "small",
      title: "提示消息",
      message: "请输入合法的ISBN编号"
    });
    return;
  }
  $.ajax({
    url: myUrl+"/book/searchBorrowOrder",
    type: "post",
    data: {
      isbn: $("#isbn").val()
    },
    success: function (res) {
      console.log(res);
      if(res.code==0) {
        if(res.data.length!=0) {
          showOlder(res.data);
          $("#submit-back-btn").attr('disabled',false);
        } else {
          $("#submit-back-btn").attr('disabled',true);
        }
        showAlertMessage(res.msg);
      } else {
        showAlertMessage(res.msg);
        $("#submit-back-btn").attr('disabled',true);
      }
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 格式化时间
Date.prototype.toLocaleString = function() {
  return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate() + "/ " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
};
// 展示借阅订单
function showOlder(data) {
  $("#no").empty();
  for(var i=0; i<data.length; i++) {
    var unixTimestamp = new Date(data[i].borrowTime) ;
    var commonTime = unixTimestamp.toLocaleString();
    $("#no").append("<option value=\""+data[i].no+"\">"+data[i].memberNo+" - "+data[i].memberName+" - "+commonTime+"</option>");
  }
}
// 提交还书信息
function submitBack () {
  $.ajax({
    url: myUrl+"/book/backBook",
    type: "post",
    data: {
      isbn: $("#isbn").val(),
      no: $("#no").val()
    },
    success: function (res) {
      console.log(res);
      showAlertMessage(res.msg);
    },
    error: function (res) {
      console.log(res);
    }
  });
}

/***  insertBook.html  ***/
// 手动录入图书信息
function submitHandBookInfo() {
  $.ajax({
    url: myUrl+"/book/add",
    type: "post",
    data: $("#form-import").serialize(),
    success: function (res) {
      console.log(res);
      showAlertMessage(res.msg);
    },
    error: function (res) {
      console.log(res);
    }
  });
}
// 文件上传图书信息
xhr = new XMLHttpRequest();
function uploadBookInfo() {
  bootbox.alert({
    size: "small",
    title: "提示信息",
    message: "正在上传，请耐心等待上传结果!(上传过程真得很慢，不要刷新页面。)"
  });
  var fileObj = $("#exampleInputFile")[0].files[0];
  var FileController = myUrl+"/book/addByFile";
  var form = new FormData();
  form.append("file", fileObj);
  xhr.open("post", FileController, true);
  xhr.onload = function () {
//           alert("上传完成!");
  };
  xhr.send(form);
  xhr.onreadystatechange = callbackUploadBookFile;
}
function callbackUploadBookFile() {
  if(xhr.readyState == 4 && xhr.status == 200) {
    var obj = jQuery.parseJSON(xhr.responseText);
    console.log(obj);
    if(obj.data.length!=0) {
      var str = "";
      for(var i=0; i<obj.data.length; i++)
        str.append(obj.data.data[0]+",");
      showAlertMessage(obj.msg+"\n以下图书录入出错："+str);
    } else {
      showAlertMessage(obj.msg);
    }
  }
}















