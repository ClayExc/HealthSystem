/*器材修改图片回显*/
function setImagePreview_update() {
        var docObj=document.getElementById("doc_update");
         var img_file= docObj.files[0];
        var imgObjPreview=document.getElementById("equip_img_update");
                if(docObj.files &&    docObj.files[0]){
                        //火狐下，直接设img属性
                        imgObjPreview.style.display = 'block';
                        imgObjPreview.style.width = '300px';
                        imgObjPreview.style.height = '200px';
                        //imgObjPreview.src = docObj.files[0].getAsDataURL();

      //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
      imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

                }else{
                        //IE下，使用滤镜
                        docObj.select();
                        var imgSrc = document.selection.createRange().text;
                        var localImagId = document.getElementById("img_update");
                        //必须设置初始大小
                        localImagId.style.width = "300px";
                        localImagId.style.height = "200px";
                        //图片异常的捕捉，防止用户修改后缀来伪造图片
try{
                                localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                        }catch(e){
                                alert("您上传的图片格式不正确，请重新选择!");
                                return false;
                        }
                        imgObjPreview.style.display = 'none';
                        document.selection.empty();
                }
                return true;
        }
/*修改运动器材*/

function update_equip(id){
    var equip_name=$("#equip_name_update").val();
    var equip_des=$("#equip_des_update").val();
    var formData = new FormData();
    formData.append("id", id);
    formData.append("equipmentName", equip_name);
    formData.append("equipmentDescription", equip_des);
    var docObj=document.getElementById("doc_update");
            var img_file= docObj.files[0];
            console.log( $("#doc_update")[0].files[0]);
    formData.append("file", $("#doc_update")[0].files[0]);
    $.ajax({
                        type:"POST",
                        url:"equipment/updateSE.do",
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
                                           url:"equipment/selectSE.do",
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