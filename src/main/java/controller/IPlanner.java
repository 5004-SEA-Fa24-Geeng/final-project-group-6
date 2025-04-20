package controller;

import model.BookData;
import model.IBook;

import java.util.stream.Stream;

/**
 * Sets up filters for the book data.
 */
public interface IPlanner {
    /**
     * Assumes the results are sorted in ascending order, and that the stream is sorted by the author name
     * of the book
     * @param filter The filter to apply to the books
     * @return A stream of books that match the filter.
     */
    Stream<IBook> filter(String filter);

    /**
     * Filters the books by the passed in text filter. Assumes the results are sorted in
     * ascending order.
     *
     * @param filter The filter to apply to the books.
     * @param sortOn The column to sort the results on.
     * @return A stream of books that match the filter.
     */
    Stream<IBook> filter(String filter, BookData sortOn);

    /**
     * Filters the books by the passed in text filter.
     *
     * @param filter The filter to apply to the books.
     * @param sortOn The column to sort the results on.
     * @param ascending Whether to sort the results in ascending order or descending order.
     * @return A stream of books that match the filter.
     */
    Stream<IBook> filter(String filter, BookData sortOn, boolean ascending);
}