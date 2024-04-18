package com.sports.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sports.Vo.StandarRecipeVo;
import com.sports.Vo.StandarRecipeVo2;
import com.sports.common.ServerResponse;
import com.sports.dao.*;
import com.sports.entity.*;
import com.sports.service.StandardRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("StandardRecipeService")
public class StandardRecipeServiceImpl implements StandardRecipeService {

    @Autowired
    StandardRecipeMapper standardRecipeMapper;


    @Autowired
    private SportsEffectMapper sportsEffectMapper;
    @Autowired
    private SportTypesMapper sportTypesMapper;
    @Autowired
    private SportsIntensityStandardMapper sportsIntensityStandardMapper;
    @Autowired
    private SportEquipmentMapper sportEquipmentMapper;
    @Autowired
    private FatigueDegreeMapper fatigueDegreeMapper;
    @Autowired
    private DiseaseMapper diseaseMapper;


    //处方的添加

    public ServerResponse<String> addSR(StandardRecipe standardRecipe,SportsIntensityStandard sportsIntensityStandard){
        // 校验数据库中是否存在该处方名，name不允许重复
        int resultCount = standardRecipeMapper.checkSRName(standardRecipe.getName());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("该处方已存在");
        }
        //强度添加 返回id
        sportsIntensityStandard.setIntensityType(sportsIntensityStandard.getIntensityType());
            if(sportsIntensityStandard.getIntensityType() == "心率"){
                sportsIntensityStandard.setHeartRateMin(sportsIntensityStandard.getHeartRateMin());
                sportsIntensityStandard.setHeartRateMax(sportsIntensityStandard.getHeartRateMax());
            }else {
                sportsIntensityStandard.setFuzhongRange(sportsIntensityStandard.getFuzhongRange());
                sportsIntensityStandard.setNumber(sportsIntensityStandard.getNumber());
                sportsIntensityStandard.setGroupNum(sportsIntensityStandard.getGroupNum());
            }
        sportsIntensityStandardMapper.insert2(sportsIntensityStandard);
        Integer qiangduId;
        sportsIntensityStandard=sportsIntensityStandardMapper.selectMaxId();
        qiangduId=sportsIntensityStandard.getId();
        standardRecipe.setSportsIntensityId(qiangduId);

