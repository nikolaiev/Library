package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public final class  UrlsConst {
    private UrlsConst(){}
    private static final String USER="/user";
    private static final String ADMIN="/admin";

    /*COMMON*/
    public static final String LOGIN="/login";
    public static final String REGISTER="/register";
    public static final String LOGOUT="/logout";
    public static final String MAPPED_STATIC="/static/{path}";


    private static final String BOOKS="/books";
    private static final String AUTHORS="/authors";
    private static final String PUBLISHERS="/publishers";
    private static final String ORDERS="/orders";

    private static final String BOOK="/book";
    private static final String AUTHOR="/author";
    private static final String PUBLISHER="/publisher";
    private static final String ORDER="/order";

    /*USER*/

    public static final String USER_BOOKS=USER+BOOKS;
    public static final String USER_BOOKS_ADD=USER+BOOKS+"/add";
    public static final String USER_BOOKS_REMOVE=USER+BOOKS+"/remove";

    public static final String USER_ORDERS=USER+"/orders";
    public static final String USER_PROFILE=USER+"/profile";
    public static final String USER_PROCESS_ORDERS=USER+"/process";


    /*ADMIN*/
    private static final String ADD="/add";
    private static final String REMOVE="/remove";
    private static final String UPDATE="/update";
    private static final String MAPPED_ID="/{id}";

    //books
    public static final String ADMIN_BOOKS = ADMIN+BOOKS;
    public static final String ADMIN_BOOK_ADD = ADMIN+BOOK+ADD;
    public static final String ADMIN_BOOK= ADMIN+BOOK+MAPPED_ID;
    public static final String ADMIN_BOOK_UPDATE = ADMIN+BOOK+UPDATE;
    //public static final String ADMIN_BOOKS_UPDATE = ADMIN+BOOKS+UPDATE;

    //authors
    public static final String ADMIN_AUTHORS = ADMIN+AUTHORS;
    public static final String ADMIN_AUTHORS_ADD = ADMIN+AUTHORS+ADD;
    public static final String ADMIN_AUTHORS_REMOVE = ADMIN+AUTHORS+REMOVE;
    public static final String ADMIN_AUTHORS_UPDATE= ADMIN+AUTHORS+UPDATE;

    //publishers
    public static final String ADMIN_PUBLISHERS = ADMIN+PUBLISHERS;
    public static final String ADMIN_PUBLISHERS_ADD = ADMIN+PUBLISHERS+ADD;
    public static final String ADMIN_PUBLISHERS_REMOVE = ADMIN+PUBLISHERS+REMOVE;
    public static final String ADMIN_PUBLISHERS_UPDATE = ADMIN+PUBLISHERS+UPDATE;

    //orders
    public static final String ADMIN_ORDERS = ADMIN+ORDERS;
    public static final String ADMIN_ORDERS_UPDATE = ADMIN+ORDERS+UPDATE;
}
