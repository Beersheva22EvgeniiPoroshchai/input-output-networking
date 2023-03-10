package telran.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

abstract public class Copy {
String srcFilePath;
String destFilePath;
boolean overwrite;

Copy(String srcFilePath, String destFilePath, boolean overwrite) {
	super();
	this.srcFilePath = srcFilePath;
	this.destFilePath = destFilePath;
	this.overwrite = overwrite;
}



abstract long copy() throws IOException;
abstract DisplayResult getDisplayResult (long copyTime, long fileSize);
	
public void copyRun() throws Exception{
 	if (!overwrite && Files.exists(Path.of(destFilePath))) {
 		throw new Exception (destFilePath + "can't be overwritten");
 	}
	Instant time = Instant.now();
	long size = copy();
	DisplayResult dispRes = getDisplayResult (ChronoUnit.MILLIS.between(time, Instant.now()), size);
	System.out.println(dispRes);
	
}


	
	
}
