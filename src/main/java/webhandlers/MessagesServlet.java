package webhandlers;

import classes.User;
import cookies.Cookies;
import dao.services.MessagesDaoService;
import dao.services.UsersDaoService;
import template_engine.TemplateEngine;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;


public class MessagesServlet extends HttpServlet {
    private int senderID;
    private int receiverID;
    private UsersDaoService serviceUser;
    private MessagesDaoService service;
    private TemplateEngine engine;

    public MessagesServlet(TemplateEngine engine){
        service = new MessagesDaoService();
        this.engine = engine;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        senderID = Cookies.getIdFromCookies(request);
        receiverID = Integer.parseInt(request.getPathInfo().replace("/", ""));
        HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("messages", service.getMessages(senderID, receiverID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        engine.render("chat.ftl", data, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        receiverID = Integer.parseInt(request.getPathInfo().replace("/", ""));
        String text = request.getParameter("message");
        senderID = Cookies.getIdFromCookies(request);
        service.saveMessage(senderID, receiverID, text, System.currentTimeMillis());

        response.sendRedirect("/chat" + request.getPathInfo());
    }
}