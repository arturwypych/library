package pl.edu.wszib.library.database;

import org.springframework.stereotype.Component;
import pl.edu.wszib.library.exceptions.BookDoesNotExistException;
import pl.edu.wszib.library.exceptions.CanNotRentBookException;
import pl.edu.wszib.library.exceptions.CannotDeleteBookException;
import pl.edu.wszib.library.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class BookRepository implements IBookRepository {

    private final Scanner scanner;
    private final List<Book> books = new ArrayList<>();

    public BookRepository(Scanner scanner) {
        this.scanner = scanner;
        this.books.add(new Book(1, "Metro 2033", "Dmitry Glukhovsky", 2005));
        this.books.add(new Book(2, "Metro 2034", "Dmitry Glukhovsky", 2009));
        this.books.add(new Book(3, "Metro 2035", "Dmitry Glukhovsky", 2015));
        this.books.add(new Book(4, "The Girl with All the Gifts", "M.R. Carey", 2014));
        this.books.add(new Book(5, "Bird Box", "Josh Malerman", 2014));
        this.books.add(new Book(6, "Wool", "Hugh Howey", 2011));
        this.books.add(new Book(7, "The Passage", "Justin Cronin", 2010));
        this.books.add(new Book(8, "The Fireman", "Joe Hill", 2016));
        this.books.add(new Book(9, "One Second After", "William R. Forstchen", 2009));
        this.books.add(new Book(10, "Station Eleven", "Emily St. John Mandel", 2014));
    }

    @Override
    public void rentBook(int id) {
        for(Book c : this.books) {
            if(c.getId() == id && !c.isRent()) {
                c.setRent(true);
                return;
            }
        }
        throw new CanNotRentBookException();
    }

    @Override
    public Book addBook(String title, String author, Integer year) {
        Book book = new Book(generateId(), title, author, year);
        this.books.add(book);
        return book;
    }

    @Override
    public int generateId() {
        return this.books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
    }

    @Override
    public void deleteBook(int id) throws CannotDeleteBookException {
        boolean removed = this.books.removeIf(book -> (book.getId() == id) && !book.isRent());
        if (!removed) {
            throw new CannotDeleteBookException();
        }
    }

    @Override
    public void editBook(Book oldDataBook, String newTitle, String newAuthor, Integer newYear){
        if(!newTitle.isEmpty()){oldDataBook.setTitle(newTitle);}
        if(!newAuthor.isEmpty()){oldDataBook.setAuthor(newAuthor);}
        if(newYear != null){oldDataBook.setYear(newYear);}
    }

    @Override
    public Book findBookById(int id) throws BookDoesNotExistException {
        for(Book book : this.books)
            if (book.getId() == id) {
                return book;
            }
        throw new BookDoesNotExistException();
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Book> searchBooks(String searchTerm) {
        String searchTermLowerCase = searchTerm.toLowerCase();
        return books.stream()
                .filter(b ->
                        b.getTitle().toLowerCase().contains(searchTermLowerCase) ||
                        b.getAuthor().toLowerCase().contains(searchTermLowerCase))
                .collect(Collectors.toList());
    }

}
