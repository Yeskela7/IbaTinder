package dao.services;

import classes.User;
import dao.interfaces.Dao;
import dao.localstore.UsersDaoSql;

import java.sql.SQLException;
import java.util.List;

public class UsersDaoService {

    private Dao<User> usersDao = new UsersDaoSql();

    public List<User> getAllUsers() {
        try {
            return usersDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUser(int userId) {
        try {
            return usersDao.get(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(String email, String name, String surname, String picUrl, int age, int password) {
        usersDao.save(new User(email, name, surname, picUrl, age, password));
    }

    public int getUserIdByMail(String mail) {
        return usersDao.getId(mail);
    }

}
