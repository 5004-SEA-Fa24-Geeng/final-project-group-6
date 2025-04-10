package util;

public class InputValidator {

    // Validate if the ISBN is in the correct format (10 or 13 digits)
    public static boolean isValidISBN(String isbn) {
        return isbn.matches("\\d{10}|\\d{13}");
    }
}
