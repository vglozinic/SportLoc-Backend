package servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.FilterBean;
import helper.HttpServletHelper;
import model.EventModel;

@WebServlet(name = "EventListServlet", urlPatterns = {"/getEvents"})
public class EventListServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		FilterBean filter = gson.fromJson(getRequestBody(request), FilterBean.class);
		String result = gson.toJson(new EventModel().getEventList(filter));
		sendResponse(response, result);
	}

}
