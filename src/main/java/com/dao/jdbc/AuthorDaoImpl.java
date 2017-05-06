package com.dao.jdbc;

import com.dao.AuthorDao;
import com.dao.exception.DaoException;
import com.model.entity.book.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class AuthorDaoImpl extends AbstractDao implements AuthorDao{
    private static final String LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_NAME ="Database error while getting by name ";
    private static final String LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_NAME_SONAME ="Database error while getting by name soname ";

    private static final String SELECT_ALL="SELECT id, name, soname" +
            "  FROM public.author";
    private static final String SELECT_AUTHOR_BY_ID=SELECT_ALL+" WHERE id =?";
    private static final String SELECT_AUTHOR_BY_NAME=SELECT_ALL+" WHERE name LIKE '%'||?||'%'";
    private static final String SELECT_AUTHOR_BY_NAME_SONAME=SELECT_ALL+" WHERE lower(name)= lower(?)  and lower(soname)= lower(?) LIMIT 1";

    private static final String UPDATE_AUTHOR_BY_ID="UPDATE public.author" +
            "   SET name=?, soname=?" +
            " WHERE id=?";

    private static final String INSERT_AUTHOR="INSERT INTO public.author(\n" +
            "            name, soname)\n" +
            "    VALUES (?, ?)";

    private static final String ID_FIELD="id";
    private static final String NAME_FIELD="name";
    private static final String SONAME_FIELD="soname";
    private static final String TABLE="author";

    @Override
    public List<Author> getAuthorsByName(String name) {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_AUTHOR_BY_NAME)){
            statement.setString(1,name);
            try (ResultSet resultSet=statement.executeQuery()){
                return parseResultSet(resultSet);
            }
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_NAME + name);
        }
    }

    @Override
    public Optional<Author> getAuthorByNameSoname(String name, String soname) {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_AUTHOR_BY_NAME_SONAME)){
            statement.setString(1,name);
            statement.setString(2,soname);
            try (ResultSet resultSet=statement.executeQuery()){
                List<Author> list=parseResultSet(resultSet);
                checkSingleResult(list);
                return list.isEmpty() ?
                        Optional.empty()
                        : Optional.of(list.get(0));
            }
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_NAME_SONAME + name+ " "+ soname);
        }
    }

    private static class InstanceHolder{
        private static AuthorDaoImpl INSTANCE=new AuthorDaoImpl();
    }

    public static AuthorDao getInstance(Connection connection){
        /*set ThreadLocal variable*/
        InstanceHolder.INSTANCE.connection.set(connection);
        return InstanceHolder.INSTANCE;
    }

    private AuthorDaoImpl(){}

    @Override
    public List<Author> getAll()  {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_ALL);
            ResultSet resultSet=statement.executeQuery()){
                return parseResultSet(resultSet);
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public Optional<Author> getById(int id)  {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_AUTHOR_BY_ID)){
            statement.setInt(1,id);
            try (ResultSet resultSet=statement.executeQuery()){
                List<Author> list=parseResultSet(resultSet);
                checkSingleResult(list);
                return list.isEmpty() ?
                        Optional.empty()
                        : Optional.of(list.get(0));
            }
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_ID + id);
        }
    }

    @Override
    public Author insert(Author author){
        checkForNull(author);
        checkIsUnsaved(author);

        try(PreparedStatement statement=connection.get().prepareStatement(INSERT_AUTHOR,
                Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,author.getName());
            statement.setString(2,author.getSoname());
            int id=executeInsertStatement(statement);
            author.setId(id);
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING+author.toString());
        }

        return author;
    }

    @Override
    public void update(Author author) {
        checkForNull(author);
        checkIsSaved(author);

        try(PreparedStatement statement=connection.get().prepareStatement(UPDATE_AUTHOR_BY_ID)){
            statement.setString(1,author.getName());
            statement.setString(2,author.getSoname());
            statement.setInt(3,author.getId());
            statement.executeUpdate();
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_UPDATING+author.toString());
        }
    }

    @Override
    public void removeById(int id) {
        super.deleteById(TABLE,id);
    }

    private List<Author> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Author> authorList=new ArrayList<>();
        while (resultSet.next()){
            Author author=new Author(resultSet.getInt(ID_FIELD),
                    resultSet.getString(NAME_FIELD),
                    resultSet.getString(SONAME_FIELD));
            authorList.add(author);
        }

        return authorList;
    }
}
