package dao.services;

import classes.Message;
import dao.interfaces.Dao;
import dao.localstore.MessagesDaoSql;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MessagesDaoService {

    private Dao<Message> messageDao = new MessagesDaoSql();

    public List<Message> getMessageFrom(int userId) throws SQLException {
        return messageDao.getAll().stream().filter(id -> id.getFrom() == userId).collect(Collectors.toList());
    }

    public List<Message> getMessageTo(int userId) throws SQLException {
        return messageDao.getAll().stream().filter(id -> id.getTo() == userId).collect(Collectors.toList());
    }

    public List<Message> getMessagesPair(int userFrom, int userTo) throws SQLException {
        return messageDao.getAll().stream().filter(idFrom -> idFrom.getFrom() == userFrom)
                .filter(idTo -> idTo.getFrom() == userTo).collect(Collectors.toList());
    }

    public void saveMessage(int from, int to, String text) {
        messageDao.save(new Message(from, to, text));
    }

}
