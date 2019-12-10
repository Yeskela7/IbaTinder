package webhandlers;

import cookies.Cookies;
import dao.services.UsersDaoService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        Path path = Paths.get("./src/main/resources/webstock/login.html");
        ServletOutputStream os;
        try {
            os = resp.getOutputStream();
            Files.copy(path, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("email");

        UsersDaoService usersDaoService = new UsersDaoService();

        int uid = Cookies.getIdFromDb(login, usersDaoService);
        Cookie c = new Cookie("%Cookies%", String.valueOf(uid));
        c.setPath("/");
        resp.addCookie(c);
        resp.sendRedirect("liked");
    }
}