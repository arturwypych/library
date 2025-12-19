package pl.edu.wszib.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String author;
    private Integer year;
    private boolean rent;

    public Book(int id, String title, String author, Integer year) {
        this(id, title, author, year, false);
    }
    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.rent = false;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getId())
                .append("\t\t\t")
                .append(this.getTitle())
                .append("\t\t\t")
                .append(this.getAuthor())
                .append("\t\t\t")
                .append(this.getYear())
                .append("\t\t\t")
                .append(this.isRent() ? "Rented" : "Available")
                .toString();
    }
}
