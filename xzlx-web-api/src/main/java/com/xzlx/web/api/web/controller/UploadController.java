package com.xzlx.web.api.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: you are really cool!!
 * @Date: 2019/1/21 16:35
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class UploadController {

    @RequestMapping(value="upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile, MultipartFile editorFile, HttpServletRequest request, HttpServletResponse response){


        MultipartFile multiFile = dropFile == null ? editorFile:dropFile;

        Map<String,Object> result = new HashMap<>();
        //在static内下创建一个upload文件夹
        //1.获得该文件夹的路径的file对象
        File filePath = new File(request.getServletContext().getRealPath("/static/users/1"));
        //2.如果该文件夹不存在则创建
        if(!filePath.exists()){
            filePath.mkdirs();
        }

        //创建一个File来描述文件
        String filename = multiFile.getOriginalFilename();
        //获得扩展名

        filename = filename.substring(filename.lastIndexOf('.'));
        filename = UUID.randomUUID()+filename;
        File file = new File(filePath,filename);
        //把dropFile对象转换成file
        try {
            multiFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        filename = "/static/users/"+1+"/"+filename;
        if(dropFile!=null){
            //通过dropZone上传的图片
            //把文件名存到map里，map被转换成json，json被传给前端
            result.put("filename",filename);
            return result;
        }else{
            //通过wangEidtor上传的图片
            result.put("errno",0);
            String editorFilePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+filename;
            result.put("data",new String[]{editorFilePath});//线上图片的地址
            return result;
        }

    }





}
