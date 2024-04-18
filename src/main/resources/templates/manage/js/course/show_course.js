function show_course() {

            $.jgrid.defaults.styleUI = 'Bootstrap';
            var _height = $(window).height()*2/3;
             $.ajax({
                                type:"POST",
                                url:"course/selectCourseByName.do",
                                data:"",
                                processData: false,   // jQuery不要去处理发送的数据
                                contentType: false,   // jQuery不要去设置Content-Type请求头
                                datatype:"json",
                                //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                success:function (data) {
                                       console.log(data);
                                       var mydata = data.data;
                                                   // Configuration for jqGrid Example 2
                                                   $("#table_list_2").jqGrid({
                                                       data: mydata,
                                                       datatype: "local",
                                                       height: _height,
                                                       autowidth: true,
                                                       shrinkToFit: true,
                                                       rowNum: 20,
                                                       rowList: [10, 20, 30],
                                                       colNames: ['课程名称','课程类型','运动时间','禁忌疾病','器材','适宜人群','锻炼人数','观看视频'],
                                                       colModel: [
                                                       {
                                                          name: 'name',
                                                          index: 'name',
                                                          editable: true,
                                                          width: 50,
                                                          sortable: false
                                                        },
                                                        {
                                                           name: 'type',
                                                           index: 'type',
                                                           editable: true,
                                                           width: 70,
                                                           sorttype: false,
                                                        },
                                                        {
                                                            name: 'time',
                                                            index: 'time',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'diseaseList',
                                                            index: 'diseaseList',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'equipmentList',
                                                            index: 'equipmentList',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'genderPreference',
                                                            index: 'genderPreference',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'practiceNumber',
                                                            index: 'practiceNumber',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: '',
                                                            index: 'operate',
                                                            align: 'center',
                                                            width: 70,
                                                            formatter: function (cellvalue, options, rowObject) {
                                                                                    var detail="<button onclick='video_show(\""+ rowObject.id + "\")' style='margin-left:20px' class='btn btn-primary' type='button'><i class='fa fa-check'></i>&nbsp;观看视频</button>";
                                                                                    return detail;
                                                                                },
                                                        }
                                                       ],
                                                       pager: "#pager_list_2",
                                                       viewrecords: true,
                                                       caption: "运动课程展示",
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

                                                       add: true,
                                                       del: true,
                                                       search: true,
                                                       delfunc : del_open,
                                                       addfunc : add_equip,


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




        /*删除课程*/
        function del_open(id){

        if(window.confirm('确认删除器材吗？')){
                         $.ajax({
                                        type:"post",
                                        url:"course/deleteCourseById.do?id="+id,
                                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                        success:function (data) {

                                                $.ajax({
                                                                                     type:"POST",
                                                                                     url:"course/selectCourseByName.do",
                                                                                     data:"",
                                                                                     processData: false,   // jQuery不要去处理发送的数据
                                                                                     contentType: false,   // jQuery不要去设置Content-Type请求头
                                                                                     datatype:"json",
                                                                                     success:function(data){
                                                                                     var mydata = data.data;
                                                                                         $("#table_list_2").jqGrid('clearGridData');  //清空表格
                                                                                          $("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
                                                                                             datatype:'local',
                                                                                             data : mydata,   //  newdata 是符合格式要求的需要重新加载的数据
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

/*添加运动场地*/
function add_equip(){
       $("#modal-img").modal({
                 keyboard:false
                });
                     var str = '';
                       str += "<button type='button' onclick='clean_check()' class='btn btn-default' data-dismiss='modal'>关闭</button>"
                       str += "<button type='button' onclick='add()' class='btn btn-primary'>保存</button>";
                       $('#show_btn').empty();
                       $('#show_btn').append(str);

   }


/*查看图片按钮*/
function video_show(id){
    $.ajax({
        type:"post",
        url:"course/getVideoUrl.do?id="+id,
        success:function(data){
            $("#video_show").empty(html);
            var img_data=data.data;
            var html="";
            if(img_data.videoUrl=="无")
            {
                alert("没有视频展示");
            }
            else{
            html+="<video controls='controls' autoplay='autoplay'> <source src='"+img_data.videoUrl+"' type='video/mp4' /></video>"

                $("#modal-img").modal({
                                     keyboard:false
                                    });
            }
            $("#video_show").append(html);
        }
    })
}