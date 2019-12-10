package webhandlers;

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

public class LikedServlet extends HttpServlet {
    private int localId;
    private int likedPerson;
    private LikesDaoService serviceLike;
    private UsersDaoService serviceUser;
    private TemplateEngine engine;

    public LikedServlet(TemplateEngine engine) {
        this.engine = engine;
        serviceUser = new UsersDaoService();
        serviceLike = new LikesDaoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        localId = Cookies.getIdFromCookies(request);
        User toLike = serviceUser
                .getUser(Integer.parseInt(request.getPathInfo().replace("/", "")));
        likedPerson = serviceUser.getUserIdByMail(toLike.getEmail());
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeople", toLike);
        engine.render("like-page.ftl", data, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String like = req.getParameter("like");
        boolean likeB = Boolean.parseBoolean(like);
        System.out.println(likeB);
        localId = Cookies.getIdFromCookies(req);
        List<Integer> collect = serviceUser.getAllUsers().stream()
                .filter(user -> serviceUser.getUserIdByMail(user.getEmail()) != localId)
                .map(user -> serviceUser.getUserIdByMail(user.getEmail()))
                .filter(id -> !serviceLike.getAllMarked(localId).stream()
                        .map(Like::getUserTo)
                        .collect(Collectors.toSet())
                        .contains(id)).collect(Collectors.toList());
        likedPerson = collect.get(0);
        serviceLike.saveLike(localId, likedPerson, likeB);
        resp.sendRedirect(String.valueOf(likedPerson));
        collect.remove(0);
    }
}

