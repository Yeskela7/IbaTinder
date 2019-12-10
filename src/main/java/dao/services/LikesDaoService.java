package dao.services;

import classes.Like;
import dao.interfaces.Dao;
import dao.localstore.LikesDaoSql;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LikesDaoService {


    private Dao<Like> likesDao = new LikesDaoSql();

    public List<Like> getAll() {
        try {
            return likesDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Like> getAllMarked(int userFrom){
        try {
            return likesDao.getAll().stream()
                    .filter(u -> u.getUserFrom() == userFrom)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Like> getAllLikedUsers(int userFrom) {
        try {
            return likesDao.getAll().stream()
                    .filter(u -> u.getUserFrom() == userFrom)
                    .filter(Like::isLiked)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveLike(int from, int to, boolean liked) {
        likesDao.save(new Like(from, to, liked));
    }

}
