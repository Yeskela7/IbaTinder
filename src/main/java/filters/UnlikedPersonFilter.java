package filters;

import classes.Controller;
import cookies.Cookies;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class UnlikedPersonFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        System.out.println(3);
        if (req.getMethod().equalsIgnoreCase("GET")) {
            if (Controller.getUnmarked(Cookies.getIdFromCookies(req)).size() > 0) {
                System.out.println("sds");
                int s = Controller.getUnmarked(Cookies.getIdFromCookies(req)).get(0);
                System.out.println(s);
                rs.sendRedirect("/liked/" + s);
                chain.doFilter(request, response);
            }else {
                System.out.println(32);
                rs.sendRedirect("/users");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
