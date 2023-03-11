package telran.io.app;

import telran.io.Copy;
import telran.io.FilesCopyBuilder;

public class FilesCopyApp {
	private static final String FILES_COPY = "FilesCopy";

	public static void main(String[] args) {
		try {
			FilesCopyBuilder builder = new FilesCopyBuilder();
			Copy copy = builder.build(FILES_COPY, args);
			copy.copyRun();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

