/*修改运动器材*/

function update_disease(id){
    var disease_name=$("#disease_name_update").val();
    var disease_des=$("#disease_des_update").val();
    var formData = new FormData();
    formData.append("id", id);
    formData.append("diseaseName", disease_name);
    formData.append("description", disease_des);

    $.ajax({
                        type:"POST",
                        url:"disease/updateDisease.do",
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

                                alert("运动器材修改成功！");
                                $('#modal-update').modal('hide');
                               $.ajax({
                                            type:"POST",
                                           url:"disease/selectDisease.do",
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