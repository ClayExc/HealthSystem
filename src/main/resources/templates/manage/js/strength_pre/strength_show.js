function show_strength() {

            $.jgrid.defaults.styleUI = 'Bootstrap';
               var _height = $(window).height()*2/3;
               console.log(_height);
             $.ajax({
                                type:"POST",
                                url:"recipeStrength/selectRecipeStrengthAll",
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
                                                       colNames: ['id','处方名称', '运动效果', '运动类型', '锻炼部位', '器材','运动频率', '负重','组数','次数','注意事项','方法介绍'],
                                                       colModel: [
                                                       {
                                                          name: 'id',
                                                          index: 'id',
                                                          editable: true,
                                                          width: 50,
                                                          sortable: false
                                                        },
                                                        {
                                                           name: 'recipeName',
                                                           index: 'recipeName',
                                                           editable: true,
                                                           width: 70,
                                                           sorttype: false,
                                                        },
                                                           {
                                                               name: 'effect',
                                                               index: 'effect',
                                                               editable: true,
                                                               width: 80,
                                                               sorttype: "int",
                                                               search: true
                                                           },

                                                           {
                                                               name: 'type',
                                                               index: 'type',
                                                               editable: true,
                                                               width: 70
                                                           },
                                                           {
                                                               name: 'musclesTrained',
                                                               index: 'musclesTrained',
                                                               editable: true,
                                                               width: 70,

                                                           },
                                                           {
                                                               name: 'equipment',
                                                               index: 'equipment',
                                                               editable: true,
                                                               width: 70,

                                                           },
                                                           {
                                                               name: 'sportsFrequency',
                                                               index: 'sportsFrequency',
                                                               editable: true,
                                                               width: 90,

                                                           },

                                                           {
                                                               name: 'loadRange',
                                                               index: 'loadRange',
                                                               editable: true,
                                                               width: 70,
                                                               sortable: false
                                                           },
                                                           {
                                                               name: 'frequencyRange',
                                                               index: 'frequencyRange',
                                                               editable: true,
                                                               width: 70,
                                                               sortable: false
                                                           },

                                                           {
                                                               name: 'groupRange',
                                                               index: 'groupRange',
                                                               editable: true,
                                                               width: 90,


                                                            },
                                                           {
                                                                name: 'notice',
                                                                index: 'notice',
                                                                editable: true,
                                                                width: 200,
                                                                sortable: false
                                                           },
                                                           {
                                                                name: 'methodIntroduction',
                                                                index: 'methodIntroduction',
                                                                editable: true,
                                                                width: 200,
                                                                sortable: false
                                                           },
                                                       ],
                                                       pager: "#pager_list_2",
                                                       viewrecords: true,
                                                       caption: "力量运动处方信息展示",
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
