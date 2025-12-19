package pl.edu.wszib.library.gui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.User;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class GUI implements IGUI {
    private final Scanner scanner;

    @Override
    public String showMenuAndReadChoose(boolean isAdmin) {
        System.out.println("1. List all books");
        System.out.println("2. Filter books");
        System.out.println("3. Rent book");
        if(isAdmin) {
            System.out.println("4. Add book");
            System.out.println("5. Edit book");
            System.out.println("6. Delete book");
        }
        System.out.println("7. Exit");
        System.out.println();

        return this.scanner.nextLine();
    }

    @Override
    public int readId() {
        System.out.println("ID:");

        return Integer.parseInt(this.scanner.nextLine());
    }
    @Override
    public void showExceptionMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void listBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public String readTitle(boolean allowEmpty) {
        while (true) {
            System.out.println("Enter Title" + (allowEmpty ? " (Enter to skip):" : ":"));
            String input = this.scanner.nextLine().trim();

            if (!input.isEmpty() || allowEmpty) {
                return input;
            }

            System.out.println("Title cannot be empty.");
        }
    }

    public String readAuthor(boolean allowEmpty) {
        while (true) {
            System.out.println("Enter Author" + (allowEmpty ? " (Enter to skip):" : ":"));
            String input = this.scanner.nextLine().trim();

            if (allowEmpty && input.isEmpty()) {
                return "";
            }

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Author cannot be empty.");
        }
    }

    public Integer readYear(boolean allowEmpty) {
        while (true) {
            System.out.println("Enter year of publication" + (allowEmpty ? " (Enter to skip):" : ":"));
            String input = scanner.nextLine().trim();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }

            try {
                Integer year = Integer.parseInt(input);
                if (year > 0) {
                    return year;
                } else {
                    System.out.println("Year must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    public String searchBooks() {
        System.out.println("Enter search value:");
        return this.scanner.nextLine().toLowerCase();
    }

    @Override
    public void showRentSuccessMessage(boolean success) {
        System.out.println(
                success ?
                        "Book rented successfully." :
                        "Cannot rent the book.");
    }

    @Override
    public void showWrongOptionMessage() {
        System.out.println("Wrong option. Please try again.");
    }

    @Override
    public User readLoginAndPassword() {
        System.out.println("Login: ");
        String login = this.scanner.nextLine();
        System.out.println("Password: ");
        return new User(login, this.scanner.nextLine());
    }
}
