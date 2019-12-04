package dao.services;

import classes.Like;
import dao.interfaces.Dao;
import dao.localstore.LikesDaoSql;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class LikesDaoService {


    private Dao<Like> likesDao = new LikesDaoSql();

    public List<Like> getAll() throws SQLException {
        return likesDao.getAll();
    }

    public List<Like> getAllLikedUsers(int userFrom) throws SQLException {
        return likesDao.getAll().stream()
                .filter(u -> u.getUserFrom() == userFrom)
                .filter(Like::isLiked)
                .collect(Collectors.toList());
    }

    public void toLike(int from, int to, boolean liked) {
        likesDao.save(new Like(from, to, liked));
    }

}
