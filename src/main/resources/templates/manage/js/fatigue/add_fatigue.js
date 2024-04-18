 function add(){
        var fatigue_name=$("#fatigue_name").val();
         var fatigue_face=$("#fatigue_face").val();
         var fatigue_sweat=$("#fatigue_sweat").val();
         var fatigue_action=$("#fatigue_action").val();
         var fatigue_attention=$("#fatigue_attention").val();
         var fatigue_feel=$("#fatigue_feel").val();
         var fatigue_breathe=$("#fatigue_breathe").val();
         var formData = new FormData();

         formData.append("face", fatigue_face);
         formData.append("name", fatigue_name);
         formData.append("sweat", fatigue_sweat);
         formData.append("action", fatigue_action);
         formData.append("attention", fatigue_attention);
         formData.append("feel", fatigue_feel);
         formData.append("breathe", fatigue_breathe);


        $.ajax({
                            type:"POST",
                            url:"fatigueDegree/addFatigueDegree.do",
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
                                        url:"fatigueDegree/selectFatigueDegree.do",
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