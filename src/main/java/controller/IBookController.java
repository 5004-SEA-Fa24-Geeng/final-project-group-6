package controller;

/**
 * Interface for book checkout/check-in controller logic.
 */
public interface IBookController {

    /**
     * Check out a book by its ISBN. May require password if it's an illegal book.
     * @param ISBN the ISBN of the book to check out.
     */
    void checkOutBooks(String ISBN);

    /**
     * Check in a book by its ISBN (return).
     * @param ISBN the ISBN of the book to check in.
     */
    void checkInBooks(String ISBN);

    /**
     * Display all books currently in the checkout list.
     */
    void displayCheckoutList();

}
