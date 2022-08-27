package com.tcgl.file.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tcgl.common.core.domain.R;
import com.tcgl.common.core.utils.file.FileUtils;
import com.tcgl.file.service.ISysFileService;
import com.tcgl.system.api.domain.SysFile;

import javax.annotation.Resource;

/**
 * 文件请求处理
 *
 * @author tcgl
 */
@RestController
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Resource
    private ISysFileService sysFileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public R<SysFile> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = sysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }
}