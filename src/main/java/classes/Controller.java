package classes;

import dao.localstore.LikesDaoSql;
import dao.services.UsersDaoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {

    private static UsersDaoService serviceUser;

    private static LikesDaoSql serviceLike;

    public static User getUnmarked(int localId) throws SQLException {
        User u = serviceUser.getUser(localId);
        Set<User> liedUser = serviceLike.getAll()
                .stream().map(id -> serviceUser.getUser(id.getUserTo()))
                .collect(Collectors.toSet());
        List<User> collect = serviceUser.getAllUsers().stream()
                .filter(user -> !user.getEmail().equalsIgnoreCase(u.getEmail()))
                .filter(user -> liedUser.stream()
                        .noneMatch(user::equals))
                .collect(Collectors.toList());
        return collect.get(0);
    }

    public static Set<User> getLikedUser(int localId) throws SQLException {
        return serviceLike.getAll()
                .stream().filter(id -> id.getUserFrom() == localId)
                .map(id -> serviceUser.getUser(id.getUserTo()))
                .collect(Collectors.toSet());
    }
}
