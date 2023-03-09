package telran.io;

public class DisplayResult {
	private long fileSize;
	private long copyTime;
	
	public DisplayResult(long fileSize, long copyTime) {
		super();
		this.fileSize = fileSize;
		this.copyTime = copyTime;
	}
	
	public String toString() {
		String res = "File size is " + fileSize + "was copying for time: " + copyTime;
		return res;
	}
	

}
