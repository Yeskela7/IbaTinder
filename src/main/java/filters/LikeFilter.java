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
            if (Controller.getUnmarked(Cookies.getIdFromCookies(req)).size() < 1) {
                rs.sendRedirect("/list/");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
