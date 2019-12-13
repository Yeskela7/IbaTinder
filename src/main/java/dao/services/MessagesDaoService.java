package dao.services;

import classes.Message;
import dao.interfaces.Dao;
import dao.localstore.MessagesDaoSql;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MessagesDaoService {

    private Dao<Message> messageDao = new MessagesDaoSql();

    public List<Message> getMessageFrom(int userId) throws SQLException {
        return messageDao.getAll().stream().filter(id -> id.getFrom() == userId)
                .collect(Collectors.toList());
    }

    public List<Message> getMessageTo(int userId) throws SQLException {
        return messageDao.getAll().stream().filter(id -> id.getTo() == userId)
                .collect(Collectors.toList());
    }

    public List<Message> getMessagesFromAToB(int from, int to) throws SQLException {
        return messageDao.getAll().stream()
                .filter(message -> message.getFrom()
                        == from && message.getTo() == to)
                .collect(Collectors.toList());
    }

    public List<Message> getMessages(int userOne, int userTwo) throws SQLException {
        return messageDao.getAll().stream()
                .filter(message -> message.getFrom() == userOne || message.getFrom() == userTwo)
                .filter(message -> message.getTo() == userOne || message.getTo() == userTwo)
                .collect(Collectors.toList());
    }

//    public List<Message> getMessagesPair(int from, int to) throws SQLException {
//        return messageDao.getAll().stream()
//                .filter(message -> message.getFrom() == from || message.getFrom() == to)
//                .filter(message -> message.getTo() == to || message.getTo() == from)
//                .collect(Collectors.toList());
//    }

    public void saveMessage(int from, int to, String text, long time) {
        messageDao.save(new Message(from, to, text, time));
    }

}
