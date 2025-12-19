package pl.edu.wszib.library.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.authentication.IAuthenticator;
import pl.edu.wszib.library.database.IBookRepository;
import pl.edu.wszib.library.exceptions.BookDoesNotExistException;
import pl.edu.wszib.library.exceptions.CanNotRentBookException;
import pl.edu.wszib.library.exceptions.CannotDeleteBookException;
import pl.edu.wszib.library.gui.IGUI;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.User;

@Component
@RequiredArgsConstructor
public class Core implements ICore{
    private final IBookRepository bookRepository;
    private final IGUI gui;
    private final IAuthenticator authenticator;

    @Override
    public void run() {
        User user = gui.readLoginAndPassword();
        boolean isAuthenticated = authenticator.authenticate(
                user.getLogin(),
                user.getPassword());

        boolean isAdmin = user.getLogin().equals("admin");
        while(isAuthenticated) {
            String choice = gui.showMenuAndReadChoose(isAdmin);
            switch (choice) {
                case "1":
                    gui.listBooks(bookRepository.getBooks());
                    break;
                case "2":
                    gui.listBooks(bookRepository.searchBooks(gui.searchBooks()));
                    break;
                case "3":
                    try {
                        bookRepository.rentBook(gui.readId());
                        gui.showRentSuccessMessage(true);
                    } catch (CanNotRentBookException e) {
                        gui.showRentSuccessMessage(false);
                    }
                    break;
                case "4":
                    if(isAdmin) {
                        String title = gui.readTitle(false);
                        String author = gui.readAuthor(false);
                        Integer year = gui.readYear(false);
                        bookRepository.addBook(title, author, year);
                        break;
                    }
                    else{
                        gui.showWrongOptionMessage();
                    }
                    break;
                case "5":
                    if(isAdmin){
                        try {
                            Book oldBook = bookRepository.findBookById(gui.readId());
                            String newTitle = gui.readTitle(true);
                            String newAuthor = gui.readAuthor(true);
                            Integer newYear = gui.readYear(true);
                            bookRepository.editBook(oldBook, newTitle, newAuthor, newYear);
                        }catch (BookDoesNotExistException e){
                            gui.showExceptionMessage("Book not found.");
                        }
                        break;
                    }
                    else{
                        gui.showWrongOptionMessage();
                    }
                    break;
                case "6":
                    if(isAdmin) {
                        try {
                            bookRepository.deleteBook(gui.readId());
                        }catch (CannotDeleteBookException e){
                            gui.showExceptionMessage("Book does not exist.");
                        }
                    }
                    else{
                        gui.showWrongOptionMessage();
                    }
                    break;
                case "7":
                    return;
                default:
                    if(!choice.isEmpty())
                        gui.showWrongOptionMessage();
                    break;
            }
        }
    }
}
