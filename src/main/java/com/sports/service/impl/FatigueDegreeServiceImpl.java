package com.sports.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.dao.FatigueDegreeMapper;
import com.sports.dao.StandardRecipeMapper;
import com.sports.entity.FatigueDegree;
import com.sports.entity.StandardRecipe;
import com.sports.service.FatigueDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FatigueDegreeService")
public class FatigueDegreeServiceImpl implements FatigueDegreeService {

    @Autowired
    FatigueDegreeMapper fatigueDegreeMapper;

    @Autowired
    private StandardRecipeMapper standardRecipeMapper;

    @Override
    public ServerResponse<String> addFatigueDegree(FatigueDegree fatigueDegree) {
        // 校验数据库中是否存在该疲劳度等级名，name不允许重复
        int resultCount = fatigueDegreeMapper.checkFDname(fatigueDegree.getName());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("该疲劳度等级名称已存在");
        }
       resultCount= fatigueDegreeMapper.insert(fatigueDegree);
        if (resultCount ==0){
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @Override
    public JSONArray selectFatigueDegree(FatigueDegree fatigueDegree) {
        List<FatigueDegree> fatigueDegreeList = new ArrayList<>();
        String FDname = fatigueDegree.getName();
        if (FDname == null){
            fatigueDegreeList=fatigueDegreeMapper.selectAll();
        }else {
            fatigueDegreeList=fatigueDegreeMapper.selectByName(FDname);
        }
        return JSONArray.parseArray(JSON.toJSONString(fatigueDegreeList));
    }

    @Override
    public String deleteFatigueDegree(FatigueDegree fatigueDegree) {
        Integer id = fatigueDegree.getId();
        List<StandardRecipe> standardRecipeList =new ArrayList<>();
        standardRecipeList = standardRecipeMapper.selectByFatigueDegree(id);
        if (standardRecipeList.size()!=0){
            String massage="此等级已经在处方中使用，不能被删除";
            return massage;
        }else {
            int count = fatigueDegreeMapper.deleteByPrimaryKey(id);
            if(count > 0){
                return "删除成功";
            }else {
                return "删除失败";
            }
        }
    }

    @Override
    public FatigueDegree selectById(FatigueDegree fatigueDegree) {
        Integer id = fatigueDegree.getId();
        fatigueDegree  = fatigueDegreeMapper.selectByPrimaryKey(id);
        return fatigueDegree;
    }

    @Override
    public ServerResponse<FatigueDegree> updateFatigueDegree(FatigueDegree fatigueDegree) {
        FatigueDegree fatigueDegree1= new FatigueDegree();
        fatigueDegree1.setId(fatigueDegree.getId());
        fatigueDegree1.setName(fatigueDegree.getName());
        fatigueDegree1.setFeel(fatigueDegree.getFeel());
        fatigueDegree1.setFace(fatigueDegree.getFace());
        fatigueDegree1.setSweat(fatigueDegree.getSweat());
        fatigueDegree1.setBreathe(fatigueDegree.getBreathe());
        fatigueDegree1.setAction(fatigueDegree.getAction());
        fatigueDegree1.setAttention(fatigueDegree.getAttention());
        int updateCount = fatigueDegreeMapper.updateByPrimaryKeySelective(fatigueDegree);
        if (updateCount>0){
            return ServerResponse.createBySuccess("更新成功",fatigueDegree);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }


}
