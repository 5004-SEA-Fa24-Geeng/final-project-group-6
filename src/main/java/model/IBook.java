package model;

public interface IBook {

    /**
     * @return book type.
     */
    String getBookType();

    /**
     * @return book Title.
     */
    String getBookTitle();

    /**
     * @return Author.
     */
    String getAuthor();

    /**
     * @return category.
     */
    String getCategory();

    /**
     * @return ISBN.
     */
    String getISBN();

    /**
     * @return status.
     */
    String getStatus();

    /**
     * @return output string format.
     */
    String toString();

    /**
     * Get the Name (value) pair based on the bookData enum.
     * In the case of Name, it will return only the name of the book.
     *
     * @param sortON book Data enum value
     * @return value of Name (value) pair
     */
    String toStringWithInfo(BookData sortON);

    /**
     * @param other the object to be compared.
     * @return integer based on if they match or not.
     */

    int compareTo(IBook other);

    /**
     * @param status set book status.
     */
    void setStatus(String status);

}
