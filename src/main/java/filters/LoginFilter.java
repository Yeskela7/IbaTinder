package filters;

import checking.CorrectChecking;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("GET")) {
            chain.doFilter(request, response);
            if (!CorrectChecking.checkCookies(request)) {
                rs.sendRedirect("/liked/");
                return;
            }

        } else if (CorrectChecking.isLoginCorrect(request) && CorrectChecking.checkCookies(request)) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        } else {
            if (response != null) {
                rs.sendRedirect("/liked/");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
