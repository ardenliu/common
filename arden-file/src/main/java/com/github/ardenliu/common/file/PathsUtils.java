package com.github.ardenliu.common.file;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathsUtils {
    private static final Logger logger = LogManager.getLogger(PathsUtils.class);

    public static Path getPath(String pathString) {
        if (StringUtils.isEmpty(pathString)) {
            logger.warn("pathString is empty");
            return null;
        } else {
            try {
                return Paths.get(pathString);
            } catch (InvalidPathException e) {
                logger.warn("got InvalidPathException", e);
                return null;
            }
        }
    }
}