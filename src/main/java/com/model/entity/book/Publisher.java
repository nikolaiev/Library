package com.model.entity.book;


import com.model.entity.IdContainer;

/**
 * Created by vlad on 20.03.17.
 */
public class Publisher  extends IdContainer{
    private int id;
    private String title;

    public Publisher(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Publisher() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
