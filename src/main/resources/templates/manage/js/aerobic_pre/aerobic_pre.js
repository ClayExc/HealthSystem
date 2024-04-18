function show_aerobicPre() {

            $.jgrid.defaults.styleUI = 'Bootstrap';
               var _height = $(window).height()*2/3;
               console.log(_height);
             $.ajax({
                                type:"POST",
                                url:"recipegroup/selectRecipeByName.do",
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
                                                       colNames: ['id','处方名称', '运动效果', '运动类型', '适宜年龄', '运动频率', '运动时间','最小心率','最大心率','注意事项','方法介绍'],
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
                                                               name: 'typevalue',
                                                               index: 'typevalue',
                                                               editable: true,
                                                               width: 70
                                                           },
                                                           {
                                                               name: 'ageGroup',
                                                               index: 'ageGroup',
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
                                                               name: 'sportsTime',
                                                               index: 'sportsTime',
                                                               editable: true,
                                                               width: 70,
                                                               sortable: false
                                                           },
                                                           {
                                                               name: 'minHeartrate',
                                                               index: 'minHeartrate',
                                                               editable: true,
                                                               width: 70,
                                                               sortable: false
                                                           },

                                                           {
                                                               name: 'maxHeartrate',
                                                               index: 'maxHeartrate',
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
                                                       caption: "有氧运动处方信息展示",
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
                                                       addfunc : add_url,
                                                       editfunc: edit_pre,

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
        /*弹出修改处方框*/
        function edit_pre(id){

             $("#modal-form").modal({
                        keyboard:false
                    });
              var str = '';
                str += "<button type='button' onclick='clean_check()' class='btn btn-default' data-dismiss='modal'>关闭</button>"
                str += "<button type='button' onclick='update_pre("+id+")' class='btn btn-primary'>保存</button>";
                $('#show_btn').empty();
                $('#show_btn').append(str);

             infos(id);
             sportsEffect();
             sportsLike();
             equipment();
             fatigueList();
             diseaseList();
        }

        /*删除处方*/
        function del_open(id){

        if(window.confirm('确认删除运动处方吗？')){
                         $.ajax({
                                        type:"post",
                                        url:"standardRecipe/deleteSR.do?id="+id,
                                        //contentType:"application/json;charset=utf-8",这个不能设置否则后台不能接受值
                                        success:function (data) {

                                                alert("删除成功");
                                                $.ajax({
                                                                                     type:"POST",
                                                                                     url:"standardRecipe/selectSR.do",
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

   function add_url(){
        window.location.href="http://localhost:8088/mana_add_prescription01.html";
   }


   /*更换查询方式*/

   $(document).ready(function(){
    document.getElementById("weight").style.display="none";
     document.getElementById("search_type").style.display="none";
     document.getElementById("search_effect").style.display="none";
     document.getElementById("search_name").style.display="block";
     sportsType();
     pre_Effect();
   });
   function select_search(align){
             switch (align){
                case 'name':
                     document.getElementById("search_type").style.display="none";
                     document.getElementById("search_effect").style.display="none";
                     document.getElementById("search_name").style.display="block";
                     break;
                case 'type':
                     document.getElementById("search_type").style.display="block";
                     document.getElementById("search_effect").style.display="none";
                     document.getElementById("search_name").style.display="none";
                     break;
                case 'effect':
                     document.getElementById("search_effect").style.display="block";
                     document.getElementById("search_type").style.display="none";
                     document.getElementById("search_name").style.display="none";
                     break;
                default:
                     document.getElementById("search_type").style.display="none";
                     document.getElementById("search_effect").style.display="none";
                     break;
             }
   }

   /*回显查询运动效果和运动类型*/
   function sportsType() {
   /*运动类型数据展示*/
       $.ajax({
              type: "get",
              url: "standardRecipe/interestList.do",
              success: function (e) {
              console.log(e);
              var str = '';
                  			$.each(e,function(index,value){
                  					str += "<option value='"+value.id+"'>"+value.sportTypesName+"</option>";
                  				});
                  			$('#search_type').empty();
                  			$('#search_type').append(str);
              }
          });
   }

   /*运动数据信息展示*/
   function pre_Effect() {
   /*运动效果数据展示*/
       $.ajax({
           type: "get",
           url: "standardRecipe/goalList.do",
           success: function (e) {
                         console.log(e);
                         var str = '';
                             			$.each(e,function(index,value){
                             					str += "<option value='"+value.id+"'>"+value.sportsEffect+"</option>";
                             				});
                             			$('#search_effect').empty();
                             			$('#search_effect').append(str);
                         }
       });
   }

   /*展示添加模态框*/

/*负重心率切换*/

function  strength(){
     var show = "";
     show = $("input[name='radioInline']:checked").val();
     switch (show){
        case '1':
             document.getElementById("weight").style.display="none";
             document.getElementById("heart_rate").style.display="block";
             break;
        case '2':
             document.getElementById("weight").style.display="block";
             document.getElementById("heart_rate").style.display="none";
             break;
        default:
             document.getElementById("heart_rate").style.display="none";
             document.getElementById("weight").style.display="none";
             break;
     }
};


/*被修改信息回显在页面*/
function infos(id){
    $.ajax({
               type: "post",
               url: "standardRecipe/selectSRById.do?id="+id,
               success: function (e) {
                    $("#pre_name").val(e[0].name);
                    var time=e[0].sportsTime;
                    var time1 = time.substring(0, time.indexOf("~"));
                    var time2 = time.substring(time.indexOf("~")+1);
                    $("#pre_time01").val(time1);
                    $("#pre_time02").val(time2);
                    $("#pre_min_heart").val(e[0].heartRateMin);
                    $("#pre_max_heart").val(e[0].heartRateMax);
                    $("#pre_weight").val(e[0].fuzhongRange);
                    $("#pre_number").val(e[0].number);
                    $("#pre_group_num").val(e[0].groupNum);
                    $("#pre_frequency").val(e[0].sportsFrequency);
                    $("#pre_ways").val(e[0].methodsIntroduce);
                    $("#pre_cycle").val(e[0].reviewCycle);
                    $("#pre_notice").val(e[0].notice);


                    var all_options = document.getElementById("fatigueList").options;
                    for(var i=0; i<all_options.length; i++){
                        if(all_options[i].innerHTML == e[0].sportsFatigue){
                            all_options[i].selected = true;
                            break;
                        }
                    }
                    var all_options01 = document.getElementById("sportsLike").options;
                        for(var i=0; i<all_options01.length; i++){
                           if(all_options01[i].innerHTML == e[0].sportsTypeId){
                            all_options01[i].selected = true;
                            break;
                           }
                        }
                      /*回显运动效果*/
                     $("#sportsEffect").find("input[type='checkbox']").each(function(){
                                     var lbl7=document.getElementById("effect"+$(this).val()).innerText; //获取label里的值
                                     for(var i=0;i<e[0].sportsEffectList.length;i++)
                                     {
                                        if(lbl7==e[0].sportsEffectList[i])
                                        {
                                            var input_in=document.getElementById("effect_in"+$(this).val());
                                            input_in.checked = true;
                                        }
                                     }
                                     })
                     /*回显运动器材*/
                     $("#equipment").find("input[type='checkbox']").each(function(){
                            var lbl7=document.getElementById("equip"+$(this).val()).innerText; //获取label里的值
                             for(var i=0;i<e[0].sportsMustEquipmentList.length;i++)
                             {
                                 if(lbl7==e[0].sportsMustEquipmentList[i])
                                 {
                                     var input_in=document.getElementById("equipment"+$(this).val());
                                      input_in.checked = true;
                                 }
                             }
                    })
                    /*回显运动禁忌疾病*/
                    $("#diseaseList").find("input[type='checkbox']").each(function(){
                         var lbl7=document.getElementById("disease"+$(this).val()).innerText; //获取label里的值
                         for(var i=0;i<e[0].tabooDiseaseList.length;i++)
                         {
                              if(lbl7==e[0].tabooDiseaseList[i])
                              {
                                   var input_in=document.getElementById("disease_in"+$(this).val());
                                   input_in.checked = true;
                              }
                         }
                      })
               }
           });
}
/*修改信息的回显*/

/*运动数据信息展示*/
function sportsEffect() {
/*运动效果数据展示*/
    $.ajax({
        type: "get",
        url: "standardRecipe/goalList.do",

        success: function (e) {

                var str = '';
                str ="<a href='#' id='effect_a' onclick='select_effect_all()' data-toggle='check-all' class='选择运动效果'>全选</a>"
       			$.each(e,function(index,value){
       					str += "<label id='effect"+value.id+"' class='dropdown-option'> <input type='checkbox' onclick='check_effect()' id='effect_in"+value.id+"'  name='dropdown-group03' value='"+value.id+"' />"+value.sportsEffect+"</label>";
       				});

                $('#sportsEffect').empty();
    			$('#sportsEffect').append(str);
        }
    });

}
function sportsLike() {
/*运动兴趣数据展示*/
    $.ajax({
           type: "get",
           url: "standardRecipe/interestList.do",

           success: function (e) {
           console.log(e);

           var str = '';

               			$.each(e,function(index,value){
               					str += "<option value='"+value.id+"'>"+value.sportTypesName+"</option>";
               				});
               			$('#sportsLike').empty();
               			$('#sportsLike').append(str);

           }
       });

}


function equipment() {
/*运动器材数据展示*/
    $.ajax({
           type: "get",
           url: "standardRecipe/equipmentList.do",

           success: function (e) {
           console.log(e);
                   var str = '';
                   str ="<a href='#' id='equip_a' onclick='select_equip_all()' data-toggle='check-all' class='选择运动器材'>全选</a>"
       			$.each(e,function(index,value){
       					str += "<label id='equip"+value.id+"' class='dropdown-option'> <input type='checkbox' onclick='check_effect()' id='equipment"+value.id+"' name='dropdown-group02' value='"+value.id+"' />"+value.equipmentName+"</label>";
       				});
                $('#equipment').empty();
       			$('#equipment').append(str);
           }
       });

}
function fatigueList() {
/*运动疲劳度等级展示*/
    $.ajax({
           type: "get",
           url: "standardRecipe/fatigueList.do",

           success: function (e) {
           console.log(e);
                   var str = '';
                   str ="<a href='#' data-toggle='check-all' class='选择运动疲劳度等级'>全选</a>"
       			$.each(e,function(index,value){
       			    str += "<option value='"+value.id+"'>"+value.name+"</option>";
       			});
                $('#fatigueList').empty();
       			$('#fatigueList').append(str);
           }
       });

}

function diseaseList() {
/*运动禁忌疾病展示*/
    $.ajax({
           type: "get",
           url: "standardRecipe/diseaseList.do",

           success: function (e) {
           console.log(e);
                   var str = '';
                   str ="<a href='#' id='dis_a' onclick='select_dis_all()' data-toggle='check-all' class='选择禁忌疾病'>全选</a>"
       			$.each(e,function(index,value){
       					str += "<label id='disease"+value.id+"' class='dropdown-option'> <input type='checkbox' onclick='check_effect()' id='disease_in"+value.id+"' name='dropdown-group05' value='"+value.id+"' />"+value.diseaseName+"</label>";
       				});
                $('#diseaseList').empty();
       			$('#diseaseList').append(str);
           }
       });

}
/*多选控制*/
function check_effect(){
        var CheckboxDropdown = function(el) {
                var _this = this;
                this.isOpen = false;
                this.areAllChecked = false;
                this.$el = $(el);
                this.$label = this.$el.find('.dropdown-label');
                this.$checkAll = this.$el.find('[data-toggle="check-all"]').first();
                this.$inputs = this.$el.find('[type="checkbox"]');

                this.onCheckBox();

                this.$label.on('click', function(e) {
                    e.preventDefault();
                    _this.toggleOpen();
                });

                this.$checkAll.on('click', function(e) {
                    e.preventDefault();
                    _this.onCheckAll();
                });

                this.$inputs.on('change', function(e) {
                    _this.onCheckBox();
                });
            };

            CheckboxDropdown.prototype.onCheckBox = function() {
                this.updateStatus();
            };

            CheckboxDropdown.prototype.updateStatus = function() {
                var checked = this.$el.find(':checked');

                this.areAllChecked = false;
                this.$checkAll.html('全选');

                if (checked.length <= 0) {

                } else if (checked.length === 1) {
                    this.$label.html(checked.parent('label').text());
                } else if (checked.length === this.$inputs.length) {
                    this.$label.html('全选');
                    this.areAllChecked = true;
                    this.$checkAll.html('取消全选');
                } else {
                    this.$label.html(checked.length + ' Selected');
                }
            };

            CheckboxDropdown.prototype.onCheckAll = function(checkAll) {
                if (!this.areAllChecked || checkAll) {
                    this.areAllChecked = true;
                    this.$checkAll.html('取消全选');
                    this.$inputs.prop('checked', true);
                } else {
                    this.areAllChecked = false;
                    this.$checkAll.html('全选');
                    this.$inputs.prop('checked', false);
                }

                this.updateStatus();
            };

            CheckboxDropdown.prototype.toggleOpen = function(forceOpen) {
                var _this = this;

                if (!this.isOpen || forceOpen) {
                    this.isOpen = true;
                    this.$el.addClass('on');
                    $(document).on('click', function(e) {
                        if (!$(e.target).closest('[data-control]').length) {
                            _this.toggleOpen();
                        }
                    });
                } else {
                    this.isOpen = false;
                    this.$el.removeClass('on');
                    $(document).off('click');
                }
            };

            var checkboxesDropdowns = document.querySelectorAll('[data-control="checkbox-dropdown"]');
            for (var i = 0, length = checkboxesDropdowns.length; i < length; i++) {
                new CheckboxDropdown(checkboxesDropdowns[i]);
            }


}

/*设置模态框所有单选框不选中*/
function clean_check(){
        $("input:checkbox").each(function () {
                       $(this).prop("checked", false);
         });

}

/*禁忌疾病的全选切换*/
function select_dis_all(){

      var lbl7=document.getElementById("dis_a").innerHTML; //获取label里的值
      if(lbl7=="全选")
      {
             document.getElementById("dis_a").innerText="取消全选";
             var boxes = document.getElementById('diseaseList').getElementsByTagName("input");
             for(var i=0; i<boxes.length; i++){
             boxes[i].checked = true;
             }
      }
      if(lbl7=="取消全选")
      {
        document.getElementById("dis_a").innerText="全选";
        var boxes = document.getElementById('diseaseList').getElementsByTagName("input");
                     for(var i=0; i<boxes.length; i++){
                     boxes[i].checked = false;
                     }
      }



}

/*器材的全选切换*/
function select_equip_all(){
    var lbl7=document.getElementById("equip_a").innerHTML; //获取label里的值
          if(lbl7=="全选")
          {
                 document.getElementById("equip_a").innerText="取消全选";
                 var boxes = document.getElementById('equipment').getElementsByTagName("input");
                              for(var i=0; i<boxes.length; i++){
                              boxes[i].checked = true;
                              }
          }
          if(lbl7=="取消全选")
          {
            document.getElementById("equip_a").innerText="全选";
            var boxes = document.getElementById('equipment').getElementsByTagName("input");
             for(var i=0; i<boxes.length; i++){
             boxes[i].checked = false;
              }
          }
}

/*运动效果的全选切换*/

 function select_effect_all(){
 var lbl7=document.getElementById("effect_a").innerHTML; //获取label里的值
           if(lbl7=="全选")
           {
                  document.getElementById("effect_a").innerText="取消全选";
                  var boxes = document.getElementById('sportsEffect').getElementsByTagName("input");
                               for(var i=0; i<boxes.length; i++){
                               boxes[i].checked = true;
                                }
           }
           if(lbl7=="取消全选")
           {
             document.getElementById("effect_a").innerText="全选";
             var boxes = document.getElementById('sportsEffect').getElementsByTagName("input");
                          for(var i=0; i<boxes.length; i++){
                          boxes[i].checked = false;
                           }
           }
 }