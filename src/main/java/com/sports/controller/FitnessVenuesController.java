package com.sports.controller;


import com.sports.common.ServerResponse;
import com.sports.entity.FitnessVenues;
import com.sports.service.impl.FitnessVenuesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fitnessvenues/")
@CrossOrigin(origins="*",maxAge=3600)
public class FitnessVenuesController {

    @Autowired
    FitnessVenuesServiceImpl fitnessVenuesService;

    /**
     *健身场馆的添加
     * @param files
     * @param fitnessVenues
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addFitnessVenues.do", method = RequestMethod.POST)
    public ServerResponse addFitnessVenues(@RequestParam(value = "files") MultipartFile[] files, FitnessVenues fitnessVenues)throws Exception{
        return   fitnessVenuesService.addFitnessVenues(files, fitnessVenues);

    }

    /**
     * 健身场馆的查询 根据name
     * @param fitnessVenues
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectFitnessVenues.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectFitnessVenues(FitnessVenues fitnessVenues)throws Exception{
       return fitnessVenuesService.selectFitnessVenues(fitnessVenues);

    }

    /**
     * 健身场馆的查询 根据type
     * @param fitnessVenues
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectFitnessVenuesByType.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectFitnessVenuesByType(FitnessVenues fitnessVenues)throws Exception{
        return fitnessVenuesService.selectFitnessVenuesByType(fitnessVenues);

    }

    /**
     * 健身场馆的查询 根据type value
     * @param fitnessVenues
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectFitnessVenuesByTypeValue.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectFitnessVenuesByTypeValue(FitnessVenues fitnessVenues)throws Exception{
        return fitnessVenuesService.selectFitnessVenuesByTypeValue(fitnessVenues);

    }
    /**
     * 场馆的删除
     * @param fitnessVenues
     * @return
     */
    @RequestMapping(value = "deleteFitnessVenues.do", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFitnessVenues(FitnessVenues fitnessVenues){
        return fitnessVenuesService.deleteFitnessVenues(fitnessVenues);

    }

    /**
     * 根据id 获取图片的url
     * @param fitnessVenues
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getImagesUrl.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getImagesUrl(FitnessVenues fitnessVenues)throws Exception{
        return fitnessVenuesService.getImageUrl(fitnessVenues);

    }

}
