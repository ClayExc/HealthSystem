/*修改运动器材*/

function update_fatigue(id){
    var fatigue_name=$("#fatigue_name_update").val();
    var fatigue_face=$("#fatigue_face_update").val();
    var fatigue_sweat=$("#fatigue_sweat_update").val();
    var fatigue_action=$("#fatigue_action_update").val();
    var fatigue_attention=$("#fatigue_attention_update").val();
    var fatigue_feel=$("#fatigue_feel_update").val();
    var fatigue_breathe=$("#fatigue_breathe_update").val();
    var formData = new FormData();
    formData.append("id", id);
    formData.append("face", fatigue_face);
    formData.append("name", fatigue_name);
    formData.append("sweat", fatigue_sweat);
    formData.append("action", fatigue_action);
    formData.append("attention", fatigue_attention);
    formData.append("feel", fatigue_feel);
    formData.append("breathe", fatigue_breathe);


    $.ajax({
                        type:"POST",
                        url:"fatigueDegree/updateFatigueDegree.do",
                        async:true,
                        xhrFields:{
                            withCredentials:true
                        },
                        processData: false,   // jQuery不要去处理发送的数据
                        contentType: false,   // jQuery不要去设置Content-Type请求头
                        mimeType:"multipart/form-data",
                        datatype:"json",
                        data:formData,
                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                        success:function (data) {

                                alert("疲劳度修改成功！");
                                $('#modal-update').modal('hide');
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
                    });

}