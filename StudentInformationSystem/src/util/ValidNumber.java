package util;

public class ValidNumber {

    public boolean isValid (String userInput) {
        try {
            int num = Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input.");
        }
        return false;
    }
}
