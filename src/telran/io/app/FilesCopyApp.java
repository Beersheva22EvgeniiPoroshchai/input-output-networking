package telran.io.app;

import telran.io.Copy;
import telran.io.FilesCopyBuilder;

public class FilesCopyApp {

	public static void main(String[] args) throws Exception {
		FilesCopyBuilder fileCopyBuild = new FilesCopyBuilder();
		Copy filesCopy = fileCopyBuild.build("filesCopy", args);
		filesCopy.copyRun();
	}

}
