package filters;

import checking.Checking;
import checking.CorrectChecking;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (CorrectChecking.isLoginCorrect(request) && CorrectChecking.checkCookies(request)) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        } else {
            if (response instanceof HttpServletResponse) {
                HttpServletResponse rs = (HttpServletResponse) response;
                if (!CorrectChecking.checkCookies(request)){
                    rs.sendRedirect("messages/-1");
                }
                rs.sendRedirect("login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
