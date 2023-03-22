package telran.net;

public interface Protocol {
	
	Response getResponse (Request request) throws Exception;

}
