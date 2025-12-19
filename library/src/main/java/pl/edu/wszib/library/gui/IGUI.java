package pl.edu.wszib.library.gui;

import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.User;

import java.util.List;

public interface IGUI {
    String showMenuAndReadChoose(boolean isAdmin);
    int readId();
    String searchBooks();
    String readTitle(boolean allowEmpty);
    String readAuthor(boolean allowEmpty);
    Integer readYear(boolean allowEmpty);
    void listBooks(List<Book> books);
    void showRentSuccessMessage(boolean success);
    void showWrongOptionMessage();
    User readLoginAndPassword();
    void showExceptionMessage(String message);

}