        resultCount = standardRecipeMapper.insertSelective(standardRecipe);
        if (resultCount ==0){
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    //处方的查询 根据name查询 不输入name的时候查询全部

    @Override
    public JSONArray selectSR(StandardRecipe  standardRecipe){
        List<StandardRecipe> standardRecipeList =new ArrayList<>();
        String name=standardRecipe.getName();
        List<StandarRecipeVo> standarRecipeVoList =new ArrayList<>();
        if (name ==null || name.isEmpty()){
            standardRecipeList= standardRecipeMapper.selectAll();
            for (StandardRecipe standardRecipe1:standardRecipeList) {
                StandarRecipeVo standarRecipeVo=new StandarRecipeVo();
                List<String> sportsEffectList = new ArrayList<>();
                List<String> sportsMustEquipmentList = new ArrayList<>();
                List<String> tabooDiseaseList = new ArrayList<>();
                standarRecipeVo.setId(standardRecipe1.getId());
                standarRecipeVo.setName(standardRecipe1.getName());

                //效果id 多选 todo
                String effectId=standardRecipe1.getSportsEffectId();
                List<String> effectIdList = new ArrayList<>();
                String[] strArray1 = effectId.split(",");
                effectIdList= Arrays.asList(strArray1);
                System.out.println(effectIdList);
                for (int j = 0;j < effectIdList.size();j++){
                    Integer effectId2=Integer.valueOf(effectIdList.get(j));
                    SportsEffect sportsEffect = new SportsEffect();
                    sportsEffect=sportsEffectMapper.selectGetName(effectId2);
                    sportsEffectList.add(sportsEffect.getSportsEffect());
                }
                standarRecipeVo.setSportsEffectList(sportsEffectList);

                //类型 id 单选 先实验一下这个
                Integer typeId=standardRecipe1.getSportsTypeId();
                SportTypes sportTypes=new SportTypes();
                sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
                standarRecipeVo.setSportsTypeId(sportTypes.getSportTypesName());
                //强度id 单选
                standarRecipeVo.setSportsIntensityId(standardRecipe1.getSportsIntensityId());
                standarRecipeVo.setSportsTime(standardRecipe1.getSportsTime());
                standarRecipeVo.setSportsFrequency(standardRecipe1.getSportsFrequency());
                //器材id 多选
               String qicaiId=standardRecipe1.getSportsMustEquipmentId();
               List<String> qicaiIdList = new ArrayList<>();
                String[] strArray2 = qicaiId.split(",");
                qicaiIdList= Arrays.asList(strArray2);
                System.out.println(qicaiIdList);
                for (int i = 0; i <qicaiIdList.size() ; i++) {
                    Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
                    sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
                }
                standarRecipeVo.setSportsMustEquipmentList(sportsMustEquipmentList);
                //疲劳度等级 单选
                Integer pilaoId= standardRecipe1.getSportsFatigue();
                FatigueDegree fatigueDegree=new FatigueDegree();
                fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
                standarRecipeVo.setSportsFatigue(fatigueDegree.getName());
                //禁忌疾病id 多选
                String jibingId = standardRecipe1.getTabooDiseaseId();
                List<String> jibingIdList =new ArrayList<>();
                String[] strArray3 = jibingId.split(",");
                jibingIdList= Arrays.asList(strArray3);
                System.out.println(jibingIdList);
                for (int i = 0; i <jibingIdList.size() ; i++) {
                    Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(jibingId2);
                    tabooDiseaseList.add(disease.getDiseaseName());
                }
                standarRecipeVo.setTabooDiseaseList(tabooDiseaseList);
                standarRecipeVo.setMethodsIntroduce(standardRecipe1.getMethodsIntroduce());
                standarRecipeVo.setReviewCycle(standardRecipe1.getReviewCycle());
                standarRecipeVo.setVideoUrl(standardRecipe1.getVideoUrl());
                standarRecipeVo.setNotice(standardRecipe1.getNotice());
                standarRecipeVoList.add(standarRecipeVo);
            }
        }
        if(name!=null){
            standardRecipeList=standardRecipeMapper.selectByName(name);
            for (StandardRecipe standardRecipe1:standardRecipeList) {
                StandarRecipeVo standarRecipeVo=new StandarRecipeVo();
                List<String> sportsEffectList = new ArrayList<>();
                List<String> sportsMustEquipmentList = new ArrayList<>();
                List<String> tabooDiseaseList = new ArrayList<>();
                standarRecipeVo.setId(standardRecipe1.getId());
                standarRecipeVo.setName(standardRecipe1.getName());

                //效果id 多选
                String effectId=standardRecipe1.getSportsEffectId();
                List<String> effectIdList = new ArrayList<>();
                String[] strArray1 = effectId.split(",");
                effectIdList= Arrays.asList(strArray1);
                System.out.println(effectIdList);
                for (int j = 0;j < effectIdList.size();j++){
                    Integer effectId2=Integer.valueOf(effectIdList.get(j));
                    SportsEffect sportsEffect = new SportsEffect();
                    sportsEffect=sportsEffectMapper.selectGetName(effectId2);
                    sportsEffectList.add(sportsEffect.getSportsEffect());
                }
                standarRecipeVo.setSportsEffectList(sportsEffectList);

                //类型 id 单选
                Integer typeId=standardRecipe1.getSportsTypeId();
                SportTypes sportTypes=new SportTypes();
                sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
                standarRecipeVo.setSportsTypeId(sportTypes.getSportTypesName());
                //强度id 单选
                standarRecipeVo.setSportsIntensityId(standardRecipe1.getSportsIntensityId());
                standarRecipeVo.setSportsTime(standardRecipe1.getSportsTime());
                standarRecipeVo.setSportsFrequency(standardRecipe1.getSportsFrequency());
                //器材id 多选
                String qicaiId=standardRecipe1.getSportsMustEquipmentId();
                List<String> qicaiIdList = new ArrayList<>();
                String[] strArray2 = qicaiId.split(",");
                qicaiIdList= Arrays.asList(strArray2);
                System.out.println(qicaiIdList);
                for (int i = 0; i <qicaiIdList.size() ; i++) {
                    Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
                    sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
                }
                standarRecipeVo.setSportsMustEquipmentList(sportsMustEquipmentList);
                //疲劳度等级 单选
                Integer pilaoId= standardRecipe1.getSportsFatigue();
                FatigueDegree fatigueDegree=new FatigueDegree();
                fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
                standarRecipeVo.setSportsFatigue(fatigueDegree.getName());

                //禁忌疾病id 多选
                String jibingId = standardRecipe1.getTabooDiseaseId();
                List<String> jibingIdList =new ArrayList<>();
                String[] strArray3 = jibingId.split(",");
                jibingIdList= Arrays.asList(strArray3);
                System.out.println(jibingIdList);
                for (int i = 0; i <jibingIdList.size() ; i++) {
                    Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(jibingId2);
                    tabooDiseaseList.add(disease.getDiseaseName());
                }
                standarRecipeVo.setTabooDiseaseList(tabooDiseaseList);
                standarRecipeVo.setMethodsIntroduce(standardRecipe1.getMethodsIntroduce());
                standarRecipeVo.setReviewCycle(standardRecipe1.getReviewCycle());
                standarRecipeVo.setVideoUrl(standardRecipe1.getVideoUrl());
                standarRecipeVo.setNotice(standardRecipe1.getNotice());
                standarRecipeVoList.add(standarRecipeVo);
            }
        }

//        standarRecipeVoList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(standarRecipeVoList));
    }

    @Override
    public JSONArray selectByTypeId(StandardRecipe standardRecipe) {
        List<StandardRecipe> standardRecipeList =new ArrayList<>();
        int selectTypeId=standardRecipe.getSportsTypeId();
        List<StandarRecipeVo> standarRecipeVoList =new ArrayList<>();
        if (selectTypeId == 0){
            standardRecipeList= standardRecipeMapper.selectAll();


            for (StandardRecipe standardRecipe1:standardRecipeList) {
                StandarRecipeVo standarRecipeVo=new StandarRecipeVo();
                List<String> sportsEffectList = new ArrayList<>();
                List<String> sportsMustEquipmentList = new ArrayList<>();
                List<String> tabooDiseaseList = new ArrayList<>();
                standarRecipeVo.setId(standardRecipe1.getId());
                standarRecipeVo.setName(standardRecipe1.getName());

                //效果id 多选 todo
                String effectId=standardRecipe1.getSportsEffectId();
                List<String> effectIdList = new ArrayList<>();
                String[] strArray1 = effectId.split(",");
                effectIdList= Arrays.asList(strArray1);
                System.out.println(effectIdList);
                for (int j = 0;j < effectIdList.size();j++){
                    Integer effectId2=Integer.valueOf(effectIdList.get(j));
                    SportsEffect sportsEffect = new SportsEffect();
                    sportsEffect=sportsEffectMapper.selectGetName(effectId2);
                    sportsEffectList.add(sportsEffect.getSportsEffect());
                }
                standarRecipeVo.setSportsEffectList(sportsEffectList);

                //类型 id 单选 先实验一下这个
                Integer typeId=standardRecipe1.getSportsTypeId();
                SportTypes sportTypes=new SportTypes();
                sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
                standarRecipeVo.setSportsTypeId(sportTypes.getSportTypesName());
                //强度id 单选 这个没法显示
                standarRecipeVo.setSportsIntensityId(standardRecipe1.getSportsIntensityId());
                standarRecipeVo.setSportsTime(standardRecipe1.getSportsTime());
                standarRecipeVo.setSportsFrequency(standardRecipe1.getSportsFrequency());
                //器材id 多选
                String qicaiId=standardRecipe1.getSportsMustEquipmentId();
                List<String> qicaiIdList = new ArrayList<>();
                String[] strArray2 = qicaiId.split(",");
                qicaiIdList= Arrays.asList(strArray2);
                System.out.println(qicaiIdList);
                for (int i = 0; i <qicaiIdList.size() ; i++) {
                    Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
                    sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
                }
                standarRecipeVo.setSportsMustEquipmentList(sportsMustEquipmentList);
                //疲劳度等级 单选
                Integer pilaoId= standardRecipe1.getSportsFatigue();
                FatigueDegree fatigueDegree=new FatigueDegree();
                fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
                standarRecipeVo.setSportsFatigue(fatigueDegree.getName());
                //禁忌疾病id 多选
                String jibingId = standardRecipe1.getTabooDiseaseId();
                List<String> jibingIdList =new ArrayList<>();
                String[] strArray3 = jibingId.split(",");
                jibingIdList= Arrays.asList(strArray3);
                System.out.println(jibingIdList);
                for (int i = 0; i <jibingIdList.size() ; i++) {
                    Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(jibingId2);
                    tabooDiseaseList.add(disease.getDiseaseName());
                }
                standarRecipeVo.setTabooDiseaseList(tabooDiseaseList);
                standarRecipeVo.setMethodsIntroduce(standardRecipe1.getMethodsIntroduce());
                standarRecipeVo.setReviewCycle(standardRecipe1.getReviewCycle());
                standarRecipeVo.setVideoUrl(standardRecipe1.getVideoUrl());
                standarRecipeVo.setNotice(standardRecipe1.getNotice());
                standarRecipeVoList.add(standarRecipeVo);
            }
        }
        if(selectTypeId!=0){
            standardRecipeList=standardRecipeMapper.selectByTypeID(selectTypeId);
            for (StandardRecipe standardRecipe1:standardRecipeList) {
                StandarRecipeVo standarRecipeVo=new StandarRecipeVo();
                List<String> sportsEffectList = new ArrayList<>();
                List<String> sportsMustEquipmentList = new ArrayList<>();
                List<String> tabooDiseaseList = new ArrayList<>();
                standarRecipeVo.setId(standardRecipe1.getId());
                standarRecipeVo.setName(standardRecipe1.getName());

                //效果id 多选 todo
                String effectId=standardRecipe1.getSportsEffectId();
                List<String> effectIdList = new ArrayList<>();
                String[] strArray1 = effectId.split(",");
                effectIdList= Arrays.asList(strArray1);
                System.out.println(effectIdList);
                for (int j = 0;j < effectIdList.size();j++){
                    Integer effectId2=Integer.valueOf(effectIdList.get(j));
                    SportsEffect sportsEffect = new SportsEffect();
                    sportsEffect=sportsEffectMapper.selectGetName(effectId2);
                    sportsEffectList.add(sportsEffect.getSportsEffect());
                }
                standarRecipeVo.setSportsEffectList(sportsEffectList);

                //类型 id 单选 先实验一下这个
                Integer typeId=standardRecipe1.getSportsTypeId();
                SportTypes sportTypes=new SportTypes();
                sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
                standarRecipeVo.setSportsTypeId(sportTypes.getSportTypesName());
                //强度id 单选 这个没法显示
                standarRecipeVo.setSportsIntensityId(standardRecipe1.getSportsIntensityId());
                standarRecipeVo.setSportsTime(standardRecipe1.getSportsTime());
                standarRecipeVo.setSportsFrequency(standardRecipe1.getSportsFrequency());
                //器材id 多选
                String qicaiId=standardRecipe1.getSportsMustEquipmentId();
                List<String> qicaiIdList = new ArrayList<>();
                String[] strArray2 = qicaiId.split(",");
                qicaiIdList= Arrays.asList(strArray2);
                System.out.println(qicaiIdList);
                for (int i = 0; i <qicaiIdList.size() ; i++) {
                    Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
                    sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
                }
                standarRecipeVo.setSportsMustEquipmentList(sportsMustEquipmentList);
                //疲劳度等级 单选
                Integer pilaoId= standardRecipe1.getSportsFatigue();
                FatigueDegree fatigueDegree=new FatigueDegree();
                fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
                standarRecipeVo.setSportsFatigue(fatigueDegree.getName());
                //禁忌疾病id 多选
                String jibingId = standardRecipe1.getTabooDiseaseId();
                List<String> jibingIdList =new ArrayList<>();
                String[] strArray3 = jibingId.split(",");
                jibingIdList= Arrays.asList(strArray3);
                System.out.println(jibingIdList);
                for (int i = 0; i <jibingIdList.size() ; i++) {
                    Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(jibingId2);
                    tabooDiseaseList.add(disease.getDiseaseName());
                }
                standarRecipeVo.setTabooDiseaseList(tabooDiseaseList);
                standarRecipeVo.setMethodsIntroduce(standardRecipe1.getMethodsIntroduce());
                standarRecipeVo.setReviewCycle(standardRecipe1.getReviewCycle());
                standarRecipeVo.setVideoUrl(standardRecipe1.getVideoUrl());
                standarRecipeVo.setNotice(standardRecipe1.getNotice());
                standarRecipeVoList.add(standarRecipeVo);
            }
        }
        return JSONArray.parseArray(JSON.toJSONString(standarRecipeVoList));
    }

    @Override
    public JSONArray selectByEffectId(StandardRecipe standardRecipe) {
        List<StandardRecipe> standardRecipeList =new ArrayList<>();
        String effectId1=standardRecipe.getSportsEffectId();
        List<StandarRecipeVo> standarRecipeVoList =new ArrayList<>();
        if (effectId1 == null){
            standardRecipeList= standardRecipeMapper.selectAll();


            for (StandardRecipe standardRecipe1:standardRecipeList) {
                StandarRecipeVo standarRecipeVo=new StandarRecipeVo();
                List<String> sportsEffectList = new ArrayList<>();
                List<String> sportsMustEquipmentList = new ArrayList<>();
                List<String> tabooDiseaseList = new ArrayList<>();
                standarRecipeVo.setId(standardRecipe1.getId());
                standarRecipeVo.setName(standardRecipe1.getName());

                //效果id 多选 todo
                String effectId=standardRecipe1.getSportsEffectId();
                List<String> effectIdList = new ArrayList<>();
                String[] strArray1 = effectId.split(",");
                effectIdList= Arrays.asList(strArray1);
                System.out.println(effectIdList);
                for (int j = 0;j < effectIdList.size();j++){
                    Integer effectId2=Integer.valueOf(effectIdList.get(j));
                    SportsEffect sportsEffect = new SportsEffect();
                    sportsEffect=sportsEffectMapper.selectGetName(effectId2);
                    sportsEffectList.add(sportsEffect.getSportsEffect());
                }
                standarRecipeVo.setSportsEffectList(sportsEffectList);

                //类型 id 单选 先实验一下这个
                Integer typeId=standardRecipe1.getSportsTypeId();
                SportTypes sportTypes=new SportTypes();
                sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
                standarRecipeVo.setSportsTypeId(sportTypes.getSportTypesName());
                //强度id 单选 这个没法显示
                standarRecipeVo.setSportsIntensityId(standardRecipe1.getSportsIntensityId());
                standarRecipeVo.setSportsTime(standardRecipe1.getSportsTime());
                standarRecipeVo.setSportsFrequency(standardRecipe1.getSportsFrequency());
                //器材id 多选
                String qicaiId=standardRecipe1.getSportsMustEquipmentId();
                List<String> qicaiIdList = new ArrayList<>();
                String[] strArray2 = qicaiId.split(",");
                qicaiIdList= Arrays.asList(strArray2);
                System.out.println(qicaiIdList);
                for (int i = 0; i <qicaiIdList.size() ; i++) {
                    Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
                    sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
                }
                standarRecipeVo.setSportsMustEquipmentList(sportsMustEquipmentList);
                //疲劳度等级 单选
                Integer pilaoId= standardRecipe1.getSportsFatigue();
                FatigueDegree fatigueDegree=new FatigueDegree();
                fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
                standarRecipeVo.setSportsFatigue(fatigueDegree.getName());
                //禁忌疾病id 多选
                String jibingId = standardRecipe1.getTabooDiseaseId();
                List<String> jibingIdList =new ArrayList<>();
                String[] strArray3 = jibingId.split(",");
                jibingIdList= Arrays.asList(strArray3);
                System.out.println(jibingIdList);
                for (int i = 0; i <jibingIdList.size() ; i++) {
                    Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(jibingId2);
                    tabooDiseaseList.add(disease.getDiseaseName());
                }
                standarRecipeVo.setTabooDiseaseList(tabooDiseaseList);
                standarRecipeVo.setMethodsIntroduce(standardRecipe1.getMethodsIntroduce());
                standarRecipeVo.setReviewCycle(standardRecipe1.getReviewCycle());
                standarRecipeVo.setVideoUrl(standardRecipe1.getVideoUrl());
                standarRecipeVo.setNotice(standardRecipe1.getNotice());
                standarRecipeVoList.add(standarRecipeVo);
            }
        }
        if(effectId1!=null){
            standardRecipeList=standardRecipeMapper.selectByEffectName(effectId1);
            for (StandardRecipe standardRecipe1:standardRecipeList) {
                StandarRecipeVo standarRecipeVo=new StandarRecipeVo();
                List<String> sportsEffectList = new ArrayList<>();
                List<String> sportsMustEquipmentList = new ArrayList<>();
                List<String> tabooDiseaseList = new ArrayList<>();
                standarRecipeVo.setId(standardRecipe1.getId());
                standarRecipeVo.setName(standardRecipe1.getName());

                //效果id 多选 todo
                String effectId=standardRecipe1.getSportsEffectId();
                List<String> effectIdList = new ArrayList<>();
                String[] strArray1 = effectId.split(",");
                effectIdList= Arrays.asList(strArray1);
                System.out.println(effectIdList);
                for (int j = 0;j < effectIdList.size();j++){
                    Integer effectId2=Integer.valueOf(effectIdList.get(j));
                    SportsEffect sportsEffect = new SportsEffect();
                    sportsEffect=sportsEffectMapper.selectGetName(effectId2);
                    sportsEffectList.add(sportsEffect.getSportsEffect());
                }
                standarRecipeVo.setSportsEffectList(sportsEffectList);

                //类型 id 单选 先实验一下这个
                Integer typeId=standardRecipe1.getSportsTypeId();
                SportTypes sportTypes=new SportTypes();
                sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
                standarRecipeVo.setSportsTypeId(sportTypes.getSportTypesName());
                //强度id 单选 这个没法显示
                standarRecipeVo.setSportsIntensityId(standardRecipe1.getSportsIntensityId());
                standarRecipeVo.setSportsTime(standardRecipe1.getSportsTime());
                standarRecipeVo.setSportsFrequency(standardRecipe1.getSportsFrequency());
                //器材id 多选
                String qicaiId=standardRecipe1.getSportsMustEquipmentId();
                List<String> qicaiIdList = new ArrayList<>();
                String[] strArray2 = qicaiId.split(",");
                qicaiIdList= Arrays.asList(strArray2);
                System.out.println(qicaiIdList);
                for (int i = 0; i <qicaiIdList.size() ; i++) {
                    Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
                    SportEquipment sportEquipment = new SportEquipment();
                    sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
                    sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
                }
                standarRecipeVo.setSportsMustEquipmentList(sportsMustEquipmentList);
                //疲劳度等级 单选
                Integer pilaoId= standardRecipe1.getSportsFatigue();
                FatigueDegree fatigueDegree=new FatigueDegree();
                fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
                standarRecipeVo.setSportsFatigue(fatigueDegree.getName());
                //禁忌疾病id 多选
                String jibingId = standardRecipe1.getTabooDiseaseId();
                List<String> jibingIdList =new ArrayList<>();
                String[] strArray3 = jibingId.split(",");
                jibingIdList= Arrays.asList(strArray3);
                System.out.println(jibingIdList);
                for (int i = 0; i <jibingIdList.size() ; i++) {
                    Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
                    Disease disease = new Disease();
                    disease=diseaseMapper.selectGetName(jibingId2);
                    tabooDiseaseList.add(disease.getDiseaseName());
                }
                standarRecipeVo.setTabooDiseaseList(tabooDiseaseList);
                standarRecipeVo.setMethodsIntroduce(standardRecipe1.getMethodsIntroduce());
                standarRecipeVo.setReviewCycle(standardRecipe1.getReviewCycle());
                standarRecipeVo.setVideoUrl(standardRecipe1.getVideoUrl());
                standarRecipeVo.setNotice(standardRecipe1.getNotice());
                standarRecipeVoList.add(standarRecipeVo);
            }
        }
        return JSONArray.parseArray(JSON.toJSONString(standarRecipeVoList));
    }

    //删除
    @Override
    public void deleteSR1(StandardRecipe standardRecipe) {
        Integer chufangId=standardRecipe.getId();
        standardRecipe=standardRecipeMapper.selectByPrimaryKey(chufangId);
        Integer qiangduId=standardRecipe.getSportsIntensityId();
        sportsIntensityStandardMapper.deleteByPrimaryKey(qiangduId);
        standardRecipeMapper.deleteByPrimaryKey(chufangId);
    }

    //修改 通过id查询出来 然后装配到vo 返回volist 然后进行修改 update数据库

    @Override
    public JSONArray selectSRyId(StandarRecipeVo2 standarRecipeVo2, StandardRecipe standardRecipe) {
//        List<StandardRecipe> standardRecipeList =new ArrayList<>();
//        StandardRecipe standardRecipe=new StandardRecipe();
        List<StandarRecipeVo2> standarRecipeVo2List =new ArrayList<>();
        Integer id=standardRecipe.getId();
        standardRecipe= standardRecipeMapper.selectByPrimaryKey(id);

//        StandarRecipeVo2 standarRecipeVo2=new StandarRecipeVo2();

        List<String> sportsEffectList = new ArrayList<>();
        List<String> sportsMustEquipmentList = new ArrayList<>();
        List<String> tabooDiseaseList = new ArrayList<>();
//        standarRecipeVo2.setId(standardRecipe.getId());
        standarRecipeVo2.setName(standardRecipe.getName());

        //效果id 多选 todo
        String effectId=standardRecipe.getSportsEffectId();
        List<String> effectIdList = new ArrayList<>();
        String[] strArray1 = effectId.split(",");
        effectIdList= Arrays.asList(strArray1);
        System.out.println(effectIdList);
        for (int j = 0;j < effectIdList.size();j++){
            Integer effectId2=Integer.valueOf(effectIdList.get(j));
            SportsEffect sportsEffect = new SportsEffect();
            sportsEffect=sportsEffectMapper.selectGetName(effectId2);
            sportsEffectList.add(sportsEffect.getSportsEffect());
        }
        standarRecipeVo2.setSportsEffectList(sportsEffectList);

        //类型 id 单选 先实验一下这个
        Integer typeId=standardRecipe.getSportsTypeId();
        SportTypes sportTypes=new SportTypes();
        sportTypes=sportTypesMapper.selectByPrimaryKey(typeId);
        standarRecipeVo2.setSportsTypeId(sportTypes.getSportTypesName());

        //强度id 单选
        Integer qiangduId=standardRecipe.getSportsIntensityId();
        SportsIntensityStandard sportsIntensityStandard=new SportsIntensityStandard();
        sportsIntensityStandard=sportsIntensityStandardMapper.selectByPrimaryKey(qiangduId);
        standarRecipeVo2.setIntensityType(sportsIntensityStandard.getIntensityType());
            standarRecipeVo2.setSportsIntensityId(sportsIntensityStandard.getId());
            standarRecipeVo2.setHeartRateMin(sportsIntensityStandard.getHeartRateMin());
            standarRecipeVo2.setHeartRateMax(sportsIntensityStandard.getHeartRateMax());
            standarRecipeVo2.setFuzhongRange(sportsIntensityStandard.getFuzhongRange());
            standarRecipeVo2.setNumber(sportsIntensityStandard.getNumber());
            standarRecipeVo2.setGroupNum(sportsIntensityStandard.getGroupNum());

//        standarRecipeVo.setSportsIntensityId(standardRecipe.getSportsIntensityId());


        standarRecipeVo2.setSportsTime(standardRecipe.getSportsTime());
        standarRecipeVo2.setSportsFrequency(standardRecipe.getSportsFrequency());
        //器材id 多选
        String qicaiId=standardRecipe.getSportsMustEquipmentId();
        List<String> qicaiIdList = new ArrayList<>();
        String[] strArray2 = qicaiId.split(",");
        qicaiIdList= Arrays.asList(strArray2);
        System.out.println(qicaiIdList);
        for (int i = 0; i <qicaiIdList.size() ; i++) {
            Integer qicaiId2=Integer.valueOf(qicaiIdList.get(i));
            SportEquipment sportEquipment = new SportEquipment();
            sportEquipment=sportEquipmentMapper.selectGetName(qicaiId2);
            sportsMustEquipmentList.add(sportEquipment.getEquipmentName());
        }
        standarRecipeVo2.setSportsMustEquipmentList(sportsMustEquipmentList);
        //疲劳度等级 单选
        Integer pilaoId= standardRecipe.getSportsFatigue();
        FatigueDegree fatigueDegree=new FatigueDegree();
        fatigueDegree=fatigueDegreeMapper.selectByPrimaryKey(pilaoId);
        standarRecipeVo2.setSportsFatigue(fatigueDegree.getName());
        //禁忌疾病id 多选
        String jibingId = standardRecipe.getTabooDiseaseId();
        List<String> jibingIdList =new ArrayList<>();
        String[] strArray3 = jibingId.split(",");
        jibingIdList= Arrays.asList(strArray3);
        System.out.println(jibingIdList);
        for (int i = 0; i <jibingIdList.size() ; i++) {
            Integer jibingId2=Integer.valueOf(jibingIdList.get(i));
            Disease disease = new Disease();
            disease=diseaseMapper.selectGetName(jibingId2);
            tabooDiseaseList.add(disease.getDiseaseName());
        }
        standarRecipeVo2.setTabooDiseaseList(tabooDiseaseList);
        standarRecipeVo2.setMethodsIntroduce(standardRecipe.getMethodsIntroduce());
        standarRecipeVo2.setReviewCycle(standardRecipe.getReviewCycle());
        standarRecipeVo2.setVideoUrl(standardRecipe.getVideoUrl());
        standarRecipeVo2.setNotice(standardRecipe.getNotice());
        standarRecipeVo2List.add(standarRecipeVo2);
        return JSONArray.parseArray(JSON.toJSONString(standarRecipeVo2List));
    }


    @Override
    public ServerResponse<StandardRecipe> updateInformation(StandardRecipe standardRecipe,SportsIntensityStandard sportsIntensityStandard) {
        StandardRecipe updateStandardRecipe=new StandardRecipe();
        updateStandardRecipe.setId(standardRecipe.getId());
        updateStandardRecipe.setName(standardRecipe.getName());
        updateStandardRecipe.setSportsEffectId(standardRecipe.getSportsEffectId());
        updateStandardRecipe.setSportsTypeId(standardRecipe.getSportsTypeId());
        updateStandardRecipe.setSportsIntensityId(standardRecipe.getSportsIntensityId());
//        sportsIntensityStandard=sportsIntensityStandardMapper.selectByPrimaryKey(qiangduId);

        SportsIntensityStandard sportsIntensityStandard1 =new SportsIntensityStandard();
        sportsIntensityStandard1.setId(sportsIntensityStandard.getId());
        sportsIntensityStandard1.setIntensityType(sportsIntensityStandard.getIntensityType());
        sportsIntensityStandard1.setHeartRateMin(sportsIntensityStandard.getHeartRateMin());
        sportsIntensityStandard1.setHeartRateMax(sportsIntensityStandard.getHeartRateMax());
        sportsIntensityStandard1.setFuzhongRange(sportsIntensityStandard.getFuzhongRange());
        sportsIntensityStandard1.setNumber(sportsIntensityStandard.getNumber());
        sportsIntensityStandard1.setGroupNum(sportsIntensityStandard.getGroupNum());
        //更新强度信息
        sportsIntensityStandardMapper.updateByPrimaryKeySelective(sportsIntensityStandard1);

        updateStandardRecipe.setSportsTime(standardRecipe.getSportsTime());
        updateStandardRecipe.setSportsFrequency(standardRecipe.getSportsFrequency());
        updateStandardRecipe.setSportsMustEquipmentId(standardRecipe.getSportsMustEquipmentId());
        updateStandardRecipe.setSportsFatigue(standardRecipe.getSportsFatigue());
        updateStandardRecipe.setTabooDiseaseId(standardRecipe.getTabooDiseaseId());
        updateStandardRecipe.setMethodsIntroduce(standardRecipe.getMethodsIntroduce());
        updateStandardRecipe.setReviewCycle(standardRecipe.getReviewCycle());
        updateStandardRecipe.setVideoUrl(standardRecipe.getVideoUrl());
        updateStandardRecipe.setNotice(standardRecipe.getNotice());
        int updateCount=standardRecipeMapper.updateByPrimaryKeySelective(standardRecipe);
        if (updateCount>0){
            return ServerResponse.createBySuccess("更新成功",standardRecipe);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }


    //效果

    @Override
    public JSONArray goalList() {
        List<SportsEffect> sportsEffectList=new ArrayList<>();
        sportsEffectList= sportsEffectMapper.selectAll();
        sportsEffectList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(sportsEffectList));
    }

    //类型 name

    @Override
    public JSONArray interestList() {
        List<SportTypes> sportTypesList=new ArrayList<>();
        sportTypesList=sportTypesMapper.selectAll();
        sportTypesList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(sportTypesList));
    }
    //强度


//器材 多选 name

    @Override
    public JSONArray equipmentiList() {
        List<SportEquipment> sportEquipmentList=new ArrayList<>();
        sportEquipmentList=sportEquipmentMapper.selectAll();
        sportEquipmentList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(sportEquipmentList));
    }
//疲劳度等级 name

    @Override
    public JSONArray fatigueList() {
        List<FatigueDegree> fatigueDegreeList=new ArrayList<>();
        fatigueDegreeList=fatigueDegreeMapper.selectName();
        fatigueDegreeList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(fatigueDegreeList));
    }
//禁忌疾病 name

    @Override
    public JSONArray diseaseList() {
        List<Disease> diseaseList=new ArrayList<>();
        diseaseList=diseaseMapper.selectName();
        diseaseList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(diseaseList));
    }


}
