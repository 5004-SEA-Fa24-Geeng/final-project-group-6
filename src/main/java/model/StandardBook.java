package model;

public class StandardBook extends AbstractBook {

    /**
     *
     * @param isbn isbn.
     * @param bookTitle book title.
     * @param author book author.
     * @param category book category.
     * @param status book status.
     */

    public StandardBook(int isbn, String bookTitle, String author, String category, String status) {
        super(isbn,BookType.STANDARD, bookTitle, author, category, status);
    }


}