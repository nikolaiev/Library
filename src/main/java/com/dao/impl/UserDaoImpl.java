package com.dao.impl;

import com.dao.UserDao;
import com.dao.exception.DaoException;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final String SELECT_ALL="SELECT id, name, soname, login, pass, role" +
            "  FROM public.\"user\" ";

    private static final String SELECT_USER_BY_ID=SELECT_ALL+" WHERE id =?";

    private static final String DELETE_USER_BY_ID="DELETE FROM public.\"user\" WHERE id=?";

    private static final String UPDATE_USER_BY_ID="UPDATE public.\"user\" " +
            "   SET name=?, soname=?, login=?, pass=?, role=?" +
            " WHERE id=?";

    private static final String INSERT_USER="INSERT INTO public.\"user\"" +
            "(name, soname, login, pass, role)\n" +
            "    VALUES (?, ?, ?, ?, ?);";

    private static final String ID_FIELD="id";
    private static final String LOGIN_FIELD="login";
    private static final String NAME_FIELD="name";
    private static final String SONAME_FIELD="soname";
    private static final String PASSWORD_FIELD="pass";
    private static final String ROLE_FIELD="role";
    private static final String TABLE="public.\"user\"";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User insert(User user){
        checkForNull(user);
        checkIsUnsaved(user);
        try(PreparedStatement statement=connection.prepareStatement(INSERT_USER,
                Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,user.getName());
            statement.setString(2,user.getSoname());
            statement.setString(3,user.getPassword());
            statement.setString(4, String.valueOf(user.getRole()));
            executeInsertStatement(statement);

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING+ user.toString());
        }
        return user;
    }

    @Override
    public void update(User user) {
        checkForNull(user);
        checkIsSaved(user);
        try(PreparedStatement statement=connection.prepareStatement(UPDATE_USER_BY_ID)){
            statement.setString(1,user.getName());
            statement.setString(2,user.getSoname());
            statement.setString(3,user.getPassword());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_UPDATING+user.toString());
        }
    }

    @Override
    public List<User> getAll() {
        try(PreparedStatement statement=connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet=statement.executeQuery();
            return parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public Optional<User> getById(int id){

        try(PreparedStatement statement=connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            List<User> userList=parseResultSet(resultSet);
            checkSingleResult(userList);

            return userList.isEmpty()?
                    Optional.empty():
                    Optional.of(userList.get(0));

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public void removeById(int id) {
        super.deleteById(TABLE,id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return null;
    }

    private List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList=new ArrayList<>();
        while(resultSet.next()){
            User user=new User.Builder()
                    .setId(resultSet.getInt(ID_FIELD))
                    .setLogin(resultSet.getString(LOGIN_FIELD))
                    .setName(resultSet.getString(NAME_FIELD))
                    .setSoname(resultSet.getString(SONAME_FIELD))
                    .setPassword(resultSet.getString(PASSWORD_FIELD))
                    .setRole(UserRole.valueOf(resultSet.getString(ROLE_FIELD)))
                    .build();

            userList.add(user);
        }
        return userList;
    }
}
