// Pedro Amarante
// CSE 143 BN with Justin Shaw
// Homework 1
// LetterInventory class, used to store a string as an array of integers,
// where each index corresponds to the number of letters of that index that the string has.

public class LetterInventory {

    public static final int DEFAULT_CAPACITY = 26;

    private int[] letterCount; // Array which stores the number of each alphabetical letter.
    private int size; // Stores the sum of all letters.

    // Post: Constructs an array which stores the number of recurrent alphabetical letters.
    // Parameters: data is a String that is converted to the letterCount format.
    public LetterInventory(String data) {
        this.letterCount = new int[DEFAULT_CAPACITY];
        String filteredData = data.toLowerCase();

        for (int i = 0; i < filteredData.length(); i++)  {
            if (Character.isLetter(filteredData.charAt(i))) {
                this.letterCount[filteredData.charAt(i) - 'a']++;
                this.size++;
            }
        }
    }

    // Pre: Must be an alphabetical letter (throws IllegalArgumentException if not)
    // Post: Returns the number of appearances of this letter in the inventory.
    // Parameters: letter is the char that the number of appearances will be checked.
    public int get(char letter) {
        nonLetterException(letter);
        char filteredLetter = Character.toLowerCase(letter);
        return this.letterCount[filteredLetter - 'a'];
    }

    // Post: Returns the sum of all the counts in this inventory.
    public int size() {
        return this.size;
    }

    // Post: Returns true if this inventory is empty (all counts are 0).
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Pre: If nonalphabetic character is passed or if value is negative,
    //      throws an IllegalArgumentException.
    // Post: Sets the count for the given letter to the given value.
    // Parameters: letter is the char that will have its corresponding index changed
    //             value is the integer that will be set at the corresponding index
    public void set(char letter, int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value < 0: " + value);
        }
        nonLetterException(letter);

        char filteredLetter = Character.toLowerCase(letter);
        this.size+= value - this.letterCount[filteredLetter - 'a'];
        this.letterCount[filteredLetter - 'a'] = value;
    }

    // Post: Constructs and returns a new LetterInventory object
    //       which, represents the sum of this letter inventory and the other LetterInventory.
    // Parameters: other is the LetterInventory that is added to the object
    public LetterInventory add(LetterInventory other) {
        int[] newLetterCount = new int[DEFAULT_CAPACITY];

        for (int i=0; i< DEFAULT_CAPACITY; i++) {
            newLetterCount[i] = this.letterCount[i] + other.letterCount[i];
        }

        return new LetterInventory(countToString(newLetterCount));

    }

    // Post: Constructs and returns a new LetterInventory object
    //       which, is the result of subtracting the other inventory from this inventory
    // Parameters: other is the LetterInventory that is added to the object
    public LetterInventory subtract(LetterInventory other) {
        int[] newLetterCount = new int[DEFAULT_CAPACITY];

        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            if (this.letterCount[i] - other.letterCount[i] < 0) {
                return null;
            }
            newLetterCount[i] = this.letterCount[i] - other.letterCount[i];
        }

        return new LetterInventory(countToString(newLetterCount));
    }

    // Post: Returns a String representation of the inventory with the letters all in lowercase
    //       and in sorted order and surrounded by square brackets
    public String toString() {
        return "[" + countToString(this.letterCount) + "]";
    }

    // Pre: Array must be in the size represented by the DEFAULT_CAPACITY constant,
    //      otherwise, throws an IllegalArgumentException
    // Post: Converts an array, which follows an alphabetical index sorting pattern
    //       to a string composed of the representative letters, repeated by their corresponding index
    // Parameters: letterCount is the array that is converted
    private String countToString(int[] letterCount) {
        if (letterCount.length != DEFAULT_CAPACITY) {
            throw new IllegalArgumentException("Array not in the proper format");
        }

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String result = "";

        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            result += String.valueOf(alphabet.charAt(i)).repeat(Math.max(0, letterCount[i]));
        }

        return result;
    }

    // Post: Utility method which throws an IllegalArgumentException if the parameter passed is not a letter
    // Parameters: Letter is the char that is checked
    private void nonLetterException(char Letter) {
        if (!Character.isLetter(Letter)) {
            throw new IllegalArgumentException("Non alphabetical letter: " + Letter);
        }
    }
}
