package filters;


import checking.CorrectChecking;
import classes.Controller;
import cookies.Cookies;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LikeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("GET")) {
            if (CorrectChecking.checkCookies(request)) {
                rs.sendRedirect("/login/");
            } else {
                if (Controller.getUnmarked(Cookies.getIdFromCookies(req)).size() < 1) {
                    rs.sendRedirect("/message/");
                }
            }
            System.out.println(1);
            chain.doFilter(request, response);
            return;
        } else {
            System.out.println(5);
            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {

    }
}
