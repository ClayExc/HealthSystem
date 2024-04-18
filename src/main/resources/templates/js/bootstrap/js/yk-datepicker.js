/*! yk-datepicker.js
 * 基于bootstrap datepicker的日期控件扩展
 * 支持展示 年 季度 年月  年月日
 * QQ:416064823
 * Released by yk, 2023-05-23.
 * Copyright (c) 2023 yank
 */
// 季度
$.fn.datetimepicker.dates['zh-cn-qtrs'] = {
    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
    months: ["Q1", "Q2", "Q3", "Q4", "", "", "", "", "", "", "", ""],
    monthsShort: ["一季度", "二季度", "三季度", "四季度", "", "", "", "", "", "", "", ""],
    clear: "清除",
    meridiem: ["上午", "下午"]
};
// 按年
$.fn.getY = function (options) {
    var opt = {
        format: 'yyyy'
    };
    $.extend(opt, options);
    if (this.is('input[type=text]')) {
        this.datetimepicker({
            format: opt.format,
            autoclose: true,
            startView: 4,
            minView: 4,
            forceParse: false,
            language: 'zh-cn'
        });
    }
    return this;
};
// 按年月
$.fn.getYM = function (options) {
    var opt = {
        format: 'yyyy-mm'
    };
    $.extend(opt, options);
    if (this.is('input[type=text]')) {
        this.datetimepicker({
            format: opt.format,
            weekStart: 1,
            autoclose: true,
            startView: 3,
            minView: 3,
            forceParse: false,
            language: 'zh-cn'
        });
    }
    return this;
};
// 季度
$.fn.getQT = function (options) {
    var opt = {
        format: 'yyyy-mm'
    };
    var date = new Date();
    date.setDate(1);
    date.setMonth(Math.floor(date.getMonth() / 3));
    $.extend(opt, options);
    if (this.is('input[type=text]')) {
        
        this.datetimepicker({
            format: opt.format,
            weekStart: 1,
            autoclose: true,
            startView: 3,
            minView: 3,
            language: 'zh-cn-qtrs',
            initialDate: date
        });
		this.data('datetimepicker').picker.addClass('quarter');
    }
    return this;
};
// 年月日
$.fn.getYMD = function (options) {
    var opt = {
        format: 'yyyy-mm-dd'
    };
    $.extend(opt, options);
    if (this.is('input[type=text]')) {
        this.datetimepicker({
            format: opt.format,
            autoclose: true,
            startView: 2,
            minView: 2,
            forceParse: false,
            language: 'zh-cn'
        });
    }
    return this;
};