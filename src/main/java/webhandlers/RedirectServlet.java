package webhandlers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    private final String redirectTo;

    public RedirectServlet(String redirectTo) {
        this.redirectTo = redirectTo;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect(redirectTo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
