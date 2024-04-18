function search_pre(){
    var name = $("#name_val").val();
    var ageGroup=$("#search_age").val();
    var effect=$("#search_effect").val();
    console.log(name,ageGroup,effect);
    var  formData= new FormData();
    if(name!="")
        {
           formData.append("recipeName",name);

        }
    if(ageGroup!="")
    {
        formData.append("ageGroup",ageGroup);
    }


    var formData02= new FormData();
    formData02.append("recipeName",name);
    formData02.append("ageGroup",ageGroup);
    formData02.append("effect",effect);

//根据处方名称以及年龄查询
    if(effect=="")
    {
            $.ajax({
                type:"post",
                url:"recipegroup/selectRecipeByName.do",
                data:formData,
                processData: false,   // jQuery不要去处理发送的数据
                contentType: false,   // jQuery不要去设置Content-Type请求头
                datatype:"json",
                success: function(data){
                var myData=data.data;
                                 $("#table_list_2").jqGrid('clearGridData');  //清空表格
                                      $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                                       datatype:'local',
                                       data : myData,   //  newdata 是符合格式要求的需要重新加载的数据
                                       page:1
                                       }).trigger("reloadGrid");
                }
            })
    }
//根据功能查询
    else{
         $.ajax({
        type:"post",
        url:"recipegroup/selectRecipe.do",
        data:formData02,
        processData: false,   // jQuery不要去处理发送的数据
        contentType: false,   // jQuery不要去设置Content-Type请求头
        datatype:"json",
        success: function(data){
        var myData=data.data;
                         $("#table_list_2").jqGrid('clearGridData');  //清空表格
                              $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                               datatype:'local',
                               data : myData,   //  newdata 是符合格式要求的需要重新加载的数据
                               page:1
                               }).trigger("reloadGrid");
        }
    })
    }

}


//查询重置处方表
function clean(){
 document.getElementById("name_val").value="";
 document.getElementById("search_age").value="";
 document.getElementById("search_effect").value="";

    $.ajax({
        type:"post",
        url:"recipegroup/selectRecipeByName.do",
        data: "",
        success:function(data){
            var myData=data.data;
                         $("#table_list_2").jqGrid('clearGridData');  //清空表格
                              $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                               datatype:'loca
                               l',
                               data : myData,   //  newdata 是符合格式要求的需要重新加载的数据
                               page:1
                               }).trigger("reloadGrid");
        }
    })
}

//根据处方id删除