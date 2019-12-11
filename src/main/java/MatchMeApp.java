import classes.Like;
import filters.LikeFilter;
import filters.LoginFilter;
import filters.RegisterFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import template_engine.TemplateEngine;
import webhandlers.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class MatchMeApp {

    public static void main(String[] args) throws Exception {

        Server webServer = new Server(8088);
        ServletContextHandler webHandler = new ServletContextHandler();
        TemplateEngine engine = TemplateEngine.folder("./src/main/resources/webstock/webfiles/ftl/");

        webHandler.addServlet(new ServletHolder(new UsersServlet(engine)), "/users/*");
        webHandler.addServlet(new ServletHolder(new LikedServlet(engine)), "/liked/*");
        webHandler.addServlet(new ServletHolder(new MessagesServlet(engine)), "/messages/*");
        webHandler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        webHandler.addServlet(new ServletHolder(new LogoutServlet()), "/logout/*");
        webHandler.addServlet(new ServletHolder(new RegisterServlet()), "/register/*");
        webHandler.addServlet(new ServletHolder(new RedirectServlet("/login")), "/*");
        webHandler.addServlet(new ServletHolder(new FileServlet()), "/webstock/*");


        webHandler.addFilter(RegisterFilter.class, "/register/*", EnumSet.of(DispatcherType.REQUEST));
        webHandler.addFilter(LoginFilter.class, "/login/*", EnumSet.of(DispatcherType.REQUEST));
//        webHandler.addFilter(LikeFilter.class, "/liked/*", EnumSet.of(DispatcherType.REQUEST));


        webServer.setHandler(webHandler);
        webServer.start();
        webServer.join();
    }

}