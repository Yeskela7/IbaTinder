package webhandlers;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class MainServer {

    public static void WebServer() throws Exception {

        Server webServer = new Server(7557);
        ServletContextHandler webHandler = new ServletContextHandler();

        webHandler.addServlet(new ServletHolder(new UsersServlet()), "/users/*");
        webHandler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
        webHandler.addServlet(new ServletHolder(new MessagesServlet()), "/messages/*");
        webHandler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        webHandler.addServlet(new ServletHolder(new DefServlet()), "/*");

        webServer.setHandler(webHandler);
        webServer.start();
        webServer.join();
    }

}
