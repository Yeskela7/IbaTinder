package dao.services;

import classes.User;
import dao.interfaces.Dao;
import dao.localstore.UsersDaoSql;

import java.util.List;
import java.util.stream.Collectors;

public class UsersDaoService {

    private Dao<User> usersDao = new UsersDaoSql();

    public List<User> getAllUsers() {
        return usersDao.getAll();
    }

    public List<User> getUsersWithEmail(String email) {
        return usersDao.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithName(String name) {
        return usersDao.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithSurname(String surname) {
        return usersDao.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    public User getUser(int userId) {
        return usersDao.get(userId);
    }


    public void addUser(String email, String name, String surname, String picUrl, int age) {
        usersDao.save(new User(email, name, surname, picUrl, age));
    }

    public int getUserId(User user){
        return usersDao.getAll().indexOf(user);
    }
}
