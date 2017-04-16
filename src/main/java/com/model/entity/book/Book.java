package com.model.entity.book;

import com.model.entity.Identified;
import com.model.entity.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by vlad on 16.03.17.
 */
public class Book implements Identified {
    private int id;
    private String title;
    private Author author;
    private BookGenre genre;
    private BookLanguage language;
    private Publisher publisher;
    private LocalDate date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", language=" + language +
                ", publisher=" + publisher +
                ", date=" + date +
                ", image='" + image + '\'' +
                ", count=" + count +
                '}';
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

        public Builder setDate(LocalDate date){
            book.setDate(date);
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
            //TODO check values
            return book;
        }
    }
}
