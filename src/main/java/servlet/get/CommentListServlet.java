package servlet.get;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import helper.HttpServletHelper;
import model.UserModel;

@WebServlet(name = "CommentListServlet", urlPatterns = {"getComments"})
public class CommentListServlet extends HttpServletHelper {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		UserModel user = new UserModel();
		String result = gson.toJson(user.getCommentList(request.getParameter("id")));
		sendResponse(response, result);
	}

}
