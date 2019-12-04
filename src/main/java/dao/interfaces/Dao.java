package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    T get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t);

    void update(T t);

    void delete(T t);

}
