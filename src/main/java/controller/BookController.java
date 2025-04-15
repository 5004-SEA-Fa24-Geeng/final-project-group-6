package controller;

import model.IBook;
import model.*;
import model.IBookList;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BookController implements IBookController {

private static final String CHECKIN_FILE = "Library.csv";
private static final String CHECKOUT_FILE = "checkoutBook.csv";


// Process book check-out (borrowing the book)
@Override
public void checkOutBooks(Collection<IBook> books, String ISBN, IBookList bookList) {
    for (IBook book : books) {
        if (book.getISBN().equals(ISBN)) {
            if (book instanceof IllegalBook) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("This is an ILLEGAL book. Enter the access password: ");
                String inputPassword = scanner.nextLine();
                IllegalBook illegalBook = (IllegalBook) book;

                if (illegalBook.validatePassword(inputPassword)) {
                    if (illegalBook.getStatus().equalsIgnoreCase("available")) {
                        book.setStatus("unavailable");
                        StandardBook replacement = illegalBook.getReplacement();
                        books.add(replacement); //add replacement book
                        bookList.updateLibrary(CHECKIN_FILE, ISBN, "unavailable");
                        bookList.updateCheckoutFile(CHECKOUT_FILE, book);
                        System.out.println("Illegal book checked out. A replacement has been added.");
                    } else {
                        System.out.println("This illegal book is currently unavailable.");
                    }
                } else {
                    System.out.println("Incorrect password. Access denied.");
                }
                return;
            } else { //StandardBook
                if (book.getStatus().equalsIgnoreCase("available")) {
                    book.setStatus("unavailable");
                    System.out.println(book.getBookTitle() + " checked out.");
                    bookList.updateLibrary(CHECKIN_FILE, ISBN, "unavailable");
                    bookList.updateCheckoutFile(CHECKOUT_FILE, book);

                } else {
                    System.out.println("This book is already checked out.");
                }
                return;
            }
        }
    }
    System.out.println("Book not found for checkout.");
}

// Process book check-in (returning the book)
@Override
public void checkInBooks(Collection<IBook> books, String ISBN, IBookList bookList) {
    for (IBook book : books) {
        if (book.getISBN().equals(ISBN)) {
            if (book.getStatus().equalsIgnoreCase("unavailable")) {
                book.setStatus("available");
                System.out.println(book.getBookTitle() + " checked in.");

                bookList.updateLibrary(CHECKIN_FILE, ISBN, "available");
            } else {
                System.out.println("This book is already checked in.");
            }
            return;
        }
    }
    System.out.println("Book not found for check-in.");
}}
