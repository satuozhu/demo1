package com.users.modules.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.api.framework.util.BASE64DecodedMultipartFile;
import com.api.framework.util.file.AliyunOssUtil;
import com.api.framework.util.file.DeleteFileUtil;
import com.users.component.config.aspect.annotation.LogForController;
import com.users.component.entity.Message;
import com.users.component.entity.STATUS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*")
@Api(description = "文件接口")
public class FileController {

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @LogForController
    @ApiOperation(value = "上传单个文件到OSS")
    @RequestMapping(value = "/uploadSingleFile", method = RequestMethod.POST)
    public Message<?> uploadSingleFile(@ApiParam(value = "文件MultipartFile", required = true) @RequestParam(value = "file") MultipartFile file) {
        log.info("上传单个文件到OSS");
        try {
            if (null != file) {
                String fileName = file.getOriginalFilename();
                if (!aliyunOssUtil.checkImage(fileName)) {
                    return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片类型只能为jpg、png、gif");
                }
                if (file.getSize() > 5 * 1024 * 1024) {
                    return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片大小不能超过5M");
                }
                if (!"".equals(fileName.trim())) {
                    File newFile = new File("img", fileName);
                    if (!newFile.exists()) {
                        newFile.getParentFile().mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(file.getBytes());
                    fos.close();

                    JSONObject uploadUrl = aliyunOssUtil.upLoadFile(newFile, null);
                    if (DeleteFileUtil.fileFilter(fileName)) {
                        DeleteFileUtil.deleteFile(new File("img").getAbsolutePath() + "/" + fileName);
                    }
                    if (null != uploadUrl) {
                        return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, uploadUrl);
                    }
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @LogForController
    @ApiOperation(value = "上传单个文件(base64图片流)到OSS")
    @RequestMapping(value = "/uploadSingleFileBase", method = RequestMethod.POST)
    public Message<?> uploadSingleFileBase(@ApiParam(value = "文件base64图片流，后台需转为MultipartFile文件", required = true) @RequestParam(value = "file") String file1) {
        log.info("上传单个文件(base64图片流)到OSS");
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart("data:image/png;base64," + file1);
        log.info("file = " + file);
        try {
            if (null != file) {
                String fileName = file.getOriginalFilename();
                if (!aliyunOssUtil.checkImage(fileName)) {
                    return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片类型只能为jpg、png、gif");
                }
                if (file.getSize() > 5 * 1024 * 1024) {
                    return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片大小不能超过5M");
                }
                if (!"".equals(fileName.trim())) {
                    File newFile = new File("img", fileName);
                    if (!newFile.exists()) {
                        newFile.getParentFile().mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(file.getBytes());
                    fos.close();

                    JSONObject uploadUrl = aliyunOssUtil.upLoadFile(newFile, null);
                    if (DeleteFileUtil.fileFilter(fileName)) {
                        DeleteFileUtil.deleteFile(new File("img").getAbsolutePath() + "/" + fileName);
                    }
                    if (null != uploadUrl) {
                        return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, uploadUrl);
                    }
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    @LogForController
    @ApiOperation(value = "上传单个文件到OSS指定文件夹")
    @RequestMapping(value = "/uploadSingleFileFolder", method = RequestMethod.POST)
    public Message<?> uploadSingleFileFolder(@ApiParam(value = "文件MultipartFile", required = true) @RequestParam(value = "file") MultipartFile file
            , @ApiParam(value = "指定文件夹", required = true) @RequestParam("folder") String folder) {
        log.info("上传单个文件到OSS指定文件夹");
        try {
            if (null != file) {
                String fileName = file.getOriginalFilename();
                if (!aliyunOssUtil.checkImage(fileName)) {
                    return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片类型只能为jpg、png、gif");
                }
                if (file.getSize() > 5 * 1024 * 1024) {
                    return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片大小不能超过5M");
                }
                if (!"".equals(fileName.trim())) {
                    File newFile = new File("img", fileName);
                    if (!newFile.exists()) {
                        newFile.getParentFile().mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(file.getBytes());
                    fos.close();

                    JSONObject uploadUrl = aliyunOssUtil.upLoadFile(newFile, folder);
                    if (DeleteFileUtil.fileFilter(fileName)) {
                        DeleteFileUtil.deleteFile(new File("img").getAbsolutePath() + "/" + fileName);
                    }
                    if (null != uploadUrl) {
                        return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, uploadUrl);
                    }
                }
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

    //使用swagger测试不通过，可以使用Postman来测试
    @LogForController
    @ApiOperation(value = "上传多个文件到OSS指定文件夹")
    @RequestMapping(value = "/uploadMultipleFileFolder", method = RequestMethod.POST, headers = "content-type=multipart/form-data")//swagger需要：headers = "content-type=multipart/form-data"
    public Message<?> uploadMultipleFileFolder(@ApiParam(value = "文件MultipartFile", required = true) @RequestParam(value = "file") MultipartFile[] files
            , @ApiParam(value = "指定文件夹", required = true, defaultValue = "a/1/2") @RequestParam("folder") String folder) {
        log.info("上传多个文件到OSS指定文件夹");
        if (files.length <= 0) {
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        }
        try {
            List<JSONObject> list = new ArrayList<>();
            for (MultipartFile file : files) {
                if (null != file) {
                    String fileName = file.getOriginalFilename();
                    if (!aliyunOssUtil.checkImage(fileName)) {
                        return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片类型只能为jpg、png、gif");
                    }
                    if (file.getSize() > 5 * 1024 * 1024) {
                        return new Message<>(STATUS.CODE_PARAMETER_ERROR, STATUS.MSG_ERR, "图片大小不能超过5M");
                    }
                    if (!"".equals(fileName.trim())) {
                        File newFile = new File("img", fileName);
                        if (!newFile.exists()) {
                            newFile.getParentFile().mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(newFile);
                        fos.write(file.getBytes());
                        fos.close();

                        JSONObject uploadUrl = aliyunOssUtil.upLoadFile(newFile, folder);
                        if (DeleteFileUtil.fileFilter(fileName)) {
                            DeleteFileUtil.deleteFile(new File("img").getAbsolutePath() + "/" + fileName);
                        }
                        if (null != uploadUrl) {
                            list.add(uploadUrl);
                        }
                    }
                }
            }
            if (list.size() > 0) {
                return new Message<>(STATUS.CODE_SUCCESS, STATUS.MSG_SUCCESS, list);
            }
            return new Message<>(STATUS.CODE_ERR, STATUS.MSG_ERR, false);
        } catch (Exception e) {
            log.error("程序出错", e);
        }
        return new Message<>(STATUS.CODE_FAILURE, STATUS.MSG_FAILURE);
    }

}