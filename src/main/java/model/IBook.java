package model;

public interface IBook {
    String getBookType();
    String getBookTitle();
    String getAuthor();
    String getCategory();
    String getISBN();
    String getStatus();
    String toString();
    void setStatus(String status);

}
