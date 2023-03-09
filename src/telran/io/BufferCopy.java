package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferCopy extends Copy {
private long bufferSize;

public BufferCopy(String srcFilePath, String destFilePath, boolean overwrite, long bufferSize) {
	super(srcFilePath, destFilePath, overwrite);
	this.bufferSize = bufferSize;
}	


	@Override
	public long copy() throws IOException {
		try(InputStream inputStream = new FileInputStream(srcFilePath); 
			OutputStream outputStream = new FileOutputStream(destFilePath);) {
			byte[] buffer = new byte [(int) bufferSize];
			int length = inputStream.read(buffer);
			while (length > 0) {
				outputStream.write(buffer, 0, length);
				length = inputStream.read(buffer);
			}
			
			return Files.size(Path.of(destFilePath));
		}
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
	DisplayResultBuffer res = new DisplayResultBuffer(copyTime, fileSize, bufferSize);
	return res;
		
	}



}
