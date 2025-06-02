package com.ruoyi.admin.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件
 *
 * @author ruoyi
 * @date 2024-10-15
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件管理")
public class FileController {

    @Value("${layer.webUrl}")
    private String otherFileWebUrl;
    @Value("${file.savePath}")
    private String savePath;
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    /**
     * 上传文件返回路径
     *
     * @return
     */
    @ApiOperation("上传文件返回路径")
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file, HttpServletRequest req) {
        //1、处理文件位置
        //文件存放位置
        String realPath = savePath + "/otherFile/";
        //按照时间格式划分
        String format = sdf.format(new Date());
        String path = realPath + format;
        //文件夹
        File folder = new File(path);
        //判断文件是否存在
        if (!folder.exists()) {//不存在，则创建
            //这里需要使用mkdirs，因为为多层目录
            folder.mkdirs();
        }
        //2、处理文件名
        //获取文件后缀+文件名
        String oldName = file.getOriginalFilename();
        //将后缀加到新的文件名上
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            //文件保存
            file.transferTo(new File(folder, newName));
            String webPath = "files/" + format + newName;//文件的访问路径
            return AjaxResult.success("成功", webPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.error();
    }
}
