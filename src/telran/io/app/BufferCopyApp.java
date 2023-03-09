package telran.io.app;

import telran.io.Copy;
import telran.io.FilesCopyBuilder;

public class BufferCopyApp {
	
	public static void main(String[] args) throws Exception {
		FilesCopyBuilder fileCopyBuild = new FilesCopyBuilder();
		Copy buffCopy = fileCopyBuild.build("bufferCopy", args);
		buffCopy.copyRun();
	}
}
