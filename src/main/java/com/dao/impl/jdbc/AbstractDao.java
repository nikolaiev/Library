package com.dao.impl.jdbc;

import com.dao.GenericDao;
import com.dao.exception.DaoException;
import com.model.entity.Identified;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vlad on 17.03.17.
 */
public abstract class AbstractDao {
    static final String LOG_MESSAGE_DB_ERROR_WHILE_INSERTING = "Database error while inserting entity ";
    static final String LOG_MESSAGE_DB_ERROR_WHILE_UPDATING = "Database error while updating entity ";
    static final String LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL = "Database error while getting all ";
    static final String LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_ID = "Database error while getting by id ";
    static final String LOG_MESSAGE_DB_ERROR_WHILE_DELETING_BY_ID = "Database error while deleting entity with id = ";

    static final String FIELD_ID = "id";
    static final String ORDER_BY_ID = " ORDER BY id ";

    private static final String LOG_MESSAGE_DB_ERROR_UNEXPECTED_MULTIPLE_RESULT_WHILE_GETTING_BY_ID =
            "Unexpected multiple result while getting by id ";
    private static final String LOG_MESSAGE_DB_ERROR_CAN_NOT_CREATE_ALREADY_SAVED =
            "Can not insert already saved entity (id!=0): ";
    private static final String LOG_MESSAGE_DB_ERROR_CAN_NOT_UPDATE_EMPTY = "Can not update null entity ";
    private static final String LOG_MESSAGE_DB_ERROR_CAN_NOT_UPDATE_UNSAVED = "Can not update unsaved entity (id==0): ";
    private static final String LOG_MESSAGE_FORMAT_DB_ERROR_WHILE_DELETING_ENTITY_BY_ID =
            "Database error while deleting entity from table '%s' with id = %d";

    private static final String DELETE_SQL_FORMAT = "DELETE FROM %s WHERE id=?";

    /*Thread local variable to store connection inside simple thread*/
    final ThreadLocal<Connection> connection=new ThreadLocal<>();

    /*public AbstractDao (Connection connection){
        this.connection.set(connection);
    }*/

    /**
     * Provides check if the list has only one element
     *
     * @param list list to be checked on single element
     */
    void checkSingleResult(List list) {
        if ((list != null) && (list.size() > 1)) {
            throw new DaoException()
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_UNEXPECTED_MULTIPLE_RESULT_WHILE_GETTING_BY_ID);
        }
    }

    /**
     * @param statement statement to be executed
     * @return Generated entity id.
     * @throws SQLException in case of problems with executing statement
     */
    int executeInsertStatement(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        try (ResultSet keys = statement.getGeneratedKeys()) {
            keys.next();
            return keys.getInt(1);
        }
    }

    void checkForNull(Object entity) throws DaoException {
        if (entity == null) {
            throw new DaoException().addLogMessage(LOG_MESSAGE_DB_ERROR_CAN_NOT_UPDATE_EMPTY);
        }
    }


    void checkIsSaved(Identified entity) throws DaoException {
        if (entity.getId() == 0) {
            throw new DaoException()
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_CAN_NOT_UPDATE_UNSAVED + entity);
        }
    }

    void checkIsUnsaved(Identified entity) throws DaoException {
        if (entity.getId() != 0) {
            throw new DaoException()
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_CAN_NOT_CREATE_ALREADY_SAVED + entity);
        }
    }

    void deleteById(String table, int id) {

        try (PreparedStatement statement =
                     connection.get().prepareStatement(
                             String.format(DELETE_SQL_FORMAT, table))) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(String.format(LOG_MESSAGE_FORMAT_DB_ERROR_WHILE_DELETING_ENTITY_BY_ID, table, id));
        }
    }

    int getSimpleIntValueOrZero(final String FIELD_NAME, ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            return resultSet.getInt(FIELD_NAME);
        }

        return 0;
    }
}
