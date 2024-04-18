package com.sports.controller;


import com.sports.common.ServerResponse;
import com.sports.entity.FitnessVenues;
import com.sports.service.OssUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/demo/common")
public class OssUploadController {
    private static final Logger logger = LoggerFactory.getLogger(OssUploadController.class);

    @Autowired
    private OssUploadService ossUploadService;

    @RequestMapping(value = "/upload/oss", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ServerResponse uploadOSS(@RequestParam(value = "files") MultipartFile[] files, FitnessVenues fitnessVenues) throws Exception {
//        ServerResponse apiResult = ossUploadService.uploadOSS(files, fitnessVenues);
        return   ossUploadService.uploadOSS(files, fitnessVenues);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        return new ResponseEntity<>(apiResult, headers, HttpStatus.CREATED);
    }
}
