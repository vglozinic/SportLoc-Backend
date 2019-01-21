package servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import beans.ParticipantBean;
import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "ResolveParticipantServlet", urlPatterns = {"/resolveParticipant"})
public class ResolveParticipantServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		ParticipantBean participant = gson.fromJson(getRequestBody(request), ParticipantBean.class);
		result.put("success", new EventModel().resolveParticipant(participant));
		sendResponse(response, result);
	}

}
