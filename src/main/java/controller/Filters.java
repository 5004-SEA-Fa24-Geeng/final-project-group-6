package controller;

import model.BookData;
import model.IBook;

/**
 * A class that filters the books.
 */
public class Filters implements IFilters {
    /**
     * Constructor
     */
    public Filters() {}

    /**
     * controller.Filters the books.
     * @param book The book.
     * @param column name of the type of data.
     * @param op operation done.
     * @param value value to compare with the value of the book.
     * @return if the filter done on the book is true or not
     */
    @Override
    public boolean filter(IBook book, BookData column, Operations op, String value) {
        switch (column) {
            case CATEGORY:
                return filterString(book.getCategory(), op, value);
            case AUTHOR:
                return filterString(book.getAuthor(), op, value);
            case TITLE:
                return filterString(book.getBookTitle(), op, value);
            case ISBN:
                return filterString(book.getISBN(), op, value);
            default:
                return false;
        }
    }

    /**
     * Filter based on string.
     * @param bookData name of the book.
     * @param op Operation done.
     * @param value value to compare with the value of the book.
     * @return if the filter done on the book is true or not.
     */
    @Override
    public boolean filterString(String bookData, Operations op, String value) {
        switch (op) {
            case EQUALS:
                return bookData.replaceAll(" ", "").equalsIgnoreCase(value.replaceAll(" ", ""));
            case CONTAINS:
                return bookData.toLowerCase().replaceAll(" ", "").contains(value.toLowerCase().replaceAll(" ", ""));
            default:
                return false;
        }
    }
}
