package com.sports.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sports.common.ServerResponse;
import com.sports.dao.RunDataMapper;
import com.sports.exception.DiseaseException;
import com.sports.exception.RunDataException;
import com.sports.service.impl.RunDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;


@RestController
@RequestMapping("/runData/")
@CrossOrigin(origins="*",maxAge=3600)
public class RunDataController {

    @Autowired
    RunDataServiceImpl runDataService;

    /**
     * 获取每天的心率 和速度曲线的数据
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getRunData.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getRunData (@RequestBody JSONObject jsonObject) throws Exception{
        JSONArray jsonArray = runDataService.getRunData(jsonObject);
        return jsonArray;
    }

    /**
     * 根据时间查询 每天的
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getRunDataByTime.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getRunDataByTime (@RequestBody JSONObject jsonObject) throws Exception{
        JSONArray jsonArray = runDataService.getRunDataByTime(jsonObject);
        return jsonArray;
    }

    /**
     * 获取周数据记录
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getWeekData.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getWeekData (@RequestBody JSONObject jsonObject) throws Exception{
        JSONArray jsonArray = runDataService.getWeekData(jsonObject);
        return jsonArray;
    }

    /**
     * 按照时间范围查询数据  每周的数据
     */
    @RequestMapping(value = "getWeekDataByTime.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getWeekDataByTime (@RequestBody JSONObject jsonObject) throws Exception{
        JSONArray jsonArray = runDataService.getWeekDataByTime(jsonObject);
        return jsonArray;
    }

    /**
     * 获取月数据
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getMoonData.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = RunDataException.class)
    public JSONArray getMoonData (@RequestBody JSONObject jsonObject) throws Exception{
        JSONArray jsonArray = runDataService.getMoonData(jsonObject);
        return jsonArray;
    }
}
