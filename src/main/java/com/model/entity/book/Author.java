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

    public Author() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        return soname != null ? soname.equals(author.soname) : author.soname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (soname != null ? soname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", soname='" + soname + '\'' +
                '}';
    }
}

