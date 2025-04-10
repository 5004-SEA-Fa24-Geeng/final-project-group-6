package model;

public class StandardBook implements IBook {
    private final String isbn;
    private final String bookTitle;
    private final String author;
    private final String category;
    private String status;

    public StandardBook(String isbn, String bookTitle, String author, String category, String status) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    @Override
    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getISBN() {
        return isbn;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%s) - ISBN: %s - %s", bookTitle, author, category, isbn, status);
    }
}
