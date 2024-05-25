package com.example.library;

import com.example.library.data.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.UUID;

public class AddBookController {
    @FXML
    private TextField codeField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField copiesField;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void addBook(ActionEvent actionEvent) {
        String id = UUID.randomUUID().toString();
        String code = codeField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        int copies = Integer.parseInt(copiesField.getText());
        if (!code.isEmpty() && !title.isEmpty() && !author.isEmpty()) {
            Book book = new Book(id, code, title, author, year, copies);
            mainController.addBookToList(book);
        }
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
