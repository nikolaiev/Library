package com.model.entity.user;

import com.model.entity.Identified;

/**
 * Created by vlad on 20.03.17.
 */
public class User implements Identified{

    private int id;
    private String login;
    private String password;
    private String fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    public static class Builder{
        User user=new User();

        public Builder setId(int id){
            user.setId(id);
            return this;
        }

        public Builder setFullName(String fullName){
            user.setFullName(fullName);
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
