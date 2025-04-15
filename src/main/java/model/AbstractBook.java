package model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class AbstractBook implements IBook {

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
        return String.format("[ILLEGAL] %s by %s (%s) - ISBN: %s", bookTitle, author, category, isbn);
    }

    /**
     * Get the Name (value) pair based on the GameData enum.
     *
     * In the case of Name, it will return only the name of the game.
     *
     * @param col GameData enum value
     * @return value of Name (value) pair
     */
    public String toStringWithInfo(BookData col) {
        switch (col) {
            case ISBN:
                return isbn;
            case AUTHOR:
                return String.format("%s, %s", isbn, author);
            case TITLE:
                return String.format("%s, %s", isbn, bookTitle);
            case CATEGORY:
                return String.format("%s, %s", isbn, category);
            default:
                return isbn;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IBook book = (IBook) obj;
        return this.getISBN().equalsIgnoreCase(book.getISBN());
    }


    public int hashcode(Object obj){
        return HashCodeBuilder.reflectionHashCode(this,"status");
    }


    }

