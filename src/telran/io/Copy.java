package telran.io;

import java.io.IOException;
import java.io.OutputStream;
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
 	Instant time = Instant.now();
	long size = copy();
	DisplayResult dispRes = getDisplayResult (ChronoUnit.MILLIS.between(time, Instant.now()), size);
	System.out.println(dispRes);
	
	
	
}


	
	
}
