package com.sports.service.impl;

import com.sports.common.OSSConfig;
import com.sports.common.ServerResponse;
import com.sports.dao.FitnessVenuesMapper;
import com.sports.entity.FitnessVenues;
import com.sports.exception.FitnessVenuesException;
import com.sports.service.FitnessVenuesService;
import com.sports.util.OSSBootUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service("FitnessVenuesService")
public class FitnessVenuesServiceImpl implements FitnessVenuesService {

    @Autowired
    private FitnessVenuesMapper fitnessVenuesMapper;

    @Autowired
    private OSSConfig ossConfig;

    /**
     * 运动场馆的添加
     * @param fitnessVenues
     * @return
     */
    @Override
    @Transactional(rollbackFor = FitnessVenuesException.class)
    public ServerResponse<String> addFitnessVenues(MultipartFile[] files, FitnessVenues fitnessVenues) {
        // 校验数据库中是否存在该场馆，name不允许重复
        int resultCount = fitnessVenuesMapper.checkFitnessName(fitnessVenues.getName());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("该场馆已存在");
        }else {

            if(files.length<0){
                return  ServerResponse.createByErrorMessage("文件数量不足");
            }
            //上传文件
            for(int i = 0 ; i < files.length ; i++){
                String ossFileUrlBoot = null;
                ossFileUrlBoot = OSSBootUtil.upload(ossConfig, files[i], "sport-image-manage/FitnessVenuesImage");
                switch (i){
                    case 0:
                        fitnessVenues.setImage1(ossFileUrlBoot);
                        break;
                    case 1:
                        fitnessVenues.setImage2(ossFileUrlBoot);
                        break;
                    case 2:
                        fitnessVenues.setImage3(ossFileUrlBoot);
                        break;
                    case 3:
                        fitnessVenues.setImage4(ossFileUrlBoot);
                        break;
                    case 4:
                        fitnessVenues.setImage5(ossFileUrlBoot);
                        break;
                    case 5:
                        fitnessVenues.setImage6(ossFileUrlBoot);
                        break;
                    case 6 :
                        fitnessVenues.setImage7(ossFileUrlBoot);
                        break;
                    case 7:
                        fitnessVenues.setImage8(ossFileUrlBoot);
                        break;
                    case 8:
                        fitnessVenues.setImage9(ossFileUrlBoot);
                        break;
                }
            }
            // 插入到数据中
            resultCount = fitnessVenuesMapper.insertSelective(fitnessVenues);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("添加失败");
            }else {
                return ServerResponse.createBySuccessMessage("添加成功");
            }
        }

    }

    /**
     * 场馆的查询 查询出全部 按照name查询
     * @param fitnessVenues
     * @return
     */
    @Override
    @Transactional(rollbackFor = FitnessVenuesException.class)
    public ServerResponse selectFitnessVenues(FitnessVenues fitnessVenues) {
        List<FitnessVenues> fitnessVenuesList = new ArrayList<>();
        //获取场馆名称
        String fitnessName = fitnessVenues.getName();
        if (fitnessName == null){
            //如果不传入name 查询出全部
            fitnessVenuesList = fitnessVenuesMapper.selectAll();
        }else{
            //传入name 按照name进行查询
            fitnessVenuesList = fitnessVenuesMapper.selectByName(fitnessName);
        }

        return ServerResponse.SUCCESS(fitnessVenuesList);

    }

    /**
     * 根据type查询 场馆或者公共场地
     * @param fitnessVenues
     * @return
     */

    @Override
    @Transactional(rollbackFor = FitnessVenuesException.class)
    public ServerResponse selectFitnessVenuesByType(FitnessVenues fitnessVenues) {
        List<FitnessVenues> fitnessVenuesList = new ArrayList<>();
        //获取type
        String type = fitnessVenues.getType();
        if(type == null){
            //如果不传入type 查询出全部
            fitnessVenuesList = fitnessVenuesMapper.selectAll();
        }else {
            //传入type 按照type进行查询
            fitnessVenuesList = fitnessVenuesMapper.selectByType(type);
        }
        return  ServerResponse.SUCCESS(fitnessVenuesList);

    }


    /**
     * 根据typeValue查询
     * @param fitnessVenues
     * @return
     */
    @Override
    @Transactional(rollbackFor = FitnessVenuesException.class)
    public ServerResponse selectFitnessVenuesByTypeValue(FitnessVenues fitnessVenues) {
        List<FitnessVenues> fitnessVenuesList = new ArrayList<>();
        //获取typeValue
        String typeValue = fitnessVenues.getTypeValue();
        if (typeValue == null){
            //如果不传入typeValue 查询出全部
            fitnessVenuesList = fitnessVenuesMapper.selectAll();
        }else {
            //传入typeValue 按照typeValue查询
            fitnessVenuesList = fitnessVenuesMapper.selectByTypeValue(typeValue);
        }
        return ServerResponse.SUCCESS(fitnessVenuesList);
    }

    /**
     * 场馆删除
     * @param fitnessVenues
     * @return
     */
    @Override
    @Transactional(rollbackFor = FitnessVenuesException.class)
    public String deleteFitnessVenues(FitnessVenues fitnessVenues) {
        Integer fitnessVenuesId = fitnessVenues.getId();
        fitnessVenuesMapper.deleteByPrimaryKey(fitnessVenuesId);
        return "删除成功";
    }

    @Override
    @Transactional(rollbackFor = FitnessVenuesException.class)
    public ServerResponse getImageUrl(FitnessVenues fitnessVenues) {
//        List<FitnessVenues> fitnessVenuesList = new ArrayList<>();
        //获取id
        Integer id = fitnessVenues.getId();
        if (id == null){
            return ServerResponse.createByErrorMessage("未获取到id");
        }
        fitnessVenues = fitnessVenuesMapper.getImageUrl(id);
        return ServerResponse.SUCCESS(fitnessVenues);
    }
}
