package com.dao.jdbc;

import com.dao.BookDao;
import com.dao.exception.DaoException;
import com.dao.jdbc.helper.ConditionSelectQueryBuilder;
import com.model.entity.book.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by vlad on 17.03.17.
 */

public class BookDaoImpl extends  AbstractDao implements BookDao{

    private static Logger logger= Logger.getLogger(BookDaoImpl.class);

    private static final String GET_BOOKS_AVAILABLE_AMOUNT =
            "SELECT count-count_in_use as count FROM book WHERE id=?";

    private static final String SELECT_ALL="SELECT id,  author_id, publisher_id, title, genre, " +
            "lang, pdate, publisher_title, " +
            "       author_name, author_soname, image , count" +
            "  FROM public.book_full_view ";

    private static final String SELECT_COUNT="SELECT count(*) as count from \"book_full_view\" ";

    private static final String LIMIT_OFFSET=" limit ? offset ?";

    private static final String SELECT_LIMIT_OFFSET=SELECT_ALL+LIMIT_OFFSET;

    private static final String SELECT_BOOK_BY_ID=SELECT_ALL+" WHERE id =?";

    private static final String BY_AUTHOR_FILTER=" author_id=? ";

    private static final String BY_LANG_FILTER=" lang=? ";

    private static final String BY_GENRE_FILTER=" genre=? ";

    private static final String BY_PUBLISHER_FILTER=" publisher_id=? ";

    private static final String BY_TITLE_FILTER=" lower(title) like lower('%'||?||'%') ";


    private static final String UPDATE_BOOK_CLOSE="UPDATE public.book ";
    private static final String WHERE_ID_CLOSE=" WHERE id=? ";

    private static final String UPDATE_BOOK_BY_ID = UPDATE_BOOK_CLOSE +
            "   SET aid=?, pid=?, genre=?, lang=?, pdate=?, title=? ,image=?,count=?" +
            WHERE_ID_CLOSE;

    private static final String UPDATE_GRANTED_BOOK = UPDATE_BOOK_CLOSE +
            "   SET count_in_use=count_in_use+1 " +
            WHERE_ID_CLOSE;

    private static final String UPDATE_RETURNED_BOOK = UPDATE_BOOK_CLOSE +
            "   SET count_in_use=count_in_use-1 " +
            WHERE_ID_CLOSE;

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
    public static final String TITLE_FIELD_BOOL= "title";
    public static final String PUBLISH_DATE_FIELD_BOOK="pdate";
    public static final String IMAGE_PATH_FIELD_BOOK="image";
    public static final String COUNT_FIELD="count";

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

        try(PreparedStatement statement=connection.get().prepareStatement(GET_BOOKS_AVAILABLE_AMOUNT)){
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
        ConditionSelectQueryBuilder queryBuilder=new ConditionSelectQueryBuilder(connection.get());

        queryBuilder.addFilterParam(title,BY_TITLE_FILTER);
        queryBuilder.addFilterParam(authorId,BY_AUTHOR_FILTER);
        queryBuilder.addFilterParam(genre,BY_GENRE_FILTER);
        queryBuilder.addFilterParam(language,BY_LANG_FILTER);
        queryBuilder.addFilterParam(publisherId,BY_PUBLISHER_FILTER);

        try(PreparedStatement statement=queryBuilder.getPreparedStatementLimitOffset(SELECT_ALL,limit,offset)){
            return  parseResultSet(statement.executeQuery());
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_FILTERED_ROW);
        }
    }

    @Override
    public int getBooksCountByParams(String title, Integer authorId, BookGenre genre, BookLanguage language, Integer publisherId) {
        ConditionSelectQueryBuilder queryBuilder=new ConditionSelectQueryBuilder(connection.get());

        queryBuilder.addFilterParam(title,BY_TITLE_FILTER);
        queryBuilder.addFilterParam(authorId,BY_AUTHOR_FILTER);
        queryBuilder.addFilterParam(genre,BY_GENRE_FILTER);
        queryBuilder.addFilterParam(language,BY_LANG_FILTER);
        queryBuilder.addFilterParam(publisherId,BY_PUBLISHER_FILTER);

        try(PreparedStatement statement=queryBuilder.getPreparedStatement(SELECT_COUNT)){

            ResultSet resultSet=statement.executeQuery();

            return getSimpleIntValueOrZero(COUNT_FIELD,resultSet);
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_FILTERED_ROW);
        }
    }

    @Override
    public boolean updateIfPossible(Book newBook) {
        int bookId=newBook.getId();

        Book oldBook=getById(bookId).get();

        /*check if new book count
            is not less than books count in use*/

        int countAvailable=getCountAvailable(bookId);
        int booksInUse=oldBook.getCount()-countAvailable;
        int newBookCount=newBook.getCount();

        if(booksInUse>newBookCount){
            return false;
        }

        update(newBook);
        return true;
    }

    @Override
    public Book insert(Book book) {
        checkForNull(book);
        checkIsUnsaved(book);
        try(PreparedStatement statement=connection.get().prepareStatement(INSERT_BOOK,
                Statement.RETURN_GENERATED_KEYS)) {
            //  aid, pid, genre, lang, pdate, title, image, count

            statement.setInt(1,book.getAuthor().getId());
            statement.setInt(2,book.getPublisher().getId());
            statement.setString(3,book.getGenre().toString());
            statement.setString(4,book.getLanguage().toString());
            statement.setObject(5, LocalDateTime.ofInstant(book.getInstant(),ZoneId.systemDefault()).toLocalDate());
            statement.setString(6,book.getTitle());
            statement.setString(7,book.getImage());
            statement.setInt(8,book.getCount());

            logger.info("Insert method"+statement);



            int id=executeInsertStatement(statement);
            book.setId(id);

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
            //aid=?, pid=?, genre=?, lang=?, pdate=?, title=? ,image=? , count=?" +
            //" WHERE id=?
            statement.setInt(1,book.getAuthor().getId());
            statement.setInt(2,book.getPublisher().getId());
            statement.setString(3,book.getGenre().toString());
            statement.setString(4,book.getLanguage().toString());
            statement.setObject(5,LocalDateTime.ofInstant(book.getInstant(),ZoneId.systemDefault()).toLocalDate());
            statement.setString(6,book.getTitle());
            statement.setString(7,book.getImage());
            statement.setInt(8,book.getCount());
            statement.setInt(9,book.getId());

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
                    .setInstant(resultSet.getTimestamp(PUBLISH_DATE_FIELD_BOOK).toInstant())
                    .setGenre(BookGenre.valueOf(resultSet.getString(GENRE_FIELD_BOOK)))
                    .setTitle(resultSet.getString(TITLE_FIELD_BOOL))
                    .setLanguage(BookLanguage.valueOf(resultSet.getString(LANG_FIELD_BOOK)))
                    .setImage(resultSet.getString(IMAGE_PATH_FIELD_BOOK))
                    .setCount(resultSet.getInt(COUNT_FIELD))
                    .build();
            bookList.add(book);
        }
        return  bookList;
    }
}


