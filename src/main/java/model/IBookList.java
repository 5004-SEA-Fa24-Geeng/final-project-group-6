package model;

import java.util.List;
import java.util.stream.Stream;

public interface IBookList {

    /**
     * @return book list.
     */
    List<IBook> getBooks();

    /**
     * books to add to (check in or check out) list.
     *
     * @param input    String to parse.
     * @param filtered filtered collection to use for adding.
     * @throws IllegalArgumentException if string is invalid.
     */
    void addToList(String input, Stream<IBook> filtered) throws IllegalArgumentException;


    /**
     * clear all books from list.
     */
    void clear();

    /**
     * Update the library file.
     *
     * @param filename     file containing library books.
     * @param isbnToUpdate isbn for book that needs updating.
     * @param newStatus    status you want to change to(available/unavailable).
     */
    void updateLibrary(String filename, String isbnToUpdate, String newStatus);

    /**
     * update checkout file.
     *
     * @param filename designated checkout file.
     * @param book     book to be checked out.
     */
    void updateCheckoutFile(String filename, IBook book);

    /**
     * remove book from list by isbn.
     *
     * @param input data of book to remove.
     */
    void removeFromList(String input);

    /**
     * @return no. of books.
     */
    int count();
}
