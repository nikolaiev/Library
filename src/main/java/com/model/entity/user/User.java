package com.model.entity.user;

import com.model.entity.IdContainer;

/**
 * Created by vlad on 20.03.17.
 */
public class User extends IdContainer {

    private int id;
    private String login;
    private String password;
    private String name;
    private String soname;
    private UserRole role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoname() {
        return soname;
    }

    public void setSoname(String soname) {
        this.soname = soname;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public static class Builder{
        User user=new User();

        public Builder setId(int id){
            user.setId(id);
            return this;
        }

        public Builder setName(String name){
            user.setName(name);
            return this;
        }

        public Builder setSoname(String soname){
            user.setSoname(soname);
            return this;
        }

        public Builder setLogin(String login){
            user.setLogin(login);
            return this;
        }

        public Builder setRole(UserRole role){
            user.setRole(role);
            return this;
        }

        public Builder setPassword(String password){
            user.setPassword(password);
            return this;
        }

        public User build(){
            //TODO check values
            return user;
        }
    }

}
