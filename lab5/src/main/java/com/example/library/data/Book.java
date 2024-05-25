package com.example.library.data;

public class Book {
    private String id;
    private String code;
    private String title;
    private String author;
    private int year;
    private int copies;

    public Book() {
    }

    public Book(String id, String code, String title, String author, int year, int copies) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.author = author;
        this.year = year;
        this.copies = copies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return code + ": " + title + " by " + author + " (" + year + ") - " + copies + " copies";
    }
}
