package com.sports.service;

import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.entity.Disease;

import java.util.List;

public interface DiseaseService {

    //疾病的添加

    ServerResponse<String> addDisease(Disease disease);

    //疾病的查询

    JSONArray selectDisease(Disease disease);

    //疾病的删除

    String  deleteDisease(Disease disease);

    //疾病的修改

    //先查

    Disease selectById(Disease disease);

    //后改
    ServerResponse<Disease> updateDisease(Disease disease);
}
