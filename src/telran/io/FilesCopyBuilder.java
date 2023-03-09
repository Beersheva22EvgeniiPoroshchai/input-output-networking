package telran.io;

public class FilesCopyBuilder {
	
	
	public Copy build (String type, String [] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException("least arguments");
		}
		String srcFilePath = args[0];
		String destFilePath = args[1];
		boolean overwrite = getOverwriteValue(args);
		
		return switch(type) {
		case "transferCopy" -> new TransferCopy(srcFilePath, destFilePath, overwrite);
		case "filesCopy" -> new FilesCopy(srcFilePath, destFilePath, overwrite);
		case "bufferCopy" -> new BufferCopy(srcFilePath, destFilePath, overwrite, getBufferSizeValue(args));
		default -> throw new IllegalArgumentException();
		};
		
	}

	private long getBufferSizeValue(String[] args) {
		long res = 1_048_576;
		if (args.length > 3) {
			try {
				res = Long.valueOf(args[3]);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}
		return res;
	}



	private boolean getOverwriteValue(String[] args) {
		boolean res = false;
		if (args.length > 2 && args[2].equalsIgnoreCase("overwrite")) {
			res = true;
		}
		return res;
	}

}
