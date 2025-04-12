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

    public StandardBook(String isbn, String bookTitle, String author, String category, String status) {
        super(isbn,"STANDARD", bookTitle, author, category, status);
    }


}