package webhandlers;

import classes.Like;
import dao.services.LikesDaoService;
import template_engine.TemplateEngine;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {
    private int             localId;
    private LikesDaoService service;

    public LikedServlet() {
        service = new LikesDaoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie oneCookie : cookies) {
            if (oneCookie.getName().equals("%ID%"))
                localId = Integer.parseInt(oneCookie.getValue());
        }
        List<Like> likedUsers = null;
        try {
            likedUsers = service.getAllLikedUsers(localId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TemplateEngine engine = new TemplateEngine("./content");
        HashMap<String, Object> data = new HashMap<>();
        data.put("likedPeoples", likedUsers);
        engine.render("like-page.ftl", data, response);
    }
}

