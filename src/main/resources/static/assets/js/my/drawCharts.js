function drawChart1(chart_1, certNameArray, maxArray, averageArray, allArray, minArray) {
    // 总分走势 图
    // chart_1 = echarts.init(document.getElementById('chart_1'));
    chart_1_option = {
        title: {
            text: '总分走势'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['最高分','平均分','我的得分','最低分']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        // toolbox: {
        //     feature: {
        //         saveAsImage: {}
        //     }
        // },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: certNameArray
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'最高分',
                type:'line',
                // stack: '500',
                data: maxArray
            },
            {
                name:'平均分',
                type:'line',
                // stack: '500',
                data: averageArray
            },
            {
                name:'我的得分',
                type:'line',
                // stack: '500',
                data: allArray
            },
            {
                name:'最低分',
                type:'line',
                // stack: '500',
                data: minArray
            }
        ]
    };
    chart_1.setOption(chart_1_option);
}

function drawChart2(chart_2, scores) {
    // 蜘蛛网 图
    // chart_2 = echarts.init(document.getElementById('chart_2'));
    chart_2_option = {
        title: {
            text: '能力分布'
        },
        tooltip: {},
        legend: {
            data: ['我的成绩（Score）']
        },
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
            }
            },
            indicator: [
                { name: '第一题（1）', max: 100},
                { name: '第二题（2）', max: 100},
                { name: '第三题（3）', max: 100},
                { name: '第四题（4）', max: 100},
                { name: '第五题（5）', max: 100}
            ]
        },
        series: [{
            // name: '预算 vs 开销（Budget vs spending）',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : [scores.first, scores.second, scores.third, scores.forth, scores.fifth],
                    name : '我的成绩（Score）'
                }
            ]
        }]
    };
    chart_2.setOption(chart_2_option);
}

function drawChart2_2(chart_2_2, certNameArray, scoreArray) {
    // 题目得分走势 图
    // chart_2_2 = echarts.init(document.getElementById('chart_2_2'));
    chart_2_2_option = {
        title: {
            text: '题目得分走势'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['第1题']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        // toolbox: {
        //     feature: {
        //         saveAsImage: {}
        //     }
        // },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: certNameArray
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'第1题',
                type:'line',
                data: scoreArray
            }
        ]
    };
    chart_2_2.setOption(chart_2_2_option);
}

function drawChart3(chart_3, scores) {
    // 柱状图
    chart_3 = echarts.init(document.getElementById('chart_3'));
    chart_3_option = {
        title: {
            text: '平均对比'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['最高分', '平均分', '我的得分', '最低分']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['第一题','第二题','第三题','第四题','第五题']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
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
    };
    chart_3.setOption(chart_3_option);
}

function drawPie(mychart, certName, statisticsArray) {
    option = {
        title : {
            text: certName,
            subtext: '分阶段统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: [
                '0~99 (' + statisticsArray[0] + ')',
                '100~199 (' + statisticsArray[1] + ')',
                '200~299 (' + statisticsArray[2] + ')',
                '300~500 (' + statisticsArray[3] + ')'
            ]
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value: statisticsArray[0], name:'0~99 (' + statisticsArray[0] + ')'},
                    {value: statisticsArray[1], name:'100~199 (' + statisticsArray[1] + ')'},
                    {value: statisticsArray[2], name:'200~299 (' + statisticsArray[2] + ')'},
                    {value: statisticsArray[3], name:'300~500 (' + statisticsArray[3] + ')'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    mychart.setOption(option);
}
function drawLine2(chart_1, certNameArray, maxArray, averageArray, minArray) {
    // 特征值统计 图
    chart_1_option = {
        title: {
            text: '三围预览'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['最高分','平均分','最低分']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: certNameArray
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'最高分',
                type:'line',
                data: maxArray
            },
            {
                name:'平均分',
                type:'line',
                data: averageArray
            },
            {
                name:'最低分',
                type:'line',
                data: minArray
            }
        ]
    };
    chart_1.setOption(chart_1_option);
}