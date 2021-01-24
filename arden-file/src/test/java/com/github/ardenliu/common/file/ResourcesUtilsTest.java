package com.github.ardenliu.common.file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ResourcesUtilsTest {

    @Test
    void copyFromClassPath(@TempDir Path tempDir) throws IOException {
        String resourcePath = "com/github/ardenliu/common/file/resourcestest";
        ResourcesUtils.copyFromClassPath(resourcePath, tempDir);

        Path txt1File = Paths.get(tempDir.toString(), resourcePath, "1.txt");
        String contentOfTxt1File = org.apache.commons.io.FileUtils.readFileToString(txt1File.toFile() , StandardCharsets.UTF_8);
        assertEquals("111", contentOfTxt1File);

        Path txt2File = Paths.get(tempDir.toString(), resourcePath, "folder", "2.txt");
        String contentOfTxt2File = org.apache.commons.io.FileUtils.readFileToString(txt2File.toFile() , StandardCharsets.UTF_8);
        assertEquals("222", contentOfTxt2File);
    }
    
    @Test
    void copyFromClassPath_fromJar(@TempDir Path tempDir) throws IOException {
        String resourcePath = "com/github/ardenliu/common/testjar/resourcestest";
        ResourcesUtils.copyFromClassPath(resourcePath, tempDir);

        Path txt1File = Paths.get(tempDir.toString(), resourcePath, "1.txt");
        String contentOfTxt1File = org.apache.commons.io.FileUtils.readFileToString(txt1File.toFile() , StandardCharsets.UTF_8);
        assertEquals("111", contentOfTxt1File);

        Path txt2File = Paths.get(tempDir.toString(), resourcePath, "folder", "2.txt");
        String contentOfTxt2File = org.apache.commons.io.FileUtils.readFileToString(txt2File.toFile() , StandardCharsets.UTF_8);
        assertEquals("222", contentOfTxt2File);
    }
    
    @Test
    void resourceToString() {
        String resourcePath = "com/github/ardenliu/common/file/resourcestest/1.txt";
        String content = ResourcesUtils.resourceToString(resourcePath);
        System.out.println(content);
        assertEquals("111", content);
    }
}