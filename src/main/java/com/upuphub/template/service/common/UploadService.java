package com.upuphub.template.service.common;

import com.upuphub.template.util.http.message.Msg;
import com.upuphub.template.config.BaseStatic;
import com.upuphub.template.enums.ResultEnum;
import com.upuphub.template.lambda.FileRename;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * @program: UploadService
 * @description: 多媒体文件上传的Service
 * @author: 王志立
 * @create: 2018/12/14 16:27
 **/
@Service
public class UploadService {

    @Value("${upload.image.max.size}")
    private long maxSize;
    @Value("${upload.image.min.size}")
    private long minSize;
    @Value("${upload.image.max.width}")
    private int maxWidth;
    @Value("${upload.image.min.width}")
    private int minWidth;
    @Value("${upload.image.max.height}")
    private int maxHeight;
    @Value("${upload.image.min.height}")
    private int minHeight;
    @Value("${upload.image.type}")
    private String allowType;
    @Value("${upload.image.base.path}")
    private String path;

    private Msg message;

    private Logger log = LoggerFactory.getLogger(getClass());

    public Msg getMessage() {
        return message;
    }

    private boolean checkFileSize(MultipartFile file) {
        return file.getSize() < minSize || file.getSize() > maxSize;
    }

    private boolean checkFileType(MultipartFile file, HttpServletRequest request) {
        log.debug("contentType=" + request.getContentType());
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!allowType.contains(suffixName)) {
            log.debug(suffixName + "-> 图片格式不正确");
        }
        if (!request.getContentType().contains(BaseStatic.FORM_DATA)) {
            log.debug("请求验证失败");
        }
        return request.getContentType().contains("multipart/form-data") && allowType.contains(suffixName);
    }

    /**
     * 自定义重命名方式
     *
     * @param file    文件
     * @param request 请求
     * @param rename  重命名方式
     * @return 相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, FileRename rename) {
        if (file == null) {
            return null;
        }
        if (checkFileSize(file)) {
            message = Msg.fail(ResultEnum.UNSUPPORTED_MEDIA_TYPE, "请上传大小在" + minSize / 1024 + "KB到" + maxSize / 1024 + "KB之间的文件");
            return null;
        }
        if (!checkFileType(file, request)) {
            message = Msg.fail(ResultEnum.UNSUPPORTED_MEDIA_TYPE, "请上传jpeg、png、jpg 格式文件");
            return null;
        }
        String fileName = path + File.separator + rename.rename(file, request);
        log.debug("凭借的文件路径" + fileName);
        File target = new File(fileName);
        log.debug("绝对路径=" + target.getAbsolutePath());
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        try {
            file.transferTo(target);
            return target.getAbsolutePath().replace(path, "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用默认的重命名方式
     *
     * @param file    文件
     * @param request 请求
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request) {
        return upload(file, request, (file1, request1) -> System.currentTimeMillis() + ".jpeg");
    }

    /**
     * 自定义maxSize上传
     *
     * @param file    文集
     * @param request 请求
     * @param maxSize 最大大小
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, long maxSize) {
        long temp = this.maxSize;
        this.maxSize = maxSize;
        String result = upload(file, request);
        //必须归位，否则下次将使用这一次的maxSize
        this.maxSize = temp;
        return result;
    }

    /**
     * 自定义maxSize上传
     *
     * @param file    文集
     * @param request 请求
     * @param maxSize 最大大小
     * @param rename  文件重命名方式
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, long maxSize, FileRename rename) {
        long temp = this.maxSize;
        this.maxSize = maxSize;
        String result = upload(file, request, rename);
        //必须归位，否则下次将使用这一次的maxSize
        this.maxSize = temp;
        return result;
    }

    /**
     * 自定义maxSize上传并设置子目录
     *
     * @param file               文集
     * @param request            请求
     * @param maxSize            最大大小
     * @param rename             文件重命名方式
     * @param subdirectoriesName 自定义储存子目录
     * @return 基础存储路径下的相对路径
     */
    public String upload(MultipartFile file, HttpServletRequest request, long maxSize, String subdirectoriesName, FileRename rename) {
        long tempMaxSize = this.maxSize;
        String tempPath = this.path;
        this.maxSize = maxSize;
        this.path = path + "/" + subdirectoriesName;
        String result = upload(file, request, rename);
        //必须归位，否则下次将使用这一次的maxSize/Path
        this.maxSize = tempMaxSize;
        this.path = tempPath;
        return result;
    }

    /**
     * 删除文件
     *
     * @return 删除结果
     */
    public boolean deleteFile(File file) {
        log.info("删除:" + file.getAbsolutePath());
        return file.delete();
    }
}