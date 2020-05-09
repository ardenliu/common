package com.github.ardenliu.common.file;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


class ZipFileUtilsTest {

	@Test
	void extractToFolder(@TempDir Path tempDir) {
		System.out.println(tempDir.toString());

		Path targetTestZipFile = Paths.get(tempDir.toString(), "test.zip");
		FileUtils.saveResourceIntoFile("com/github/ardenliu/common/file/test.zip", targetTestZipFile.toString());
		
		ZipFileUtils.extractToFolder(targetTestZipFile.toString(), tempDir.toString());
		
		assertTrue(Files.exists(Paths.get(tempDir.toString(), "1.txt")));
		assertTrue(Files.exists(Paths.get(tempDir.toString(), "2.txt")));
		assertTrue(Files.exists(Paths.get(tempDir.toString(), "3", "4.txt")));
	}

	@Test
	void extractToSameFolder(@TempDir Path tempDir) {
		System.out.println(tempDir.toString());

		Path targetTestZipFile = Paths.get(tempDir.toString(), "test.zip");
		FileUtils.saveResourceIntoFile("com/github/ardenliu/common/file/test.zip", targetTestZipFile.toString());
		
		ZipFileUtils.extractToSameFolder(targetTestZipFile.toString());

		assertTrue(Files.exists(Paths.get(tempDir.toString(), "1.txt")));
		assertTrue(Files.exists(Paths.get(tempDir.toString(), "2.txt")));
		assertTrue(Files.exists(Paths.get(tempDir.toString(), "3", "4.txt")));
	}
}