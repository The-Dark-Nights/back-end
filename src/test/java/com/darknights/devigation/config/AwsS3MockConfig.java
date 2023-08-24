package com.darknights.devigation.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.findify.s3mock.S3Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AwsS3MockConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Bean
    public S3Mock s3Mock(){
        return new S3Mock.Builder().withPort(9999).withInMemoryBackend().build();
        // S3Mock 서버로 사용될 S3Mock 등록, 서버 사용 port와 저장유무만 확인하면 되기 때문에 withMemoryBackend() 사용
    }

    @Bean
    public AmazonS3 amazonS3(S3Mock s3Mock){
        s3Mock.start();
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder
                .EndpointConfiguration("http://localhost:9999",region);

        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .build();
        client.createBucket(bucket);
        // End point => 서비스에 연결할 떄 필요한 endpoint 정보
        // AWS 서비스가 호스팅 되는 위치 => http://localhost:9999
        // endpoint를 명시적으로 지정하지 않을 경우 계정에서 사용하는 지역과 연결된 endpoint를 사용하기 때문에
        // 테스트할 때는 서버가 호스팅 되는 위치를 명시적으로 지정해준 것
        return client;
    }
}
