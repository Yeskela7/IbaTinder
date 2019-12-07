package cookies;

import dao.services.UsersDaoService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class Cookies {

    public static int getIdFromDb(String email, UsersDaoService service){
        try {
            return service.getUserIdByMail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getIdFromCookies(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        int id = 0;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("%Cookies%")) {
                    id = Integer.parseInt(c.getValue());
                }
            }
        }
        return id;
    }
}
