package filters;

import checking.CorrectChecking;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("GET")) {
            chain.doFilter(request, response);}

        else if (CorrectChecking.isUniqueUser(request)
                && CorrectChecking.checkPasswordEquals(request)) {
            try {
                rs.sendRedirect("/login/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (response != null) {
                rs.reset();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
