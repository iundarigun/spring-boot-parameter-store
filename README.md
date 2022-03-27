# Parameter Store

This repo aims to show how to use `parameter store` by AWS as an external properties source.

### Dependencies

Since String boot 2.4, we need to use `io.awspring.cloud` instead of `org.springframework.cloud`.

### Bootstrap
The configuration of parameter store must be on `bootstrap.yaml`, because these properties are used on startup. To allow this, we need to add the bootstrap for spring cloud:

```kotlin
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
```

### AWS Credentials

We can not use AWS credentials inside application (property `cloud.aws.credentials.access-key` and `cloud.aws.credentials.secret-key`) because on startup the AWSCredentials bean is not created yet. So we need to add two environment variables:
```sh
EXPORT AWS_ACCESS_KEY_ID=<access key>
EXPORT AWS_SECRET_ACCESS_KEY=<secret key>
```

### Refresh scope
We can use the endpoint `refresh` on actuator to get updated on properties. We need to allow actuator with the next property on `application.yaml`:
```
management:
  endpoints:
    web:
      exposure:
        include: refresh
```

We can use `Refresh Endpoint` to do it internally. See `SchedulingRefreshConfiguration` class.

--- 

## References
- https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-parameter-store.html
- https://github.com/awspring/spring-cloud-aws
- https://docs.awspring.io/spring-cloud-aws/docs/2.3.1/reference/html/index.html#integrating-your-spring-cloud-application-with-the-aws-parameter-store
- https://towardsaws.com/how-to-externalize-spring-boot-properties-to-an-aws-system-manager-parameter-store-2a945b1e856f




