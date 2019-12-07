package checking;

import dao.services.UsersDaoService;

import javax.servlet.ServletRequest;
import java.sql.SQLException;

public class CorrectChecking{

    public static boolean checkCookies(ServletRequest req) {
        return false;
    }

    public static boolean isUniqueUser(ServletRequest req){
        String email = req.getParameter("email");
        UsersDaoService service = new UsersDaoService();

        try {
            return service.getAllUsers().stream().noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isLoginCorrect(ServletRequest req) {
        return false;
    }

//TODO fix
    public static boolean checkPasswordEquals(ServletRequest req) {
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if(password == null || repassword == null)return true;
        return !password.equals(repassword);
    }

    public static boolean checkInputRegister(ServletRequest req) {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String picUrl = req.getParameter("picUrl");
        String age = req.getParameter("age");

        return login == null || password == null || name == null ||
                surname == null || picUrl == null || age == null || repassword == null;
    }

    public static boolean checkInputLogin(ServletRequest req) {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        return login != null && password != null;
    }

    public static boolean hasCookies(ServletRequest request) {
        return false;
    }
}
