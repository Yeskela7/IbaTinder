package filters;

import classes.Controller;
import cookies.Cookies;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnlikedPersonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        if (req.getMethod().equalsIgnoreCase("GET")) {
            if (Controller.getUnmarked(Cookies.getIdFromCookies(req)).size() > 0) {
                chain.doFilter(request, response);
            } else {
                rs.sendRedirect("/list/");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
