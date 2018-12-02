package servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import com.google.gson.Gson;

import beans.EventBean;
import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "CreateEventServlet", urlPatterns = {"/createEvent"})
public class CreateEventServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		EventBean event = gson.fromJson(getRequestBody(request), EventBean.class);
		result.put("success", new EventModel().createEvent(event));
		sendResponse(response, result);
	}

}
