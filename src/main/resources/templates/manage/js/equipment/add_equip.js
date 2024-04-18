 function add(){
        var equip_name=$("#equip_name").val();
        var docObj=document.getElementById("doc");
        var equip_des=$("#equip_des").val();

        var img_file= docObj.files[0];
        console.log( $("#doc")[0].files[0]);
        var formData = new FormData();
        formData.append("equipmentName", equip_name);
        formData.append("equipmentDescription", equip_des);
        formData.append("file", $("#doc")[0].files[0]);
        $.ajax({
                            type:"POST",
                            url:"equipment/addSE.do",
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

                                     data01=JSON.parse(data);
                                     alert(data01.msg);
                                    $('#modal-form').modal('hide');
                                    $.ajax({
                                        type:"POST",
                                        url:"equipment/selectSE.do",
                                        data:"",
                                        processData: false,   // jQuery不要去处理发送的数据
                                        contentType: false,   // jQuery不要去设置Content-Type请求头
                                        datatype:"json",
                                        success:function(data){
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