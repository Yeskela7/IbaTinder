package dao.localstore;

import classes.Message;
import dao.interfaces.Dao;
import jdbc.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDaoSql implements Dao<Message> {

    @Override
    public Message get(int id) throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM messages WHERE from_id = " + id + " OR to_id = " + id;
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();

        int from_id = 0;
        int to_id = 0;
        String message = "";

        while (rset.next()) {
            from_id = rset.getInt("from_id");
            to_id = rset.getInt("to_id");
            message = rset.getString("content");
        }

        return new Message(from_id,to_id,message);
    }


    @Override
    public List<Message> getAll() throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM messages";
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();
        List<Message> allMessages = new ArrayList<>();

        while (rset.next()) {

            allMessages.add(new Message(
                    rset.getInt("from_id"),
                    rset.getInt("to_id"),
                    rset.getString("content")
            ));
        }
        return allMessages;
    }

    @Override
    public void save(Message message) {
        Connection conn = DbConnection.getConnection();
        final String SQLI = "INSERT INTO messages (from_id, to_id, content) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setInt(1, message.getFrom());
            ps.setInt(2, message.getTo());
            ps.setString(3, message.getContent());
            ps.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(Message message) {

    }
}
