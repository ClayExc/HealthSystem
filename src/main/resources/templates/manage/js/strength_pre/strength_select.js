/*多条件查询*/
function strength_select(){
    var name=$("#name_val").val();
    var equip=$("#search_equip").val();
    var muscles=$("#search_muscles").val();
    var formData= new FormData();
    if(name!="")
    {
        formData.append("recipeName",name);
    }
    if(equip!="")
        {
            formData.append("equipment",equip);
        }
    if(muscles!="")
        {
            formData.append("musclesTrained",muscles);
        }
     $.ajax({
        type:"post",
        url:"recipeStrength/selectByConditions",
        data:formData,
        processData: false,   // jQuery不要去处理发送的数据
        contentType: false,   // jQuery不要去设置Content-Type请求头
        datatype:"json",
        success:function(data){
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


/*重置功能*/
function clean(){
    $("#name_val").val("");
    $("#search_equip").val("");
    $("#search_muscles").val("");
    $.ajax({
        type:"post",
        url:"recipeStrength/selectByConditions",
        data: "",
        success:function(data){
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