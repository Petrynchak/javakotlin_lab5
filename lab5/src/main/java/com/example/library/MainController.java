package com.example.library;

import com.example.library.data.Book;
import com.example.library.data.BookRepository;
import com.example.library.data.JDBCBookRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> bookListView;
    @FXML
    private HBox topHBox;

    private List<Book> allBooks = new ArrayList<>();
    private BookRepository bookRepository;

    @FXML
    public void initialize() {
        topHBox.setPadding(new Insets(10));
        bookRepository = new JDBCBookRepository("jdbc:h2:~/test", "sa", ""); // Замість цього вкажіть свої налаштування підключення до бази даних
        loadBooks();
        updateBookListView();
    }

    @FXML
    public void handleSearch() {
        String query = searchField.getText().toLowerCase();
        List<Book> filteredBooks = allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query) ||
                        book.getAuthor().toLowerCase().contains(query))
                .collect(Collectors.toList());
        updateBookListView(filteredBooks);
        System.out.println("Searching for: " + query);
    }

    @FXML
    public void viewAllBooks() {
        updateBookListView(allBooks);
        System.out.println("Viewing all books");
    }

    @FXML
    public void showAddBookDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/library/add-book-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddBookController addBookController = fxmlLoader.getController();
            addBookController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Add New Book");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteSelectedBook() {
        String selectedBook = bookListView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Book bookToDelete = allBooks.stream()
                    .filter(book -> book.toString().equals(selectedBook))
                    .findFirst()
                    .orElse(null);
            if (bookToDelete != null) {
                bookRepository.deleteBook(bookToDelete.getId());
                allBooks.remove(bookToDelete);
                updateBookListView();
            }
        }
    }

    public void addBookToList(Book book) {
        bookRepository.save(book);
        allBooks.add(book);
        updateBookListView();
    }

    private void loadBooks() {
        allBooks = bookRepository.findAll();
    }

    private void updateBookListView() {
        bookListView.getItems().setAll(allBooks.stream().map(Book::toString).collect(Collectors.toList()));
    }

    private void updateBookListView(List<Book> books) {
        bookListView.getItems().setAll(books.stream().map(Book::toString).collect(Collectors.toList()));
    }
}
