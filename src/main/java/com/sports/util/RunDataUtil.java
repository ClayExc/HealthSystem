package com.sports.util;

import com.alibaba.fastjson.JSONObject;
import com.sports.common.ResponseCode;
import com.sports.exception.CustomException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RunDataUtil {

    /**
     * 时间判断工具
     * @param startTime
     * @param endTime
     * @return
     */
    private static boolean judgeBefore(String startTime , String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            boolean isBefore = start.before(end);
            return isBefore;
        } catch (Exception e) {
            throw new CustomException(ResponseCode.TIME_PARAM_PARSE_ERROR);
        }
    }

    public static Map getData(String time , String Ratestring, String Speedstring) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //数据格式
        /*String time = "2023-06-29 17:39:18";
        String Ratestring = "[1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0]";
        String Speedstring = "[3.0,3.0,3.0,3.0,3.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,16.0,16.0,16.0,16.0]";*/
        // 初始化 返回列表
        List<Double> rate_dataList = new ArrayList<>();
        List<Double> speed_dataList = new ArrayList<>();
        List<String> rate_timeList = new ArrayList<>();
        Ratestring = Ratestring.substring(1);
        Speedstring = Speedstring.substring(1);
        String Rsubstring = Ratestring.substring(0, Ratestring.length() - 1);
        String Ssubstring = Speedstring.substring(0, Speedstring.length() - 1);
        //以逗号分割，得出的数据存到 result 里面
        String[] rate_result = Rsubstring.split(",");
        String[] speed_result = Ssubstring.split(",");
        for (String rresult : rate_result) {
            rate_dataList.add(Double.valueOf(rresult));
        }
        for (String sresult : speed_result) {
            speed_dataList.add(Double.valueOf(sresult));
        }
        System.out.println("心率数据：" + rate_dataList.toString());
        System.out.println("速度数据：" + speed_dataList.toString());
        // 时间逻辑
        // 待修改 设置成动态间隔
        for(int i = 0 ; i < rate_dataList.size() ; i++ ){
//            int j;
            Date sTime = sdf.parse(time);
            sTime.setTime(sTime.getTime() + i * 2 * 1000);
            rate_timeList.add(sdf.format(sTime));
        }
        System.out.println("心率数据：" + rate_timeList.toString());
        Map map = new HashMap();
        map.put("rate_dataList",rate_dataList);
        map.put("speed_dataList",speed_dataList);
        map.put("rate_timeList",rate_timeList);

        return map;
    }

    public static Map<String, String> getDateMap(JSONObject jsonObject){
        Map<String,String> map = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date startTime = new Date();
        String startTime=null;
//        Date startTimeTemp = new Date();
        String startTimeTemp=null;
        try {
            // 得到时间
            if(jsonObject.get("startTime") != null && jsonObject.get("endTime") != null){
                if(RunDataUtil.judgeBefore((String) jsonObject.get("startTime"),(String) jsonObject.get("endTime"))){
                    // 时间从参数中获取
                    startTime = (String) jsonObject.get("startTime");
                    startTimeTemp = (String) jsonObject.get("endTime");
                }else {
                    System.out.println("时间范围出错抛出异常！");
                    throw new CustomException(ResponseCode.TIME_ERROR);
                }
            }
            map.put("startTime",startTime);
            map.put("endTime",startTimeTemp);
            return map;

        } catch (Exception e) {
            throw new CustomException(ResponseCode.PARAM_TYPE_BIND_ERROR);
        }
    }

    /**
     *
     * @doc 日期转换星期几
     * @param datetime
     *            日期 例:2017-10-17
     * @return String 例:星期二
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

    public static Date StringToDate(String datetime){
        SimpleDateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdFormat.parse(datetime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }
}
