package com.imcode.common.controller;

import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    private FileService fileService;


    /**
     * 单文件上传
     *
     * @param uploadFile
     * @return
     */
    @PostMapping("/uplaodToQiniu")
    @ResponseBody
    public R qiniu(@RequestParam("uploadFile") MultipartFile uploadFile) {
        try {
            return fileService.uplaodToQiniu(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 单文件上传
     *
     * @param uploadFile
     * @return
     */
    @PostMapping("/uplaodToLocal")
    @ResponseBody
    public R uplaod(@RequestParam("uploadFile") MultipartFile uploadFile) {
        try {
            return fileService.uplaodToLocal(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    /**
     * 多文件上传
     *
     * @param uploadFile
     * @param
     * @return
     */
    @PostMapping("/uplaodsToLocal")
    @ResponseBody
    public R uplaods(@RequestParam("uploadFile") MultipartFile[] uploadFile) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (MultipartFile f : uploadFile) {
            try {
                data.add(fileService.uplaodToLocal(f));
            } catch (IOException e) {
                e.printStackTrace();
                data.add(R.error(e.getMessage()));
            }
        }
        return R.ok().put("data", data);
    }
}
