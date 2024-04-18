/*页面加载调用参数*/
function all_info(){
    sportsEffect();
    sportsLike();
    equipment();

}
 /*运动数据信息展示*/
function sportsEffect() {
/*运动目的数据展示*/
    $.ajax({
        type: "get",
        url: "user/goalList.do",

        success: function (e) {

                var str = '';

    			$.each(e,function(index,value){
    					str += "<option value='"+value.id+"'>"+value.sportsEffect+"</option>";
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
           url: "user/interestList.do",

           success: function (e) {
           console.log(e);
                   var str = '';
                   str ="<a href='#' data-toggle='check-all' class='选择运动兴趣'>全选</a>"
       			$.each(e,function(index,value){
       					str += "<label class='dropdown-option'> <input type='checkbox' onclick='check_effect()' name='dropdown-group01' value='"+value.id+"' /> "+value.sportTypesName+" </label>";
       				});

       			$('#sportsLike').append(str);
           }
       });

}


function equipment() {
/*运动器材数据展示*/
    $.ajax({
           type: "get",
           url: "user/equipmentList.do",

           success: function (e) {
           console.log(e);
                   var str = '';
                   str ="<a href='#' data-toggle='check-all' class='选择运动器材'>全选</a>"
       			$.each(e,function(index,value){
       					str += "<label class='dropdown-option'> <input type='checkbox' onclick='check_effect()' id='equipment"+value.id+"' name='dropdown-group02' value='"+value.id+"' /> "+value.equipmentName+" </label>";
       				});

       			$('#equipment').append(str);
           }
       });

}

/*选择回显*/
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
