function search_fatigue(){
    /*获取疲劳度名称*/
    var name = document.getElementById("name_val").value;
     // 用表单来初始化
     var formData = new FormData();
     formData.append("diseaseName", name);
       /*根据疲劳度名称查询*/
        //进行后台传值
                    $.ajax({
                        type:"POST",
                        url:"fatigueDegree/selectFatigueDegree.do",
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

function clean(){
     /*重置处方名称*/
      document.getElementById("name_val").value="";
      $.ajax({
            type:"POST",
           url:"fatigueDegree/selectFatigueDegree.do",
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