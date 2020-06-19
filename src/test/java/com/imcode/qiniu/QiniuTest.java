package com.imcode.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

public class QiniuTest {

    @Test
    public void test() {

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
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\101.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String fileName = null; // 上传后的文件名称
        try {
            Response response = uploadManager.put(localFilePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);// FnetrAK9alQAhMRbWbCwt5pacCC2
            System.out.println(putRet.hash);//FnetrAK9alQAhMRbWbCwt5pacCC2
        } catch (QiniuException ex) {
            ex.printStackTrace();
            Response r = ex.response;
            System.err.println(r);
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex.printStackTrace();
            }
        }
    }
}
