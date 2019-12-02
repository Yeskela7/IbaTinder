package dao.services;

import classes.Like;
import classes.User;
import dao.interfaces.Dao;
import dao.localstore.LikesDaoSql;
import dao.localstore.UsersDaoSql;

public class LikesDaoService {


    private Dao<Like> likesDao = new LikesDaoSql();



}
