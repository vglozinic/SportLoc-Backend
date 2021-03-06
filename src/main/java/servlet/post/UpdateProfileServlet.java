package servlet.post;

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

@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/updateProfile"})
public class UpdateProfileServlet extends HttpServletHelper {

	static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		UserBean user = gson.fromJson(getRequestBody(request), UserBean.class);
		result.put("success", (new UserModel().updateProfile(user)));
		sendResponse(response, result);
	}

}
