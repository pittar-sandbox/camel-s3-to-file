package ca.pitt.camel.s3;

import org.apache.camel.builder.RouteBuilder;

public class S3Resource extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("aws2-s3://{{aws.s3.bucketName}}?amazonS3Client=#noobaaClient&delay=1000")
            .to("file://{{download-dir}}/?fileName=${header.CamelAwsS3Key}");

    }

}
