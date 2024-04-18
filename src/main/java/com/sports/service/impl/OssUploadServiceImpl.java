package com.sports.service.impl;

import com.sports.common.OSSConfig;
import com.sports.common.ServerResponse;
import com.sports.dao.FitnessVenuesMapper;
import com.sports.entity.FitnessVenues;
import com.sports.service.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("OssUploadService")
public class OssUploadServiceImpl implements OssUploadService {

    @Autowired
    private OSSConfig ossConfig;

    @Autowired
    private FitnessVenuesMapper fitnessVenuesMapper;

    @Override
    public ServerResponse<String> uploadOSS(MultipartFile[] files, FitnessVenues fitnessVenues) throws Exception {

        // 低依赖版本 oss 上传工具
//        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));;
//        String ossFileUrlSingle = null;
//        ossFileUrlSingle = OSSSingleUtil.upload(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(),
//                ossConfig.getAccessKeySecret(), ossConfig.getBucketName(), ossConfig.getUrl(), file.getInputStream(),
//                "sport-image-manage/demo", fileSuffix);


/**
        // todo 赋值后的对象
        // 校验数据库中是否存在该场馆，name不允许重复
        int resultCount = fitnessVenuesMapper.checkFitnessName(fitnessVenues.getName());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("该场馆已存在");
        }else {

            if(files.length!=9){
                return  ServerResponse.createByErrorMessage("文件数量不足");
            }
            // todo 实例化对象
//        FitnessVenues fitnessVenues = new FitnessVenues();
            for(int i = 0 ; i < files.length ; i++){
                // 高依赖版本 oss 上传工具
                String ossFileUrlBoot = null;
                ossFileUrlBoot = OSSBootUtil.upload(ossConfig, files[i], "sport-image-manage/demo");
                switch (i){
                    case 0:
                        // todo
                        fitnessVenues.setImage1(ossFileUrlBoot);
                        break;
                    case 1:
                        // todo
                        fitnessVenues.setImage2(ossFileUrlBoot);
                        break;
                    case 2:
                        // todo
                        fitnessVenues.setImage3(ossFileUrlBoot);
                        break;
                    case 3:
                        // todo
                        fitnessVenues.setImage4(ossFileUrlBoot);
                        break;
                    case 4:
                        // todo
                        fitnessVenues.setImage5(ossFileUrlBoot);
                        break;
                    case 5:
                        // todo
                        fitnessVenues.setImage6(ossFileUrlBoot);
                        break;
                    case 6 :
                        // todo
                        fitnessVenues.setImage7(ossFileUrlBoot);
                        break;
                    case 7:
                        // todo
                        fitnessVenues.setImage8(ossFileUrlBoot);
                        break;
                    case 8:
                        // todo
                        fitnessVenues.setImage9(ossFileUrlBoot);
                        break;

                }

            }

            resultCount = fitnessVenuesMapper.insertSelective(fitnessVenues);
            if (resultCount == 0){
                return ServerResponse.createByErrorMessage("添加失败");
            }else {
                return ServerResponse.createBySuccessMessage("添加成功");
            }
        }

        // todo 存储路对象
*/

        return  null ;

//        return ServerResponse.SUCCESS(resultMap);
    }
}
