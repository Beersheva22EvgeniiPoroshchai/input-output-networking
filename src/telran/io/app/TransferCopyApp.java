package telran.io.app;

import telran.io.Copy;
import telran.io.FilesCopyBuilder;

public class TransferCopyApp {

	public static void main(String[] args) throws Exception {
		FilesCopyBuilder fileCopyBuild = new FilesCopyBuilder();
		Copy transfCopy = fileCopyBuild.build("transferCopy", args);
		transfCopy.copyRun();
	}

}
