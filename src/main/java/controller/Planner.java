package controller;

import model.BookData;
import model.IBook;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Planner implements IPlanner {

    private Set<IBook> books;

    public Planner(Set<IBook> books) {
        this.books = books;
    }

    @Override
    public Stream<IBook> filter(String filter) {
        ISorts bookSorts = new Sorts();
        Stream<IBook> filteredStream = filterSingle(filter, books.stream()).sorted(bookSorts.getSorting(BookData.AUTHOR, true));
        return filteredStream;
    }

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

    @Override
    public Stream<IBook> filter(String filter, BookData sortOn) {
        ISorts bookSorts = new Sorts();
        Stream<IBook> filteredStream = filterSingle(filter, books.stream()).sorted(bookSorts.getSorting(sortOn, true));
        return filteredStream;

    }

    @Override
    public Stream<IBook> filter(String filter, BookData sortOn, boolean ascending) {
        ISorts bookSorts = new Sorts();
        Stream<IBook> filteredStream = filterSingle(filter, books.stream()).sorted(bookSorts.getSorting(sortOn, ascending));
        return filteredStream;
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
}
