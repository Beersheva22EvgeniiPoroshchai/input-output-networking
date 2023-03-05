package telran.io.test;

import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
	String fileName = "myFile";
	String directoryName = "myDirectory1/myDirectory2";
	
	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}
	
	@Test
	@Disabled
	void test() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1.getAbsolutePath());
	}
	

	@Test
	@Disabled
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());
	}
	
	@Test
	@Disabled
	void printDirectoryFileTest()  {
		printDirectoryFile("src", 0);
	}
	
	void printDirectoryFile(String path, int maxLevel) {	
	File file = new File(path);
	File[] firstLevel = file.listFiles();
	if (firstLevel != null && firstLevel.length > 0) {
		for (File newFile : firstLevel) {
			for (int i = 0; i < maxLevel; i++) {
				System.out.print("\t");
			}
			if (newFile.isDirectory()) {
				System.out.println("directory: <" + newFile.getName() + ">");
				printDirectoryFile(newFile.getAbsolutePath(), maxLevel+1);
			} else {
				System.out.println("file: " + newFile.getName());
			}
		}
	}
}
	
	
	@Test
	void printDirectoryFilesTest() throws IOException  {
		System.out.println();
		printDirectoryFiles("src", 1);
	}
	
	void printDirectoryFiles(String path, int maxLevel) throws IOException {
		Path newPath = Path.of(path);
		System.out.println("directory: <" + newPath.getFileName()  + ">");
		if (maxLevel < 1) {
			Files.walk(newPath, FileVisitOption.FOLLOW_LINKS).filter(n -> n != newPath).forEach(n -> printAllTypesOfFiles(n));
		} else {
			Files.walk(newPath, maxLevel, FileVisitOption.FOLLOW_LINKS).filter(n -> n != newPath).forEach(n -> printAllTypesOfFiles(n));
		}
	}

	private void printAllTypesOfFiles(Path path) {
		if (Files.isDirectory(path)) {
			System.out.println(" ".repeat(path.getNameCount()) + "directory: <" + path.getFileName() + ">");
		} else {
			System.out.println(" ".repeat(path.getNameCount()) + "file: " + path.getFileName());
		}
		
	}

}
	

