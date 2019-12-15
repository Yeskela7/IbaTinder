package webhandlers;

import classes.Controller;
import classes.User;
import cookies.Cookies;
import dao.services.LikesDaoService;
import dao.services.UsersDaoService;
import template_engine.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class UsersServlet extends HttpServlet {
    private TemplateEngine engine;
    private UsersDaoService service = new UsersDaoService();

    public UsersServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int localId = Cookies.getIdFromCookies(req);
        List<User> toLike = Controller.getLikedUser(localId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeople", toLike);
        engine.render("people-list.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        int id = service.getUserIdByMail(login);
        resp.sendRedirect("/chat/" + id);
    }
}
