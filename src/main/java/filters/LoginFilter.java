package filters;

import checking.CorrectChecking;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("GET")) {
            chain.doFilter(request, response);

        } else {
            if (CorrectChecking.isLoginCorrect(request)) {
                chain.doFilter(request, response);
            } else {
                rs.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
