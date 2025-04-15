package controller;

import model.BookData;
import model.IBook;

public class Filters implements IFilters {
    /**
     * Constructor
     */
    public Filters() {}

    /**
     * controller.Filters the books.
     * @param book
     * @param column
     * @param op
     * @param value
     * @return
     */
    @Override
    public boolean filter(IBook book, BookData column, Operations op, String value) {
        switch (column) {
            case CATEGORY:
                //getCategory might not exist in our implementation or be different. Placeholder for now
                return filterString(book.getCategory(), op, value);
            case AUTHOR:
                return filterString(book.getAuthor(), op, value);
            case TITLE:
                return filterString(book.getBookTitle(), op, value);
            case ISBN:
                return filterInteger(Integer.parseInt(book.getISBN()), op, value);
            default:
                return false;
        }
    }

    /**
     * Filter based on string. Figure out if we are using a similar filtering system as 5-6?
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    @Override
    public boolean filterString(String bookData, Operations op, String value) {
        switch (op) {
            case EQUALS:
                return bookData.replaceAll(" ", "").equals(value);
            case CONTAINS:
                return bookData.toLowerCase().replaceAll(" ", "").contains(value.toLowerCase().replaceAll(" ", ""));
            default:
                return false;
        }
    }

    /**
     * Filter based on int.
     * @param bookData
     * @param op
     * @param value
     * @return
     */
    @Override
    public boolean filterInteger(int bookData, Operations op, String value) {
        switch (op) {
            case EQUALS:
                return bookData == Integer.parseInt(value);
            case CONTAINS:
                return Integer.toString(bookData).replaceAll(" ", "").contains(value.toLowerCase().replaceAll(" ", ""));
            default:
                return false;
        }
    }
}
