package com.controller.validation.model;

/**
 * Created by vlad on 23.04.17.
 */
public class LoginData {
    /*email*/
    private String login;
    private String password;

    public LoginData(String email, String password) {
        this.login = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
