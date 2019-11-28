package dao.services;

import classes.User;
import dao.interfaces.Dao;
import dao.localstore.UsersDaoSql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDaoService {

    private Dao<User> usersDao = new UsersDaoSql();

    public List<User> getAllUsers() {
        return usersDao.getAll();
    }

    public List<User> getUsersWithNickname(String nickname) {
        return usersDao.getAll().stream()
                .filter(user -> user.getName().equalsIgnoreCase(nickname))
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

    public List<User> getUsersOver(int age) {
        return usersDao.getAll().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public List<User> getUsersUnder(int age) {
        return usersDao.getAll().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public List<User> getFilteredUsersByAge(int min, int max){
        return usersDao.getAll().stream().filter(user -> user.getAge() > min)
                .filter(user -> user.getAge() < max).collect(Collectors.toList());
    }

    public User getUser(int userId){
        return usersDao.get(userId);
    }
}
