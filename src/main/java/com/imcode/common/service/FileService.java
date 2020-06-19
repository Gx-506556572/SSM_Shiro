package com.imcode.common.service;


import com.imcode.common.model.R;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.google.gson.Gson;

@Service
public class FileService {

    @Autowired(required = false)
    private HttpSession session;

    @Value("${file.location}")
    private String fileLocation;

    @Value("${file.local.server}")
    private String fileLocalServer;

    @Value("${file.qiniu.server}")
    private String fileQiniuServer;


    /**
     * 上传文件到本地服务器
     *
     * @param uploadFile
     * @return
     */
    public R uplaodToLocal(MultipartFile uploadFile) throws IOException {

        String fileName = uploadFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") - 1);
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;

        String parent = session.getServletContext().getRealPath(fileLocation);
        File file = new File(parent, fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        uploadFile.transferTo(file);
        R r = R.ok()
                .put("url", fileLocalServer + "/" + fileName)
                .put("name", fileName);
        return r;
    }

    /**
     * 上传文件到本地服务器
     *
     * @param uploadFile
     * @return
     */
    public R uplaodToQiniu(MultipartFile uploadFile) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //文件上传管理器
        UploadManager uploadManager = new UploadManager(cfg);
        //生成上传凭证，然后准备上传
        String accessKey = "G4nLglkv8U0MO5jOh6h8OGu2PlO824dfRrkoSSNR";
        String secretKey = "DqO2bUaLgWckpEr6NWaLtSS9GQgT5-HNk2hgOsnR";
        String bucket = "i-admin";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        String fileName = uploadFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") - 1);
        String uuid = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        fileName = uuid + suffix;

        Response response = uploadManager.put(uploadFile.getBytes(), fileName, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        //System.out.println(putRet.key);// FnetrAK9alQAhMRbWbCwt5pacCC2
        //System.out.println(putRet.hash);//FnetrAK9alQAhMRbWbCwt5pacCC2
        R r = R.ok()
                .put("url", fileQiniuServer + "/" + putRet.key)
                .put("name", putRet.key);
        return r;
    }
}
