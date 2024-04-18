

/*获取register.html中url传值*/
/*
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");
    for(var i = 0; i < strs.length; i ++) {
    theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
    }
    }
    return theRequest;
}
var Request = new Object();
             Request = GetRequest();
             var userName,userPassword,phone;
             userName = Request['user_name'];
             userPassword = Request['user_password'];
             phone = Request['phone'];
             alert(userName);
*/

$(function () {

    $("#submit").click(function () {
        var Request = new Object();
         Request = GetRequest();
         var userName,userPassword,phone;
         userName = Request['user_name'];
         userPassword = Request['user_password'];
         phone = Request['phone'];

        /*昵称*/
        var name=$("#name").val();
        var nameF=checkName(name);
        if (!nameF){return;}

        /*性别*/
        var sex=$("#sex").val();

        /*年龄*/
        var age=$("#age").val();
        var ageF=checnAge(age);
        if (!ageF){return;}

        /*身高*/
        var height=$("#height").val();
        var heightF=checkHeight(height);
        if (!heightF){return;}

        /*体重*/
        var weight=$("#weight").val();
        var weightF=checkHeight(weight);
        if (!weightF){return;}

        /*健康*/
        var disease=$("#disease").val();

        /*静息心率*/
        var rate=$("#heartRate").val();
        var rateF=checkRate(rate);
        if (!rateF){return;}

        /*运动目的*/

         var select = document.getElementById("sportsEffect");
         var opt_load = select.value;


        var strSel=[];
        var strSel1=[];
        //校验运动兴趣是否为空
        $("[name='dropdown-group01']:checked").each(function (index,element) {
            strSel.push($(this).val());
        });

        if (strSel.length==0){
            alert("请至少选择一个运动兴趣");
            return;
        }
         //校验运动器材是否为空
         $("[name='dropdown-group02']:checked").each(function (index,element) {
           strSel1.push($(this).val());
          });

         if (strSel1.length==0){
            alert("请至少选择一个运动器材");
             return;
         }


        uploadUserInfo(userName,userPassword,phone,name,age,sex,opt_load,strSel,strSel1);
    });
});
/*获取register.html中url传值*/

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");
    for(var i = 0; i < strs.length; i ++) {
    theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
    }
    }
    return theRequest;
}
function checkName(name){
    if (name.length<=0 || name.length>=20){
        alert("昵称不正确");
        return false;
    }
    return true;
}

function checnAge(age) {
    if (age.length<=0){
        alert("年龄不正确");
        return false;
    }
    /*判断选择的日期是否大于当前日期*/
    //获取现在实际日期
    var d=new Date();
    var str=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
    if (Date.parse(age)>Date.parse(str)){
        alert("选择的日期不能大于当前日期");
        return false;
    }
    return true;
}

function checkHeight(height) {
    if (height<=0 || height>200){
        alert("身高不正确");
        return false;
    }
    return true;
}

function checkHeight(weight) {
    if (weight<=0 || weight>200){
        alert("体重不正确");
        return false;
    }
    return true;
}

function checkRate(rate) {
    if (rate<=50 || rate>200){
        alert("心率不正确");
        return false;
    }
    return true;
}

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

function uploadUserInfo(userName,userPassword,phone,name,age,sex,opt_load,strSel,strSel1){
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
     formData.append("userName", userName);
     formData.append("userPassword", userPassword);
     formData.append("userAccount", phone);

     formData.append("userBirthday", age);
     formData.append("userSex", sex);

     formData.append("userSportObjective1", opt_load);
     formData.append("userSportLike", strSel.toString());
     formData.append("userSportEquipmentId",  strSel1.toString());



    var account=$.cookie("ACCOUNT");

    var jsonResult={};
    jsonResult.user_name=name;
    jsonResult.user_birthday=age;
    jsonResult.user_sex=parseInt(sex);
    jsonResult.user_account=account;



    $.ajax({
                    type:"POST",
                    url:"user/register.do",
                    data:formData,
                    processData: false,   // jQuery不要去处理发送的数据
                    contentType: false,   // jQuery不要去设置Content-Type请求头
                    datatype:"json",
                    //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                    success:function (data) {
                        if (data.msg=="注册成功") {
                            alert("注册成功了！");
                            $.cookie("user_account",account,{expires:7,path:"/"});
                            window.location.href="http://localhost:8088/login.html";
                        }else{
                            alert("用户存在，不能进行注册！");
                        }
                    }
                });



    /*window.location.href="http://localhost:8081/upgrade/html/index.html";*/

}