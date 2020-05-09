package com.github.ardenliu.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZipFileUtils {
    private static final Logger logger = LogManager.getLogger(ZipFileUtils.class);

    private ZipFileUtils() {
    }

    public static void extractToSameFolder(String zipFilename) {
        Path path = Paths.get(zipFilename);

        extractToFolder(zipFilename, path.getParent().toString());
    }

    public static void extractToFolder(String zipFilename, String extractFolder) {

        int bufferSize = 2048;

        try (ZipFile zipFile = new ZipFile(new File(zipFilename))) {

            new File(extractFolder).mkdir();

            Enumeration<? extends ZipEntry> zipFileEntries = zipFile.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements()) {
                // grab a zip file entry
                ZipEntry entry = zipFileEntries.nextElement();
                String currentEntry = entry.getName();

                File destFile = new File(extractFolder, currentEntry);
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                if (!entry.isDirectory()) {
                    try (BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry))) {
                        int currentByte;
                        // establish buffer for writing file
                        byte data[] = new byte[bufferSize];

                        // write the current file to disk
                        try (BufferedOutputStream dest = new BufferedOutputStream(new FileOutputStream(destFile), bufferSize)) {

                            // read and write until last byte is encountered
                            while ((currentByte = is.read(data, 0, bufferSize)) != -1) {
                                dest.write(data, 0, currentByte);
                            }
                            dest.flush();
                            dest.close();
                        }
                        is.close();
                    }
                }
            }
            zipFile.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}