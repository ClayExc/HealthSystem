$(function () {

    $("#submit").click(function () {
        /*处方名称*/
        var pre_name=$("#pre_name").val();
         /*时间*/
        var pre_time01=$("#pre_time01").val();
        var pre_time02=$("#pre_time02").val();
        var pre_time=pre_time01+"~"+pre_time02;



        /*强度刻画类型*/
       var strength= $("input[name='radioInline']:checked").val();
       var strength01
       if(strength==1)
       {
              strength01="心率";
       }
       if(strength==2)
       {
                strength01="负重";
       }
        /*心率*/
            var pre_min_heart=$("#pre_min_heart").val();
            var pre_max_heart=$("#pre_max_heart").val();

        /*负重*/
        var pre_weight=$("#pre_weight").val();
        var pre_number=$("#pre_number").val();
        var pre_group_num=$("#pre_group_num").val();


        /*运动频率*/
        var pre_frequency=$("#pre_frequency").val();

        /*运动类型*/
         var select = document.getElementById("sportsLike");
         var sport_type = select.value;
        /*疲劳度等级*/
        var select01 = document.getElementById("fatigueList");
         var fatigue = select01.value;

        var effect=[];
        var equipment=[];
        var disease=[];

        //校验运动效果是否为空
        $("[name='dropdown-group03']:checked").each(function (index,element) {
            effect.push($(this).val());
        });

        if (effect.length==0){
            alert("请至少选择一个运动效果");
            return;
        }
         //校验运动器材是否为空
         $("[name='dropdown-group02']:checked").each(function (index,element) {
           equipment.push($(this).val());
          });

         if (equipment.length==0){
            alert("请至少选择一个运动器材");
             return;
         }
          //校验禁忌疾病是否为空
          $("[name='dropdown-group05']:checked").each(function (index,element) {
             disease.push($(this).val());
          });

          if (disease.length==0){
             alert("请至少选择一个禁忌疾病");
             return;
          }
         /*方法介绍*/
         var pre_ways=$("#pre_ways").val();
         /*评测周期*/
         var pre_cycle=$("#pre_cycle").val();
         /*视频地址*/
         var pre_address=$("#pre_address").val();
         /*注意事项*/
         var pre_notice=$("#pre_notice").val();

        console.log(pre_name,strength01,pre_min_heart,fatigue,);

        uploadUserInfo(pre_name,pre_time,strength01,pre_min_heart,pre_max_heart,pre_weight,pre_number,
        pre_group_num,pre_frequency,sport_type,fatigue,effect,equipment,disease,pre_ways,pre_cycle,pre_address,pre_notice);
    });
});


function getCheckBoxValue(strSel) {
    var params={};
    for (var i = 0; i < strSel.length; i++) {
        switch (strSel[i]) {
            case "upper":
                params.category_upper_limb="1";
                break;
            case "body":
                params.category_body_limb="1";
                break;
            case "lower":
                params.category_lower_limb="1";
                break;
            case "endurance":
                params.category_endurance="1";
                break;
            case "sensitive":
                params.category_sensitive="1";
                break;
            case "power":
                params.category_power="1";
                break;
            case "speed":
                params.category_speed="1";
                break;
            case "tenacity":
                params.category_tenacity="1";
                break;
        }
    }
    /*此处判断字典是否包含某个属性，没有包含则属性设置为0*/
    if (!params.hasOwnProperty("category_upper_limb")){params.category_upper_limb="0";}
    if (!params.hasOwnProperty("category_body_limb")){params.category_body_limb="0";}
    if (!params.hasOwnProperty("category_lower_limb")){params.category_lower_limb="0";}
    if (!params.hasOwnProperty("category_endurance")){params.category_endurance="0";}
    if (!params.hasOwnProperty("category_sensitive")){params.category_sensitive="0";}
    if (!params.hasOwnProperty("category_power")){params.category_power="0";}
    if (!params.hasOwnProperty("category_speed")){params.category_speed="0";}
    if (!params.hasOwnProperty("category_tenacity")){params.category_tenacity="0";}

    params.user_account=$.cookie("ACCOUNT");

    return params;
}

function uploadUserInfo(pre_name,pre_time,strength01,pre_min_heart,pre_max_heart,pre_weight,pre_number,
                                pre_group_num,pre_frequency,sport_type,fatigue,effect,equipment,disease,pre_ways,pre_cycle,pre_address,pre_notice){
        /*var like;
        for(var i=0;i<strSel.length;i++)
        {
            like += strSel[i]+",";
        }
        var equip;
         for(var i=0;i<strSel.length;i++)
         {
              equip += strSel[i]+",";
          }*/
    var formData = new FormData();
     formData.append("name", pre_name);
     formData.append("sportsTime", pre_time);
     formData.append("intensityType", strength01);

     formData.append("heartRateMin", pre_min_heart);
     formData.append("heartRateMax", pre_max_heart);

     formData.append("fuzhongRange", pre_weight);
     formData.append("number", pre_number);
     formData.append("groupNum", pre_group_num);
     formData.append("sportsFrequency", pre_frequency);
     formData.append("sportsTypeId", sport_type);
     formData.append("sportsFatigue", fatigue);
     formData.append("sportsEffectId", effect.toString());
     formData.append("sportsMustEquipmentId", equipment.toString());
     formData.append("tabooDiseaseId", disease.toString());
    formData.append("methodsIntroduce", pre_ways);
     formData.append("reviewCycle", pre_cycle);
     formData.append("videoUrl", pre_address);
     formData.append("notice",  pre_notice);


    $.ajax({
                    type:"POST",
                    url:"standardRecipe/addSR.do",
                    data:formData,
                    processData: false,   // jQuery不要去处理发送的数据
                    contentType: false,   // jQuery不要去设置Content-Type请求头
                    datatype:"json",
                    //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                    success:function (data) {

                            alert("处方录入成功！");
                            window.location.href="http://localhost:8088/mana_show_pre.html";
                    }
                });



    /*window.location.href="http://localhost:8081/upgrade/html/index.html";*/

}






$(document).ready(function(){
  document.getElementById("weight").style.display="none";
  document.getElementById("ex01").style.display="none";
  document.getElementById("ex02").style.display="none";
  document.getElementById("ex03").style.display="none";

});

function  strength(){
     var show = "";
     show = $("input[name='radioInline']:checked").val();
     switch (show){
        case '1':
             document.getElementById("weight").style.display="none";
             document.getElementById("heart_rate").style.display="block";
             break;
        case '2':
             document.getElementById("weight").style.display="block";
             document.getElementById("heart_rate").style.display="none";
             break;
        default:
             document.getElementById("heart_rate").style.display="none";
             document.getElementById("weight").style.display="none";
             break;
     }
};

