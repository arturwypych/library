package pl.edu.wszib.library.database;

import pl.edu.wszib.library.exceptions.BookDoesNotExistException;
import pl.edu.wszib.library.exceptions.CannotDeleteBookException;
import pl.edu.wszib.library.model.Book;

import java.util.List;

public interface IBookRepository {
    void rentBook(int id);
    Book addBook(String title, String author, Integer year);
    int generateId();
    List<Book> searchBooks(String searchTerm);
    void deleteBook(int id) throws CannotDeleteBookException;
    Book findBookById(int id) throws BookDoesNotExistException;
    void editBook(Book oldDataBook, String newTitle, String newAuthor, Integer newYear);
    List<Book> getBooks();

}
