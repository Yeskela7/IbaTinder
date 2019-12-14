package dao.localstore;

import classes.Message;
import dao.interfaces.Dao;
import jdbc.DbConnection;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesDaoSql implements Dao<Message> {

    private LocalDateTime time;

    @Override
    public Message get(int id) throws SQLException {
        Connection conn = DbConnection.getConnection();
        final String SQLS = "SELECT * FROM messages WHERE id = " + id;
        PreparedStatement stmt = conn.prepareStatement(SQLS);
        ResultSet rset = stmt.executeQuery();

        int from_id = 0;
        int to_id = 0;
        String message = "";
        long time = 0;

        while (rset.next()) {
            from_id = rset.getInt("from_id");
            to_id = rset.getInt("to_id");
            message = rset.getString("content");
            time = rset.getLong("time");
        }
        return new Message(from_id, to_id, message, time);
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
                    rset.getString("content"),
                    rset.getLong("time")
            ));
        }
        return allMessages;
    }

    @Override
    public void save(Message message) {
        Connection conn = DbConnection.getConnection();
        final String SQLI = "INSERT INTO messages (from_id, to_id, content, time) VALUES (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(SQLI);
            ps.setInt(1, message.getFrom());
            ps.setInt(2, message.getTo());
            ps.setString(3, message.getContent());
            ps.setLong(4, message.getDate());
            ps.execute();
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

    @Override
    public int getId(String s) {
        return 0;
    }
}
