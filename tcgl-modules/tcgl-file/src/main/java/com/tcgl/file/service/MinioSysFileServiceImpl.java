package com.tcgl.file.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.tcgl.file.config.MinioConfig;
import com.tcgl.file.utils.FileUploadUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import javax.annotation.Resource;

/**
 * Minio 文件存储
 *
 * @author tcgl
 */
@Service
public class MinioSysFileServiceImpl implements ISysFileService {
    @Resource
    private MinioConfig minioConfig;

    @Resource
    private MinioClient client;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtils.extractFilename(file);
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }
}
