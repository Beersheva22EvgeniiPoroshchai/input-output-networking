package telran.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesCopy extends Copy {

	public FilesCopy(String srcFilePath, String destFilePath, boolean overwrite) {
		super(srcFilePath, destFilePath, overwrite);
		
	}

	@Override
	long copy() throws IOException {
		try (OutputStream outputStream = new FileOutputStream (destFilePath);) {
			Files.copy(Path.of(srcFilePath), outputStream);
		}
		
		return Files.size(Path.of(destFilePath));
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		DisplayResult res = new DisplayResult(copyTime, fileSize);
		return res;
	}

}
