import filters.LoginFilter;
import filters.RegisterFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import webhandlers.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class MatchMeApp {

    public static void main(String[] args)throws Exception {

        Server webServer = new Server(8088);
        ServletContextHandler webHandler = new ServletContextHandler();

        webHandler.addServlet(new ServletHolder(new UsersServlet()), "/users/*");
        webHandler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
        webHandler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");
        webHandler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        webHandler.addServlet(new ServletHolder(new LogoutServlet()), "/logout/*");
        webHandler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        webHandler.addServlet(new ServletHolder(new RedirectServlet("/register")), "/*");

        webHandler.addFilter(RegisterFilter.class, "/register/*", EnumSet.of(DispatcherType.REQUEST));
        webHandler.addFilter(LoginFilter.class, "/login/*", EnumSet.of(DispatcherType.REQUEST));


        webServer.setHandler(webHandler);
        webServer.start();
        webServer.join();
    }

}