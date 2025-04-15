package model;


public class IllegalBook extends AbstractBook {


    protected String PASSWORD = "secret";

    /**
     *
     *
     * @param bookTitle title of book.
     * @param author author of book.
     * @param category book category.
     * @param status book status.
     */
    public IllegalBook(String isbn, String bookTitle, String author, String category, String status) {
        super(isbn,"ILLEGAL", bookTitle, author, category, status);
    }



    public StandardBook getReplacement(){
        String newISBN = "978-3-49-823091-7";
        String hiddenTitle = "UNKNOWN";
        String hiddenAuthor = "J.R";

        return new StandardBook(newISBN, hiddenTitle,hiddenAuthor, "STANDARD", getStatus());
    }


    public Boolean validatePassword(String password){
        if (PASSWORD.equalsIgnoreCase(password)){
            return true;
        }else{
            System.out.println("Wrong Password.");
            return false;
        }

    }

}