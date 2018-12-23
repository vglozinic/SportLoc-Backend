package servlet.get;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import beans.UserBean;
import helper.HttpServletHelper;
import model.UserModel;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel model = new UserModel();
		Integer id = model.checkParameters(request.getParameterMap());
		if(id != 0) {
			Gson gson = new Gson();
			UserBean user = model.getProfile(id.toString());
			String result = gson.toJson(user);
			sendResponse(response, result);
		} else {
			JSONObject result = new JSONObject();
			result.put("userId", id);
			sendResponse(response, result);
		}
	}

}
