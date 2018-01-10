$(document).ready(function(){
    var chart_1 = echarts.init(document.getElementById('chart_1'));
    var chart_2 = echarts.init(document.getElementById('chart_2'));

    url = "http://192.168.1.101:8080/csp/memberScore";
    $.post(url, function(result){
        console.log(result);
        if (result && !result.code) {
            var certNameArray = new Array();
            var maxArray = new Array();
            var averageArray = new Array();
            var minArray = new Array();
            for (var i = 0; i < result.data.length; i++) {
                certName = result.data[i].certName;
                certNameArray.push(certName.substring(0, certName.indexOf("CCF")));
                maxArray.push(result.data[i].max.all);
                averageArray.push(result.data[i].average.all);
                minArray.push(result.data[i].min.all);
            }
            drawLine2(chart_1, certNameArray, maxArray, averageArray, minArray);
        } // if
    });
    
    url = "http://192.168.1.101:8080/csp/certScore";
    $.post(url, {
        certNo: 12
    }, function(result){
        console.log(result);
        if (result && !result.code) {
            var statisticsArray = [0, 0, 0, 0, 0];
            for (var i = 0; i < result.data.length; i++) {
                if (result.data[i].all < 100) {
                    statisticsArray[0]++;
                } else if (result.data[i].all < 200) {
                    statisticsArray[1]++;
                } else if (result.data[i].all < 300) {
                    statisticsArray[2]++;
                } else {
                    statisticsArray[3]++;
                }
            }
            drawPie(chart_2, result.data[0].certName, statisticsArray);
            $(".btn.dropdown-toggle").html('第十二次CCF计算机软件能力认证\n<span class="caret"></span>');
        }

    });

    $.ajax({
        url: "http://192.168.1.101:8080/csp/certSet",
        type: "post",
        success: function (res) {
            console.log(res);
            if(res && res.code == 0) {
                console.log("添加csp考试选项");
                for(var i = 0; i < res.data.length; i++) {
                    $("ul.dropdown-menu").append(
                        '<li role="presentation">\
                            <a class="select-questionNo" id="select-questionNo-' + res.data[i].no + '" role="menuitem" tabindex="-1" style="cursor: pointer;">' + res.data[i].name + '</a>\
                        </li>'
                    );
                }
                $("a.select-questionNo").click(function(){
                    $(".btn.dropdown-toggle").html($(this).text() + '\n<span class="caret"></span>');
                    var domId = $(this).attr('id');
                    $.post("http://192.168.1.101:8080/csp/certScore", {
                        certNo: parseInt(domId.substring(domId.lastIndexOf("-") + 1))
                    }, function(result){
                        console.log(result);
                        if (result && !result.code) {
                            var statisticsArray = [0, 0, 0, 0, 0];
                            if (result.data.length) { // 有数据
                                for (var i = 0; i < result.data.length; i++) {
                                    if (result.data[i].all < 100) {
                                        statisticsArray[0]++;
                                    } else if (result.data[i].all < 200) {
                                        statisticsArray[1]++;
                                    } else if (result.data[i].all < 300) {
                                        statisticsArray[2]++;
                                    } else {
                                        statisticsArray[3]++;
                                    }
                                }
                                drawPie(chart_2, result.data[0].certName, statisticsArray);
                            } else {
                                // $("#chart_2").empty();
                                console.log("没有数据，清空显示。");
                                // alert("没有数据！");
                            }// if
                        } else {
                            alert("网络请求出错！");
                        }
                
                    }); // post
                }); // click
            } else {
                // showAlertMessage(res.msg);
            }
        },
        error: function (result) {
          console.log(result);
        }
    });

});