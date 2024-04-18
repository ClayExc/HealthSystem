function show_disease() {

            $.jgrid.defaults.styleUI = 'Bootstrap';
            var _height = $(window).height()*2/3;
             $.ajax({
                                type:"POST",
                                url:"disease/selectDisease.do",
                                data:"",
                                processData: false,   // jQuery不要去处理发送的数据
                                contentType: false,   // jQuery不要去设置Content-Type请求头
                                datatype:"json",
                                //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                success:function (data) {

                                       console.log(data);
                                       var mydata = data;
                                                   // Configuration for jqGrid Example 2
                                                   $("#table_list_2").jqGrid({
                                                       data: mydata,
                                                       datatype: "local",
                                                       height: _height,
                                                       autowidth: true,
                                                       shrinkToFit: true,
                                                       rowNum: 20,
                                                       rowList: [10, 20, 30],
                                                       colNames: ['编号','疾病名称','疾病描述'],
                                                       colModel: [
                                                       {
                                                          name: 'id',
                                                          index: 'id',
                                                          editable: true,
                                                          width: 50,
                                                          sortable: false
                                                        },
                                                        {
                                                           name: 'diseaseName',
                                                           index: 'diseaseName',
                                                           editable: true,
                                                           width: 70,
                                                           sorttype: false,
                                                        },
                                                        {
                                                            name: 'description',
                                                            index: 'description',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        }

                                                       ],
                                                       pager: "#pager_list_2",
                                                       viewrecords: true,
                                                       caption: "禁忌疾病展示",
                                                       add: true,
                                                       edit: true,
                                                       del:true,
                                                       addtext: 'Add',
                                                       edittext: 'Edit',
                                                       hidegrid: false
                                                   });

                                                   // Add selection
                                                   $("#table_list_2").setSelection(4, true);


                                                   // Setup buttons
                                                   $("#table_list_2").jqGrid('navGrid', '#pager_list_2', {
                                                       edit: true,
                                                       add: true,
                                                       del: true,
                                                       search: true,
                                                       delfunc : del_open,
                                                       addfunc : add_disease,
                                                       editfunc: edit_disease,

                                                   }, {
                                                       height: 200,
                                                       reloadAfterSubmit: true
                                                   });

                                                   // Add responsive to jqGrid
                                                   $(window).bind('resize', function () {
                                                       var width = $('.jqGrid_wrapper').width();

                                                       $('#table_list_2').setGridWidth(width);
                                                   });


                                }
                            });

            // Examle data for jqGrid

 };


/*弹出修改器材框*/
        function edit_disease(id){

             $("#modal-update").modal({
                        keyboard:false
                    });
              var str = '';
                str += "<button type='button' onclick='clean_check()' class='btn btn-default' data-dismiss='modal'>关闭</button>"
                str += "<button type='button' onclick='update_disease("+id+")' class='btn btn-primary'>保存</button>";
                $('#show_btn_update').empty();
                $('#show_btn_update').append(str);
              $.ajax({
                         type:"POST",
                         url:"disease/selectById.do?id="+id,
                         data:"",
                         processData: false,   // jQuery不要去处理发送的数据
                         contentType: false,   // jQuery不要去设置Content-Type请求头
                         datatype:"json",
                          //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                          success:function (data) {
                                 $("#disease_name_update").val(data.diseaseName);
                                 $("#disease_des_update").val(data.description);

                              }
                          });

        }

        /*删除处方*/
        function del_open(id){

        if(window.confirm('确认删除器材吗？')){
                         $.ajax({
                                        type:"post",
                                        url:"disease/deleteDisease.do?id="+id,
                                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                        success:function (data) {

                                                alert(data);
                                                $.ajax({
                                                                                     type:"POST",
                                                                                     url:"disease/selectDisease.do",
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
                //alert("确定");

                return true;
                }else{
                //alert("取消");
                 return false;
                  }




        }

   function add_disease(){
       $("#modal-form").modal({
                 keyboard:false
                });
                     var str = '';
                       str += "<button type='button' onclick='clean_check()' class='btn btn-default' data-dismiss='modal'>关闭</button>"
                       str += "<button type='button' onclick='add()' class='btn btn-primary'>保存</button>";
                       $('#show_btn').empty();
                       $('#show_btn').append(str);

   }




/*设置模态框所有单选框不选中*/
function clean_check(){


}

/*查看图片按钮*/
function img_show(id){

                  var xmlhttp;
                  xmlhttp=new XMLHttpRequest();
                  xmlhttp.open("GET","equipment/getImage.do?id="+id,true);
                  xmlhttp.responseType = "blob";
                  xmlhttp.onload = function(){
                      console.log(this);
                      if (this.status == 200) {
                          var blob = this.response;
                          var img = document.getElementById("show_img");
                          img.onload = function(e) {
                              window.URL.revokeObjectURL(img.src);
                          };
                          img.src = window.URL.createObjectURL(blob);
                          document.getElementById("img_show").appendChild(img);
                      }
                  }
                  xmlhttp.send();
                  $("#modal-img").modal({
                                   keyboard:false
                                  });
                  var str = '';
                  str += "<button type='button' onclick='clean_check()' class='btn btn-default' data-dismiss='modal'>关闭</button>"

                  $('#show_btn_img').empty();
                  $('#show_btn_img').append(str);
}