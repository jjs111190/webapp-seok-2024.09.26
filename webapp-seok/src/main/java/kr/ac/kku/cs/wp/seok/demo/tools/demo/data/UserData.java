package kr.ac.kku.cs.wp.seok.demo.tools.demo.data;

import java.util.Map;

import kr.ac.kku.cs.wp.seok.demo.user.User;

public class UserData {
private static UserData THIS;
private UserData() {

this.initData();
}
private Map<String, User> users;
private void initData() {
}
//UserConrollerServlet에 있는 내용 복사하고 users 필드에 대입하도록 변경
public Map<String, User> getData() {

return users;
}
public static synchronized UserData getInstance() {
if (THIS == null) {

	THIS = new UserData();
}
	return THIS;

}


}