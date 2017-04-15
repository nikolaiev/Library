package com.dao.impl.jdbc;

import com.dao.BookDao;
import com.dao.exception.DaoException;
import com.model.entity.book.*;

import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 17.03.17.
 */

//TODO replace errors in DaoException!
//TODO check if we need PrepareStatement in select All!
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

    private static final String BY_TITLE_FILTER=" title like '%'||?||'%' ";



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
            "            aid, pid, genre, lang, pdate, title,image)" +
            "    VALUES (?, ?, ?, ?, ?, ?,?);";


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
    public List<Book> getBooksByTitleLimitOffset(String title,int limit,int offset) {
        try (PreparedStatement statement=connection.get()
                .prepareStatement(SELECT_ALL+WHERE+BY_TITLE_FILTER+LIMIT_OFFSET)){
            statement.setString(1,title);
            statement.setInt(2,limit);
            statement.setInt(3,offset);

            return parseResultSet(statement.executeQuery());
        }
        catch (SQLException e){
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_FILTERED_ROW);
        }
    }

    @Override
    public List<Book> getAllLimitOffset(int limit, int offset) {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_LIMIT_OFFSET)){
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            System.out.println(statement);
            return  parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_SIMPLE_FIELD);
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
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_ALL)){
            return  parseResultSet(statement.executeQuery());
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
                    //.setDate(resultSet.getObject(PUBLISH_DATE_FIELD_BOOK, LocalDateTime.class))
                    //TODO переделать
                    .setDate(((Timestamp)resultSet.getObject(PUBLISH_DATE_FIELD_BOOK)).toLocalDateTime())
                    //.setDate(resultSet.getObject(7, LocalDateTime.class))
                    .setGenre(BookGenre.valueOf(resultSet.getString(GENRE_FIELD_BOOK)))
                    .setTitle(resultSet.getString(TITLE_FIELD_BOOL))
                    .setLanguage(BookLanguage.valueOf(resultSet.getString(LANG_FIELD_BOOK)))
                    .setImage(resultSet.getString(IMAGE_PATH_FIELD_BOOK))
                    .build();
            bookList.add(book);
        }
        return  bookList;
    }
}
