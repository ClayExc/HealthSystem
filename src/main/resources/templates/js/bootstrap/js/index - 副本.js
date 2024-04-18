$('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	$('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
	$('.form_Y').getY().on('changeDate', function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	$('#qt_1').getQT().on('changeDate', function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	$('#startQT').getQT().on('changeDate', function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	$('#endQT').getQT().on('changeDate', function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	$('.form_YM').getYM().on('changeDate', function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
	$('.form_YMD').getYMD().on('changeDate', function(event) {
		event.preventDefault();
		event.stopPropagation();
	});
$(function() {
// 初期展示 设置日期格式
        $("#date-start").datetimepicker('remove');
        $("#date-start").getY().on('changeDate', function(event) {
            event.preventDefault();
            event.stopPropagation();
            var startTime = event.date;
            $('#date-end').datetimepicker('setStartDate', startTime);
        });

        $("#date-end").datetimepicker('remove');
        $("#date-end").getY().on('changeDate', function(event) {
            event.preventDefault();
            event.stopPropagation();
            var endTime = event.date;
            $("#date-start").datetimepicker('setEndDate', endTime);
        });
    // 根据按年  按月  显示不同日历
    $("#dateType").change(function () {
        var dateType = $("#dateType").val();
        // 按年
        if (dateType == 1) {
            $("#date-start").datetimepicker('remove');
            $("#date-start").getY().on('changeDate', function(event) {
                event.preventDefault();
                event.stopPropagation();
                var startTime = event.date;
                $('#date-end').datetimepicker('setStartDate', startTime);
            });

            $("#date-end").datetimepicker('remove');
            $("#date-end").getY().on('changeDate', function(event) {
                event.preventDefault();
                event.stopPropagation();
                var endTime = event.date;
                $("#date-start").datetimepicker('setEndDate', endTime);
            });
        } else if (dateType == 2) {
            // 按季度
            $("#date-start").datetimepicker('remove');
            $("#date-start").getQT().on('changeDate', function(event) {
                event.preventDefault();
                event.stopPropagation();
                var startTime = event.date;
                $('#date-end').datetimepicker('setStartDate', startTime);
            });

            $("#date-end").datetimepicker('remove');
            $("#date-end").getQT().on('changeDate', function(event) {
                event.preventDefault();
                event.stopPropagation();
                var endTime = event.date;
                $("#date-start").datetimepicker('setEndDate', endTime);
            });
        } else if (dateType == 3) {
            // 按月
            $("#date-start").datetimepicker('remove');
            $("#date-start").getYM().on('changeDate', function(event) {
                event.preventDefault();
                event.stopPropagation();
                var startTime = event.date;
                $('#date-end').datetimepicker('setStartDate', startTime);
            });

            $("#date-end").datetimepicker('remove');
            $("#date-end").getYM().on('changeDate', function(event) {
                event.preventDefault();
                event.stopPropagation();
                var endTime = event.date;
                $("#date-start").datetimepicker('setEndDate', endTime);
            });
        }
    });
});