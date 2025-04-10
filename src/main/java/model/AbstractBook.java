package model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class AbstractBook implements IBook {

    /** variable for isbn. */
    protected Integer isbn;


    /** variable for bookTitle */
    private String bookTitle;

    /** variable for book author. */
    private String author;

    /** variable for category. */
    private String category;

    /** variable for bookType. */
    protected BookType bookType;

    /** variable for status. */
    private String status;

    /**
     *
     *
     * @param bookTitle book title.
     * @param author book author.
     * @param category book category.
     * @param status book status.
     */
    public AbstractBook(Integer isbn, BookType bookType, String bookTitle, String author, String category, String status) {
        this.isbn = isbn;
        this.bookType = bookType;
        this.bookTitle = bookTitle;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    @Override
    public String getBookType(){
        return this.bookType.name();
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
    public int getISBN() {
        return isbn;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status){
        this.status = status;
    }

    /**
     *
     * @return output string format.
     */

    @Override
    public String toString() {
        return String.format("[ILLEGAL] %s by %s (%s) - ISBN: %s", bookTitle, author, category, isbn);
    }

    /**
     *
     * @return book data as a string for csv file.
     */
    @Override
    public String toCSV(){
        return String.format("%d,%s,%s,%s,%s", this.getISBN(), this.getBookTitle(), this.getAuthor(), this.getCategory(), this.getStatus());
    }

    public int hashcode(Object obj){
        return HashCodeBuilder.reflectionHashCode(this,"status");
    }


    }
}
