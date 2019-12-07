package filters;

import checking.Checking;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFilterdn implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
if (Checking.checkInputLogin(request)){
    try {
        if (Checking.isLoginCorrect(request)){
            chain.doFilter(request,response);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    }

    @Override
    public void destroy() {

    }
}
