package capstone.zigtong.adminserver.infra.s3.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;

    //@Value("${spring.cloud.aws.s3.bucket}")
    private String BUCKET_NAME = "zigtong-post-images";

    @SneakyThrows
    public String uploadImage(String adminId, MultipartFile profileImage) {
        if (!profileImage.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .key(adminId)
                .bucket(BUCKET_NAME)
                .contentType(profileImage.getContentType())
                .contentLength(profileImage.getSize())
                .build();

        RequestBody requestBody = RequestBody.fromInputStream(profileImage.getInputStream(), profileImage.getSize());

        PutObjectResponse response = s3Client.putObject(putObjectRequest, requestBody);

        if (!response.sdkHttpResponse().isSuccessful()) {
            log.error("S3 업로드 실패: {}", response.sdkHttpResponse().statusText());
        }

        // 업로드 경로 반환
        return s3Client.utilities().getUrl(builder -> builder.bucket(BUCKET_NAME).key(adminId)).toExternalForm();
    }
}
