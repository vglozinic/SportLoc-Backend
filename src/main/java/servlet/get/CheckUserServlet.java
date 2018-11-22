package servlet.get;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import helper.HttpServletHelper;
import model.UserModel;

@WebServlet(name = "CheckUserServlet", urlPatterns = {"/checkUser"})
public class CheckUserServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		UserModel user = new UserModel();
		result.put("success", user.checkUser(request.getParameter("username")));
		sendResponse(response, result);
	}

}
