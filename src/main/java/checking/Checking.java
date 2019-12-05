package checking;

import dao.services.UsersDaoService;

import javax.servlet.ServletRequest;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Checking {

    public static boolean isUniqueUser(ServletRequest req, UsersDaoService uds){
        String email = req.getParameter("email");
        return uds.getAllUsers().stream().noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public static boolean checkInputRegister(ServletRequest req){

        String login = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("email");
        String surname = req.getParameter("password");
        String picUrl = req.getParameter("email");
        String age = req.getParameter("password");


    }

}
