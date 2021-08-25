# Camel Quarkus - S3/NooBaa Integration

## Setup

1. Create a new `ObjectBucketClaim`.
2. Find the credentials and bucket name from the `Secret` and `ConfigMap` created when the `BucketClaim` is created.
3. Find your S3 URL: `oc get route -n openshift-storage s3`

## AWS CLI Setup

Create a [named profile] for your the Noobaa gateway.  For example:

```
 cat ~/.aws/config 
[profile noobaa]
region = ca-central-1
```

```
 cat ~/.aws/credentials 
; https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-files.html
[default]
aws_access_key_id     = notMyRealAWSID
aws_secret_access_key = notMyRealAWSSecretKey

[noobaa]
aws_access_key_id = notMyRealNoobaaID
aws_secret_access_key = NotMyRealNoobaaSecretKey
```

## AWS CLI Usage

Upload all files in the current directory to your bucket:

```
aws --profile=noobaa \
    --endpoint-url=https://s3-openshift-storage.apps.<cluster url> \
    s3 cp ./ s3://<your bucket name> \
    --recursive
```

Download all files from a bucket into the current directory:

```
aws --profile=noobaa \
    --endpoint-url=https://s3-openshift-storage.apps.<cluster url>  \
    s3 cp s3://<your bucket name> ./  \
    --recursive
```

## Use Camel/Quarkus to poll and download files from a bucket to a directory

```
mvn compile quarkus:dev \
    -Daws.accessKeyId="yourBucketAccessKeyId" \
    -Daws.secretAccessKey="yourBucketSecretAccessKey" \
    -Daws.s3.bucketName="yourBucketName" \
    -Ddownload-dir=/tmp/bucket-dl
```
