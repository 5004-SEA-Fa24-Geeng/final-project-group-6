package model;

public class IllegalBook extends AbstractBook {



    /**
     *
     *
     * @param bookTitle title of book.
     * @param author author of book.
     * @param category book category.
     * @param status book status.
     */
    public IllegalBook(String bookTitle, String author, String category, String status) {
        super(null,BookType.ILLEGAL, bookTitle, author, category, status);
    }

    /**
     *
     * @return altered toString for illegal book format.
     */
    @Override
    public String toString() {
        return String.format("[ILLEGAL] %s by %s (%s)", getBookTitle(), getAuthor(), getCategory());
    }
    /**
     *
     * @return csv line with book information.
     */
    @Override
    public String toCSV(){
        return String.format("%s,%s,%s,%s", this.getBookTitle(), this.getAuthor(), this.getCategory(), this.getStatus());
    }

}