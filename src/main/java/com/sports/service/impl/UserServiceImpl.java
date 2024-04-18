package com.sports.service.impl;

import com.alibaba.fastjson.JSON;
import com.sports.common.ResponseCode;
import com.sports.common.ServerResponse;
import com.sports.dao.SportEquipmentMapper;
import com.sports.dao.SportTypesMapper;
import com.sports.dao.SportsEffectMapper;
import com.sports.dao.UserMapper;
import com.sports.entity.SportEquipment;
import com.sports.entity.SportTypes;
import com.sports.entity.SportsEffect;
import com.sports.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sports.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

@Service("iUserService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SportsEffectMapper sportsEffectMapper;
    @Autowired
    private SportTypesMapper sportTypesMapper;
    @Autowired
    private SportEquipmentMapper sportEquipmentMapper;
    /**
     * 用户注册
     */
    @Override
    public ServerResponse<String> register(User user) {
        // 校验数据库中是否存在该用户名，用户名不允许重复
        int resultCount = userMapper.checkUsername(user.getUserName());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("该用户名已存在");
        }
        //md5加密 暂时不用
//        user.setUserPassword(MD5Util.MD5EncodeUtf8(user.getUserPassword()));
        user.setUserName(user.getUserName());
        user.setUserAccount(user.getUserAccount());
        user.setUserPassword(user.getUserPassword());
        user.setUserBirthday(user.getUserBirthday());
        user.setUserSex(user.getUserSex());
        //运动目标
        user.setUserSportObjective1(user.getUserSportObjective1());
        //运功兴趣
        user.setUserSportLike(user.getUserSportLike());
        //运动器材
        user.setUserSportEquipmentId(user.getUserSportEquipmentId());
        //用户名密码插入到数据库中
        resultCount=userMapper.insert(user);
        if (resultCount ==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        User updateUser=new User();
        updateUser.setUserId(user.getUserId());
        updateUser.setUserName(user.getUserName());
        updateUser.setUserBirthday(user.getUserBirthday());
        updateUser.setUserSex(user.getUserSex());
        updateUser.setUserAccount(user.getUserAccount());
        updateUser.setUserPassword(user.getUserPassword());
        updateUser.setUserBmi(user.getUserBmi());
        updateUser.setUserDisease(user.getUserDisease());
        updateUser.setUserOptimalRate(user.getUserOptimalRate());
        updateUser.setUserImage(user.getUserImage());
        updateUser.setUserDelcar(user.getUserDelcar());
        updateUser.setUserWeight(user.getUserWeight());
        updateUser.setUserHeight(user.getUserHeight());
        updateUser.setUserWaist(user.getUserWaist());
        updateUser.setUserChest(user.getUserChest());
        updateUser.setUserHipline(user.getUserHipline());
        updateUser.setUserSubhealthy(user.getUserSubhealthy());
        updateUser.setUserSportAbility(user.getUserSportAbility());
        updateUser.setUserSportEquipmentId(user.getUserSportEquipmentId());
        updateUser.setUserSportLike(user.getUserSportLike());
        updateUser.setUserSportObjective1(user.getUserSportObjective1());
        updateUser.setUserSportObjective2(user.getUserSportObjective2());
        updateUser.setUserRecipeNum(user.getUserRecipeNum());
        updateUser.setUserRecipeStatus(user.getUserRecipeStatus());
        // 在设置username之前，应该先校验在数据库中是否已经存在
        int resultCount = userMapper.checkUsername(user.getUserName());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("信息修改失败，该用户名已存在");
        }
        //更新用户信息
        int updateCount=userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount>0){
            return ServerResponse.createBySuccess("更新成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }


    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    @Override
    public ServerResponse<User> login(String userName, String userPassword) {
        //校验是否存在该用户名
        int resultCount = userMapper.checkUsername(userName);
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //将password进行MD5加密 暂时先不用
//        String md5Password = MD5Util.MD5EncodeUtf8(userPassword);
        User user =userMapper.selectLogin(userName,userPassword);
        if (user==null){
            return ServerResponse.createByErrorMessage("密码错误");
//throw new UserException(ResponseCode.ERROR);
        }
        logger.info("用户："+user.getUserName()+"登录成功");
        // 将密码置空
        user.setUserPassword(null);
        // 登录成功后返回学生信息
        logger.info(user.toString());
//        return ServerResponse.createBySuccess("登录成功", user);
        return ServerResponse.createBySuccess(ResponseCode.SUCCESS.getDesc(),user);
    }

    //目标
    @Override
    public JSONArray goalList(){
        List<SportsEffect> sportsEffectList;
        sportsEffectList= sportsEffectMapper.selectAll();
        sportsEffectList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(sportsEffectList));
    }

    //兴趣
    @Override
    public JSONArray interestList() {
        List<SportTypes> sportTypesList;
        sportTypesList=sportTypesMapper.selectAll();
        sportTypesList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(sportTypesList));
    }

    //器材
    @Override
    public JSONArray equipmentiList() {
        List<SportEquipment> sportEquipmentList;
        sportEquipmentList=sportEquipmentMapper.selectAll();
        sportEquipmentList.forEach(System.out::println);
        return JSONArray.parseArray(JSON.toJSONString(sportEquipmentList));
    }

//    @Override
//    public List<User> findUserByAccount(User user) {
//        return null;
//    }
//
//    @Override
//    public User findUserByAccountAndPwd(User user) {
//        return null;
//    }

//    @Override
//    public User findUserByAccountAndPwd(User user) {
//        return userDao.findUserByAccountAndPwd(user);
//    }

}
