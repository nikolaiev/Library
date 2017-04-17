package com.dao.impl.jdbc;

import com.dao.BookDao;
import com.dao.exception.DaoException;
import com.model.entity.book.*;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by vlad on 17.03.17.
 */

public class BookDaoImpl extends  AbstractDao implements BookDao{

    private static final String GET_BOOKS_AVAIlABLE_AMOUNT =
            "SELECT count-count_in_use as count FROM book WHERE id=?";

    private static final String SELECT_ALL="SELECT id,  author_id, publisher_id, title, genre, " +
            "lang, pdate, publisher_title, " +
            "       author_name, author_soname, image" +
            "  FROM public.book_full_view ";

    private static final String LIMIT_OFFSET=" limit ? offset ?";

    private static final String SELECT_LIMIT_OFFSET=SELECT_ALL+LIMIT_OFFSET;

    private static final String SELECT_BOOK_BY_ID=SELECT_ALL+" WHERE id =?";

    private static final String BY_AUTHOR_FILTER=" author_id=? ";

    private static final String BY_LANG_FILTER=" lang=? ";

    private static final String BY_GENRE_FILTER=" genre=? ";

    private static final String BY_PUBLISHER_FILTER=" publisher_id=? ";

    private static final String BY_TITLE_FILTER=" lower(title) like lower('%'||?||'%') ";



    private static final String UPDATE_BOOK_BY_ID="UPDATE public.book " +
            "   SET aid=?, pid=?, genre=?, lang=?, pdate=?, title=? ,image=?" +
            " WHERE id=?";

    private static final String UPDATE_GRANTED_BOOK="UPDATE public.book " +
            "   SET count_in_use=count_in_use+1 " +
            " WHERE id=?";

    private static final String UPDATE_RETURNED_BOOK="UPDATE public.book " +
            "   SET count_in_use=count_in_use-1 " +
            " WHERE id=?";

    private static final String INSERT_BOOK="INSERT INTO public.book(" +
            "            aid, pid, genre, lang, pdate, title,image,count)" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String ID_FIELD_AUTHOR="author_id";
    private static final String NAME_FIELD_AUTHOR="author_name";
    private static final String SONAME_FIELD_AUTHOR="author_soname";

    private static final String ID_FIELD_PUBLISHER="publisher_id";
    private static final String TITLE_FIELD_PUBLISHER="publisher_title";


    public static final String ID_FIELD_BOOK="id";
    public static final String LANG_FIELD_BOOK="lang";
    public static final String GENRE_FIELD_BOOK="genre";
    public static final String TITLE_FIELD_BOOL="title";
    public static final String PUBLISH_DATE_FIELD_BOOK="pdate";
    public static final String IMAGE_PATH_FIELD_BOOK="image";

    public static final String TABLE="book";
    public static final String WHERE=" WHERE ";
    public static final String AND=" AND ";

    private static final String LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD ="Database error while getting simple field";
    private static final String LOG_MESSAGE_DB_ERROR_WHILE_GETTING_FILTERED_ROW ="Database error while getting filtered row";

    private static class InstanceHolder{
        private static BookDaoImpl INSTANCE=new BookDaoImpl();
    }

    public static BookDao getInstance(Connection connection){
        /*set ThreadLocal variable*/
        InstanceHolder.INSTANCE.connection.set(connection);
        return InstanceHolder.INSTANCE;
    }

    private BookDaoImpl(){}


    @Override
    public int getCountAvailable(int bookId){
        int count=0;

        try(PreparedStatement statement=connection.get().prepareStatement(GET_BOOKS_AVAIlABLE_AMOUNT)){
            statement.setInt(1,bookId);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                count=resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
        }

        return count;
    }

    @Override
    public void grantBook(int bookId) {
        try(PreparedStatement statement=connection.get().prepareStatement(UPDATE_GRANTED_BOOK)){
            statement.setInt(1,bookId);
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
        }
    }

    @Override
    public void returnBook(int bookId) {
        try(PreparedStatement statement=connection.get().prepareStatement(UPDATE_RETURNED_BOOK)){
            statement.setInt(1,bookId);
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
        }
    }

