function week_ex(){
var obj = document.getElementById("jcalendar_week");
    var year=obj.getAttribute("year");
    var week=obj.getAttribute("weeknum");
    var time=getXDate(year,week);
    var start_time=time.slice(0,19);
    var end_time=time.slice(21,42);

    var user=$.cookie('user_id');
    var formData={
           "user_id":user,
           "startTime":start_time,
           "endTime":end_time
     };

     $.ajax({
                   type:"POST",
                   url:"runData/getWeekDataByTime.do",
                   data:JSON.stringify(formData),
                   datatype:"text",
                   contentType : "application/json",
                   success:function(data){

                   if(data.status==0){
                                              alert("没有数据哦！请保持运动！");
                                          }
                                       else{
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
                        for(i=0;i<arr_week.length;i++)
                        {
                            arr_time[i]=arr_week[i];
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
                         $('#time_all').empty();
                         var str="<li><time class='cbp_tmtime' datetime='2017-11-04T18:30'><span style='margin-right:30px' class='hidden'>"+recordTime[0].slice(0,10)+"</span></time><div class='cbp_tmicon'><i class='zmdi zmdi-account'></i></div><div class='cbp_tmlabel'><h2><a href='javascript:void(0);'>运动</a> <span>行走  "+stepNumber[0]+" 步</span></h2></div></li>";
                        for (i=1;i<distance.length;i++) {
                                 str+="<li><time class='cbp_tmtime' datetime='2017-11-04T18:30'><span style='margin-right:30px' class='hidden'>"+recordTime[i].slice(0,10)+"</span></time><div class='cbp_tmicon bg-info'><i class='zmdi zmdi-label'></i></div><div class='cbp_tmlabel'><h2><a href='javascript:void(0);'>运动</a> <span>行走  "+stepNumber[i]+" 步</span></h2></div></li>";

                           }
                            $('#time_all').append(str);
                          //时长echarts

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
                    }
                   });

}

//时间转换（年周转换日期）
function getXDate(year,weeks){
				var date = new Date(year,"0","1");
				var time = date.getTime();
				// 获取当前星期几，0：星期一 。。。。
				var _week = date.getDay();
				//当这一年的1月1日为周日时则本年有54周，否则没有54周，没有则去除第54周的提示
				if(_week!=0){//一年53周情况
							if(weeks==54){
								return '今年没有54周';
							}
							var cnt=0;// 获取距离周末的天数
							if(_week==0){
								cnt = 7;
							}else if(_week==1){
								cnt = 6;
							}else if(_week==2){
								cnt = 5;
							}else if(_week==3){
								cnt = 4;
							}else if(_week==4){
								cnt = 3;
							}else if(_week==5){
								cnt = 2;
							}else if(_week==6){
								cnt = 1;
							}
							cnt += 1;//加1表示以星期一为一周的第一天
							// 将这个长整形时间加上第N周的时间偏移
							time += cnt*24*3600000; //第2周开始时间

							var nextYear = new Date(parseInt(year,10)+1,"0","1");
							var nextWeek = nextYear.getDay();
							var lastcnt = 0;//获取最后一周开始时间到周末的天数
							if(nextWeek==0){
								lastcnt = 6;
							}else if(nextWeek==1){
								lastcnt = 0;
							}else if(nextWeek==2){
								lastcnt = 1;
							}else if(nextWeek==3){
								lastcnt = 2;
							}else if(nextWeek==4){
								lastcnt = 3;
							}else if(nextWeek==5){
								lastcnt = 4;
							}else if(nextWeek==6){
								lastcnt = 5;
							}
							if(weeks==1){//第1周特殊处理
								// 为日期对象 date 重新设置成时间 time
								var start = date.format("yyyy-mm-dd 00:00:00");
								date.setTime(time-24*3600000);
								return start +'--'+ date.format("yyyy-mm-dd 00:00:00");
							}else if(weeks==53){//第53周特殊处理
								var start = time+(weeks-2)*7*24*3600000; //第53周开始时间
								var end = time+(weeks-2)*7*24*3600000 + lastcnt*24*3600000 - 24*3600000; //第53周结束时间
								// 为日期对象 date 重新设置成时间 time
								date.setTime(start);
								var _start = date.format("yyyy-mm- 00:00:00");
								date.setTime(end);
								var _end = date.format("yyyy-mm-dd 00:00:00");
								return _start +'--'+ _end;
							}else{
								var start = time+(weeks-2)*7*24*3600000; //第n周开始时间
								var end = time+(weeks-1)*7*24*3600000 - 24*3600000; //第n周结束时间
								// 为日期对象 date 重新设置成时间 time
								date.setTime(start);
								var _start = date.format("yyyy-mm-dd 00:00:00");
								date.setTime(end);
								var _end = date.format("yyyy-mm-dd 00:00:00");
								return _start +'--'+ _end;
							}
				}else{//一年54周情况
							var cnt=0;// 获取距离周末的天数
							if(_week==0 && weeks==1){//第一周
								cnt = 0;
							}else if(_week==0){
								cnt = 7;
							}else if(_week==1){
								cnt = 6;
							}else if(_week==2){
								cnt = 5;
							}else if(_week==3){
								cnt = 4;
							}else if(_week==4){
								cnt = 3;
							}else if(_week==5){
								cnt = 2;
							}else if(_week==6){
								cnt = 1;
							}
							cnt += 1;//加1表示以星期一为一周的第一天
							// 将这个长整形时间加上第N周的时间偏移
							time += 24*3600000; //第2周开始时间

							var nextYear = new Date(parseInt(year,10)+1,"0","1");
							var nextWeek = nextYear.getDay();
							var lastcnt = 0;//获取最后一周开始时间到周末的天数
							if(nextWeek==0){
								lastcnt = 6;
							}else if(nextWeek==1){
								lastcnt = 0;
							}else if(nextWeek==2){
								lastcnt = 1;
							}else if(nextWeek==3){
								lastcnt = 2;
							}else if(nextWeek==4){
								lastcnt = 3;
							}else if(nextWeek==5){
								lastcnt = 4;
							}else if(nextWeek==6){
								lastcnt = 5;
							}

							if(weeks==1){//第1周特殊处理
								// 为日期对象 date 重新设置成时间 time
								var start = date.format("yyyy-mm-dd 00:00:00");
								date.setTime(time-24*3600000);
								return start +'--'+ date.format("yyyy-mm-dd 00:00:00");
							}else if(weeks==54){//第54周特殊处理
								var start = time+(weeks-2)*7*24*3600000; //第54周开始时间
								var end = time+(weeks-2)*7*24*3600000 + lastcnt*24*3600000 - 24*3600000; //第53周结束时间
								// 为日期对象 date 重新设置成时间 time
								date.setTime(start);
								var _start = date.format("yyyy-mm-dd 00:00:00");
								date.setTime(end);
								var _end = date.format("yyyy-mm-dd 00:00:00");
								return _start +'--'+ _end;
							}else{
								var start = time+(weeks-2)*7*24*3600000; //第n周开始时间
								var end = time+(weeks-1)*7*24*3600000 - 24*3600000; //第n周结束时间
								// 为日期对象 date 重新设置成时间 time
								date.setTime(start);
								var _start = date.format("yyyy-mm-dd 00:00:00");
								date.setTime(end);
								var _end = date.format("yyyy-mm-dd 00:00:00");
								return _start +'--'+ _end;
							}
				}

		}