package com.xzlx.web.api.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@RestController
@RequestMapping("api")
public class UploadController {

    public String upload(MultipartFile MultipartFile, HttpServletRequest request){
        File filePath = new File(request.getServletContext().getRealPath("/static/upload"));
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        String filename = MultipartFile.getOriginalFilename();
        filename = filename.substring(filename.lastIndexOf('.'));
        filename = UUID.randomUUID()+filename;
        File file = new File(filePath,filename);

        try {
            MultipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filename = "/static/upload/"+filename;
        return filename;
    }


    @RequestMapping(value = "uploadPic",method = RequestMethod.POST)
    public Map<String,Object> uploadPic(MultipartFile dropFile, HttpServletRequest request){
        String filename = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+upload(dropFile,request);
        Map<String,Object> map = new HashMap<>();
        map.put("filename",filename);
        return map;
    }


    @RequestMapping(value = "uploadContentPic",method = RequestMethod.POST)
    public Map<String,Object> uploadContent(MultipartFile editorFile, HttpServletRequest request){
        String filename = upload(editorFile,request);
        Map<String,Object> map = new HashMap<>();
        String editorFilePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+filename;
        map.put("errno",0);
        map.put("data",new String[]{editorFilePath});
        return map;
    }





}
