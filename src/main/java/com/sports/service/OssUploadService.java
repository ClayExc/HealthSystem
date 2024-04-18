package com.sports.service;

import com.sports.common.ServerResponse;
import com.sports.entity.FitnessVenues;
import org.springframework.web.multipart.MultipartFile;

public interface OssUploadService {
    /**
     * 上传文件至阿里云 oss
     *
     * @param files
     * @param
     * @return
     * @throws Exception
     */
    ServerResponse<String> uploadOSS(MultipartFile[] files,  FitnessVenues fitnessVenues) throws Exception;
}
