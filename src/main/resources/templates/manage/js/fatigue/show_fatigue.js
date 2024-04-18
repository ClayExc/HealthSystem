function show_fatigue() {

            $.jgrid.defaults.styleUI = 'Bootstrap';
             var _height = $(window).height()*2/3;
             $.ajax({
                                type:"POST",
                                url:"fatigueDegree/selectFatigueDegree.do",
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
                                                       colNames: ['编号','疲劳度','面色','汗量','动作','注意力','感觉','心跳'],
                                                       colModel: [
                                                       {
                                                          name: 'id',
                                                          index: 'id',
                                                          editable: true,
                                                          width: 50,
                                                          sortable: false
                                                        },
                                                        {
                                                           name: 'name',
                                                           index: 'name',
                                                           editable: true,
                                                           width: 70,
                                                           sorttype: false,
                                                        },
                                                        {
                                                            name: 'face',
                                                            index: 'face',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                             name: 'sweat',
                                                             index: 'sweat',
                                                             editable: true,
                                                             width: 70,
                                                             sorttype: false,
                                                        },
                                                        {
                                                            name: 'action',
                                                            index: 'action',
                                                            width: 70,
                                                            sorttype: false,

                                                        },
                                                        {
                                                            name: 'attention',
                                                           index: 'attention',
                                                           width: 70,
                                                           sorttype: false,
                                                        },
                                                        {
                                                            name: 'feel',
                                                            index: 'feel',
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'breathe',
                                                           index: 'breathe',
                                                             width: 70,
                                                             sorttype: false,
                                                        }

                                                       ],
                                                       pager: "#pager_list_2",
                                                       viewrecords: true,
                                                       caption: "疲劳度展示",
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
                                                       addfunc : add_fatigue,
                                                       editfunc: edit_fatigue,

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
        function edit_fatigue(id){

             $("#modal-update").modal({
                        keyboard:false
                    });
              var str = '';
                str += "<button type='button' onclick='clean_check()' class='btn btn-default' data-dismiss='modal'>关闭</button>"
                str += "<button type='button' onclick='update_fatigue("+id+")' class='btn btn-primary'>保存</button>";
                $('#show_btn_update').empty();
                $('#show_btn_update').append(str);
              $.ajax({
                         type:"POST",
                         url:"fatigueDegree/selectById.do?id="+id,
                         data:"",
                         processData: false,   // jQuery不要去处理发送的数据
                         contentType: false,   // jQuery不要去设置Content-Type请求头
                         datatype:"json",
                          //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                          success:function (data) {
                                 $("#fatigue_name_update").val(data.name);
                                 $("#fatigue_face_update").val(data.face);
                                 $("#fatigue_sweat_update").val(data.sweat);
                                 $("#fatigue_action_update").val(data.action);
                                 $("#fatigue_attention_update").val(data.attention);
                                 $("#fatigue_feel_update").val(data.feel);
                                 $("#fatigue_breathe_update").val(data.breathe);

                              }
                          });

        }

        /*删除处方*/
        function del_open(id){

        if(window.confirm('确认删除器材吗？')){
                         $.ajax({
                                        type:"post",
                                        url:"fatigueDegree/deleteFatigueDegree.do?id="+id,
                                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                        success:function (data) {

                                                alert(data);
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
                //alert("确定");

                return true;
                }else{
                //alert("取消");
                 return false;
                  }




        }

   function add_fatigue(){
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

