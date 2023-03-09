package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TransferCopy extends Copy {

	public TransferCopy(String srcFilePath, String destFilePath, boolean overwrite) {
		super(srcFilePath, destFilePath, overwrite);
	}

	@Override
	long copy() throws IOException {
		try (InputStream inputStream = new FileInputStream(srcFilePath);
				OutputStream outputStream = new FileOutputStream(destFilePath);) {
			inputStream.transferTo(outputStream);
		}
		return Files.size(Path.of(destFilePath));
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		DisplayResult res = new DisplayResult(copyTime, fileSize);
		return res;
	}

}
