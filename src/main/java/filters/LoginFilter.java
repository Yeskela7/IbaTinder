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
        if (CorrectChecking.isLoginCorrect(request)) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        } else {
            if (response instanceof HttpServletResponse) {
                HttpServletResponse rs = (HttpServletResponse) response;
                try {
                    rs.sendRedirect("login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
