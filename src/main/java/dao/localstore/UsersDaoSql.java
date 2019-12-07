package dao.localstore;

import classes.User;
import dao.interfaces.Dao;
import jdbc.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoSql implements Dao<User> {

    @Override
    public User get(int id) throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM users WHERE id =" + id;
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();
        int singleUserID = 0;

        String email = "";
        String name = "";
        String surname = "";
        String picUrl = "";
        int age = 0;
        int password = 0;

        while (rset.next()) {
            email = rset.getString("email");
            name = rset.getString("name");
            surname = rset.getString("surname");
            picUrl = rset.getString("picUrl");
            age = rset.getInt("age");
            password = rset.getInt("password");

        }
        return new User(email, name, surname, picUrl, age, password);
    }


    @Override
    public List<User> getAll() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM users";
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();
        List<User> allUsers = new ArrayList<>();

        while (rset.next()) {

            allUsers.add(new User(
                    rset.getString("email"),
                    rset.getString("name"),
                    rset.getString("surname"),
                    rset.getString("picUrl"),
                    rset.getInt("age"),
                    rset.getInt("password")
            ));
        }
        return allUsers;
    }

    @Override
    public void save(User user) {
        Connection conn = DbConnection.getConnection();
        final String SQLI = "INSERT INTO users (email, name, surname, picUrl, age, password) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getSurname());
            ps.setString(4, user.getPicURL());
            ps.setInt(5, user.getAge());
            ps.setInt(6, user.getPassword());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public int getId(String user) {
        Connection conn = DbConnection.getConnection();
        final String SQLO = "SELECT * FROM users";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLO);
            ResultSet rset = ps.executeQuery();
            String mail = "";
            int id;
            while (rset.next()) {
                id = rset.getInt("id");
                mail = rset.getString("email");
                if (user.equalsIgnoreCase(mail)) {
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
