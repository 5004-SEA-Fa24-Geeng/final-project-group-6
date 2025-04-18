package model;

public interface IBook {
    String getBookType();
    String getBookTitle();
    String getAuthor();
    String getCategory();
    String getISBN();
    String getStatus();
    String toString();
    String toStringWithInfo(BookData col);
    int compareTo(IBook other);
    void setStatus(String status);

}
