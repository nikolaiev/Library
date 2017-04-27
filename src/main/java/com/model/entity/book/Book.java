package com.model.entity.book;

import com.model.entity.IdContainer;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Created by vlad on 16.03.17.
 */
public class Book extends IdContainer{
    private String title;
    private Author author;
    private BookGenre genre;
    private BookLanguage language;
    private Publisher publisher;
    private Instant instant;
    private String image;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public BookLanguage getLanguage() {
        return language;
    }

    public void setLanguage(BookLanguage language) {
        this.language = language;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public static class Builder{
        Book book=new Book();

        public Builder setId(int id){
            book.setId(id);
            return this;
        }

        public Builder setTitle(String title){
            book.setTitle(title);
            return this;
        }

        public Builder setAuthor(Author author){
            book.setAuthor(author);
            return this;
        }

        public Builder setGenre(BookGenre genre){
            book.setGenre(genre);
            return this;
        }

        public Builder setLanguage(BookLanguage language){
            book.setLanguage(language);
            return this;
        }

        public Builder setPublisher(Publisher publisher){
            book.setPublisher(publisher);
            return this;
        }

        public Builder setInstant(Instant instant){
            book.setInstant(instant);
            return this;
        }

        public Builder setCount(int count){
            book.setCount(count);
            return this;
        }

        public Builder setImage(String image){
            book.setImage(image);
            return this;
        }

        public Book build(){
            return book;
        }
    }
}
