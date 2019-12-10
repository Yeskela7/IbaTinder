package checking;

import chip.Ciphering;
import cookies.Cookies;
import dao.services.UsersDaoService;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Checking {

    public static boolean checkCookies(ServletRequest req){
        UsersDaoService service = new UsersDaoService();
        int c = Cookies.getIdFromCookies((HttpServletRequest) req);
        int size = service.getAllUsers().size();
        if (size > c) return true;
        return false;
    }

    public static boolean isUniqueUser(ServletRequest req) throws SQLException {
        String email = req.getParameter("email");
        UsersDaoService service = new UsersDaoService();
        return service.getAllUsers().stream().noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public static boolean isLoginCorrect(ServletRequest req) throws SQLException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");

        UsersDaoService service = new UsersDaoService();
        return service.getAllUsers().stream()
                .filter(user -> user.getEmail()
                        .equalsIgnoreCase(login))
                .allMatch(user -> user.getPassword() == Ciphering.passwordCrypt(password));
    }

    public static boolean checkPasswordEquals(ServletRequest req) {
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        return repassword.equals(password);
    }

    public static boolean checkInputRegister(ServletRequest req) {

        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String picUrl = req.getParameter("picUrl");
        String age = req.getParameter("age");

        if (login != null && password != null && name != null &&
                surname != null && picUrl != null && age != null) {
            try {
                int a = Integer.parseInt(age);
            } catch (NumberFormatException ex) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean checkInputLogin(ServletRequest req) {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        return login != null && password != null;
    }

    public static boolean hasCookies(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        String id;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("%Cookies%")) {
                    id = c.getValue();
                    if (id != null) return true;
                }
            }
        }
        return false;
    }

}
