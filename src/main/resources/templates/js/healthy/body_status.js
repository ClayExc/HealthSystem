relation_network();
function relation_network(){
    var myChart = echarts.init(document.getElementById('main'), 'macarons');
//创建Nodes
    nodes=[
        {category:0, name: '用户', label: 'wkz'},
        {category:1, name: '发育',value :""},
        {category:1, name: '体能',value :""},
        {category:1, name: '疾病',value :""},
        {category:1, name: '亚健康',value :""},
        {category:1, name: '体质',value :""},
        {category:2, name: '运动处方',value :""},
        {category:2, name: '运动强度',value :""},
        {category:2, name: '运动时间',value :""},
        {category:2, name: '运动频率',value :""},
        {category:2, name: '运动类型',value :""},
        {category:3, name: '兴趣爱好',value :""},
        {category:3, name: '运动目标',value :""},
        {category:3, name: '运动器材',value :""},
        {category:1, name: '肥胖',value :""},
        {category:1, name: '内脂高',value :""},
        {category:1, name: '高血压',value :""},
        {category:1, name: '肌肉耐力',value :""},
        {category:1, name: '中等',value :""},
        {category:1, name: '心肺能力',value :""},
        {category:1, name: '良好',value :""},
        {category:1, name: '轻微',value :""},
        {category:2, name: '有氧运动',value :""},
        {category:2, name: '中等强度',value :""},
        {category:2, name: '长时间',value :""},
        {category:2, name: '高',value :""},
        {category:3, name: '跑步',value :""},
        {category:3, name: '游泳',value :""},
        {category:3, name: '乒乓球',value :""},
        {category:3, name: '哑铃',value :""},
        {category:3, name: '杠铃',value :""},
        {category:3, name: '跳绳',value : ""},
        {category:3, name: '减脂瘦身',value :""},

    ];
//创建links
    links=[
        {source : '用户', target : '体质', weight : 1, name: 'include'},
        {source : '用户', target : '运动处方', weight : 2, name: 'include'},
        {source : '用户', target : '运动目标', weight : 2, name: 'include'},
        {source : '用户', target : '兴趣爱好', weight : 2,name: 'include'},
        {source : '用户', target : '运动器材', weight : 2,name: 'include'},
        {source : '体质', target : '发育', weight : 2,name: 'include'},
        {source : '体质', target : '体能', weight : 2,name: 'include'},
        {source : '体质', target : '疾病', weight : 2,name: 'include'},
        {source : '体质', target : '亚健康', weight : 2,name: 'include'},
        {source : '运动处方', target : '运动类型', weight : 2,name: 'include'},
        {source : '运动处方', target : '运动强度', weight : 2,name: 'include'},
        {source : '运动处方', target : '运动时间', weight : 2,name: 'include'},
        {source : '运动处方', target : '运动频率', weight : 2,name: 'include'},
        {source : '发育', target : '肥胖', weight : 2,name: 'is'},
        {source : '发育', target : '内脂高', weight : 2,name: 'is'},
        {source : '体能', target : '肌肉耐力', weight : 2,name: 'include'},
        {source : '体能', target : '心肺能力', weight : 2,name: 'include'},
        {source : '肌肉耐力', target : '中等', weight : 2,name: 'is'},
        {source : '心肺能力', target : '良好', weight : 2,name: 'is'},
        {source : '疾病', target : '高血压', weight : 2,name: 'has'},
        {source : '亚健康', target : '轻微', weight : 2,name: 'is'},
        {source : '运动类型', target : '有氧运动', weight : 2,name: 'belong'},
        {source : '运动强度', target : '中等强度', weight : 2,name: 'belong'},
        {source : '运动时间', target : '长时间', weight : 2,name: 'belong'},
        {source : '运动频率', target : '高', weight : 2,name: 'belong'},
        {source : '运动目标', target : '减脂瘦身', weight : 2,name: 'is'},
        {source : '兴趣爱好', target : '跑步', weight : 2,name: 'has'},
        {source : '兴趣爱好', target : '游泳', weight : 2,name: 'has'},
        {source : '兴趣爱好', target : '乒乓球', weight : 2,name: 'has'},
        {source : '运动器材', target : '哑铃', weight : 2,name: 'has'},
        {source : '运动器材', target : '杠铃', weight : 2,name: 'has'},
        {source : '运动器材', target : '跳绳', weight : 2,name: 'has'},
    ];
    var categories =[{name:"用户"},{name:"体质"},{name:"运动处方"},{name:"其他"}];
    option = {
        // 图的标题
        title: {
            text: '个人信息'
        },
        // 提示框的配置
        tooltip: {
            formatter: function (x) {
                return x.data.des;
            }
        },
        // 工具箱
        toolbox: {
            // 显示工具箱
            show: true,
            feature: {
                mark: {
                    show: true
                },
                // 还原
                restore: {
                    show: true
                },
                // 保存为图片
                saveAsImage: {
                    show: true
                }
            }
        },
        legend: [{
            // selectedMode: 'single',
            data: categories.map(function (a) {
                return a.name;
            })
        }],
        series: [{
            type: 'graph', // 类型:关系图
            layout: 'force', //图的布局，类型为力导图
            symbolSize: 30, // 调整节点的大小
            roam: true, // 是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移,可以设置成 'scale' 或者 'move'。设置成 true 为都开启
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [2, 5],
            edgeLabel: {
                normal: {
                    textStyle: {
                        fontSize: 5
                    }
                }
            },
            force: {
                repulsion: 170,
                edgeLength: [10, 20],
            },
            draggable: true,
            lineStyle: {
                normal: {
                    width: 2,
                    color: '#4b565b',
                }
            },
            edgeLabel: {
                normal: {
                    show: true,
                    formatter: function (x) {
                        return x.data.name;
                    }
                }
            },
            label: {
                normal: {
                    show: true,
                    textStyle: {}
                }
            },
            data:nodes,
            links:links,
            categories: categories,
        }]
    };
    myChart.setOption(option);
}
