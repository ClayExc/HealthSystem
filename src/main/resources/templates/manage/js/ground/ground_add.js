
$(function () {
    $("#submit").click(function () {

        /*场地名称*/
        var ground_name=$("#ground_name").val();
        /*场地类型*/
         var ground_type= $("input[name='radioInline']:checked").val();
               var type_value
               if(ground_type==1)
               {
                      var ground="健身场馆";
                      var type_place=$("input[name='radio_indoor']:checked").val();
                      if(type_place==1)
                      {
                        type_value="室内健身房";
                      }
                      if(type_place==2)
                      {
                        type_value="篮球场";
                      }
                      if(type_place==3)
                      {
                        type_value="网球场";
                      }
               }
               if(ground_type==2)
               {
                        var ground="公共场地";
                       var type_place=$("input[name='radio_public']:checked").val();
                      if(type_place==1)
                      {
                        type_value="开放公园";
                      }
                      if(type_place==2)
                      {
                        type_value="学校";
                      }
                      if(type_place==3)
                      {
                        type_value="体育馆";
                      }
               }
        /*运动项目*/
         var sport_event=[];
         $("[name='dropdown-group03']:checked").each(function (index,element) {
                    sport_event.push($(this).val());
                });

                if (sport_event.length==0){
                    alert("请至少选择一个运动项目");
                    return;
                }
        /*出入凭证*/
        var access = document.getElementById("access");
        var access_type = access.value;
        /*地址*/
        var address=$("#address").val();
        /*面积*/
        var area=$("#area").val();
        /*价格*/
        var price=$("#price").val();
        /*风格*/
        var style=$("style").val();
        /*场馆简介*/
        var ground_introduction=$("#ground_introduction").val();
         /*场馆优势*/
         var ground_advantage=$("#ground_advantage").val();

        /*器材介绍*/
        var equip_introduction=$("#equip_introduction").val();


        uploadUserInfo(ground_name,ground,type_value,sport_event,access_type,address,area,
        price,style,ground_introduction,ground_advantage,equip_introduction);
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

function uploadUserInfo(ground_name,ground,type_value,sport_event,access_type,address,area,
                                price,style,ground_introduction,ground_advantage,equip_introduction){

     formData2.append("name", ground_name);

     formData2.append("type", ground);
     formData2.append("typeValue", type_value);
     formData2.append("sportEvent", sport_event);

     formData2.append("voucher", access_type);
     formData2.append("address", address);

     formData2.append("area", area);
     formData2.append("style", style);
     formData2.append("price", price);
     formData2.append("briefIntro", ground_introduction);
     formData2.append("advantage", ground_advantage);
     formData2.append("equipmentIntro", equip_introduction);



    $.ajax({
                    type:"POST",
                    url:"fitnessvenues/addFitnessVenues.do",
                    data:formData2,
                    processData: false,   // jQuery不要去处理发送的数据
                    contentType: false,   // jQuery不要去设置Content-Type请求头
                    datatype:"json",
                    //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                    success:function (data) {
                            if(data.msg=="该场馆已存在")
                            {
                                alert("该场馆已存在");
                            }
                            else
                            {
                                alert("场地录入成功！");
                            }
                            formData2.delete("name");
                            formData2.delete("type");
                            formData2.delete("typeValue");
                            formData2.delete("sportEvent");
                            formData2.delete("voucher");
                            formData2.delete("address");
                            formData2.delete("area");
                            formData2.delete("style");
                            formData2.delete("price");
                            formData2.delete("briefIntro");
                            formData2.delete("advantage");
                            formData2.delete("equipmentIntro");

                    }
                });



    /*window.location.href="http://localhost:8081/upgrade/html/index.html";*/

}






$(document).ready(function(){
  document.getElementById("public_space").style.display="none";
});

function  strength(){
     var show = "";
     show = $("input[name='radioInline']:checked").val();
     switch (show){
        case '1':
             document.getElementById("public_space").style.display="none";
             document.getElementById("gymnasium").style.display="block";
             break;
        case '2':
             document.getElementById("public_space").style.display="block";
             document.getElementById("gymnasium").style.display="none";
             break;
        default:
             document.getElementById("gymnasium").style.display="none";
             document.getElementById("public_space").style.display="none";
             break;
     }
};

