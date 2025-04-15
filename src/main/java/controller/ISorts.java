package controller;

import model.IBook;

import java.util.Comparator;


public interface ISorts {
    /**
     * controller.Sorts the books according to the column (either by title or author) and order (ascending or descending).
     * @param bookData The column name.
     * @param ascending In ascending or descending order.
     * @return A comparator to sort the books.
     */
    public Comparator<IBook> getSorting(BookDataSabrina bookData, boolean ascending);
}
