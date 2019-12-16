package webhandlers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("%Cookies%")) {
                    c.setPath("/");
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }
        }
        try {
            resp.sendRedirect("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
