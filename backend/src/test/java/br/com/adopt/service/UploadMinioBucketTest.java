package br.com.adopt.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UploadMinioBucketTest {

    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://127.0.0.1:9000/")
                            .credentials("minio_access_key", "minio_secret_key")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("adopt").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("adopt").build());
            } else {
                System.out.println("Bucket 'adopt' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            String uuid = UUID.randomUUID().toString();
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("adopt")
                            .object(uuid + "/" + "call.png")
                            .filename("/home/moisez_dantas/Imagens/call.png")
                            .build());
            System.out.println(
                    "'/home/moisez_dantas/Imagens/call.png' is successfully uploaded as "
                            + "object 'call.png' to bucket 'adopt'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
