package dao.interfaces;

import java.util.List;

public interface DAO<T> {

    T get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

}
