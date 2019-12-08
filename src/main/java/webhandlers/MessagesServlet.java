package webhandlers;

import cookies.Cookies;
import dao.services.MessagesDaoService;
import template_engine.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class MessagesServlet extends HttpServlet {
    private int senderID;
    private int receiverID;
    private MessagesDaoService mds;
    private UsersServlet uds;

    private TemplateEngine engine;

    public MessagesServlet(TemplateEngine engine) {
        senderID = -1;
        receiverID = -1;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        senderID = Cookies.getIdFromCookies(req);
        String id = req.getContextPath().replace("/","");
        receiverID = 3;
//        receiverID = Integer.parseInt(id);
        HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("messages", mds.getMessagesPair(senderID, receiverID));
        } catch (NullPointerException  | SQLException e) {
            e.printStackTrace();
        }
        engine.render("chat.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = req.getParameter("text");
        mds.saveMessage(senderID, receiverID, text);
        resp.setHeader("method", "get");
        resp.sendRedirect(String.format("/messages/%s",receiverID));
    }
}
