package com.sports.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTest {
    public static void main(String[] args) throws Exception {
//        String startTime = "2017-10-01";
//        String endTime = "2017-10-08";

        Date nowTime=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> dateList = getBetweenDates(sdf.parse(startTime), sdf.parse(endTime));
//        for (int i = 0; i < dateList.size(); i++) {
        Map map = dateToWeek(sdf.format(nowTime));
        int index =(Integer) map.get("index");
        String value =(String) map.get("value");
        System.out.println(sdf.format(nowTime) + " " +index + " " + value );
//        }
    }

    /**
     *
     * @doc 获取日期间的日期
     * @param start
     *            开始日期
     * @param end
     *            结束日期
     * @return List集合
     * @author lzy
     * @history 2017年10月17日 上午9:55:04 Create by 【lzy】
     */
    private static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        //添加或减去指定的时间给定日历领域，基于日历的规则。例如，从日历当前的时间减去5天，您就可以通过
        tempStart.add(Calendar.DAY_OF_YEAR, 0);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        tempEnd.add(Calendar.DAY_OF_YEAR, 1);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     *
     * @doc 日期转换星期几
     * @param datetime
     *            日期 例:2017-10-17
     * @return String 例:星期二
     * @author lzy
     * @history 2017年10月17日 上午9:55:30 Create by 【lzy】
     */
    public static Map dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。

        if (w < 0){
            w = 0;
        }


        Map map = new HashMap<>();
        map.put("index",w);
        map.put("value",weekDays[w]);
        return map;
    }

}

