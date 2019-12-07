package dao.services;

import classes.User;
import dao.interfaces.Dao;
import dao.localstore.UsersDaoSql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDaoService {

    private Dao<User> usersDao = new UsersDaoSql();

    public List<User> getAllUsers() throws SQLException {
        return usersDao.getAll();
    }

    public User getUsersWithEmail(String email) throws SQLException {
        return new ArrayList<>(usersDao.getAll()).stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList()).get(0);
    }

    public List<User> getUsersWithName(String name) throws SQLException {
        return usersDao.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithSurname(String surname) throws SQLException {
        return usersDao.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    public User getUser(int userId) throws SQLException {
        return usersDao.get(userId);
    }


    public void addUser(String email, String name, String surname, String picUrl, int age, int password) {
        usersDao.save(new User(email, name, surname, picUrl, age, password));
    }

    public int getUserIdByMail(String mail) throws SQLException {
        return usersDao.getId(mail);
    }

    public int getIdByUser(User user) throws SQLException {
        return usersDao.getAll().indexOf(user);
    }
}
