package com.controller.validation.model;

/**
 * Created by vlad on 23.04.17.
 */
public class RegistrationData extends LoginData {
    private String name;
    private String soname;
    private String passwordConfirm;

    public RegistrationData(String email, String password,  String passwordConfirm, String name, String soname) {
        super(email, password);
        this.name = name;
        this.soname = soname;
        this.passwordConfirm = passwordConfirm;
    }

    public String getName() {
        return name;
    }

    public String getSoname() {
        return soname;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}
