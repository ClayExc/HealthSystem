$(document).on('click', '.waves-effect', function (e) {
    var text = $(this).text();
    var bodydiv=$(e.currentTarget).parent().parent().parent().parent().parent();
    if(text=="完成"&&bodydiv.attr('id')=="tizhi"){
        var user_height=$('input[name="user_height"]').val();
        var user_weight=$('input[name="user_weight"]').val();
        var user_optimal_rate1=$('input[name="user_optimal_rate1"]').val();
        var user_optimal_rate2=$('input[name="user_optimal_rate2"]').val();
        var user_chest=$('input[name="user_chest"]').val();
        var user_waist=$('input[name="user_waist"]').val();
        var user_hipline=$('input[name="user_hipline"]').val();
        var disease_str="";
        var sumdisease =[];
        var subhealthy={};
        var uid=10001;
        $('input[name="disease"]:checked').each(function(){
            sumdisease.push($(this).val());
        });
        var sub_name;
        var redio_name;
        for(var i=1;i<27;i++){
            sub_name="q"+i;
            redio_name="gender"+i;
            subhealthy[sub_name]=$('input[name='+redio_name+']:checked').val();
        }
        for (var i=0;i<sumdisease.length;i++) {
            if (i!=sumdisease.length-1){
                disease_str+=sumdisease[i]+",";
            }else {
                disease_str+=sumdisease[i];
            }

        }

        if(user_height!=""&&user_weight!=""&&user_optimal_rate1!=""&&user_optimal_rate2!=""&&user_chest!=""&&user_waist!=""&&user_hipline!=""){
            var data=JSON.stringify(subhealthy);
            $.ajax({
                url:"/healthy/tizhi_evaluting.do?disease_str="+disease_str+"&&user_height="+user_height+"&&user_weight="+user_weight+"&&user_optimal_rate1="+user_optimal_rate1+"&&user_optimal_rate2="+user_optimal_rate2+"&&user_chest="+user_chest+"&&user_waist="+user_waist+"&&user_hipline="+user_hipline+"&&uid="+uid,
                type: "post",
                async: false,
                data: data,
                headers : {"Content-Type" : "application/json;charset=utf-8"},
                success:function (data) {
                    $("#tizhibody").attr("style","display:none;");
                    var bodycss=$("#ydnlbody").css("display");
                    if (bodycss=="none") {
                        window.location.href="http://localhost:8088/body_status.html";
                    }
                },
                error:function () {
                    alert("网络异常");

                }
            });

        }else{
            alert("请填写完整信息");
        }
    }

    if(text=="完成"&&bodydiv.attr('id')=="ydnl"){
        var fuwocheng=$('input[name="fuwocheng_number"]').val();
        var yangwoqizuo=$('input[name="yangwoqizuo_number"]').val();
        var taijie1=$('input[name="taijie1_number"]').val();
        var taijie2=$('input[name="taijie2_number"]').val();
        var taijie3=$('input[name="taijie3_number"]').val();
        var uid=10001;

        if(fuwocheng!=""&&yangwoqizuo!=""&&taijie1!=""&&taijie2!=""&&taijie3!=""){
            var taijieceshi=Number(taijie1)+Number(taijie2)+Number(taijie3);
            var sport_ability={};
            sport_ability.userId=uid;
            sport_ability.yangwoqizuo=yangwoqizuo;
            sport_ability.fuwocheng=fuwocheng;
            sport_ability.taijieceshi=taijieceshi;
            var data=JSON.stringify(sport_ability);
            $.ajax({
                url:"/healthy/sportability_evaluting.do",
                type: "post",
                async: false,
                data: data,
                headers : {"Content-Type" : "application/json;charset=utf-8"},
                success:function (data) {
                    $("#ydnlbody").attr("style","display:none;");
                    var bodycss=$("#tizhibody").css("display");
                    if (bodycss=="none") {
                        window.location.href="http://localhost:8088/body_status.html";
                    }
                },
                error:function () {
                    alert("网络异常");

                }
            });

        }else{
            alert("请完成所有测试信息的填写");
        }
    }
});