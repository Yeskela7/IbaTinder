package dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t);

    void update(T t);

    void delete(T t);

    int getId(String s);

}
