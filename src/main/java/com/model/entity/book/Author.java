package com.model.entity.book;

import com.model.entity.Identified;

/**
 * Created by vlad on 20.03.17.
 */
public class Author implements Identified{
    private int id;
    private String name;
    private String soname;

    public Author(int id, String name, String soname) {
        this.id = id;
        this.name = name;
        this.soname = soname;
    }

    public Author(String name, String soname) {
        this.name = name;
        this.soname = soname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

