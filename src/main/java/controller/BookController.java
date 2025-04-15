package controller;

import model.IBook;
import model.*;
import model.IBookList;

import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BookController implements IBookController {
//    private final IBookList bookList;
//    private final IFilters filters;
//    private final ISorts sorts;
//
//    public BookController(IBookList bookList, IFilters filters, ISorts sorts) {
//        this.bookList = bookList;
//        this.filters = filters;
//        this.sorts = sorts;
//    }
//
//    @Override
//    public void checkOutBooks(String isbn) {
//        IBook book = bookList.getBookByISBN(isbn);
//        if (book == null) {
//            System.out.println("Book with ISBN " + isbn + " not found.");
//            return;
//        }
//        if (!book.getStatus().equalsIgnoreCase("available")) {
//            System.out.println("Book is not available for checkout.");
//            return;
//        }
//        book.setStatus("unavailable");
//        System.out.println("Checked out: " + book.getBookTitle());
//    }
//
//    @Override
//    public void checkInBooks(String isbn) {
//        IBook book = bookList.getBookByISBN(isbn);
//        if (book == null) {
//            System.out.println("Book with ISBN " + isbn + " not found.");
//            return;
//        }
//        if (!book.getStatus().equalsIgnoreCase("unavailable")) {
//            System.out.println("Book is not currently checked out.");
//            return;
//        }
//        book.setStatus("available");
//        System.out.println("Checked in: " + book.getBookTitle());
//    }
//
//    @Override
//    public void displayCheckoutList() {
//        Set<IBook> checkedOutBooks = bookList.getAllBooks().stream()
//                .filter(book -> book.getStatus().equalsIgnoreCase("unavailable"))
//                .collect(Collectors.toSet());
//        if (checkedOutBooks.isEmpty()) {
//            System.out.println("No books currently checked out.");
//        } else {
//            checkedOutBooks.forEach(book ->
//                    System.out.println(book.getBookTitle() + " by " + book.getAuthor() +
//                            " (ISBN: " + book.getISBN() + ")"));
//        }
//    }
//}

// Process book check-out (borrowing the book)
@Override
public void checkOutBooks(Collection<IBook> books, String ISBN) {
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
public void checkInBooks(Collection<IBook> books, String ISBN) {
    for (IBook book : books) {
        if (book.getISBN().equals(ISBN)) {
            if (book.getStatus().equalsIgnoreCase("unavailable")) {
                book.setStatus("available");
                System.out.println(book.getBookTitle() + " checked in.");
            } else {
                System.out.println("This book is already checked in.");
            }
            return;
        }
    }
    System.out.println("Book not found for check-in.");
}}
