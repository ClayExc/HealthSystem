package com.sports.controller;

import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.entity.SportEquipment;
import com.sports.exception.EquipmentException;
import com.sports.service.impl.EquipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/equipment/")
@CrossOrigin(origins="*",maxAge=3600)
public class EquipmentController {

    @Autowired
    EquipmentServiceImpl equipmentService;

    /**
     * 运动器材的添加
     */
    @RequestMapping(value = "addSE.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = EquipmentException.class)
    public ServerResponse addSE(@RequestParam("file") MultipartFile file ,SportEquipment sportEquipment)throws Exception{
        if(!file.isEmpty()) {
            BASE64Encoder encoder = new BASE64Encoder();
            String image = encoder.encode(file.getBytes());
//            SportEquipment sportEquipment = new SportEquipment();
            sportEquipment.setEquipmentName(sportEquipment.getEquipmentName());
            sportEquipment.setEquipmentDescription(sportEquipment.getEquipmentDescription());
            sportEquipment.setEquipmentImage(image);
            return  equipmentService.addSE(sportEquipment);
        }else {
            return null;
        }

    }

    /**
     * 运动器材的查询
     */
    @RequestMapping(value = "selectSE.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = EquipmentException.class)
    @ResponseBody
    public JSONArray selectSE(SportEquipment sportEquipment)throws Exception{
        JSONArray jsonArray=equipmentService.selectSE(sportEquipment);
        return jsonArray;
    }

    /**
     * 运动器材的删除
     */

    @RequestMapping(value = "deleteSE.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = EquipmentException.class)
    @ResponseBody
    public String   deleteSE(SportEquipment sportEquipment){

        return equipmentService.deleteSE(sportEquipment);

    }

    /**
     * 运动器才的更新功能
     */

    @RequestMapping(value = "selectById.do", method = RequestMethod.POST)
    @Transactional(rollbackFor = EquipmentException.class)
    @ResponseBody
    public SportEquipment selectById(SportEquipment sportEquipment)throws Exception{
        SportEquipment sportEquipment1=equipmentService.selectById(sportEquipment);
        return sportEquipment1;
    }

    @RequestMapping(value = "updateSE.do",method = RequestMethod.POST)
    @Transactional(rollbackFor = EquipmentException.class)
    public ServerResponse updateSE( MultipartFile file,HttpSession session, SportEquipment sportEquipment)throws Exception{
        ServerResponse<SportEquipment> response =equipmentService.updateSE(file,sportEquipment);
        return response;
    }

    /**
     * 器材图片的展示
     */
//    @RequestMapping(value = "getImage.do",method = RequestMethod.GET)
//    @Transactional(rollbackFor = EquipmentException.class)
//    @ResponseBody
    @GetMapping("getImage.do")
    public void getImage(@RequestParam("id") Integer id, HttpServletResponse response) throws Exception{
        equipmentService.getImage(id,response);
    }
}
