package webhandlers;

import dao.services.MessagesDaoService;
import template_engine.TemplateEngine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class MessagesServlet extends HttpServlet {
    private int senderID;
    private int receiverID;
    private MessagesDaoService service;

    public MessagesServlet(){
        senderID = -1;
        receiverID = -1;
        service = new MessagesDaoService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie oneCookie : cookies){
            if (oneCookie.getName().equals("%ID%")){
                senderID = Integer.parseInt(oneCookie.getValue());
            }
        }
        receiverID = Integer.parseInt(request.getParameter("id"));
        TemplateEngine engine = new TemplateEngine("./templates");
        HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("messages", service.getMessagesPair(senderID, receiverID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        engine.render("chat.ftl", data, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String text = request.getParameter("message");
        service.saveMessage(senderID, receiverID, text);
    }
}