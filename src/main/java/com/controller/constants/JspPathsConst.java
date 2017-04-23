package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public final class JspPathsConst {
    //TODO add JSP paths params constants
    private JspPathsConst(){}

    private static final String VIEW_PATH="/WEB-INF/view";
    private static final String USER="/user";
    private static final String ADMIN="/admin";
    private static final String MODEL="/model";
    private static final String VIEW_EXTENSION=".jsp";

    /*COMMON*/
    public static final String ERROR_VIEW = VIEW_PATH+"/errorPage"+VIEW_EXTENSION;
    public static final String LOGIN_REG_VIEW = VIEW_PATH+"/loginPage"+VIEW_EXTENSION;

    /*USER*/
    public static final String USER_BOOKS_VIEW = VIEW_PATH+USER+"/booksPage"+VIEW_EXTENSION;
    public static final String USER_ORDER_LIST_VIEW = VIEW_PATH+USER+"/orderListPage"+VIEW_EXTENSION;
    public static final String USER_PROFILE_VIEW = VIEW_PATH+USER+"/profilePage"+VIEW_EXTENSION;

    /*ADMIN*/
    public static final String ADMIN_AUTHOR_VIEW = VIEW_PATH+ADMIN+"/authorsPage"+VIEW_EXTENSION;
    public static final String ADMIN_BOOK_VIEW = VIEW_PATH+ADMIN+"/booksPage"+VIEW_EXTENSION;
    public static final String ADMIN_ORDER_VIEW = VIEW_PATH+ADMIN+"/ordersPage"+VIEW_EXTENSION;
    public static final String ADMIN_PUBLISHER_VIEW = VIEW_PATH+ADMIN+"/publishersPage"+VIEW_EXTENSION;

    public static final String ADMIN_SINGLE_BOOK_VIEW=VIEW_PATH+ADMIN+MODEL+"/bookPage"+VIEW_EXTENSION;

}
