package model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class AbstractBook implements IBook, Comparable<IBook> {

    /** variable for isbn. */
    protected String isbn;

    /** variable for bookTitle */
    private String bookTitle;

    /** variable for book author. */
    private String author;

    /** variable for category. */
    private String category;

    /** variable for bookType. */
    protected String bookType;

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
    public AbstractBook(String isbn, String bookType, String bookTitle, String author, String category, String status) {
        this.isbn = isbn;
        this.bookType = bookType;
        this.bookTitle = bookTitle;
        this.author = author;
        this.category = category;
        this.status = status;
    }

    /**
     *
     * @return book type.
     */
    @Override
    public String getBookType(){
        return this.bookType;
    }


    /**
     *
     * @return book Title.
     */
    @Override
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     *
     * @return author.
     */

    @Override
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @return book category.
     */
    @Override
    public String getCategory() {
        return category;
    }


    /**
     *
     * @return isbn.
     */
    @Override
    public String getISBN() {
        return isbn;
    }

    /**
     *
     * @return book status.
     */

    @Override
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status set book status.
     */

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
        return String.format("%s by %s (%s) — ISBN: %s", getBookTitle(), getAuthor(), getCategory(), getISBN());
    }


    /**
     * Get the Name (value) pair based on the bookData enum.
     *
     * In the case of Name, it will return only the name of the book.
     *
     * @param sortON  book Data enum value
     * @return value of Name (value) pair
     */
    @Override
    public String toStringWithInfo(BookData sortON) {
        return toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IBook book = (IBook) obj;
        return this.getISBN().equalsIgnoreCase(book.getISBN());
    }

    @Override
    public int compareTo(IBook other) {
        return this.getISBN().compareTo(other.getISBN());  // ascending by ISBN
    }



    @Override
    public int hashCode() {
        return getISBN().toLowerCase().hashCode();
    }


}

