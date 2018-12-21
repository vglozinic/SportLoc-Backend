package servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import beans.CommentBean;
import beans.UserBean;
import helper.HttpServletHelper;
import model.UserModel;

@WebServlet(name = "WriteCommentServlet", urlPatterns = {"/writeComment"})
public class WriteCommentServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JSONObject result = new JSONObject();
		CommentBean comment = gson.fromJson(getRequestBody(request), CommentBean.class);
		result.put("success", new UserModel().writeComment(comment));
		sendResponse(response, result);
	}

}
