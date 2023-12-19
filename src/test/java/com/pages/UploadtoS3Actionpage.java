package com.pages;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UploadtoS3Actionpage
{


    public void uploadfilestoS3() {


        String bucketName = "cdndem";
        String accessKey = "AKIAZ4INMNQ5YNYE2EHL";
        String secretKey = "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";
        List<String> filePaths = new ArrayList<>();
        filePaths.add("XMLFiles.zip");
        filePaths.add("CSV-FILE-1.csv");
        filePaths.add("CSV-FILE-2.csv");

        File file = new File(System.getProperty("user.dir")+File.separator+"XMLFiles.zip");


        // Create AWS credentials
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);


        // Upload a file to the S3 bucket
         S3Client s3 = S3Client.builder()
                .region(Region.AP_SOUTH_1)     // Specify the AWS region
                .credentialsProvider(() -> awsCredentials)
                .build();



        try {
            // Iterate over each file path and upload to the S3 bucket
            for (String filePath : filePaths)
            {
                String absFilePath=System.getProperty("user.dir")+File.separator+filePath;
                uploadFileToS3(s3, bucketName, absFilePath);
            }

            System.out.println("All files uploaded successfully to S3!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the S3 client to release resources
            s3.close();
        }

        // Close the S3 client to release resources


    }




    private static void uploadFileToS3(S3Client s3, String bucketName, String filePath) {
        File file = new File(filePath);
        String keyName = file.getName();

        // Upload the file to the S3 bucket
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        s3.putObject(request, file.toPath());
        System.out.println("File '" + filePath + "' uploaded to S3 with key '" + keyName + "'");
    }





}