    @Override
    public List<Book> getAllLimitOffset(int limit, int offset) {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_LIMIT_OFFSET)){
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            return  parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
        }
    }

    @Override
    public List<Book> getBooksByParams(String title, Integer authorId, BookGenre genre, BookLanguage language, Integer publisherId, int limit, int offset) {

        try(PreparedStatement statement=new SelectQueryBuilder().getQuery(title,authorId,genre,language,publisherId,limit,offset)){
            return  parseResultSet(statement.executeQuery());
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_FILTERED_ROW);
        }
    }

    @Override
    public Book insert(Book book) {
        checkForNull(book);
        checkIsUnsaved(book);
        try(PreparedStatement statement=connection.get().prepareStatement(INSERT_BOOK,
                Statement.RETURN_GENERATED_KEYS)) {
            //  aid, pid, genre, lang, pdate, title, image
            statement.setInt(1,book.getAuthor().getId());
            statement.setInt(2,book.getPublisher().getId());
            statement.setString(3,book.getGenre().toString());
            statement.setString(4,book.getLanguage().toString());
            statement.setObject(5,book.getDate());
            statement.setString(6,book.getTitle());
            statement.setString(7,book.getImage());
            statement.setInt(8,book.getCount());

            executeInsertStatement(statement);

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING + book.toString());
        }

        return book;
    }

    @Override
    public void update(Book book) {
        checkForNull(book);
        checkIsSaved(book);
        try(PreparedStatement statement=connection.get().prepareStatement(UPDATE_BOOK_BY_ID)) {
            //aid=?, pid=?, genre=?, lang=?, pdate=?, title=? " +
            //" WHERE id=?
            statement.setInt(1,book.getAuthor().getId());
            statement.setInt(2,book.getPublisher().getId());
            statement.setString(3,book.getGenre().toString());
            statement.setString(4,book.getLanguage().toString());
            statement.setObject(5,book.getDate());
            statement.setString(6,book.getTitle());
            statement.setString(7,book.getImage());
            statement.setInt(8,book.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING + book.toString());
        }
    }

    @Override
    public List<Book> getAll() {
        try(Statement statement=connection.get().createStatement()){
            return  parseResultSet(statement.executeQuery(SELECT_ALL));
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
        }
    }

    @Override
    public Optional<Book> getById(int key) {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_BOOK_BY_ID)){
            statement.setInt(1,key);

            List<Book> bookList=parseResultSet(statement.executeQuery());
            checkSingleResult(bookList);

            return bookList.isEmpty()?
                    Optional.empty():
                    Optional.of(bookList.get(0));

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
        }
    }

    @Override
    public void removeById(int key) {
        super.deleteById(TABLE,key);
    }

    private List<Book> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Book> bookList = new ArrayList<>();

        while (resultSet.next()){
            Author author=new Author(resultSet.getInt(ID_FIELD_AUTHOR),
                    resultSet.getString(NAME_FIELD_AUTHOR),
                    resultSet.getString(SONAME_FIELD_AUTHOR));

            Publisher publisher=new Publisher(resultSet.getInt(ID_FIELD_PUBLISHER),
                    resultSet.getString(TITLE_FIELD_PUBLISHER));

            Book book=new Book.Builder()
                    .setId(resultSet.getInt(ID_FIELD_BOOK))
                    .setAuthor(author)
                    .setPublisher(publisher)
                    .setDate(((Date)resultSet.getObject(PUBLISH_DATE_FIELD_BOOK)).toLocalDate())
                    .setGenre(BookGenre.valueOf(resultSet.getString(GENRE_FIELD_BOOK)))
                    .setTitle(resultSet.getString(TITLE_FIELD_BOOL))
                    .setLanguage(BookLanguage.valueOf(resultSet.getString(LANG_FIELD_BOOK)))
                    .setImage(resultSet.getString(IMAGE_PATH_FIELD_BOOK))
                    .build();
            bookList.add(book);
        }
        return  bookList;
    }

    /**
     * Inner helper class to build complex select query
     */
    class SelectQueryBuilder {
        /*Map to hold parameters data
        Integer - > order number in preparedStatement
        Object -> data to set
        */
        HashMap<Integer,Object> params =new HashMap<>();

        /*initial where close*/
        StringBuilder whereQuery=new StringBuilder();

        /*initial statement parameter index*/
        Integer paramIndex=1;


        PreparedStatement getQuery(String title, Integer authorId, BookGenre genre, BookLanguage language, Integer publisherId, int limit, int offset) throws SQLException {

            addFilterParam(title,BY_TITLE_FILTER);
            addFilterParam(authorId,BY_AUTHOR_FILTER);
            addFilterParam(genre,BY_GENRE_FILTER);
            addFilterParam(language,BY_LANG_FILTER);
            addFilterParam(publisherId,BY_PUBLISHER_FILTER);

            return getPreparedStatement(limit,offset);
        }

        void addFilterParam(Object val,String whereClose){
            if(val!=null && !val.equals("")){
                //if at least one param was added
                if(paramIndex!=1){
                    whereQuery.append(AND);
                }

                whereQuery.append(whereClose);
                params.put(paramIndex,val);
                paramIndex++;
            }
        }

        PreparedStatement getPreparedStatement(int limit,int offset) throws SQLException {
            //if at least one param was added
            if(paramIndex!=1){
                //prepend
                whereQuery.insert(0,WHERE);
            }

            final String RESULT_QUERY=SELECT_ALL + whereQuery.toString() + LIMIT_OFFSET;

            PreparedStatement resultStatement=connection.get().prepareStatement(RESULT_QUERY);

            /*set param values*/
            for(Map.Entry<Integer,Object> entry:params.entrySet()){

                Integer index=entry.getKey();
                Object param=entry.getValue();

                if(param instanceof String || param.getClass().isEnum()){
                    resultStatement.setString(index,param.toString());
                }
                else{
                    resultStatement.setInt(index,(Integer)param);
                }
            }

            /*set limit offset*/
            resultStatement.setInt(paramIndex++,limit);
            resultStatement.setInt(paramIndex,offset);

            return  resultStatement;
        }
    }
}


