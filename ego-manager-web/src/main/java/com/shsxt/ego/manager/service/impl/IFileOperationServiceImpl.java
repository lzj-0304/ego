package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.manager.service.IFileOperationService;
import com.shsxt.ego.model.PictureResult;
import com.shsxt.ego.utils.FtpUtils;
import com.shsxt.ego.utils.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class IFileOperationServiceImpl implements IFileOperationService {
    @Value("192.168.10.20")
    private String FTP_HOST;
    @Value("21")
    private Integer FTP_PORT;
    @Value("ftpuser")
    private String FTP_USERNAME;
    @Value("1qaz2wsx")
    private String FTP_PASSWORD;
    @Value("/home/ftpuser")
    private String FTP_PATH;
    @Value("http://www.ego.com")
    private String IMAGE_HTTP_PATH;


    @Override
    public PictureResult uploadFile(MultipartFile file) {
        /**
         * 上传文件到vsftp 服务器
         */
        boolean flag=false;
        String fileName=null;
        try{
            //获得信息的文件名字
            fileName= IDUtils.genImageName();
            //获得上传的文件的原始名字
            String oriName = file.getOriginalFilename();
            //获得文件扩展名
            String ext=oriName.substring(oriName.lastIndexOf("."));
            fileName=fileName+ext;
            InputStream local = file.getInputStream();
            //实现文件上传到ftp
            flag= FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                    FTP_PATH, fileName, local);
        }catch(Exception ex){
            ex.printStackTrace();
            flag=false;
        }
        PictureResult result=null;
        if(flag){
            result=new PictureResult();
            result.setError(0);
            result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
            result.setMessage("ok");
        }else{
            result=new PictureResult();
            result.setError(1);
            result.setUrl("url");
            result.setMessage("error");
        }
        return result;
    }
}
