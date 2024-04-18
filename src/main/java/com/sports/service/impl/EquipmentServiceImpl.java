package com.sports.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sports.common.ServerResponse;
import com.sports.dao.SportEquipmentMapper;
import com.sports.dao.StandardRecipeMapper;
import com.sports.entity.SportEquipment;
import com.sports.entity.StandardRecipe;
import com.sports.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service("EquipmentService")
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private SportEquipmentMapper sportEquipmentMapper;

    @Autowired
    private StandardRecipeMapper standardRecipeMapper;

    @Override
    public ServerResponse<String> addSE(SportEquipment sportEquipment) {
        // 校验数据库中是否存在该器材名，name不允许重复
        int resultCount = sportEquipmentMapper.checkEquipmentName(sportEquipment.getEquipmentName());
        if (resultCount>0){
            return ServerResponse.createByErrorMessage("该器材已存在");
        }
         resultCount = sportEquipmentMapper.insertSelective(sportEquipment);
        if (resultCount ==0){
            return ServerResponse.createByErrorMessage("添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @Override
    public JSONArray selectSE(SportEquipment sportEquipment) {
        List<SportEquipment> sportEquipmentList = new ArrayList<>();
        String name = sportEquipment.getEquipmentName();
        if (name==null){
            sportEquipmentList=sportEquipmentMapper.selectAll();
        }else {
            sportEquipmentList=sportEquipmentMapper.selectByName(name);



        }
        return JSONArray.parseArray(JSON.toJSONString(sportEquipmentList));
    }

    @Override
    public String deleteSE(SportEquipment sportEquipment) {
        Integer sportsMustEquipmentId =sportEquipment.getId();
        List<StandardRecipe> standardRecipeList =new ArrayList<>();
        standardRecipeList = standardRecipeMapper.selectByEquipment(sportsMustEquipmentId);
        if (standardRecipeList.size()!=0){
            String massage="器材已经在处方中使用，不能被删除";
            return massage;
        }else {
            sportEquipmentMapper.deleteByPrimaryKey(sportsMustEquipmentId);
            return "删除成功";
        }

    }

    @Override
    public SportEquipment selectById(SportEquipment sportEquipment) {

        Integer id =sportEquipment.getId();
        sportEquipment=sportEquipmentMapper.selectByPrimaryKey(id);
        return sportEquipment;
    }

    @Override
    public ServerResponse<SportEquipment> updateSE(MultipartFile file,SportEquipment sportEquipment)throws Exception {

            SportEquipment sportEquipmentUpdate = new SportEquipment();
            sportEquipmentUpdate.setId(sportEquipment.getId());
            sportEquipmentUpdate.setEquipmentName(sportEquipment.getEquipmentName());
            sportEquipmentUpdate.setEquipmentDescription(sportEquipment.getEquipmentDescription());
        if(!file.isEmpty()) {
            BASE64Encoder encoder = new BASE64Encoder();
            String image = encoder.encode(file.getBytes());
            sportEquipment.setEquipmentImage(image);
        }
        sportEquipmentUpdate.setEquipmentImage(sportEquipment.getEquipmentImage());
        int updateCount=sportEquipmentMapper.updateByPrimaryKeySelective(sportEquipment);
        if (updateCount>0){
            return ServerResponse.createBySuccess("更新成功",sportEquipment);
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    /**
     * 图片的展示
     */
    public String getImage(Integer id, HttpServletResponse response){
        try {
//            Integer id=sportEquipment.getId();
//            SportEquipment sportEquipment =new SportEquipment();
            SportEquipment sportEquipment=sportEquipmentMapper.selectImageById(id);
//             sportEquipment.getEquipmentName();
            byte[] image = (byte[])sportEquipment.getEquipmentImage();
            String value = new String(image,"UTF-8");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(value);
            for(int i=0;i<bytes.length;i++){
                if(bytes[i]<0){
                    bytes[i]+=256;
                }
            }
            response.setContentType("image/jpeg");
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return "ok";
    }
}
