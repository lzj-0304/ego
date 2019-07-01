package com.shsxt.ego.manager.controller;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.shsxt.ego.model.PictureResult;
import com.shsxt.ego.utils.IDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ImageController {

    private String ak="3K77OJ214syThkwIbX8skAMHcEYmZJuqGcTSEEW1";
    private String sk="DpQoXLAfJWJJblp-kdBm4t573Tzf_cEmq6xLoFW3";
    private String basePath="http://pty9die0h.bkt.clouddn.com/";
    private  String bucket="20190701";

    @RequestMapping("pic/upload")
    @ResponseBody
    public PictureResult uploadFile(HttpServletRequest request) {
        MultipartHttpServletRequest mhsf = (MultipartHttpServletRequest) request;
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        PictureResult result = new PictureResult();
        try {
            Auth auth = Auth.create(ak, sk);
            String upToken = auth.uploadToken(bucket);
            MultipartFile mf = mhsf.getFile("uploadFile");
            //获得信息的文件名字
            String fileName = IDUtils.genImageName();
            //获得上传的文件的原始名字
            String oriName = mf.getOriginalFilename();
            //获得文件扩展名
            String ext = oriName.substring(oriName.lastIndexOf("."));
            fileName = fileName + ext;
            Response response = uploadManager.put(mf.getInputStream(), fileName, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            result.setUrl(putRet.key);
            result.setError(0);
            result.setUrl(basePath+fileName);
            result.setMessage("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            result=new PictureResult();
            result.setError(1);
            result.setUrl("url");
            result.setMessage("error");
        }
        return result;
    }
}
