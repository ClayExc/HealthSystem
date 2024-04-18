function show_ground() {

            $.jgrid.defaults.styleUI = 'Bootstrap';
            var _height = $(window).height()*2/3;
             $.ajax({
                                type:"POST",
                                url:"fitnessvenues/selectFitnessVenues.do",
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
                                                       colNames: ['场地名称','场馆类型','适宜运动','面积','风格','价格','地址','查看图片'],
                                                       colModel: [
                                                       {
                                                          name: 'name',
                                                          index: 'name',
                                                          editable: true,
                                                          width: 50,
                                                          sortable: false
                                                        },
                                                        {
                                                           name: 'typeValue',
                                                           index: 'typeValue',
                                                           editable: true,
                                                           width: 70,
                                                           sorttype: false,
                                                        },
                                                        {
                                                            name: 'sportEvent',
                                                            index: 'sportEvent',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'area',
                                                            index: 'area',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'style',
                                                            index: 'style',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'price',
                                                            index: 'price',
                                                            editable: true,
                                                            width: 70,
                                                            sorttype: false,
                                                        },
                                                        {
                                                            name: 'address',
                                                            index: 'address',
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
                                                                                    var detail="<button onclick='img_show(\""+ rowObject.id + "\")' style='margin-left:20px' class='btn btn-primary' type='button'><i class='fa fa-check'></i>&nbsp;查看图片</button>";
                                                                                    return detail;
                                                                                },
                                                        }
                                                       ],
                                                       pager: "#pager_list_2",
                                                       viewrecords: true,
                                                       caption: "运动场馆展示",
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




        /*删除处方*/
        function del_open(id){

        if(window.confirm('确认删除器材吗？')){
                         $.ajax({
                                        type:"post",
                                        url:"fitnessvenues/deleteFitnessVenues.do?id="+id,
                                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                        success:function (data) {

                                                alert(data);
                                                $.ajax({
                                                                                     type:"POST",
                                                                                     url:"fitnessvenues/selectFitnessVenues.do",
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
function img_show(id){
    $.ajax({
        type:"post",
        url:"fitnessvenues/getImagesUrl.do?id="+id,
        success:function(data){
            $("#img_show").empty(html);
            var img_data=data.data;
            var html="";
            var j=0;
            for (i=1;i<10;i++)
            {
                var imageUrl=eval('img_data.image'+i);
                console.log(imageUrl);
                if(imageUrl==null)
                {
                      j++;
                }
                else{
                    html+="<img id='show_img' width=-1 height=-1 style='diplay:none;margin-top:20px;width:400px;height:200px' src='"+imageUrl+"' />"
                }

            }
            if(j==9)
            {
                alert("没有图片介绍");
            }
            else{
                $("#modal-img").modal({
                                     keyboard:false
                                    });
            }
            $("#img_show").append(html);
        }
    })
}