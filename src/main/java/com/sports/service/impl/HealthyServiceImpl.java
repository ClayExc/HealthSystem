package com.sports.service.impl;

import com.sports.common.ResponseCode;
import com.sports.common.ServerResponse;
import com.sports.dao.*;
import com.sports.entity.*;
import com.sports.service.HealthyService;
import com.sports.tools.CorporeityAssessment;
import com.sports.tools.Datetools;
import com.sports.tools.RecimpeCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("HealthyService")
public class HealthyServiceImpl implements HealthyService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SubhealthyMapper subhealthyMapper;
    @Autowired
    private SportAbilityMapper sportAbilityMapper;
    @Autowired
    private UserTizhiMapper userTizhiMapper;
    @Autowired
    private SportsEffectMapper sportsEffectMapper;
    @Autowired
    private UserRecipeMapper userRecipeMapper;

//    体测基础信息录入
    @Override
    public ServerResponse<String> tizhi_evaluting(Subhealthy subhealthy, String disease_str, float user_height, float user_weight,int user_optimal_rate1, int user_optimal_rate2, float user_chest, float user_waist, float user_hipline,int uid) {
        Subhealthy subhealthy_d=subhealthyMapper.selectByUser_id(uid);

        if(subhealthy_d==null){
            subhealthy.setUserId(uid);
            int subhealthy_insert=subhealthyMapper.insert(subhealthy);
        }else {
            subhealthy.setUserId(uid);
            subhealthy.setId(subhealthy_d.getId());
            int subhealthy_update=subhealthyMapper.updateByPrimaryKey(subhealthy);
        }
        subhealthy_d=subhealthyMapper.selectByUser_id(uid);
        User user=userMapper.selectByPrimaryKey(uid);
        user.setUserSubhealthy(subhealthy_d.getId());
        user.setUserDisease(disease_str);
        user.setUserHeight(user_height);
        user.setUserWeight(user_weight);
        user.setUserOptimalRate((user_optimal_rate1+user_optimal_rate2)/2);
        user.setUserChest(user_chest);
        user.setUserWaist(user_waist);
        user.setUserHipline(user_hipline);
        int user_update=userMapper.updateByPrimaryKeySelective(user);
        if (user_update==1){
            return ServerResponse.createBySuccess("更新成功");
        }else {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"更新失败");
        }
    }

   //体测运动能力信息录入
    @Override
    public ServerResponse<String> sportability_evaluting(SportAbility sportAbility) {
        SportAbility sportAbility_d= sportAbilityMapper.selectByUser_id(sportAbility.getUserId());
        if (sportAbility_d==null){
            int sportAbility_insert=sportAbilityMapper.insert(sportAbility);
        }else {
            sportAbility.setId(sportAbility_d.getId());
            int sportAbility_update=sportAbilityMapper.updateByPrimaryKeySelective(sportAbility);
        }
        sportAbility_d=sportAbilityMapper.selectByUser_id(sportAbility.getUserId());
        User user=userMapper.selectByPrimaryKey(sportAbility.getUserId());
        user.setUserSportAbility(sportAbility_d.getId());
        int user_update=userMapper.updateByPrimaryKeySelective(user);
        if (user_update==1){
            return ServerResponse.createBySuccess("更新成功");
        }else {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"更新失败");
        }
    }

    // 进行体质评测
    @Override
    public void tizhi_comprehensive(int userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        SportAbility sportAbility=sportAbilityMapper.selectByUser_id(userid);
        Subhealthy subhealthy=subhealthyMapper.selectByUser_id(userid);
        CorporeityAssessment corporeityAssessment=new CorporeityAssessment();

        UserTizhi userTizhi=userTizhiMapper.selectByPrimaryUserid(userid);
        int HeartLungAbility=corporeityAssessment.getHeartLungAbility(180,sportAbility.getTaijieceshi());
        int MuscularEndurance=corporeityAssessment.getMuscularEndurance(user.getUserSex(), Datetools.getAgeByBirth(user.getUserBirthday()),sportAbility.getFuwocheng(),sportAbility.getYangwoqizuo());
        int Physique=corporeityAssessment.getPhysique(user.getUserHeight(),user.getUserWeight(),user.getUserChest());
        int WithinFat=corporeityAssessment.getWithinFat(user.getUserSex(),user.getUserWaist(),user.getUserHipline());
        int SubhealtNum=corporeityAssessment.getSubhealthyNumber(subhealthy);
        String QualityNumber=corporeityAssessment.getQualityNumber(user.getUserHeight(),user.getUserWeight());
        if (userTizhi==null){
            userTizhi.setUserid(user.getUserId());
            userTizhi.setHeartLungAbility(HeartLungAbility);
            userTizhi.setMuscularEndurance(MuscularEndurance);
            userTizhi.setPhysique(Physique);
            userTizhi.setWithinFat(WithinFat);
            userTizhi.setSubhealtNum(SubhealtNum);
            userTizhi.setQualityNumber(QualityNumber);
            userTizhiMapper.insert(userTizhi);
        }else {
            userTizhi.setHeartLungAbility(HeartLungAbility);
            userTizhi.setMuscularEndurance(MuscularEndurance);
            userTizhi.setPhysique(Physique);
            userTizhi.setWithinFat(WithinFat);
            userTizhi.setSubhealtNum(SubhealtNum);
            userTizhi.setQualityNumber(QualityNumber);
            userTizhiMapper.updateByPrimaryKeySelective(userTizhi);
        }
    }

