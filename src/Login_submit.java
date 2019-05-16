import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login_submit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<User> users = new ArrayList<>();
	private InitServer_getUser IG;
	public Login_submit() {
		IG =new InitServer_getUser();
		users = IG.getUserList();
	}

	public Boolean isUser(User user) {
		for(User u:users) {
			if(u.getName().equals(user.getName()) & u.getPassword().equals(user.getPassword()))
				return true;
		}
		return false;
	}
	
	private Boolean insertUser(User user) {
		users.add(user);
		return IG.insertUser(user);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;character=gbk");
		PrintWriter out = resp.getWriter();
		try {
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			System.out.println("login/register:"+name);
			User user = new User(name, password);
			if (isUser(user))
			{
				out.print("success");
			}
			else {
				if(insertUser(user))
					out.print("success");
				else
					out.print("false");
			}


		} finally {
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get Request");
		doGet(req, resp);
	}

}
