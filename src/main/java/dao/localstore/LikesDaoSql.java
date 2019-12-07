package dao.localstore;

import classes.Like;
import classes.Message;
import dao.interfaces.Dao;
import jdbc.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikesDaoSql implements Dao<Like> {


    @Override
    public Like get(int id) throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM likes WHERE id = " + id;
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();

        int user_from = 0;
        int user_to = 0;
        boolean liked = false;

        while (rset.next()) {
            user_from = rset.getInt("user_from");
            user_to = rset.getInt("user_to");
            liked = rset.getBoolean("liked");
        }

        return new Like(user_from,user_to,liked);
    }

    @Override
    public List<Like> getAll() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM likes";
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();
        List<Like> allLikes = new ArrayList<>();

        while (rset.next()) {

            allLikes.add(new Like(
                    rset.getInt("user_from"),
                    rset.getInt("user_to"),
                    rset.getBoolean("liked")
            ));
        }

        return allLikes;
    }

    @Override
    public void save(Like like) {
        Connection conn = DbConnection.getConnection();
        final String SQLI = "INSERT INTO ibatech_online.likes (user_from, user_to, liked) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setInt(1, like.getUserFrom());
            ps.setInt(2, like.getUserTo());
            ps.setBoolean(3, like.isLiked());
            ps.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Like like) {

    }

    @Override
    public void delete(Like like) {

    }
}
