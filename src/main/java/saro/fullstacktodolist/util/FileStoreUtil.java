package saro.fullstacktodolist.util;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileStoreUtil {

    private final AmazonS3 s3;

    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) {
        ObjectMetadata metaData = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metaData::addUserMetadata);
            }
        });
        try {
            s3.putObject(path, fileName, inputStream, metaData);
        } catch (AmazonS3Exception e) {
            throw new IllegalStateException("Failed to store file to S3", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to S3", e);
        }
    }


}
