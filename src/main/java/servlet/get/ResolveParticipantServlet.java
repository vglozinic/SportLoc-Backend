package servlet.get;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "ResolveParticipantServlet", urlPatterns = {"/resolveParticipant"})
public class ResolveParticipantServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		EventModel event = new EventModel();
		result.put("success", event.resolveParticipant(request.getParameterMap()));
		sendResponse(response, result);
	}

}
