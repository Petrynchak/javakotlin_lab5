package com.example.library.data;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    List<Book> findAll();
    void deleteBook(String id);
}
