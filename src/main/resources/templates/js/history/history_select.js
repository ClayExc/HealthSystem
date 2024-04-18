function select_time(){
var user=$.cookie('user_id')
     var time=$("#dtp_input1").val();
 var myColor = [ '#11da01', '#118b43', '#c3eb18',
               '#dfde14', '#eac736', '#ed5501', '#9d0700', '#e80c00'
          ];
     //获取值
         var formData={
                          "user_id":user,
                          "record_time":time
                      };

        $.ajax({
                        type:"POST",
                        url:"runData/getRunDataByTime.do",
                        data:JSON.stringify(formData),
                        datatype:"text",
                        contentType : "application/json",
                        success:function(data){
                            console.log(data);

                            if(data.status==0){
                                alert("没有数据哦！请保持运动！");
                            }
                            else{
                                $("#steps_today").html(data[0].stepNumber);
                                                            $("#distance").html(data[0].distance);
                                                            $("#calorie").html(data[0].calorie);
                                                            $("#stepNumber").html(data[0].stepNumber);
                                                            $("#recordTime").html(data[0].recordTime.slice(0,10));
                            }



                        }
                      })
        $.ajax({
                                          type:"POST",
                                          url:"runData/getRunData.do",
                                          data:JSON.stringify(formData),
                                          datatype:"text",
                                          contentType : "application/json",
                                          //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                          success:function (data) {
                                                  console.log(data);
                                                    if(data.status==0){
                                                             alert("没有数据哦！请保持运动！");
                                                    }
                                                    else{

                                                  let arr_time = data.map(obj => {return obj.recordTime});
                                                  let arr_heart = data.map(obj => {return obj.heartRate});
                                                   let arr_speed = data.map(obj => {return obj.runSpeed});
                                                  var heart=new Array();
                                                  var speed=new Array();
                                                  for (var key in arr_heart) {
                                                          heart[key] = arr_heart[key].toFixed(2);
                                                          speed[key] = arr_speed[key].toFixed(2);
                                                      }
                                                  console.log(arr_heart);
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
                                                                                                                             return value + " m/s  -";
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
                                                                                  name: '运动速度',
                                                                                                                         type: 'line',
                                                                                                                         symbol: 'none', //这句就是去掉折线上的点的
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

                                                                              }
                                                                          }
                                                              myChart.setOption(speed);

}
                                          }
                                      });

}