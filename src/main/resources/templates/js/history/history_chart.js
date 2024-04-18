function chart_width(){

var user=$.cookie('user_id')
    //设置宽度
    $("#mileage").css('width',$("#wid_chart").width());
    $("#steps").css('width',$("#wid_chart").width());
    $("#consume").css('width',$("#wid_chart").width());
    $("#frequency").css('width',$("#wid_chart").width());
    $("#days").css('width',$("#wid_chart").width());
    $("#consume_month").css('width',$("#wid_chart").width());

    //心率
    //进行后台传值
    var formData={
                     "user_id":user
                 };

              //天步数里程
              $.ajax({
                type:"POST",
                url:"runData/getRunDataByTime.do",
                data:JSON.stringify(formData),
                datatype:"text",
                contentType : "application/json",
                success:function(data){
                    console.log(data);
                    console.log(data[0].stepNumber);

                    $("#steps_today").html(data[0].stepNumber);
                    $("#distance").html(data[0].distance);
                    $("#calorie").html(data[0].calorie);
                    $("#stepNumber").html(data[0].stepNumber);
                    $("#recordTime").html(data[0].recordTime.slice(0,10));

                }
              });
              //天心率速度
              $.ajax({
                    type:"POST",
                    url:"runData/getRunData.do",
                    data:JSON.stringify(formData),
                    datatype:"text",
                    contentType : "application/json",
                    //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                    success:function (data) {
                            console.log(data);
                            let arr_time = data.map(obj => {return obj.recordTime});
                            let arr_heart = data.map(obj => {return obj.heartRate});
                             let arr_speed = data.map(obj => {return obj.runSpeed});
                            var heart=new Array();
                            var speed=new Array();
                            for (var key in arr_heart) {
                                    heart[key] = arr_heart[key].toFixed(2);
                                    speed[key] = arr_speed[key].toFixed(2);
                                }

                            var myChart = echarts.init(document.getElementById('heart'));
                                    heart = {
                                    grid: {
                                                                           top: 15,
                                                                           left: 15,
                                                                           right: 15,
                                                                           bottom: "20%",
                                                                           containLabel: true //轴上的数值
                                                                       },

                                    tooltip: {
                                        trigger: 'axis'
                                    },
                                    xAxis: {
                                     type: 'category',
                                      splitLine: false,
                                     boundaryGap: false,
                                     axisLine: {
                                          show: false,
                                          lineStyle: {
                                              color: myColor[0]
                                           }
                                     },
                                     axisTick: {
                                         lineStyle: {
                                          color: myColor[0]
                                          }
                                     },
                                        data: arr_time.map(function(str) {
                                                                      return str.replace(" ", "\n");
                                                                    }),
                                    },
                                    yAxis: {
                                    type: 'value',

                                                                           splitLine: false,
                                                                           axisLine: {
                                                                               show: false
                                                                           },
                                                                           axisTick: {
                                                                               show: false
                                                                           },
                                                                           interval: 30,
                                                                           axisLabel: {
                                                                               formatter: function (value) {
                                                                                   return value + " 次/分  -";
                                                                               },
                                                                               textStyle: {
                                                                                   color: function (value) {
                                                                                       //注意：当值大于999(三位)时，会以“1,000”形式，所以要做以下处理
                                                                                       value = parseInt(value.replace(/,/g, ""));
                                                                                       var idx = parseInt(value / 30);
                                                                                       return myColor[idx];
                                                                                   }
                                                                               }
                                                                           }
                                                                       },
                                    dataZoom: [{
                                        startValue: '2014-06-01'
                                    }, {
                                        type: 'inside'
                                    }],
                                     visualMap: {
                                                                           show: false,
                                                                           calculable: true,


                                                                           inRange: {
                                                                               color: myColor
                                                                           }
                                                                       },
                                                                       tooltip: {
                                                                           trigger: 'axis',
                                                                           axisPointer: {
                                                                               label: {
                                                                                   textStyle: {
                                                                                       color: "white"
                                                                                   }
                                                                               }
                                                                           }
                                                                       },
                                    series: {
                                        name: '运动心率',
                                                                               type: 'line',
                                                                               symbol: 'none', //这句就是去掉折线上的点的

                                                                               hoverAnimation: true,
                                                                               lineStyle: {
                                                                                   normal: {
                                                                                       width: 5
                                                                                   }
                                                                               },
                                                                               itemStyle: {
                                                                                   normal: {
                                                                                       opacity: 0
                                                                                   }
                                                                               },
                                        data: heart,

                                    }
                                }
                    myChart.setOption(heart);
                     var myChart = echarts.init(document.getElementById('speed'));
                                                        speed = {
    grid: {
                                           top: 15,
                                           left: 15,
                                           right: 15,
                                           bottom: "20%",
                                           containLabel: true //轴上的数值
                                       },
                                       xAxis: {
                                           type: 'category',
                                           splitLine: false,
                                           boundaryGap: false,
                                           axisLine: {
                                               show: false,
                                               lineStyle: {
                                                   color: myColor[0]
                                               }
                                           },
                                           axisTick: {
                                               lineStyle: {
                                                   color: myColor[0]
                                               }
                                           },
                                           data:arr_time.map(function(str) {
                                                                                                                      return str.replace(" ", "\n");
                                                                                                                    }),
                                       },
                                       yAxis: {
                                           type: 'value',
                                           min: 0,
                                           splitLine: false,
                                           axisLine: {
                                               show: false
                                           },
                                           axisTick: {
                                               show: false
                                           },
                                           interval: 1,
                                           axisLabel: {
                                               formatter: function (value) {
                                                   return value + " 时  -";
                                               },
                                               textStyle: {
                                                   color: function (value) {
                                                       //注意：当值大于999(三位)时，会以“1,000”形式，所以要做以下处理
                                                       value = parseInt(value.replace(/,/g, ""));
                                                       var idx = parseInt(value / 1);
                                                       return myColor[value];
                                                   }
                                               }
                                           }
                                       },
                                       dataZoom: [{
                                                                   startValue: '01-01'
                                                               }, {
                                                                   type: 'inside'
                                                               }],
                                       visualMap: {
                                           show: false,
                                           calculable: true,
                                           min: 0,
                                           max: 7,
                                           inRange: {
                                               color: myColor
                                           }
                                       },
                                       tooltip: {
                                           trigger: 'axis',
                                           axisPointer: {
                                               label: {
                                                   textStyle: {
                                                       color: "white"
                                                   }
                                               }
                                           }
                                       },

                                       series: [{
                                           name: '运动速度',
                                           type: 'line',
                                          
                                           smooth: true, //这句就是让曲线变平滑的
                                           hoverAnimation: true,
                                           lineStyle: {
                                               normal: {
                                                   width: 5
                                               }
                                           },
                                           itemStyle: {
                                               normal: {
                                                   opacity: 0
                                               }
                                           },
                                           data: speed,

                                       }]
                                                    }
                                        myChart.setOption(speed);


                    }
                });
              //周数据
              $.ajax({
                   type:"POST",
                   url:"runData/getWeekData.do",
                   data:JSON.stringify(formData),
                   datatype:"text",
                   contentType : "application/json",
                   success:function(data){
                         let arr_week = data.map(obj => {return obj.week});
                         let distance = data.map(obj => {return obj.distance});
                         let calorie = data.map(obj => {return obj.calorie});
                         let runTime = data.map(obj => {return obj.runTime});
                         let stepNumber = data.map(obj => {return obj.stepNumber});
                         let recordTime = data.map(obj => {return obj.recordTime});

                         //周总里程步数
                         var distance_all=0;
                         var calorie_all=0;
                         var stepNumber_all=0;
                        //周时间取反
                        var arr_time= new Array;
                        for(i=0;i<7;i++)
                        {
                            arr_time[i]=arr_week[6-i];
                        }
                          for (var key in distance) {
                               distance_all += distance[key];
                               calorie_all += calorie[key];
                               stepNumber_all += stepNumber[key];
                             }

                         $("#kilometre_all").html(distance_all);
                         $("#calorie_all").html(calorie_all);
                         $("#stepNumber_all").html(stepNumber_all);
                         $("#today").html(recordTime[0].slice(0,10));
                         $("#today_step").html(stepNumber[0]);

                        for (i=1;i<distance.length;i++) {
                                var str="<li><time class='cbp_tmtime' datetime='2017-11-04T18:30'><span style='margin-right:30px' class='hidden'>"+recordTime[i].slice(0,10)+"</span></time><div class='cbp_tmicon bg-info'><i class='zmdi zmdi-label'></i></div><div class='cbp_tmlabel'><h2><a href='javascript:void(0);'>运动</a> <span>行走  "+stepNumber[i]+" 步</span></h2></div></li>";
                                 $('#time_all').append(str);
                           }

                          //时长echarts
                           $("#hours").css('width',$("#heart").width());
                          var myChart = echarts.init(document.getElementById('hours'));

                                  option = {

                                             grid: {
                                                         top: 15,
                                                         left: 15,
                                                         right: 15,
                                                         bottom: "10%",
                                                         containLabel: true //轴上的数值
                                                    },
                                             xAxis: {
                                                        type: 'category',
                                                        boundaryGap:true,
                                                        axisLine: {
                                                             show: false,
                                                        },
                                                                 axisTick: {

                                                                 },
                                                                 data:arr_time,
                                                             },
                                             yAxis: {
                                                                   type: 'value',
                                                                   show: false, // 是否显示坐标轴
                                                                   data: [],
                                                                   axisLabel: { show: false }, // 坐标轴刻度标签
                                                                   axisLine: { show: false }, // 坐标轴轴线
                                                                   axisTick: { show: false }, // 坐标轴刻度
                                                                   splitLine: { show:false } // 分割线

                                                             },
                                             dataZoom: [ {
                                             type: 'inside'
                                             }],

                                             tooltip: {
                                                                 trigger: 'axis',
                                                                 axisPointer: {
                                                                     label: {
                                                                         textStyle: {
                                                                             color: "white"
                                                                         }
                                                                     }
                                                                 }
                                                             },
                                            series: [{
                                                                 type: 'bar',
                                                                   barWidth: 20, // 柱子宽度
                                                                   label: {
                                                                     show: true,
                                                                     position: 'top', // 位置
                                                                     color: '#1CD8A8',
                                                                     fontSize: 14,
                                                                     fontWeight: 'bold', // 加粗
                                                                     distance: 2 // 距离
                                                                   }, // 柱子上方的数值
                                                                   itemStyle: {
                                                                     barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                     color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                       '#2FAEF2',
                                                                       '#1CD8A8'
                                                                     ].map((color, offset) => ({color, offset}))), // 渐变
                                                                   },

                                                                 data:  runTime,

                                                             }]

                              };


                                      // 使用刚指定的配置项和数据显示图表。
                                      myChart.setOption(option);
                                      myChart.resize();
                                      window.addEventListener("resize", function () {
                                      myChart.resize();

                                      });
                         //周运动步数

                                var steps = echarts.init(document.getElementById('steps'));
                                option02 = {
                                     grid: {
                                         top: 15,
                                         left: 15,
                                         right: 15,
                                         bottom: "10%",
                                         containLabel: true //轴上的数值
                                         },
                                      xAxis: {
                                                                                             type: 'category',
                                                                                             boundaryGap:true,
                                                                                             axisLine: {
                                                                                                  show: false,
                                                                                             },
                                                                                                      axisTick: {

                                                                                                      },
                                                                                                      data:arr_time,
                                                                                                      show:false,
                                                                                                  },
                                     yAxis: {
                                                                                                        type: 'value',
                                                                                                        show: false, // 是否显示坐标轴
                                                                                                        data: [],
                                                                                                        axisLabel: { show: false }, // 坐标轴刻度标签
                                                                                                        axisLine: { show: false }, // 坐标轴轴线
                                                                                                        axisTick: { show: false }, // 坐标轴刻度
                                                                                                        splitLine: { show:false } // 分割线

                                                                                                  },
                                      dataZoom: [ {
                                                    type: 'inside'
                                      }],

                                     tooltip: {
                                                                                                      trigger: 'axis',
                                                                                                      axisPointer: {
                                                                                                          label: {
                                                                                                              textStyle: {
                                                                                                                  color: "white"
                                                                                                              }
                                                                                                          }
                                                                                                      }
                                                                                                  },

                                      series: [{
                                      name: '运动步数',
                                                                                                      type: 'bar',
                                                                                                        barWidth: 10, // 柱子宽度
                                                                                                        label: {
                                                                                                          show: true,
                                                                                                          position: 'top', // 位置
                                                                                                          color: '#1CD8A8',
                                                                                                          fontSize: 14,
                                                                                                          fontWeight: 'bold', // 加粗
                                                                                                          distance: 10 // 距离
                                                                                                        }, // 柱子上方的数值
                                                                                                        itemStyle: {
                                                                                                          barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                                                          color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                                                            '#2FAEF2',
                                                                                                            '#1CD8A8'
                                                                                                          ].map((color, offset) => ({color, offset}))), // 渐变
                                                                                                        },

                                                                                                      data:  stepNumber,

                                                                                                  }]
                                };


                                        // 使用刚指定的配置项和数据显示图表。
                                        steps.setOption(option02);
                                    steps.resize();
                                window.addEventListener("resize", function () {

                                steps.resize();

                                });
                         //周运动里程
                         var mileage = echarts.init(document.getElementById('mileage'));
                                         option_mileage = {
                                             grid: {
                                                         top: 15,
                                                         left: 15,
                                                         right: 15,
                                                         bottom: "10%",
                                                         containLabel: true //轴上的数值
                                                    },
                                             xAxis: {
                                                        type: 'category',
                                                        boundaryGap:true,
                                                        axisLine: {
                                                             show: false,
                                                        },
                                                                 axisTick: {

                                                                 },
                                                                 show:false,
                                                                 data:arr_time,
                                                             },
                                             yAxis: {
                                                                   type: 'value',
                                                                   show: false, // 是否显示坐标轴
                                                                   data: [],
                                                                   axisLabel: { show: false }, // 坐标轴刻度标签
                                                                   axisLine: { show: false }, // 坐标轴轴线
                                                                   axisTick: { show: false }, // 坐标轴刻度
                                                                   splitLine: { show:false } // 分割线

                                                             },
                                             dataZoom: [ {
                                             type: 'inside'
                                             }],

                                             tooltip: {
                                                                 trigger: 'axis',
                                                                 axisPointer: {
                                                                     label: {
                                                                         textStyle: {
                                                                             color: "white"
                                                                         }
                                                                     }
                                                                 }
                                                             },
                                            series: [{
                                            name:"运动里程",
                                                                 type: 'bar',
                                                                   barWidth: 10, // 柱子宽度
                                                                   label: {
                                                                     show: true,
                                                                     position: 'top', // 位置
                                                                     color: "#1CD8A8",
                                                                     fontSize: 14,
                                                                     fontWeight: 'bold', // 加粗
                                                                     distance: 10 // 距离
                                                                   }, // 柱子上方的数值
                                                                   itemStyle: {
                                                                     barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                     color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                       '#2FAEF2',
                                                                       '#1CD8A8'
                                                                     ].map((color, offset) => ({color, offset}))), // 渐变
                                                                   },

                                                                 data:  distance,

                                                             }]
                                         };


                                                 // 使用刚指定的配置项和数据显示图表。
                                                 mileage.setOption(option_mileage);
                                     mileage.resize();
                                         window.addEventListener("resize", function () {

                                         mileage.resize();

                                         });


        //周运动消耗
                var consume = echarts.init(document.getElementById('consume'));
                option_consume = {
                                             grid: {
                                                         top: 15,
                                                         left: 15,
                                                         right: 15,
                                                         bottom: "10%",
                                                         containLabel: true //轴上的数值
                                                    },
                                             xAxis: {
                                                        type: 'category',
                                                        boundaryGap:true,
                                                        axisLine: {
                                                             show: false,
                                                        },
                                                                 axisTick: {

                                                                 },
                                                                 show:false,
                                                                 data:arr_time,
                                                             },
                                             yAxis: {
                                                                   type: 'value',
                                                                   show: false, // 是否显示坐标轴
                                                                   data: [],
                                                                   axisLabel: { show: false }, // 坐标轴刻度标签
                                                                   axisLine: { show: false }, // 坐标轴轴线
                                                                   axisTick: { show: false }, // 坐标轴刻度
                                                                   splitLine: { show:false } // 分割线

                                                             },
                                             dataZoom: [ {
                                             type: 'inside'
                                             }],

                                             tooltip: {
                                                                 trigger: 'axis',
                                                                 axisPointer: {
                                                                     label: {
                                                                         textStyle: {
                                                                             color: "white"
                                                                         }
                                                                     }
                                                                 }
                                                             },
                                            series: [{
                                            name:"消耗大卡",
                                                                 type: 'bar',
                                                                   barWidth: 10, // 柱子宽度
                                                                   label: {
                                                                     show: true,
                                                                     position: 'top', // 位置
                                                                     color: "#1CD8A8",
                                                                     fontSize: 14,
                                                                     fontWeight: 'bold', // 加粗
                                                                     distance: 10 // 距离
                                                                   }, // 柱子上方的数值
                                                                   itemStyle: {
                                                                     barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                     color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                       '#2FAEF2',
                                                                       '#1CD8A8'
                                                                     ].map((color, offset) => ({color, offset}))), // 渐变
                                                                   },

                                                                 data:  calorie,

                                                             }]
                };


                        // 使用刚指定的配置项和数据显示图表。
                        consume.setOption(option_consume);
            consume.resize();
                window.addEventListener("resize", function () {

                consume.resize();

                });

                    }
                   });
              //月数据
              $.ajax({
                   type:"POST",
                   url:"runData/getMoonData.do",
                   data:JSON.stringify(formData),
                   datatype:"text",
                   contentType : "application/json",
                   success:function(data){

                         let distance = data.map(obj => {return obj.distance});
                         let calorie = data.map(obj => {return obj.calorie});
                         let runTime = data.map(obj => {return obj.runTime});
                         let stepNumber = data.map(obj => {return obj.stepNumber});
                         let recordTime = data.map(obj => {return obj.recordTime});

                         //月总里程步数
                         var distance_all=0;
                         var calorie_all=0;
                         var stepNumber_all=0;
                        //月时间取反
                          for (var key in distance) {
                               distance_all += distance[key];
                               calorie_all += calorie[key];
                               stepNumber_all += stepNumber[key];
                             }


                         var recordTime_month=new Array();

                        for (var key in recordTime) {
                               recordTime_month[key] = recordTime[key].slice(0,10);

                        }
                         $("#distance_month").html(distance_all);
                         $("#calorie_month").html(calorie_all);
                         $("#steps_month").html(stepNumber_all);
                         $("#date_month").html(recordTime[0].slice(0,10));
                         $("#timeStep_month").html(stepNumber[0]);

                        for (i=1;i<distance.length;i++) {
                                var str="<li><time class='cbp_tmtime' datetime='2017-11-04T18:30'><span style='margin-right:30px' class='hidden'>"+recordTime[i].slice(0,10)+"</span></time><div class='cbp_tmicon bg-info'><i class='zmdi zmdi-label'></i></div><div class='cbp_tmlabel'><h2><a href='javascript:void(0);'>运动</a> <span>行走  "+stepNumber[i]+" 步</span></h2></div></li>";
                                 $('#timeAll_month').append(str);
                           }

                          //时长echarts
                           $("#hours_month").css('width',$("#heart").width());
                          var myChart = echarts.init(document.getElementById('hours_month'));

                                  option = {

                                             grid: {
                                                         top: 15,
                                                         left: 15,
                                                         right: 15,
                                                         bottom: "10%",
                                                         containLabel: true //轴上的数值
                                                    },
                                             xAxis: {
                                                        type: 'category',
                                                        boundaryGap:true,
                                                        axisLine: {
                                                             show: false,
                                                        },
                                                                 axisTick: {

                                                                 },
                                                                 data:recordTime_month,
                                                             },
                                             yAxis: {
                                                                   type: 'value',
                                                                   show: false, // 是否显示坐标轴
                                                                   data: [],
                                                                   axisLabel: { show: false }, // 坐标轴刻度标签
                                                                   axisLine: { show: false }, // 坐标轴轴线
                                                                   axisTick: { show: false }, // 坐标轴刻度
                                                                   splitLine: { show:false } // 分割线

                                                             },
                                             dataZoom: [ {
                                             type: 'inside'
                                             }],

                                             tooltip: {
                                                                 trigger: 'axis',
                                                                 axisPointer: {
                                                                     label: {
                                                                         textStyle: {
                                                                             color: "white"
                                                                         }
                                                                     }
                                                                 }
                                                             },
                                            series: [{
                                                                 type: 'bar',
                                                                   barWidth: 10, // 柱子宽度
                                                                   label: {
                                                                     show: true,
                                                                     position: 'top', // 位置
                                                                     color: '#1CD8A8',
                                                                     fontSize: 14,
                                                                     fontWeight: 'bold', // 加粗
                                                                     distance: 2 // 距离
                                                                   }, // 柱子上方的数值
                                                                   itemStyle: {
                                                                     barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                     color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                       '#2FAEF2',
                                                                       '#1CD8A8'
                                                                     ].map((color, offset) => ({color, offset}))), // 渐变
                                                                   },

                                                                 data:  runTime,

                                                             }]

                              };


                                      // 使用刚指定的配置项和数据显示图表。
                                      myChart.setOption(option);
                                      myChart.resize();
                                      window.addEventListener("resize", function () {
                                      myChart.resize();

                                      });
                         //月运动步数

                                var days = echarts.init(document.getElementById('days'));
                                days_month = {
                                     grid: {
                                         top: 15,
                                         left: 15,
                                         right: 15,
                                         bottom: "10%",
                                         containLabel: true //轴上的数值
                                         },
                                      xAxis: {
                                                                                             type: 'category',
                                                                                             boundaryGap:true,
                                                                                             axisLine: {
                                                                                                  show: false,
                                                                                             },
                                                                                                      axisTick: {

                                                                                                      },
                                                                                                      data:recordTime_month,
                                                                                                      show:false,
                                                                                                  },
                                     yAxis: {
                                                                                                        type: 'value',
                                                                                                        show: false, // 是否显示坐标轴

                                                                                                        axisLabel: { show: false }, // 坐标轴刻度标签
                                                                                                        axisLine: { show: false }, // 坐标轴轴线
                                                                                                        axisTick: { show: false }, // 坐标轴刻度
                                                                                                        splitLine: { show:false } // 分割线

                                                                                                  },
                                      dataZoom: [ {
                                                    type: 'inside'
                                      }],

                                     tooltip: {
                                                                                                      trigger: 'axis',
                                                                                                      axisPointer: {
                                                                                                          label: {
                                                                                                              textStyle: {
                                                                                                                  color: "white"
                                                                                                              }
                                                                                                          }
                                                                                                      }
                                                                                                  },

                                      series: [{
                                      name: '运动步数',
                                                                                                      type: 'bar',
                                                                                                        barWidth: 4, // 柱子宽度

                                                                                                        itemStyle: {
                                                                                                          barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                                                          color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                                                            '#2FAEF2',
                                                                                                            '#1CD8A8'
                                                                                                          ].map((color, offset) => ({color, offset}))), // 渐变
                                                                                                        },

                                                                                                      data:  stepNumber,

                                                                                                  }]
                                };


                                        // 使用刚指定的配置项和数据显示图表。
                                        days.setOption(days_month);
                                    days.resize();
                                window.addEventListener("resize", function () {

                                days.resize();

                                });
                         //月运动里程
                         var frequency = echarts.init(document.getElementById('frequency'));
                                         option_frequency = {
                                             grid: {
                                                         top: 15,
                                                         left: 15,
                                                         right: 15,
                                                         bottom: "10%",
                                                         containLabel: true //轴上的数值
                                                    },
                                             xAxis: {
                                                        type: 'category',
                                                        boundaryGap:true,
                                                        axisLine: {
                                                             show: false,
                                                        },
                                                                 axisTick: {

                                                                 },
                                                                 show:false,
                                                                 data:recordTime_month,
                                                             },
                                             yAxis: {
                                                                   type: 'value',
                                                                   show: false, // 是否显示坐标轴
                                                                   data: [],
                                                                   axisLabel: { show: false }, // 坐标轴刻度标签
                                                                   axisLine: { show: false }, // 坐标轴轴线
                                                                   axisTick: { show: false }, // 坐标轴刻度
                                                                   splitLine: { show:false } // 分割线

                                                             },
                                             dataZoom: [ {
                                             type: 'inside'
                                             }],

                                             tooltip: {
                                                                 trigger: 'axis',
                                                                 axisPointer: {
                                                                     label: {
                                                                         textStyle: {
                                                                             color: "white"
                                                                         }
                                                                     }
                                                                 }
                                                             },
                                            series: [{
                                            name:"运动里程",
                                                                 type: 'bar',
                                                                   barWidth: 4, // 柱子宽度

                                                                   itemStyle: {
                                                                     barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                     color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                       '#2FAEF2',
                                                                       '#1CD8A8'
                                                                     ].map((color, offset) => ({color, offset}))), // 渐变
                                                                   },

                                                                 data:  distance,

                                                             }]
                                         };


                                                 // 使用刚指定的配置项和数据显示图表。
                                                 frequency.setOption(option_frequency);
                                     frequency.resize();
                                         window.addEventListener("resize", function () {

                                         frequency.resize();

                                         });


        //月运动消耗
                var consume_month = echarts.init(document.getElementById('consume_month'));
                option_consume_month = {
                                             grid: {
                                                         top: 15,
                                                         left: 15,
                                                         right: 15,
                                                         bottom: "10%",
                                                         containLabel: true //轴上的数值
                                                    },
                                             xAxis: {
                                                        type: 'category',
                                                        boundaryGap:true,
                                                        axisLine: {
                                                             show: false,
                                                        },
                                                                 axisTick: {

                                                                 },
                                                                 show:false,
                                                                 data:recordTime_month,
                                                             },
                                             yAxis: {
                                                                   type: 'value',
                                                                   show: false, // 是否显示坐标轴
                                                                   data: [],
                                                                   axisLabel: { show: false }, // 坐标轴刻度标签
                                                                   axisLine: { show: false }, // 坐标轴轴线
                                                                   axisTick: { show: false }, // 坐标轴刻度
                                                                   splitLine: { show:false } // 分割线

                                                             },
                                             dataZoom: [ {
                                             type: 'inside'
                                             }],

                                             tooltip: {
                                                                 trigger: 'axis',
                                                                 axisPointer: {
                                                                     label: {
                                                                         textStyle: {
                                                                             color: "white"
                                                                         }
                                                                     }
                                                                 }
                                                             },
                                            series: [{
                                            name:"消耗大卡",
                                                                 type: 'bar',
                                                                   barWidth: 4, // 柱子宽度

                                                                   itemStyle: {
                                                                     barBorderRadius: [20, 20, 0, 0],// 圆角（左上、右上、右下、左下）
                                                                     color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                                                                       '#2FAEF2',
                                                                       '#1CD8A8'
                                                                     ].map((color, offset) => ({color, offset}))), // 渐变
                                                                   },

                                                                 data:  calorie,

                                                             }]
                };


                        // 使用刚指定的配置项和数据显示图表。
                        consume_month.setOption(option_consume_month);
            consume_month.resize();
                window.addEventListener("resize", function () {
                consume_month.resize();

                });

                    }
                   });


 var myColor = [ '#11da01', '#118b43', '#c3eb18',
               '#dfde14', '#eac736', '#ed5501', '#9d0700', '#e80c00'
          ];


}



//添加class属性

