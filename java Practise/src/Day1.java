public class Day1 {
    public static void main(String[] args) {

        // taken string as input
        String input = "India@12345";

        // creating stringBuilder for all types of fields
       // StringBuilder is used because:
       // It is mutable (can change easily)
       // More efficient than String for appending

        StringBuilder alpha = new StringBuilder();
        StringBuilder numeric = new StringBuilder();
        StringBuilder special = new StringBuilder();

        for (char ch : input.toCharArray()) {    //input.toCharArray() converts string into a character array
            if (Character.isLetter(ch)) {       //Character.isLetter(ch) checks if ch is a letter (a–z or A–Z)
                alpha.append(ch);
            } else if (Character.isDigit(ch)) {  //Character.isDigit(ch) checks if ch is a number (0–9)
                numeric.append(ch);
            } else {
                special.append(ch);
            }
        }

        System.out.println("Alpha = " + alpha);
        System.out.println("Special = " + special);
        System.out.println("Numeric = " + numeric);

    }
}
