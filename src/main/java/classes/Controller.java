package classes;

import dao.localstore.LikesDaoSql;
import dao.services.LikesDaoService;
import dao.services.UsersDaoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {

    private static UsersDaoService serviceUser= new UsersDaoService();

    private static LikesDaoService serviceLike = new LikesDaoService();

    public static List<Integer> getUnmarked(int localId){
        return serviceUser.getAllUsers().stream()
                .filter(user -> serviceUser.getUserIdByMail(user.getEmail()) != localId)
                .map(user -> serviceUser.getUserIdByMail(user.getEmail()))
                .filter(id -> !serviceLike.getAllMarked(localId).stream()
                        .map(Like::getUserTo)
                        .collect(Collectors.toSet())
                        .contains(id))
                .collect(Collectors.toList());
    }

}
