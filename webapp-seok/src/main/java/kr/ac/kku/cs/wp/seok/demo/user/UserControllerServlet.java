package kr.ac.kku.cs.wp.seok.demo.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kku.cs.wp.seok.demo.tools.demo.data.UserData;

@WebServlet("/user/userlist")
public class UserControllerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<User> users = new ArrayList<User>();

		String[] sNames = {"김구","안창호", "안중근", "유관순", "홍범도", "김좌진", "남자현", "윤봉길", "이봉창", "김원봉"};

		String[] sEmails = {"kim1@kku.ac.kr", "an1@kku.ac.kr",

		"an2@kku.ac.kr","yu@kku.ac.kr",

		"hong@kku.ac.kr","kim2@kku.ac.kr",

		"nam@kku.ac.kr","yun1@kku.ac.kr",

		"lee@kku.ac.kr","kim3@kku.ac.kr"};

		String[] sRoles = {"관리자", "개발자", "시스템관리자"};

		for(int i = 0; i < 20; i++ ) {

		User user = new User();

		user.setPhotoSRC("https://via.placeholder.com/150");

		user.setName(sNames[(i+1)% 10]);

		user.setEmail(sEmails[(i+1)% 10]);

		user.setId("kku_" + (1000 + i));

		List<String> roles = new ArrayList<String>();

		roles.add(sRoles[(i+2)%3]);

		roles.add(sRoles[(i+1)%3]);

		user.setRoles(roles);

		user.setStatus("재직중");

		users.add(user);

		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/userList.jsp");

		rd.forward(req, resp);
		req.setAttribute("users", users);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		Map<String, User> usersMap = UserData.getInstance().getData();
		List<User> users = new ArrayList<User>(usersMap.values());}
	
}
