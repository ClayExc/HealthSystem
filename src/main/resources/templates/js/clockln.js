var high = 150;
var low = 120;
var allTime = 0;
var validTime = 0;
var curheart=0;
var leavel = "强度";

function showCard(){
  $("#showCard").show();
  $("#effective_time").val(validTime);
  clearInterval(interval);
}
function startRun(){
  $("#showCard").hide();

}
/*疲劳度切换*/
$(document).ready(function(){
  $("#fatigue01").click(function(){
  $("#ex").html("无任何感觉，面色稍红，排汗量较少，呼吸中等度加快，动作基本稳定，注意力较为集中");
  });
});
$(document).ready(function(){
  $("#fatigue02").click(function(){
  $("#ex").html("懒得爬楼，上楼时常常绊脚,四肢发硬，两腿沉重，双手易发抖。");
  });
});
$(document).ready(function(){
  $("#fatigue03").click(function(){
  $("#ex").html("头痛、胸痛、恶心，面色十分红或者苍白，有时呈紫蓝色，排汗量非常多，尤其是整个躯干部分，呼吸加快，有时呼吸节奏紊乱，动作稳定性、速度大幅度下降，注意力难以集中，接受信号缓慢。");
  });
});


/*用力感觉切换*/

$(document).ready(function(){
  $("#force01").click(function(){
  $("#ex_force").html("没有什么感觉,这是在你休息时侯的惑觉,丝毫不觉得疲意,呼吸完全平缓。");
  });
});
$(document).ready(function(){
  $("#force02").click(function(){
  $("#ex_force").html("这是在桌前工作或阅读时候的感觉,你丝毫不觉得疲意,而且呼吸平缓。");
  });
});
$(document).ready(function(){
  $("#force03").click(function(){
  $("#ex_force").html("这是你穿衣服时侯可能出现的感觉,你稍欲疲童或毫无疲惫,呼吸平缓。");
  });
});

$(document).ready(function(){
  $("#force04").click(function(){
  $("#ex_force").html("过房间打开电视机的惑觉,你稍微疲賁,觉察到轻微的呼吸。");
  });
});
$(document).ready(function(){
  $("#force05").click(function(){
  $("#ex_force").html("你在户外慢步行走的感觉,你感到轻微的疲意,呼吸微微上扬但是依然自在。");
  });
});
$(document).ready(function(){
  $("#force06").click(function(){
  $("#ex_force").html("这是你轻快走出商店的感觉，你感觉到轻微的疲意,你能觉察到自己的呼吸声,气息比4级还急促一些。");
  });
});
$(document).ready(function(){
  $("#force07").click(function(){
  $("#ex_force").html("这是你约会迟到急忙赶去可能出现的感党,但是你知道你可以维持这样的步调,你呼吸急促,而且可以觉察得到。");
  });
});
$(document).ready(function(){
  $("#force08").click(function(){
  $("#ex_force").html("这是你激烈运动时可能出现的感觉,你势必感到疲惫，但是确定自己可以维持到运动结束,你呼吸急促感觉得到你可以与人对话,，但是你宁愿不说话");
  });
});
$(document).ready(function(){
  $("#force09").click(function(){
  $("#ex_force").html("做剧烈运动下所出现的感觉,感到极度的疲意,你认为自己可以维持步调直到运动结束,只是你无法百分之百的确定，你的呼吸非常急促,你还是可以与人对话,但是你不想这么。");
  });
});
$(document).ready(function(){
  $("#force10").click(function(){
  $("#ex_force").html("彻底的筋疲力尽,无法持久。");
  });


});


function getAllTime(data) {
  var j=0;
  var validTimeSEC = validTime;
  var allTimeSEC = allTime;
  if (data["data"][data["data"].length-1]["value"]<=high && data["data"][data["data"].length-1]["value"]>=low){
    validTimeSEC = validTimeSEC + 3;
  }
  //for (var i=0;i<data["data"].length;i++){
  //value = data["data"][i]["value"];
  //if (value<=high && value>=low){
  // 3代表数据采样间隔是3秒
  //validTimeSEC = validTimeSEC + 3;
  //j = j+1;
  //}
  // 总时间
  //allTimeSEC = allTimeSEC + 3;
  //}
  allTimeSEC = allTimeSEC + 3;

  if(data["data"][data["data"].length-1]["value"]>150){
    leavel = "过大";
  }else if(data["data"][data["data"].length-1]["value"]<120){
    leavel = "过小";
  }else{
    leavel = "适中";
  }
  allTime = allTimeSEC;
  validTime = validTimeSEC;
}

