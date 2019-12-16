package classes;

import dao.services.LikesDaoService;
import dao.services.UsersDaoService;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private static UsersDaoService serviceUser = new UsersDaoService();

    private static LikesDaoService serviceLike = new LikesDaoService();

    public static List<Integer> getUnmarked(int localId) {
        return serviceUser.getAllUsers().stream()
                .filter(user -> serviceUser.getUserIdByMail(user.getEmail()) != localId)
                .map(user -> serviceUser.getUserIdByMail(user.getEmail()))
                .filter(id -> !serviceLike.getAllMarked(localId).stream()
                        .map(Like::getUserTo)
                        .collect(Collectors.toSet())
                        .contains(id))
                .collect(Collectors.toList());
    }

    public static List<User> getLikedUser(int localId) {
        return serviceUser.getAllUsers().stream()
                .filter(user -> serviceUser.getUserIdByMail(user.getEmail()) != localId)
                .map(user -> serviceUser.getUserIdByMail(user.getEmail()))
                .filter(id -> serviceLike.getAllLikedUsers(localId).stream()
                        .map(Like::getUserTo)
                        .collect(Collectors.toSet())
                        .contains(id))
                .map(integer -> serviceUser.getUser(integer))
                .collect(Collectors.toList());
    }
}
