package com.sports.service;

import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.entity.FatigueDegree;

public interface FatigueDegreeService {
    //疾病的添加

    ServerResponse<String> addFatigueDegree(FatigueDegree fatigueDegree);

    //疾病的查询

    JSONArray selectFatigueDegree(FatigueDegree fatigueDegree);

    //疾病的删除

    String  deleteFatigueDegree(FatigueDegree fatigueDegree);

    //疾病的修改

    //先查

    FatigueDegree selectById(FatigueDegree fatigueDegree);

    //后改
    ServerResponse<FatigueDegree> updateFatigueDegree(FatigueDegree fatigueDegree);
}
