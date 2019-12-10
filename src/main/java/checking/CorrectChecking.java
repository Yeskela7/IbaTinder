package checking;

import chip.Ciphering;
import cookies.Cookies;
import dao.services.UsersDaoService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class CorrectChecking {

    public static boolean checkCookies(ServletRequest req) {
        return (Cookies.getIdFromCookies(((HttpServletRequest) req)) < 1);
    }

    public static boolean isUniqueUser(ServletRequest req) {
        String email = req.getParameter("email");
        UsersDaoService service = new UsersDaoService();

        return service.getAllUsers().stream()
                .noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public static boolean isLoginCorrect(ServletRequest req) {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        UsersDaoService service = new UsersDaoService();
        if (login == null) return true;
        if (service.getAllUsers().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(login))) {
            return service.getAllUsers().stream().filter(user -> user.getEmail().equalsIgnoreCase(login))
                    .allMatch(user -> user.getPassword() == Ciphering.passwordCrypt(password));
        } else return false;
    }


    public static boolean checkPasswordEquals(ServletRequest req) {
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if (password == null && repassword == null) return true;
        assert password != null;
        return password.equals(repassword);
    }

//    public static boolean checkInputRegister(ServletRequest req) {
//        String login = req.getParameter("email");
//        String password = req.getParameter("password");
//        String repassword = req.getParameter("repassword");
//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
//        String picUrl = req.getParameter("picUrl");
//        String age = req.getParameter("age");
//
//        return (login == null || password == null || name == null ||
//                surname == null || picUrl == null || age == null || repassword == null);
//    }

    public static boolean checkLogin(ServletRequest req) {
        String login = req.getParameter("email");
        if (login != null) {
            UsersDaoService service = new UsersDaoService();
            return service.getUserIdByMail(login) != -1;
        }
        return true;
    }

    public static boolean hasCookies(ServletRequest request) {
        return false;
    }
}
