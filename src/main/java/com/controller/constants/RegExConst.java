package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public final class RegExConst {

    private RegExConst(){}

    public static final String LOGIN_REG_EX ="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String NAME_SONAME_REG_EX ="^[A-Za-zА-Яа-я]{2,20}$";
}
