package checking;

import dao.services.UsersDaoService;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Checking {

    public static boolean isUniqueUser(ServletRequest req, UsersDaoService uds) {
        String email = req.getParameter("email");
        return uds.getAllUsers().stream().noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
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
