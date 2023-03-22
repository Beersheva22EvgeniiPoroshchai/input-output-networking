package telran.net;

public class ProtocolImplementation implements Protocol {

	
	@Override
	public Response getResponse(Request request) {
		Response response = switch (request.type) {
		case "reverse" -> stringToReverse (request);
		case "length" -> stringToLength (request);
		default -> new Response (ResponseCode.WRONG_REQUEST, request);
	};
		
		return response;
	}

	private Response stringToLength(Request request) {
		Response response = new Response(null, request.data);
		try {
		response.data = request.data.toString().length() + "";
		response.code = ResponseCode.OK;
		} catch (Exception e) {
			response.code = ResponseCode.WRONG_DATA;
		}
		return response;
	}

	private Response stringToReverse(Request request) {
		Response response = new Response(null, request.data);
		try {
			
		
		response.data = new StringBuilder(request.data.toString()).reverse();
		response.code = ResponseCode.OK;
		} catch (Exception e) {
			response.code = ResponseCode.WRONG_DATA;
		}
		return response;
	}

}
