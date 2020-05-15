package com.github.ardenliu.common.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ResourcesUtils {
    private static final Logger logger = LogManager.getLogger(ResourcesUtils.class);

    public static void copyFromClassPath(final String resourcePath, final Path targetRoot) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resourcePath + "/**");

        for (Resource resource : resources) {
            if (resource.exists()) {
                String pathOfResource = resource.getURL().getPath();
                int lastIndex = pathOfResource.lastIndexOf(resourcePath);
                if (lastIndex == -1) {
                    logger.error("cannot find[{}] from pathOfResource[{}]", resourcePath, pathOfResource);
                    continue;
                }
                String relativePath = pathOfResource.substring(lastIndex, pathOfResource.length());
                Path tergetPath = Paths.get(targetRoot.toString(), relativePath);
                if (resource.isReadable()) {
                    logger.debug("creating file[{}]", tergetPath);
                    File tergetFile = tergetPath.toFile();
                    org.apache.commons.io.FileUtils.copyURLToFile(resource.getURL(), tergetFile);

                } else {
                    logger.debug("creating directory[{}]", tergetPath);
                    Files.createDirectories(tergetPath);
                }
            }
        }
    }
}
