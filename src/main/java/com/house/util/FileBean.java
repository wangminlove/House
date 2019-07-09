package com.house.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileBean {
    public static String Upload(MultipartFile pfile){
        //1.图片上传到图片服务器F:\王敏\images,不放在项目里
        String filename = pfile.getOriginalFilename();//获取上传的文件名称
        String pname = filename.substring(filename.lastIndexOf("."));//获取上传问价的扩展名
        String saveFileName = System.currentTimeMillis() + pname;//保存新的文件名,尽量保证每张图片的图片名不同
        String path="F:/王敏/images/"+saveFileName;//path是保存上传文件的路劲
        File saveFile=new File(path);
        try {
            pfile.transferTo(saveFile);//上传文件到指定路径
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saveFileName;
    }
}
