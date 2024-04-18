package com.sports.service;

import com.sports.common.ServerResponse;
import com.sports.entity.FitnessVenues;
import org.springframework.web.multipart.MultipartFile;

public interface FitnessVenuesService {

    //场馆的添加

    ServerResponse<String> addFitnessVenues(MultipartFile[] files, FitnessVenues fitnessVenues);

    //查询 根据name

    ServerResponse selectFitnessVenues(FitnessVenues fitnessVenues);

    //查询 根据type

    ServerResponse selectFitnessVenuesByType(FitnessVenues fitnessVenues);

    //查询 根据typeValue

    ServerResponse selectFitnessVenuesByTypeValue(FitnessVenues fitnessVenues);

    //删除

    String deleteFitnessVenues(FitnessVenues fitnessVenues);

    //根据id获取图片链接

    ServerResponse getImageUrl (FitnessVenues fitnessVenues);
}
