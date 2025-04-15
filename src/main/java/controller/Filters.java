package controller;

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
    public boolean filter(IBook book, BookDataSabrina column, Operations op, String value) {
        switch (column) {
            case BookDataSabrina.CATEGORY:
                //getCategory might not exist in our implementation or be different. Placeholder for now
                return filterString(book.getCategory(), op, value);
            case BookDataSabrina.AUTHOR:
                return filterString(book.getAuthor(), op, value);
            case BookDataSabrina.TITLE:
                return filterString(book.getBookTitle(), op, value);
            case BookDataSabrina.ISBN:
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
            default:
                return false;
        }
    }
}
