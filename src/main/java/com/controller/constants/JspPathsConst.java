package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public final class JspPathsConst {
    //TODO add JSP paths params constants
    private JspPathsConst(){}

    private static final String VIEW_PATH="/WEB-INF";
    private static final String USER="/user";
    private static final String ADMIN="/admin";
    private static final String VIEW_EXTENSION=".jsp";

    /*COMMON*/
    public static final String ERROR_PAGE=VIEW_PATH+"errorPage"+VIEW_EXTENSION;
    public static final String LOGIN_REG_PAGE=VIEW_PATH+"loginPage"+VIEW_EXTENSION;

    /*USER*/
    public static final String USER_BOOKS=VIEW_PATH+USER+"booksPage"+VIEW_EXTENSION;
    public static final String USER_ORDER_LIST=VIEW_PATH+USER+"orderListPage"+VIEW_EXTENSION;
    public static final String USER_PROFILE=VIEW_PATH+USER+"profilePage"+VIEW_EXTENSION;

    /*ADMIN*/
    public static final String ADMIN_AUTHOR=VIEW_PATH+ADMIN+"authorPage"+VIEW_EXTENSION;
    public static final String ADMIN_BOOK=VIEW_PATH+ADMIN+"bookPage"+VIEW_EXTENSION;
    public static final String ADMIN_ORDER=VIEW_PATH+ADMIN+"orderPage"+VIEW_EXTENSION;
    public static final String ADMIN_PUBLISHER=VIEW_PATH+ADMIN+"publisherPage"+VIEW_EXTENSION;

}
