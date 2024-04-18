package com.sports.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.dao.DiseaseMapper;
import com.sports.dao.StandardRecipeMapper;
import com.sports.entity.Disease;
import com.sports.entity.StandardRecipe;
import com.sports.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DiseaseService")

public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseMapper diseaseMapper;

    @Autowired
    private StandardRecipeMapper standardRecipeMapper;

    @Override
    public ServerResponse<String> addDisease(Disease disease) {
        // 校验数据库中是否存在该疾病名，name不允许重复
        int resultCount = diseaseMapper.checkDiseaseName(disease.getDiseaseName());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("该疾病已存在");
        }
        resultCount= diseaseMapper.insertSelective(disease);
        if (resultCount ==0){
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @Override
    public JSONArray selectDisease(Disease disease) {
        List<Disease> diseaseList = new ArrayList<>();
        String name = disease.getDiseaseName();
        if (name == null){
            diseaseList = diseaseMapper.selectAll();
        }else {
            diseaseList = diseaseMapper.selectByName(name);
        }
        return JSONArray.parseArray(JSON.toJSONString(diseaseList));
    }

    @Override
    public String deleteDisease(Disease disease) {
        Integer id =disease.getId();
        List<StandardRecipe> standardRecipeList =new ArrayList<>();
        standardRecipeList = standardRecipeMapper.selectByDisease(id);
        if (standardRecipeList.size()!=0){
            String massage="器材已经在处方中使用，不能被删除";
            return massage;
        }else {
            int count = diseaseMapper.deleteByPrimaryKey(id);
            if(count > 0){
                return "删除成功";
            }else {
                return "删除失败";
            }
        }

    }

    @Override
    public Disease selectById(Disease disease) {
        Integer id =disease.getId();
        disease = diseaseMapper.selectByPrimaryKey(id);
        return disease;
    }

    @Override
    public ServerResponse<Disease> updateDisease(Disease disease) {
        Disease diseaseUpdate = new Disease();
        diseaseUpdate.setId(disease.getId());
        diseaseUpdate.setDiseaseName(disease.getDiseaseName());
        diseaseUpdate.setDescription(disease.getDescription());
        int updateCount =diseaseMapper.updateByPrimaryKeySelective(disease);
        if (updateCount>0){
            return ServerResponse.createBySuccess("更新成功",disease);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }


}
