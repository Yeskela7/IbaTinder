package checking;

import dao.services.UsersDaoService;

import javax.servlet.ServletRequest;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Checking {

    public static boolean checkRegister(ServletRequest req, UsersDaoService uds){
        String email = req.getParameter("email");
        return uds.getAllUsers().stream().noneMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }


}
