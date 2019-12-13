package filters;

import checking.CorrectChecking;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookiesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        System.out.println(2);
        if(req.getMethod().equalsIgnoreCase("GET")){
            if (!CorrectChecking.checkCookies(request)){
                chain.doFilter(request,response);
            }else {
                rs.sendRedirect("/login");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
