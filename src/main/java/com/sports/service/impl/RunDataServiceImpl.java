package com.sports.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sports.Vo.RunDataVo;
import com.sports.common.ResponseCode;
import com.sports.dao.RunDataMapper;
import com.sports.entity.RunData;
import com.sports.exception.RunDataException;
import com.sports.service.RunDataService;
import com.sports.util.RunDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("RunDataService")
public class RunDataServiceImpl implements RunDataService {
    private final static Logger logger = LoggerFactory.getLogger(RunDataServiceImpl.class);

    @Autowired
    private RunDataMapper runDataMapper;

    /**
     * 根据id获取最新的数据 心率曲线 速度曲线
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getRunData(JSONObject jsonObject) throws Exception {
        List<Double> rate_dataList = new ArrayList<>();
        List<Double> speed_dataList = new ArrayList<>();
        List<String> rate_timeList = new ArrayList<>();
        // 得到用户id
        Integer userId = Integer.valueOf((String) jsonObject.get("user_id"));
        String recordTime = String.valueOf(jsonObject.get("record_time"));
        if (jsonObject.get("record_time") == null){
            //如果没有传入进来时间点 就根据id获取最新的一条数据
            RunData runData = runDataMapper.selectOneByUserId(userId);
            if (runData == null){
                throw new  RunDataException(ResponseCode.RESULT_DATA_NONE);
            }
            logger.info(runData.toString());
            Map map = RunDataUtil.getData(runData.getRecordTime(),runData.getHeartRate(),runData.getRunSpeed());
            rate_dataList = (List<Double>) map.get("rate_dataList");
            speed_dataList = (List<Double>) map.get("speed_dataList");
            rate_timeList = (List<String>) map.get("rate_timeList");
        }else{
            //如果有时间节点传入进来 就根据时间进行查询
            RunData runData = runDataMapper.selectRateByTime(userId,recordTime);
            if (runData == null){
                throw new  RunDataException(ResponseCode.RESULT_DATA_NONE);
            }
            logger.info(runData.toString());
            Map map = RunDataUtil.getData(runData.getRecordTime(),runData.getHeartRate(),runData.getRunSpeed());
            rate_dataList = (List<Double>) map.get("rate_dataList");
            speed_dataList = (List<Double>) map.get("speed_dataList");
            rate_timeList = (List<String>) map.get("rate_timeList");
        }
        List<RunDataVo> runDataVoList =  new ArrayList<>();
        for (int i = 0; i <rate_dataList.size() ; i++) {
            RunDataVo runDataVo = new RunDataVo();
            runDataVo.setUserId(userId);
//                runDataVo.setUserName(runData.getUserName());
//                runDataVo.setDistance(runData.getDistance());
            runDataVo.setRecordTime(rate_timeList.get(i));
            runDataVo.setHeartRate(rate_dataList.get(i));
            runDataVo.setRunSpeed(speed_dataList.get(i));
            runDataVoList.add(runDataVo);
        }
        return JSONArray.parseArray(JSON.toJSONString(runDataVoList));
    }

    /**
     * 获取每天的距离 卡路里 步数 等数据
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getRunDataByTime(JSONObject jsonObject) throws Exception {
        //距离
        List<Double> distance_dataList = new ArrayList<>();
        //卡路里
        List<Double> calorie_dataList = new ArrayList<>();
        //最高心率
        List<Double> max_heart_rate_dataList = new ArrayList<>();
        //最低心率
        List<Double> min_heart_rate_dataList = new ArrayList<>();
        //步数
        List<Integer> step_number_dataList = new ArrayList<>();

        List<String> rate_timeList = new ArrayList<>();
        // 得到用户id
        Integer userId = Integer.valueOf((String) jsonObject.get("user_id"));
        String recordTime = String.valueOf(jsonObject.get("record_time"));
        if(jsonObject.get("record_time") == null){
            //如果没有时间传过来 那就根据id查询最新的数据
            List<RunData> runDataList = runDataMapper.selectOneByUserId2(userId);
            //为空判断
            if (runDataList == null || runDataList.isEmpty()){
                throw new  RunDataException(ResponseCode.RESULT_DATA_NONE);
            }
            for (RunData runData : runDataList) {
                distance_dataList.add(runData.getDistance());
                calorie_dataList.add(runData.getCalorie());
                max_heart_rate_dataList.add(runData.getMaxHeartRate());
                min_heart_rate_dataList.add(runData.getMinHeartRate());
                step_number_dataList.add(runData.getStepNumber());
                rate_timeList.add(runData.getRecordTime());
            }
        }else {
            //如果传过来了时间 按照时间查询
//            Map<String, Date> map = RunDataUtil.getDateMap(jsonObject);
//            List<RunData> runDataList = runDataMapper.selectByRange(userId,map.get("startTime"), map.get("endTime"));
            List<RunData> runDataList = runDataMapper.selectByTime(userId,recordTime);
            //为空判断
            if (runDataList == null || runDataList.isEmpty()){
                throw new  RunDataException(ResponseCode.RESULT_DATA_NONE);
            }
            for (RunData runData : runDataList) {
                distance_dataList.add(runData.getDistance());
                calorie_dataList.add(runData.getCalorie());
                max_heart_rate_dataList.add(runData.getMaxHeartRate());
                min_heart_rate_dataList.add(runData.getMinHeartRate());
                step_number_dataList.add(runData.getStepNumber());
                rate_timeList.add(runData.getRecordTime());
            }
        }

        List<RunDataVo> runDataVoList =  new ArrayList<>();
        for (int i = 0; i <distance_dataList.size() ; i++) {
            RunDataVo runDataVo = new RunDataVo();
            runDataVo.setUserId(userId);
            runDataVo.setRecordTime(rate_timeList.get(i));
            runDataVo.setDistance(distance_dataList.get(i));
            runDataVo.setCalorie(calorie_dataList.get(i));
            //心率
            runDataVo.setMaxHeartRate(max_heart_rate_dataList.get(i));
            runDataVo.setMinHeartRate(min_heart_rate_dataList.get(i));
            runDataVo.setStepNumber(step_number_dataList.get(i));
            runDataVoList.add(runDataVo);
        }
        return JSONArray.parseArray(JSON.toJSONString(runDataVoList));
    }

    /**
     * 获取周数据记录
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public JSONArray getWeekData(JSONObject jsonObject) throws Exception {
        List<RunData> runDataList = new ArrayList<>();
        RunData runData = new RunData();
        //获取id
        Integer userId = Integer.valueOf((String) jsonObject.get("user_id"));
        //获取当前时间
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map map = RunDataUtil.dateToWeek(sdf.format(nowTime));
        int index =(Integer) map.get("index");
        String value =(String) map.get("value");
        //获取上周末的时间
        Date sundayTime = new Date();
        sundayTime.setTime(nowTime.getTime()-index*24*60*60*1000);
        System.out.println(sundayTime);
        //根据上周末的时间进行查询 往前查询出七天的
        Date sTime = new Date();
        for (int i = 0; i <7 ; i++) {
            //把时间进行减一天 总共减少七天 然后根据时间进行查询
            sTime.setTime(sundayTime.getTime()-i*24*60*60*1000);
            String timeString = sdf.format(sTime);
            runData = runDataMapper.selectRateByTime(userId,timeString);

            List<RunData> runDataList1 = new ArrayList<>();
            List<RunData> runDataList2 = new ArrayList<>();
            if(runData == null ){
                RunData runDataNew = new RunData();
                runDataNew.setUserId(userId);
                runDataNew.setCalorie(0.0);
                runDataNew.setRunTime("0");
                runDataNew.setRecordTime(timeString);
                runDataNew.setDistance(0.0);
                runDataNew.setCalorie(0.0);
                runDataNew.setStepNumber(0);
                runDataList1.add(runDataNew);
            }else {
                runDataList2.add(runData);
            }
            runDataList.addAll(runDataList1);
            runDataList.addAll(runDataList2);
        }
        logger.info(runDataList.toString());
        runDataList.forEach(System.out::println);
        List<RunDataVo> runDataVoList =  new ArrayList<>();
        for (RunData runData1:runDataList){
            RunDataVo runDataVo = new RunDataVo();
            runDataVo.setUserId(runData1.getUserId());
            runDataVo.setRunTime(runData1.getRunTime());
            runDataVo.setRecordTime(runData1.getRecordTime());

            //转换成星期几
            Map map1 = RunDataUtil.dateToWeek(sdf.format(RunDataUtil.StringToDate(runData1.getRecordTime())));
            String value1 =(String) map1.get("value");
            runDataVo.setWeek(value1);

            runDataVo.setDistance(runData1.getDistance());
            runDataVo.setCalorie(runData1.getCalorie());
            runDataVo.setStepNumber(runData1.getStepNumber());
            runDataVoList.add(runDataVo);
        }
        return JSONArray.parseArray(JSON.toJSONString(runDataVoList));
    }

    /**
     * 获取周数据记录 按照时间范围查询
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public JSONArray getWeekDataByTime(JSONObject jsonObject) throws Exception {
//        List<RunData> runDataList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RunData runData = new RunData();
        Integer userId = Integer.valueOf((String) jsonObject.get("user_id"));
        //如果传过来了时间 按照时间查询
        Map<String, String> map = RunDataUtil.getDateMap(jsonObject);
        List<RunData> runDataList = runDataMapper.selectByRange(userId,map.get("startTime"), map.get("endTime"));
        //为空判断
        if (runDataList == null || runDataList.isEmpty()){
            throw new  RunDataException(ResponseCode.RESULT_DATA_NONE);
        }
        List<RunDataVo> runDataVoList =  new ArrayList<>();
        for (RunData runData1:runDataList){
            RunDataVo runDataVo = new RunDataVo();
            runDataVo.setUserId(runData1.getUserId());
            runDataVo.setRunTime(runData1.getRunTime());
            runDataVo.setRecordTime(runData1.getRecordTime());

            //转换成星期几
            Map map1 = RunDataUtil.dateToWeek(sdf.format(RunDataUtil.StringToDate(runData1.getRecordTime())));
            String value1 =(String) map1.get("value");
            runDataVo.setWeek(value1);

            runDataVo.setDistance(runData1.getDistance());
            runDataVo.setCalorie(runData1.getCalorie());
            runDataVo.setStepNumber(runData1.getStepNumber());
            runDataVoList.add(runDataVo);
        }
        return JSONArray.parseArray(JSON.toJSONString(runDataVoList));
    }

    /**
     * 获取每个月的数据 按照时间进行模糊查询 装到list里面进行返回
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public JSONArray getMoonData(JSONObject jsonObject) throws Exception {
        List<RunData> runDataList = new ArrayList<>();
        //获取到用户id
        Integer userId = Integer.valueOf((String) jsonObject.get("user_id"));
        String recordTime = String.valueOf(jsonObject.get("record_time"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date nowDate = new Date();
        String selectDate = sdf.format(nowDate);
        if (jsonObject.get("record_time") == null){
            //如果没有传入具体的时间 那么就按照当前时间的月份进行模糊查询
            runDataList = runDataMapper.selectByTime(userId,selectDate);
        }else {
            //如果传入了记录时间 那么就按照传过来的时间进行查询
            runDataList = runDataMapper.selectByTime(userId,recordTime);
        }
        //为空判断
        if (runDataList == null || runDataList.isEmpty()){
            throw new  RunDataException(ResponseCode.RESULT_DATA_NONE);
        }

        List<RunDataVo> runDataVoList = new ArrayList<>();
        for (RunData runData : runDataList){
            RunDataVo runDataVo = new RunDataVo();
            runDataVo.setUserId(runData.getUserId());
            runDataVo.setRunTime(runData.getRunTime());
            runDataVo.setRecordTime(runData.getRecordTime());
            runDataVo.setDistance(runData.getDistance());
            runDataVo.setCalorie(runData.getCalorie());
            runDataVo.setStepNumber(runData.getStepNumber());
            runDataVoList.add(runDataVo);
        }
        return JSONArray.parseArray(JSON.toJSONString(runDataVoList));
    }
}
