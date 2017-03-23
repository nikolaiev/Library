package com.dao.impl;

import com.dao.PublisherDao;
import com.dao.exception.DaoException;
import com.model.entity.book.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class PublisherDaoImpl extends AbstractDao implements PublisherDao {
    private static final String SELECT_ALL="SELECT id, title" +
            "  FROM public.publisher";

    private static final String SELECT_PUBLISHER_BY_ID=SELECT_ALL+" WHERE id =?";
    private static final String SELECT_PUBLISHER_BY_TITLE=SELECT_ALL+" WHERE title =?";

    private static final String DELETE_PUBLISHER_BY_ID="DELETE FROM publisher WHERE id=?";

    private static final String UPDATE_PUBLISHER_BY_ID="UPDATE public.publisher" +
            "   SET title=?" +
            " WHERE id=?";

    private static final String INSERT_PUBLISHER="INSERT INTO " +
            " public.publisher(title)" +
            "    VALUES (?)";

    private static final String ID_FIELD="id";
    private static final String TITLE_FIELD="title";
    private static final String TABLE="publisher";

    public PublisherDaoImpl(Connection connection) {
        super(connection);
    }

    /*@Override
    public Publisher create(){
        return null;
    }
   */

    @Override
    public Publisher insert(Publisher obj){
        checkForNull(obj);
        checkIsUnsaved(obj);
        try(PreparedStatement statement=connection.prepareStatement(INSERT_PUBLISHER)){
            statement.setString(1,obj.getTitle());
            int id=executeInsertStatement(statement);
            obj.setId(id);

        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING+ obj.toString());
        }

        return obj;
    }

    @Override
    public void update(Publisher obj) {
        checkForNull(obj);
        checkIsSaved(obj);
        try(PreparedStatement statement =connection.prepareStatement(UPDATE_PUBLISHER_BY_ID)){
            statement.setString(1,obj.getTitle());
            statement.setInt(2,obj.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_UPDATING);
        }
    }

    @Override
    public List<Publisher> getAll() {
        try(PreparedStatement statement=connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet=statement.executeQuery()){
            return parseResultSet(resultSet);

        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public Optional<Publisher> getById(int id) {
        try(PreparedStatement statement=connection.prepareStatement(SELECT_PUBLISHER_BY_ID)){
            statement.setInt(1,id);

            try(ResultSet resultSet = statement.executeQuery()) {
                List<Publisher> publisherList = parseResultSet(resultSet);
                checkSingleResult(publisherList);

                return publisherList.isEmpty() ?
                        Optional.empty() :
                        Optional.of(publisherList.get(0));
            }
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public void removeById(int id) {
        super.deleteById(TABLE,id);
    }

    @Override
    public Optional<Publisher> getPublisherByTitle(String title) {
        return null;
    }

    private List<Publisher> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Publisher> publisherList=new ArrayList<>();

        while (resultSet.next()){
            Publisher publisher=new Publisher(resultSet.getInt(ID_FIELD),
                    resultSet.getString(TITLE_FIELD));
            publisherList.add(publisher);
        }
        return publisherList;
    }
}
