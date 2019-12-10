package webhandlers;

import classes.Like;
import classes.User;
import cookies.Cookies;
import dao.services.LikesDaoService;
import dao.services.UsersDaoService;
import template_engine.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class LikedServlet extends HttpServlet {
    private int localId;
    private LikesDaoService service;
    private UsersDaoService serviceUser;
    private TemplateEngine engine;

    public LikedServlet(TemplateEngine engine) {
        this.engine = engine;
        serviceUser = new UsersDaoService();
        service = new LikesDaoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        localId = Cookies.getIdFromCookies(request);
        Optional<User> u = serviceUser.getUser(localId);
        Set<Optional<User>> liedUser = service.getAll()
                .stream().map(id -> serviceUser.getUser(id.getUserTo()))
                .collect(Collectors.toSet());
        List<User> collect = serviceUser.getAllUsers().stream()
                .filter(user -> !user.getEmail().equalsIgnoreCase(u.get().getEmail()))
                .filter(user -> liedUser.stream()
                        .noneMatch(user1 -> user.equals(user1.get())))
                .collect(Collectors.toList());
        System.out.println(collect);
        User url = collect.get(0);
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeople", url);
        engine.render("like-page.ftl", data, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("like");
    }
}

