package com.github.ardenliu.common.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

public class FileUtils {
    private static final Logger logger = LogManager.getLogger(FileUtils.class);

    private FileUtils() {
    }

    public static void saveResourceIntoFile(String classPath, String targetFilePath) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(classPath);
            InputStream inputStreamFromClassPath = classPathResource.getInputStream();
            saveInputStreamIntoFile(inputStreamFromClassPath, targetFilePath);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static void saveInputStreamIntoFile(InputStream inputStream, Path targetFilePath) {
        FileUtils.saveInputStreamIntoFile(inputStream, targetFilePath.toString());
    }

    public static void saveInputStreamIntoFile(InputStream inputStream, String targetFilePath) {
        try {
            File targetFile = new File(targetFilePath);

            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, targetFile);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}