//    运动处方参数范围的确定
    public void recipeCoreParameter(int userid){
        User user=userMapper.selectByPrimaryKey(userid);
        UserTizhi userTizhi=userTizhiMapper.selectByPrimaryUserid(userid);
        String userobjects=sportsEffectMapper.selectByPrimaryKey(user.getUserSportObjective1()).getSportsEffect();
        RecimpeCore responseCode=new RecimpeCore(userobjects,userTizhi);
        String sportway=responseCode.getSportway();
        int sportStrength=responseCode.getSportStrength();
        int minStrengthType=userTizhi.getHeartLungAbility();
        int sportTime=responseCode.getSportTime();
        int minTimeType=userTizhi.getHeartLungAbility();
        int sportFrequency=responseCode.getSportFrequency();
        String strengthShowType=responseCode.getStrengthShowType();
        UserRecipe userRecipe=userRecipeMapper.selectByUserId(user.getUserId());
        if(userRecipe==null){
            userRecipe.setUserid(user.getUserId());
            userRecipe.setSportWay(sportway);
            userRecipe.setSportStrength(sportStrength);
            userRecipe.setMinStrengthType(minStrengthType);
            userRecipe.setSportTime(sportTime);
            userRecipe.setMinTimeType(minTimeType);
            userRecipe.setSportFrequency(sportFrequency);
            userRecipe.setStrengthShowType(strengthShowType);
            userRecipeMapper.insert(userRecipe);
        }else {
            userRecipe.setSportWay(sportway);
            userRecipe.setSportStrength(sportStrength);
            userRecipe.setMinStrengthType(minStrengthType);
            userRecipe.setSportTime(sportTime);
            userRecipe.setMinTimeType(minTimeType);
            userRecipe.setSportFrequency(sportFrequency);
            userRecipe.setStrengthShowType(strengthShowType);
            userRecipeMapper.updateByPrimaryKeySelective(userRecipe);
        }
    }

/**
 * 标准处方筛选器
 * userid:用户id
 * kind：采用第几种筛选方法
  */

    public ArrayList sizer(int userid,int kind){
        List recipeListAll=new ArrayList<StandardRecipe>();
        List recipeList=new ArrayList<StandardRecipe>();
        if(kind==1){

        }



        return null;
    }




}
