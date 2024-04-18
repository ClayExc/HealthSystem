function search_pre(){
   /*查询方式*/
    var select = document.getElementById("search");
    var search_type = select.value;

    /*获取处方名称*/
    var name = document.getElementById("name_val").value;
     // 用表单来初始化
     var formData = new FormData();
     formData.append("name", name);
    /*获取处方效果*/
    var effect = document.getElementById("search_effect");
    var effect_val = effect.value;

    /*获取处方类型*/
    var type = document.getElementById("search_type");
    var type_val = type.value;

       /*根据用户名查询*/
    if(search_type=="name")
    {
        //进行后台传值
                    $.ajax({
                        type:"POST",
                        url:"standardRecipe/selectSR.do",
                        data:formData,
                        processData: false,   // jQuery不要去处理发送的数据
                        contentType: false,   // jQuery不要去设置Content-Type请求头
                        datatype:"json",
                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                        success:function (data) {
                         $("#table_list_2").jqGrid('clearGridData');  //清空表格
                              $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                               datatype:'local',
                               data : data,   //  newdata 是符合格式要求的需要重新加载的数据
                               page:1
                               }).trigger("reloadGrid");
                        }
                    });
    }
    /*根据运动类型查询*/
    if(search_type=="type")
        {
            //进行后台传值
                        $.ajax({
                            type:"POST",
                            url:"standardRecipe/selectSRByType.do?sportsTypeId="+type_val,
                            data:"",
                            processData: false,   // jQuery不要去处理发送的数据
                            contentType: false,   // jQuery不要去设置Content-Type请求头
                            datatype:"json",
                            //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                            success:function (data) {
                             $("#table_list_2").jqGrid('clearGridData');  //清空表格
                                  $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                                   datatype:'local',
                                   data : data,   //  newdata 是符合格式要求的需要重新加载的数据
                                   page:1
                                   }).trigger("reloadGrid");
                            }
                        });
        }
        /*根据运动效果查询*/
            if(search_type=="effect")
                {
                    //进行后台传值
                                $.ajax({
                                    type:"POST",
                                    url:"standardRecipe/selectSRByEffect.do?sportsEffectId="+effect_val,
                                    data:"",
                                    processData: false,   // jQuery不要去处理发送的数据
                                    contentType: false,   // jQuery不要去设置Content-Type请求头
                                    datatype:"json",
                                    //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                    success:function (data) {
                                     $("#table_list_2").jqGrid('clearGridData');  //清空表格
                                          $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                                           datatype:'local',
                                           data : data,   //  newdata 是符合格式要求的需要重新加载的数据
                                           page:1
                                           }).trigger("reloadGrid");
                                    }
                                });
                }
}

function clean(){
     /*重置处方名称*/
      document.getElementById("name_val").value="";
      $.ajax({
            type:"POST",
           url:"standardRecipe/selectSR.do",
            data:"",
            processData: false,   // jQuery不要去处理发送的数据
            contentType: false,   // jQuery不要去设置Content-Type请求头
            datatype:"json",
             //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
             success:function (data) {
             $("#table_list_2").jqGrid('clearGridData');  //清空表格
              $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
             datatype:'local',
             data : data,   //  newdata 是符合格式要求的需要重新加载的数据
             page:1
              }).trigger("reloadGrid");
                 }
             });

}