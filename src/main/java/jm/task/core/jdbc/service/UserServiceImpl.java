package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

        public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        try {
            // 1. Получаем соединение
            connection = Util.getConnection();

            // 2. Отключаем авто-коммит (как в примере)
            connection.setAutoCommit(false);

            // 3. Выполняем операцию сохранения
            UserDaoJDBCImpl daoImpl = (UserDaoJDBCImpl) userDao;
            daoImpl.saveUser(connection, name, lastName, age);

            // 4. Фиксируем транзакцию (как в примере)
            connection.commit();
            System.out.println("Транзакция успешно закоммичена для пользователя " + name);

        } catch (SQLException e) {
            e.printStackTrace();

            // 5. Откатываем при ошибке (как в примере)
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Транзакция для пользователя " + name + " откачена!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // 6. Закрываем соединение (как в примере)
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        userDao.getAllUsers();
        return null;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}