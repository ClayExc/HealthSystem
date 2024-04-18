function course_data(){
/*年龄数据回显*/
    $.ajax({
        type:"get",
        url:"course/ageGroupList.do",
        success:function(data){
            console.log(data);
            var str = '';
            str ="<a href='#' data-toggle='check-all' class='选择适宜年龄'>全选</a>"
            $.each(data,function(index,value){
                   	str += "<label class='dropdown-option'> <input type='checkbox' onclick='check_effect()'  name='dropdown-group03' value='"+value.id+"' /> "+value.group+" </label>";
                   	});
            $('#course_age').append(str);
        }
    })
 /*禁忌疾病数据回显*/
 $.ajax({
    type:"get",
    url:"standardRecipe/diseaseList.do",
    success: function(data){
        console.log(data);
        var str="";
        str +="<a href='#' data-toggle='check-all' class='选择禁忌疾病'>全选</a>";
        $.each(data,function(index,value){
                str += "<label class='dropdown-option'> <input type='checkbox' onclick='check_effect()'  name='dropdown-group05' value='"+value.id+"' /> "+value.diseaseName+" </label>";
         	});
         $('#diseaseList').append(str);
    }
 })

 /*运动器材数据回显*/
 $.ajax({
    tyoe:"get",
    url:"standardRecipe/equipmentList.do",
    success: function(data){
        console.log(data);
        var str="";
        str +="<a href='#' data-toggle='check-all' class='选择运动器材'>全选</a>";
        $.each(data,function(index,value){
                str += "<label class='dropdown-option'> <input type='checkbox' onclick='check_effect()'  name='dropdown-group02' value='"+value.id+"' /> "+value.equipmentName+" </label>";
         	});
         $('#equipment').append(str);
    }
 })

}

/*全选效果*/
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