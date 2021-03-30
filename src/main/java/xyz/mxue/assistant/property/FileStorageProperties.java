package xyz.mxue.assistant.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mxuexxmy
 * @date 3/31/2021$ 12:49 AM$
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
