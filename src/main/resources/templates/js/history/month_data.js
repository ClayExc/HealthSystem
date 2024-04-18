function month_data(){
     var time=$("#dtp_input2").val();
    var user=$.cookie('user_id');
    var formData={
           "user_id":user,
           "record_time":time
     };

                $.ajax({
                   type:"POST",
                   url:"runData/getMoonData.do",
                   data:JSON.stringify(formData),
                   datatype:"text",
                   contentType : "application/json",
                   success:function(data){
                   if(data.status==0){
                           alert("没有数据哦！请保持运动！");
                       }
                    else{
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
                         $("#distance_month").html(distance_all.toFixed(2));
                         $("#calorie_month").html(calorie_all);
                         $("#steps_month").html(stepNumber_all);
                         $("#date_month").html(recordTime[0].slice(0,10));
                         $("#timeStep_month").html(stepNumber[0]);
                         $('#timeAll_month').empty();
                         var str1="<li><time class='cbp_tmtime' datetime='2017-11-04T18:30'><span style='margin-right:30px' class='hidden'>"+recordTime[0].slice(0,10)+"</span></time><div class='cbp_tmicon'><i class='zmdi zmdi-account'></i></div><div class='cbp_tmlabel'><h2><a href='javascript:void(0);'>运动</a> <span>行走  "+stepNumber[0]+" 步</span></h2></div></li>";
                         for (i=1;i<recordTime_month.length;i++) {
                                  str1+="<li><time class='cbp_tmtime' datetime='2017-11-04T18:30'><span style='margin-right:30px' class='hidden'>"+recordTime[i].slice(0,10)+"</span></time><div class='cbp_tmicon bg-info'><i class='zmdi zmdi-label'></i></div><div class='cbp_tmlabel'><h2><a href='javascript:void(0);'>运动</a> <span>行走  "+stepNumber[i]+" 步</span></h2></div></li>";
                         }
                          $('#timeAll_month').append(str1);

                          //时长echarts

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
                    }

                   });

}


