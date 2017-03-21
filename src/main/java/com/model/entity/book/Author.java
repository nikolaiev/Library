package com.model.entity.book;

/**
 * Created by vlad on 20.03.17.
 */
public class Author {
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

