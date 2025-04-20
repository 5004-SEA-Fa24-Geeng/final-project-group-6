package controller;

import model.BookData;
import model.IBook;

/**
 * A class that filters the books.
 */
public interface IFilters {
    /**
     * controller.Filters the books.
     * @param book The book.
     * @param column name of the type of data.
     * @param op operation done.
     * @param value value to compare with the value of the book.
     * @return if the filter done on the book is true or not
     */
    public boolean filter(IBook book, BookData column, Operations op, String value);

    /**
     * Filter based on string.
     * @param bookData name of the book.
     * @param op Operation done.
     * @param value value to compare with the value of the book.
     * @return if the filter done on the book is true or not.
     */
    public boolean filterString(String bookData, Operations op, String value);

}
