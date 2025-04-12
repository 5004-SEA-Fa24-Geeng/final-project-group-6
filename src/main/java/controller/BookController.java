package controller;

import model.IBook;
import model.IllegalBook;
import model.StandardBook;

import java.util.Collection;
import java.util.Scanner;

public class BookController {
    private String checkOutFile;
    private String checkInFile;

    // Constructor, initialize files
    public BookController(String checkOutFile, String checkInFile) {
        this.checkOutFile = checkOutFile;
        this.checkInFile = checkInFile;
    }

    // Process book check-out (borrowing the book)
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
    }

    // Helper method to update book status (could be implemented with DataHandler)
    private void updateBookStatus(IBook book, String newStatus) {
        book.setStatus(newStatus);

    }

}
