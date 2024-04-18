function run_charts(){
    //周运动步数

            var steps = echarts.init(document.getElementById('steps'));
            option02 = {
                 tooltip: {
                            trigger: 'axis'
                        },

                        color: ['#009688'],
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: ['星期一','星期二', '星期三', '星期四','星期五','星期六','星期日'],
                            show:false,
                        },
                        yAxis: {
                            show: false


                        },
                        series: [{
                                name: '步数',
                                type: 'bar',
                                barWidth:3,
                                smooth:true,
                                data: ['4000','3000','5000','2555', '4700', '4300','2109'],
                                markPoint: {
                                    data: [{
                                            type: 'max',
                                            name: '最大值'
                                        },
                                        {
                                            type: 'min',
                                            name: '最小值'
                                        }
                                    ]
                                },

                            },

                        ]
            };


                    // 使用刚指定的配置项和数据显示图表。
                    steps.setOption(option02);
        steps.resize();
            window.addEventListener("resize", function () {

            steps.resize();

            });

}


