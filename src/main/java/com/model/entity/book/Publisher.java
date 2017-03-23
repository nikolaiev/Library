package com.model.entity.book;

import com.model.entity.Identified;

/**
 * Created by vlad on 20.03.17.
 */
public class Publisher  implements Identified{
    private int id;
    private String title;

    public Publisher(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }
}
