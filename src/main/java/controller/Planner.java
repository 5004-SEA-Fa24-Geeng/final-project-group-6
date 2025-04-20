package controller;

import model.BookData;
import model.IBook;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Sets up filters for the book data.
 */
public class Planner implements IPlanner {

    /** A Set of books.*/
    private Set<IBook> books;

    /**
     * Constructor to Planner.
     * @param books The set of Books.
     */
    public Planner(Set<IBook> books) {
        this.books = books;
    }

    /**
     * Assumes the results are sorted in ascending order, and that the stream is sorted by the author name
     * of the book
     * @param filter The filter to apply to the books
     * @return A stream of books that match the filter.
     */
    @Override
    public Stream<IBook> filter(String filter) {
        ISorts bookSorts = new Sorts();
        Stream<IBook> filteredStream = filterSingle(filter, books.stream()).sorted(bookSorts.getSorting(BookData.AUTHOR, true));
        return filteredStream;
    }

    /**
     * Filters based on a single command.
     * @param filter The filter to apply to the books.
     * @param filteredBooks The book stream to filter.
     * @return The filtered books.
     */
    private Stream<IBook> filterSingle(String filter, Stream<IBook> filteredBooks) {
        // handle getting operation, book attribute to filter on
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredBooks;
        }
        // remove spaces
        filter = filter.replaceAll(" ", "");

        String[] parts = filter.split(operator.getOperator());
        if (parts.length != 2) {
            return filteredBooks;
        }
        BookData column;
        try {
            column = BookData.fromString(parts[0]);
        } catch (IllegalArgumentException e) {
            return filteredBooks;
        }
        String value;
        try {
            value = parts[1].trim();
        } catch (IllegalArgumentException e) {
            return filteredBooks;
        }
        IFilters bookFilter = new Filters();
        List<IBook> filteredGameList = filteredBooks.filter(book -> bookFilter.filter(book,
                column, operator, value)).toList();
        return filteredGameList.stream();
    }

    /**
     * Filters the books by the passed in text filter. Assumes the results are sorted in
     * ascending order.
     *
     * @param filter The filter to apply to the books.
     * @param sortOn The column to sort the results on.
     * @return A stream of books that match the filter.
     */
    @Override
    public Stream<IBook> filter(String filter, BookData sortOn) {
        ISorts bookSorts = new Sorts();
        Stream<IBook> filteredStream = filterSingle(filter, books.stream()).sorted(bookSorts.getSorting(sortOn, true));
        return filteredStream;

    }

    /**
     * Filters the books by the passed in text filter.
     *
     * @param filter The filter to apply to the books.
     * @param sortOn The column to sort the results on.
     * @param ascending Whether to sort the results in ascending order or descending order.
     * @return A stream of books that match the filter.
     */
    @Override
    public Stream<IBook> filter(String filter, BookData sortOn, boolean ascending) {
        ISorts bookSorts = new Sorts();
        Stream<IBook> filteredStream = filterSingle(filter, books.stream()).sorted(bookSorts.getSorting(sortOn, ascending));
        return filteredStream;
    }
}
