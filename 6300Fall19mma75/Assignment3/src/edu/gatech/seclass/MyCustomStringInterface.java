package edu.gatech.seclass;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 *
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * This interface should NOT be altered in any way.
 */
public interface MyCustomStringInterface {

    /**
     * Returns the current string.
     * If the string is null, or has not been set to a value with setString, it should return null.
     *
     * @return Current string
     */
    String getString();

    /**
     * Sets the value of the current string
     *
     * @param string The value to be set
     */
    void setString(String string);

    /**
     * Returns the alphanumeric character which appears most often in the string (capitalization insensitive).
     * Non-alphanumeric characters are not counted.  If two alphanumeric characters appear an equal number of times,
     * return the first to appear in the string.
     *
     * If the current string is null, empty, or has not been set to a value, the method should throw a NullPointerException.
     *
     * Examples:
     * - mostCommonChar would return m for string "My lucky numbers are 5, 25, and 12.".
     *
     * @throws NullPointerException     If the current string is null, empty, or has not been set to a value
     * @return the alphanumeric character which appears most frequently in the string
     */
    char mostCommonChar();

    /**
     * Returns a string equivalent to the original string after removing all of the letters either >= or <= an input
     * letter, with regards to alphabetical order (capitalization insensitive).
     * The input letter may be a-z or A-Z, inclusive.
     *
     * If 'more' is false, all letters less than or equal to n will be removed in the returned string.
     * If 'more' is true, all letters greater than or equal to n will be removed in the returned string.
     *
     * Examples:
     * - For n=h and more=false, "Hello 90, bye 2" would be converted to "llo 90, y 2".
     * - For n=c and more=false, "Abcdefg" would be converted to "defg".
     * - For n=h and more=true, "Hello 90, bye 2" would be converted to "e 90, be 2".
     * - For n=c and more=true, "Abcdefg" would be converted to "Ab".
     *
     * @param n letter to compare each other letter to
     * @param more Boolean that indicates whether letters <= or >= n will be removed
     * @return String with the indicated letters removed
     * @throws NullPointerException     If the current string is null
     * @throws IllegalArgumentException If n is not a letter (and the current string is not null)
     */
    String filterLetters(char n, boolean more);

    /**
     * Replace the individual numbers in the current string, between startPosition and endPosition
     * (included), where a number is defined as a continuous sequence of digits, with the product of the individual
     * digits. The first character in the string is considered to be in Position 1.
     *
     *
     * Examples:
     * - String "0460" would be converted to "0"
     * - String "326 abc 123" would be converted to "36 abc 6"
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider

     * @throws IllegalArgumentException    If "startPosition" < 1 or "startPosition" > "endPosition"
     * @throws MyIndexOutOfBoundsException If "endPosition" is out of bounds (greater than the length of the string)
     * and 1 <= "startPosition" <= "endPosition"
     */
    void convertDigitsToProductsInSubstring(int startPosition, int endPosition);
}
