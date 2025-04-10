package model;

public class IllegalBook implements IBook {
    private final String isbn;
    private final String bookTitle;
    private final String author;
    private final String category = "ILLEGAL";
    private String status;

    private static final String PASSWORD = "12345";


    public IllegalBook(String isbn, String bookTitle, String author, String status) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.author = author;
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

    public boolean validatePassword(String input) {
        return PASSWORD.equals(input);
    }

    public StandardBook getReplacement() {
        return new StandardBook(isbn, bookTitle, author, "ILLEGAL", "AVAILABLE");
    }

    @Override
    public String toString() {
        return String.format("[ILLEGAL] %s by %s - ISBN: %s [%s]", bookTitle, author, isbn, status);
    }
}
