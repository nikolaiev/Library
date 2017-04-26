package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public final class JspPathsConst {

    private JspPathsConst(){}

    private static final String VIEW_PATH="/WEB-INF/view";
    private static final String USER="/user";
    private static final String ADMIN="/admin";
    private static final String BOOKS="/books";
    private static final String PUBLISHERS="/publisher";
    private static final String ORDERS="/orders";
    private static final String AUTHORS="/authors";
    private static final String VIEW_EXTENSION=".jsp";

    /*COMMON*/
    public static final String ERROR_VIEW = VIEW_PATH+"/errorPage"+VIEW_EXTENSION;
    public static final String LOGIN_REG_VIEW = VIEW_PATH+"/loginPage"+VIEW_EXTENSION;

    /*USER*/
    public static final String USER_BOOKS_VIEW = VIEW_PATH+USER+"/booksPage"+VIEW_EXTENSION;
    public static final String USER_ORDER_LIST_VIEW = VIEW_PATH+USER+"/orderListPage"+VIEW_EXTENSION;
    public static final String USER_PROFILE_VIEW = VIEW_PATH+USER+"/profilePage"+VIEW_EXTENSION;

    /*ADMIN*/
    public static final String ADMIN_AUTHOR_VIEW = VIEW_PATH+ADMIN+AUTHORS+"/authorsPage"+VIEW_EXTENSION;
    public static final String ADMIN_BOOK_VIEW = VIEW_PATH+ADMIN+BOOKS+"/booksPage"+VIEW_EXTENSION;
    public static final String ADMIN_ORDER_VIEW = VIEW_PATH+ADMIN+ORDERS+"/ordersPage"+VIEW_EXTENSION;
    public static final String ADMIN_PUBLISHER_VIEW = VIEW_PATH+ADMIN+PUBLISHERS+"/publishersPage"+VIEW_EXTENSION;

    /*books crud*/
    public static final String ADMIN_EDIT_BOOK_VIEW =VIEW_PATH+ADMIN+BOOKS+"/bookEditPage"+VIEW_EXTENSION;
    public static final String ADMIN_ADD_BOOK_VIEW =VIEW_PATH+ADMIN+BOOKS+"/bookAddPage"+VIEW_EXTENSION;

}
