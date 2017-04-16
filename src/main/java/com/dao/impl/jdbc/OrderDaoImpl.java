package com.dao.impl.jdbc;

import com.dao.OrderDao;
import com.dao.exception.DaoException;
import com.model.entity.book.*;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class OrderDaoImpl extends AbstractDao implements OrderDao {
    private static final String SELECT_ALL="SELECT ord_id, uid, bid, type, status, cdate, name, soname, login, pass, \n" +
            "       role, author_id, publisher_id, genre, lang, pdate, publisher_title, \n" +
            "       author_name, author_soname, title, count\n" +
            "  FROM public.order_full_view ";

    private static final String SELECT_ORDER_BY_ID=SELECT_ALL+" WHERE ord_id =?";
    private static final String SELECT_ORDER_BY_USER_ID=SELECT_ALL+" WHERE uid =?";

    private static final String UPDATE_ORDER_BY_ID="UPDATE public.\"order\" " +
            "   SET uid=?, bid=?, status=?, type=?, cdate=?" +
            " WHERE id=?";
    private static final String UPDATE_ORDER_STATUS_BY_ID="UPDATE public.\"order\" " +
            "   SET status=?" +
            " WHERE id=?";

    private static final String INSERT_ORDER="INSERT INTO public.\"order\"" +
            " (uid, bid, status, type)\n" +
            "    VALUES (?, ?, ?, ?);";


    /*author fields*/
    private static final String ID_FIELD_AUTHOR="author_id";
    private static final String NAME_FIELD_AUTHOR="author_name";
    private static final String SONAME_FIELD_AUTHOR="author_soname";

    /*publisher fields*/
    private static final String ID_FIELD_PUBLISHER="publisher_id";
    private static final String TITLE_FIELD_PUBLISHER="publisher_title";

    /*book fields*/
    private static final String ID_FIELD_BOOK="bid";

    /*user fields*/
    private static final String ID_FIELD_USER_ORDER="uid";
    /*order fields*/
    private static final String ID_FIELD_ORDER ="ord_id";
    private static final String STATUS_FIELD_ORDER ="status";
    private static final String TYPE_FIELD_ORDER ="type";
    private static final String CREATE_DATE_FIELD_ORDER ="cdate";
    private static final String TABLE="public.\"order\"";

    /*order by*/
    private static final String ORDER_BY_CDATE_DESC="ORDER BY cdate DESC";

    private static class InstanceHolder{
        private static OrderDaoImpl INSTANCE=new OrderDaoImpl();
    }

    public static OrderDao getInstance(Connection connection){
        /*set ThreadLocal variable*/
        InstanceHolder.INSTANCE.connection.set(connection);
        return InstanceHolder.INSTANCE;
    }

    private OrderDaoImpl(){}

    @Override
    public Order insert(Order order) {
        checkForNull(order);
        checkIsUnsaved(order);
        try(PreparedStatement statement=connection.get().prepareStatement(INSERT_ORDER,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,order.getUser().getId());
            statement.setInt(2,order.getBook().getId());
            statement.setString(3,order.getStatus().toString());
            statement.setString(4,order.getType().toString());
            executeInsertStatement(statement);

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING);
        }

        return order;
    }

    @Override
    public void update(Order order) {
        checkForNull(order);
        checkIsSaved(order);
        try(PreparedStatement statement=connection.get().prepareStatement(UPDATE_ORDER_BY_ID)) {
            statement.setInt(1,order.getUser().getId());
            statement.setInt(2,order.getBook().getId());
            statement.setString(3,order.getStatus().toString());
            statement.setString(4,order.getType().toString());
            statement.setObject(5,order.getOrderDateTime());
            statement.setInt(6,order.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_UPDATING);
        }
    }

    @Override
    public void updateOrderStatus(Order order){
        checkForNull(order);
        checkIsSaved(order);

        try(PreparedStatement statement=connection.get().prepareStatement(UPDATE_ORDER_STATUS_BY_ID)) {
            statement.setString(1,order.getStatus().toString());
            statement.setInt(2,order.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_UPDATING);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        try (PreparedStatement statement=connection.get().prepareStatement(SELECT_ORDER_BY_USER_ID +ORDER_BY_CDATE_DESC)){
            statement.setInt(1,userId);
            return parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_ID);
        }
    }

    @Override
    public List<Order> getAll(){
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_ALL+ORDER_BY_CDATE_DESC)) {
            return parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public Optional<Order> getById(int id){
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_ORDER_BY_ID)) {
            statement.setInt(1,id);
            List<Order> orderList=parseResultSet(statement.executeQuery());
            checkSingleResult(orderList);

            return orderList.isEmpty()?
                    Optional.empty():
                    Optional.of(orderList.get(0));

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_BY_ID);
        }
    }

    @Override
    public void removeById(int id) {
        super.deleteById(TABLE,id);
    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        return null;
    }

    private List<Order> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Order> orderList=new ArrayList<>();
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
                    //.setDate(resultSet.getObject(BookDaoImpl.PUBLISH_DATE_FIELD_BOOK, LocalDateTime.class))
                    .setDate(((Timestamp)resultSet.getObject(BookDaoImpl.PUBLISH_DATE_FIELD_BOOK)).toLocalDateTime())

                    .setGenre(BookGenre.valueOf(resultSet.getString(BookDaoImpl.GENRE_FIELD_BOOK)))
                    .setTitle(resultSet.getString(BookDaoImpl.TITLE_FIELD_BOOL))
                    .setLanguage(BookLanguage.valueOf(resultSet.getString(BookDaoImpl.LANG_FIELD_BOOK)))
                    .build();

            User user =new User.Builder()
                    .setId(resultSet.getInt(ID_FIELD_USER_ORDER))
                    .setRole(UserRole.valueOf(resultSet.getString(UserDaoImpl.ROLE_FIELD_USER)))
                    .setName(resultSet.getString(UserDaoImpl.NAME_FIELD_USER))
                    .setSoname(resultSet.getString(UserDaoImpl.SONAME_FIELD_USER))
                    .setLogin(resultSet.getString(UserDaoImpl.LOGIN_FIELD_USER))
                    .setPassword(resultSet.getString(UserDaoImpl.PASSWORD_FIELD_USER))
                    .build();

            Order order=new Order.Builder()
                    .setUser(user)
                    .setBook(book)
                    .setId(resultSet.getInt(ID_FIELD_ORDER))
                    .setStatus(OrderStatus.valueOf(resultSet.getString(STATUS_FIELD_ORDER)))
                    .setType(OrderType.valueOf(resultSet.getString(TYPE_FIELD_ORDER)))
                    //.setOrderDateTime(resultSet.getObject(CREATE_DATE_FIELD_ORDER,LocalDate.class))
                    .setOrderDateTime(((Timestamp)resultSet.getObject(CREATE_DATE_FIELD_ORDER)).toLocalDateTime())
                    .build();

            orderList.add(order);
        }

        return  orderList;
    }
}
