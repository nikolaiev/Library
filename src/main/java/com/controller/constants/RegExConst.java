package com.controller.constants;

/**
 * Created by vlad on 19.04.17.
 */
public class RegExConst {
    private RegExConst(){}

    public static String LOGIN_REG_EX ="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static String NAME_SONAME_REG_EX ="^[A-Za-zА-Яа-я]{2,20}$";
}
