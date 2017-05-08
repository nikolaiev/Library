package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public final class RegExConst {

    private RegExConst(){}

    public static final String BOOK_TITLE_REG_EX="^[A-Za-zА-Яа-яіІїЇґҐєЄ\\s\'0-9]{4,40}$";
    public static final String LOGIN_REG_EX ="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,15}$";
    public static final String NAME_SONAME_REG_EX ="^[A-Za-zА-Яа-яіІїЇґҐєЄ]{2,25}$";
    public static final String PUBLISHER_TITLE_REG_EX ="^[A-Za-zА-Яа-яіІїЇґҐєЄ\\s\'0-9]{4,40}$";
}
