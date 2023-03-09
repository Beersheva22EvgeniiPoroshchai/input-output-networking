package telran.io;

public class DisplayResultBuffer extends DisplayResult{
long bufferSize;
	
	public DisplayResultBuffer(long fileSize, long copyTime, long bufferSize) {
		super(fileSize, copyTime);
		this.bufferSize = bufferSize;
	}
	
	@Override
	public String toString() {
		String res = super.toString() + "and buffer size was: " + bufferSize;
		return res;
	}
}
