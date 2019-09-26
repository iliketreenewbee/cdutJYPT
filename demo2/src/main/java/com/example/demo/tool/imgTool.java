package com.example.demo.tool;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class imgTool {
    private String prifixURL = "D:/cdutJYPTimg/";
    public String savaImg(MultipartFile file,String name) {
        try {
            File TempFile = new File(prifixURL);
            if (TempFile.exists()) {
                if (TempFile.isDirectory()) {
                    System.out.println("该文件夹存在。");
                }else {
                    System.out.println("同名的文件存在，不能创建文件夹。");
                }
            }else {
                System.out.println("文件夹不存在，创建该文件夹。");
                TempFile.mkdir();
            }
            String imagename = file.getOriginalFilename();
            String imagename_extension = imagename.substring(imagename
                    .lastIndexOf(".") + 1);
            String saveImageName = name+ new Date().getTime()+imagename_extension;
            System.out.println("图片名："+saveImageName);
            String saveImgURL  = prifixURL+ saveImageName;
            System.out.println("视频的后缀名:"+imagename_extension);
            File imgFile = new File(saveImgURL);
            file.transferTo(imgFile);
            return saveImgURL;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
    }
}
