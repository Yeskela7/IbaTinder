package webhandlers;

import classes.Controller;
import classes.Like;
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
import java.util.stream.Collectors;

public class LikeServlet extends HttpServlet {
    private int localId;
    private int likedPerson;
    private LikesDaoService serviceLike;
    private UsersDaoService serviceUser;
    private TemplateEngine engine;
    private List<Integer> collect = Controller.getUnmarked(localId);

    public LikeServlet(TemplateEngine engine) {
        this.engine = engine;
        serviceUser = new UsersDaoService();
        serviceLike = new LikesDaoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        collect = Controller.getUnmarked(localId);
        localId = Cookies.getIdFromCookies(request);
        likedPerson = collect.get(0);
        User toLike = serviceUser.getUser(likedPerson);
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeople", toLike);
        engine.render("like-page.ftl", data, response);
        collect.remove(0);
        System.out.println(likedPerson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        collect = Controller.getUnmarked(localId);
        boolean likeB = Boolean.parseBoolean(req.getParameter("like"));
        localId = Cookies.getIdFromCookies(req);
        likedPerson = collect.get(0);
        serviceLike.saveLike(localId, likedPerson, likeB);
        System.out.println(likedPerson);
        resp.sendRedirect("like");
    }
}

