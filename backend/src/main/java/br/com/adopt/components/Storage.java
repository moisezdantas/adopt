package br.com.adopt.components;

import br.com.adopt.config.StorageConfig;
import br.com.adopt.dto.ImageDTO;
import io.minio.*;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class Storage {

    public static final Logger LOGGER = LoggerFactory.getLogger(Storage.class);

    @Value("${minio.bucket.name}")
    private String defaultBucketName;

    @Autowired
    private StorageConfig storageConfig;

    /**
     * Method upload file
     * @param multipartFile
     * @return
     */
    public ImageDTO minioUpload(MultipartFile multipartFile) {
        try {
            ImageDTO dto = new ImageDTO();
            MinioClient minioClient = storageConfig.generateMinio();
            createBucket(minioClient);
            if (multipartFile.getSize() <= 20971520) {
                String uuid = UUID.randomUUID().toString();
                dto.setImgUrl(uuid + File.separatorChar + multipartFile.getOriginalFilename());
                String prefix = dto.getImgUrl().substring(dto.getImgUrl().lastIndexOf("."));

                File file = File.createTempFile(dto.getImgUrl(), prefix);
                multipartFile.transferTo(file);

                UploadObjectArgs args = UploadObjectArgs.
                        builder()
                        .bucket(defaultBucketName)
                        .object(dto.getImgUrl())
                        .filename(file.getAbsolutePath()).build();

                minioClient.uploadObject(args);

                LOGGER.info("File successfully uploaded " + dto.getImgUrl() + " to " + defaultBucketName);

                File fileDelete = new File(file.toURI());
                fileDelete.delete();
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploaded bucket " + defaultBucketName);
        }
    }

    /**
     * Method Delete Files
     *
     * @param fileName file name
     * @return
     */
    public boolean delete(String fileName) {
        try {
            MinioClient minioClient = storageConfig.generateMinio();
            minioClient.deleteObjectTags(
                    DeleteObjectTagsArgs.builder().bucket(defaultBucketName).object(fileName).build());
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * Get file stream
     *
     * @param objectName file name
     * @return
     */
    public InputStream getFileInputStream(String objectName) {
        try {
            MinioClient minioClient = storageConfig.generateMinio();
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(defaultBucketName)
                            .object(objectName)
                            .build()
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Error uploaded bucket " + defaultBucketName + " :" + e.getMessage());
        }

    }

    private void createBucket(MinioClient minioClient) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(defaultBucketName).build());
        if (bucketExists) {
            LOGGER.info(defaultBucketName + "Already exists, you can upload files directly.");
        } else {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(defaultBucketName).build());
        }
    }

}