var per=0.0;
function getData(start,length) {
  var x = null;
  var y = null;
  var urlapi="http://10.102.65.180:8000/sportsData/data/start"+start+"length"+length+"/";
  $.ajax({
    url:"/web/heartdata.do",
    data:{weburl:urlapi},
    dataType:"json",
    async:false,
    success:function (data) {
      if(data["status"]=="success"){
        x = [];
        y = [];
        for(var i=0;i<data['data'].length;i++){
          x.push(String(data["data"][i]["id"]));
          y.push(String(data["data"][i]["value"]));
          per = String(data["data"][i]["aerobic"]);
        }
        curheart=data['data'][data['data'].length-1]["value"];
        getAllTime(data);
      }
    }
  });
  return [x,y]
}
Chart.defaults.global = {
  // Boolean - Whether to animate the chart
  animation: false,

  // Number - Number of animation steps
  animationSteps: 60,

  // String - Animation easing effect
  animationEasing: "easeOutQuart",

  // Boolean - If we should show the scale at all
  showScale: true,

  // Boolean - If we want to override with a hard coded scale
  scaleOverride: false,

  // ** Required if scaleOverride is true **
  // Number - The number of steps in a hard coded scale
  scaleSteps: null,
  // Number - The value jump in the hard coded scale
  scaleStepWidth: null,
  // Number - The scale starting value
  scaleStartValue: null,

  // String - Colour of the scale line
  scaleLineColor: "rgba(0,0,0,.1)",

  // Number - Pixel width of the scale line
  scaleLineWidth: 1,

  // Boolean - Whether to show labels on the scale
  scaleShowLabels: true,

  // Interpolated JS string - can access value
  scaleLabel: "<%=value%>",

  // Boolean - Whether the scale should stick to integers, not floats even if drawing space is there
  scaleIntegersOnly: true,

  // Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
  scaleBeginAtZero: false,

  // String - Scale label font declaration for the scale label
  scaleFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

  // Number - Scale label font size in pixels
  scaleFontSize: 12,

  // String - Scale label font weight style
  scaleFontStyle: "normal",

  // String - Scale label font colour
  scaleFontColor: "#666",

  // Boolean - whether or not the chart should be responsive and resize when the browser does.
  responsive: false,

  // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
  maintainAspectRatio: true,

  // Boolean - Determines whether to draw tooltips on the canvas or not
  showTooltips: true,

  // Function - Determines whether to execute the customTooltips function instead of drawing the built in tooltips (See [Advanced - External Tooltips](#advanced-usage-custom-tooltips))
  customTooltips: false,

  // Array - Array of string names to attach tooltip events
  tooltipEvents: ["mousemove", "touchstart", "touchmove"],

  // String - Tooltip background colour
  tooltipFillColor: "rgba(0,0,0,0.8)",

  // String - Tooltip label font declaration for the scale label
  tooltipFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

  // Number - Tooltip label font size in pixels
  tooltipFontSize: 14,

  // String - Tooltip font weight style
  tooltipFontStyle: "normal",

  // String - Tooltip label font colour
  tooltipFontColor: "#fff",

  // String - Tooltip title font declaration for the scale label
  tooltipTitleFontFamily: "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

  // Number - Tooltip title font size in pixels
  tooltipTitleFontSize: 14,

  // String - Tooltip title font weight style
  tooltipTitleFontStyle: "bold",

  // String - Tooltip title font colour
  tooltipTitleFontColor: "#fff",

  // Number - pixel width of padding around tooltip text
  tooltipYPadding: 6,

  // Number - pixel width of padding around tooltip text
  tooltipXPadding: 6,

  // Number - Size of the caret on the tooltip
  tooltipCaretSize: 8,

  // Number - Pixel radius of the tooltip border
  tooltipCornerRadius: 6,

  // Number - Pixel offset from point x to tooltip edge
  tooltipXOffset: 10,

// String - Template string for single tooltips
// {#tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",#}

// String - Template string for multiple tooltips
  multiTooltipTemplate: "<%= value %>",

  // Function - Will fire on animation progression.
  onAnimationProgress: function(){},

// Function - Will fire on animation completion.
  onAnimationComplete: function(){}
};

var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
var ctx = document.getElementById("canvas").getContext("2d");
$(".circleChart#1").circleChart({
  size:90
});
$(".circleChart#0").circleChart({
  size: 90,
  value: 50,
  text: 0,
  onDraw: function(el, circle) {
    circle.text(Math.round(circle.value) + "%");
  }
});


var start=0;
var length = 20;
var lineChartData = {
  labels : null,
  datasets : [
    {
      label: "running",
      fillColor : "rgba(220,220,220,0.2)",
      strokeColor : "rgba(220,220,220,1)",
      pointColor : "rgba(220,220,220,1)",
      pointStrokeColor : "#fff",
      pointHighlightFill : "#fff",
      pointHighlightStroke : "rgba(220,220,220,1)",
      data : null,
    },
  ]

};

function refresh(){
  start = start+1;
  data = getData(start,length);
  x = data[0];
  y = data[1];
  if(x!=null && y!=null){
    lineChartData.labels = x;
    lineChartData.datasets[0].data = y;
    window.myLine = new Chart(ctx).Line(lineChartData, {
      responsive: true
    });
    setPer(per);
  }
  $('#currentheart').text(curheart);
  $('#currentstrength').text(leavel);
  $('#sporttime').text(allTime);
  $('#time').text(validTime);
  $('#youyang').text((Math.ceil(per*100))+"%");
  console.log(x);
}

function setPer(p){
  $("#0").circleChart({
    value: p * 100
  });
}

startrun();
 function startrun(){
  data = getData(start,length);
  x = data[0];
  y = data[1];
  lineChartData.labels = x;
  lineChartData.datasets[0].data = y;

  window.myLine = new Chart(ctx).Line(lineChartData, {
    responsive: true

  });
   setPer(per);
   setInterval("refresh()", 2000);
}


