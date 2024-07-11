package com.mountana.api.utility;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.mountana.api.constant.AmazonS3Constant;
import org.apache.commons.io.FilenameUtils;

import java.io.InputStream;
import java.util.Map;

public class AmazonS3Util {
    private final Regions clientRegion = Regions.AP_SOUTHEAST_1;

    public void MultipartUpload(String folderInS3Bucket, String keyName, InputStream fileInput) throws InterruptedException {
        MultipartUpload(folderInS3Bucket, keyName, fileInput, null);
    }
    public void MultipartUpload(String folderInS3Bucket, String keyName, InputStream fileInput, Map<String, String> userMetadatas) throws InterruptedException {
        //try {
            String bucketName = AmazonS3Constant.MOUNTANA_ASSETS_BUCKET_NAME;

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                    .build();
            TransferManager tm = TransferManagerBuilder.standard()
                    .withS3Client(s3Client)
                    .build();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            String fileExtension = FilenameUtils.getExtension(keyName);
            if(fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("jpg") ||
                    fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("webp")) {
                objectMetadata.setContentType("image/" + fileExtension);
            }
            if(userMetadatas != null && !userMetadatas.isEmpty()) {
                objectMetadata.setUserMetadata(userMetadatas);
            }

            if(!folderInS3Bucket.isBlank()) {
                keyName = folderInS3Bucket + "/" + keyName;
            }

            // TransferManager processes all transfers asynchronously,
            // so this call returns immediately.
            //Upload upload = tm.upload(bucketName, keyName, new File(filePath));
            Upload upload = tm.upload(bucketName, keyName, fileInput, objectMetadata);
            System.out.println("Object upload to S3 started");

            // Optionally, wait for the upload to finish before continuing.
            upload.waitForCompletion();
            System.out.println("Object upload to S3 complete");
//        } catch (AmazonServiceException e) {
//            // The call was transmitted successfully, but Amazon S3 couldn't process
//            // it, so it returned an error response.
//            e.printStackTrace();
//        } catch (SdkClientException e) {
//            // Amazon S3 couldn't be contacted for a response, or the client
//            // couldn't parse the response from Amazon S3.
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
