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
        User receiverUser = serviceUser
                .getUser(Integer.parseInt(request.getPathInfo().replace("/", "")));
        HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("messagesFromA", service.getMessagesFromAToB(senderID, receiverID));
            data.put("messagesFromB", service.getMessagesFromAToB(receiverID, senderID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        engine.render("chat.ftl", data, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int receiverID = Integer.parseInt(request.getParameter("id"));
        String text = request.getParameter("message");
        service.saveMessage(senderID, receiverID, text);
    }
}