package controller;

import model.BookData;
import model.IBook;

public interface IFilters {
    /**
     * controller.Filters the books.
     * @param book
     * @param column
     * @param op
     * @param value
     * @return
     */
    public boolean filter(IBook book, BookData column, Operations op, String value);

    /**
     * Filter based on string. Figure out if we are using a similar filtering system as 5-6?
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    public boolean filterString(String bookData, Operations op, String value);

}
