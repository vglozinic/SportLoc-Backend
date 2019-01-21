package helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletHelper extends HttpServlet {
	
	public static final long serialVersionUID = 1L;
	
	public String getRequestBody(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());
		return request.getReader().lines().collect(Collectors.joining(""));		
	}
	
	public void sendResponse(HttpServletResponse response, Object json) {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			PrintWriter printer = response.getWriter();
			printer.println(json.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}