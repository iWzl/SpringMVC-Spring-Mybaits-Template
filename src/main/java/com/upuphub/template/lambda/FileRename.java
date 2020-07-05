package com.upuphub.template.lambda;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: FileRename
 * @description: 文件相关的LAMBDA非函数式接口
 * @author: 王志立
 * @create: 2018/12/14 15:44
 **/
@FunctionalInterface
public interface FileRename {
    /**
     * 重命名
     *
     * @param file    上传的原始文件流
     * @param request 请求
     * @return 返回相对路径，可以创建文件夹
     */
    String rename(MultipartFile file, HttpServletRequest request);

}
