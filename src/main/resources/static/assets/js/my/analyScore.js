$(document).ready(function(){
    url = "http://192.168.1.101:8080/csp/memberScore";
    var data;
    var chart_1 = echarts.init(document.getElementById('chart_1'));
    var chart_2 = echarts.init(document.getElementById('chart_2'));
    var chart_2_2 = echarts.init(document.getElementById('chart_2_2'));
    var chart_3 = echarts.init(document.getElementById('chart_3'));

    $.post(url, function(result){
        console.log(result);
        if (result && !result.code) {
            var certNameArray = new Array();
            var maxArray = new Array();
            var averageArray = new Array();
            var allArray = new Array();
            var minArray = new Array();
            var firstArray = new Array();
            var secondArray = new Array();
            var thirdArray = new Array();
            var forthArray = new Array();
            var fifthArray = new Array();
            for (var i = 0; i < result.data.length; i++) {
                certName = result.data[i].certName;
                // console.log(certName.substring(0, certName.indexOf("CCF")));
                certNameArray.push(certName.substring(0, certName.indexOf("CCF")));
                maxArray.push(result.data[i].max.all);
                averageArray.push(result.data[i].average.all);
                allArray.push(result.data[i].all);
                minArray.push(result.data[i].min.all);
                
                firstArray.push(result.data[i].first);
                secondArray.push(result.data[i].second);
                thirdArray.push(result.data[i].third);
                forthArray.push(result.data[i].forth);
                fifthArray.push(result.data[i].fifth);
            }
            firstArray.push(100);
            secondArray.push(100);
            thirdArray.push(100);
            forthArray.push(100);
            fifthArray.push(100);

            // ######################## 画图 ##########################
            // 总分走势 折线图
            drawChart1(chart_1, certNameArray, maxArray, averageArray, allArray, minArray);
            // 题目得分走势 折线图
            $(".btn.dropdown-toggle.questionNo").html('第1题\n<span class="caret"></span>');
            drawChart2_2(chart_2_2, certNameArray, firstArray);
            // 能力分布 蜘蛛网图
            scores = result.data[result.data.length-1];
            $(".btn.dropdown-toggle.certNo").html(scores.certName + '\n<span class="caret"></span>');
            drawChart2(chart_2, scores);
            // 平均对比 柱状图
            drawChart3(chart_3, scores);


            // 题目得分走势 折线图 选择器
            $("a.select-questionNo").click(function(){
                $(".btn.dropdown-toggle.questionNo").html($(this).text() + '\n<span class="caret"></span>');
                var domId = $(this).attr('id');
                switch(parseInt(domId.substring(domId.lastIndexOf("-") + 1))) {
                case 0:
                    scoreArray = firstArray; break;
                case 1:
                    scoreArray = secondArray; break;
                case 2:
                    scoreArray = thirdArray; break;
                case 3:
                    scoreArray = forthArray; break;
                case 4:
                    scoreArray = fifthArray; break;
                }
                chart_2_2.setOption({
                    legend: {
                        data:[$(this).text()]
                    },
                    series: [
                        {
                            name: $(this).text(),
                            type:'line',
                            data: scoreArray
                        }
                    ]
                }); // chart_2_2
            });
            // $("select#select-questionNo").change(function(){ // 改变事件
            //     switch(parseInt($("select#select-questionNo").val())) {
            //     case 0:
            //         scoreArray = firstArray; break;
            //     case 1:
            //         scoreArray = secondArray; break;
            //     case 2:
            //         scoreArray = thirdArray; break;
            //     case 3:
            //         scoreArray = forthArray; break;
            //     case 4:
            //         scoreArray = fifthArray; break;
            //     }
            //     chart_2_2.setOption({
            //         legend: {
            //             data:[$("select#select-questionNo").find("option:selected").text()]
            //         },
            //         series: [
            //             {
            //                 name: $("select#select-questionNo").find("option:selected").text(),
            //                 type:'line',
            //                 data: scoreArray
            //             }
            //         ]
            //     }); // chart_2_2
            // });

            // 能力分布 蜘蛛网图 选择器
            for (var i = 0; i < result.data.length; i++) {
                // $("select#select-certNo").append(
                //     '<option value="' + i + '">' + result.data[i].certName + '</option>'
                // );
                $(".dropdown-menu.certNo").append(
                    '<li role="presentation">\
                        <a class="select-certNo" id="select-certNo-' + i + '" role="menuitem" tabindex="-1" style="cursor: pointer;">' + result.data[i].certName + '</a>\
                    </li>'
                );
            }
            $("a.select-certNo").click(function(){
                $(".btn.dropdown-toggle.certNo").html($(this).text() + '\n<span class="caret"></span>');
                var domId = $(this).attr('id');
                var no = parseInt(domId.substring(domId.lastIndexOf("-") + 1));
                scores = result.data[no];
                chart_2.setOption({
                    series: [{
                        type: 'radar',
                        data : [
                            {
                                value : [scores.first, scores.second, scores.third, scores.forth, scores.fifth],
                                name : '我的成绩（Score）'
                            }
                        ]
                    }]
                }); // chart_2
                chart_3.setOption({
                    series : [
                        {
                            name:'最高分',
                            type:'bar',
                            data: [scores.max.first, scores.max.second, scores.max.third, scores.max.forth, scores.max.fifth]
                        },
                        {
                            name:'平均分',
                            type:'bar',
                            data: [scores.average.first, scores.average.second, scores.average.third, scores.average.forth, scores.average.fifth]
                        },
                        {
                            name:'我的得分',
                            type:'bar',
                            data: [scores.first, scores.second, scores.third, scores.forth, scores.fifth]
                        },
                        {
                            name:'最低分',
                            type:'bar',
                            data: [scores.min.first, scores.min.second, scores.min.third, scores.min.forth, scores.min.fifth]
                        }
                    ]
                }); // chart_3
            });
            // $("select#select-certNo").change(function(){ // 改变事件
            //     var no = parseInt($("select#select-certNo").val());
            //     scores = result.data[no];
            //     // console.log(scores);
            //     chart_2.setOption({
            //         series: [{
            //             type: 'radar',
            //             // areaStyle: {normal: {}},
            //             data : [
            //                 {
            //                     value : [scores.first, scores.second, scores.third, scores.forth, scores.fifth],
            //                     name : '我的成绩（Score）'
            //                 }
            //             ]
            //         }]
            //     }); // chart_2
            //     chart_3.setOption({
            //         series : [
            //             {
            //                 name:'最高分',
            //                 type:'bar',
            //                 data: [scores.max.first, scores.max.second, scores.max.third, scores.max.forth, scores.max.fifth]
            //             },
            //             {
            //                 name:'平均分',
            //                 type:'bar',
            //                 data: [scores.average.first, scores.average.second, scores.average.third, scores.average.forth, scores.average.fifth]
            //             },
            //             {
            //                 name:'我的得分',
            //                 type:'bar',
            //                 data: [scores.first, scores.second, scores.third, scores.forth, scores.fifth]
            //             },
            //             {
            //                 name:'最低分',
            //                 type:'bar',
            //                 data: [scores.min.first, scores.min.second, scores.min.third, scores.min.forth, scores.min.fifth]
            //             }
            //         ]
            //     }); // chart_3
            // });

        } else {
            console.log("没有数据");
        } // if

    }); // post


});

function getData() {
    url = "http://192.168.1.101:8080/csp/memberScore";
    var data;
    $.post(url, function(result){
        // console.log(result);
        // data = result;
        data = result;
    });
    return(data);
}
