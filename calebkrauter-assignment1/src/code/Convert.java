/*
 * Convert.java
 * 
 * TCSS 371 assignment 1
 */

package code;

import java.util.Arrays;

/**
 * A class to provide static methods for converting numbers between bases.
 * 
 * @author Alan Fowler
 * @author Caleb Krauter
 * @version 1.1
 */
public final class Convert {

    /**
     * A private constructor to inhibit instantiation of this class.
     */
    private Convert() {
        // Objects should not be instantiated from this class.
        // This class is just a home for static methods (functions).
        // This design is similar to the Math class in the Java language.
    }

    /**
     * Accepts an array of characters representing the bits in a 2's complement number
     * and returns the decimal equivalent.
     *
     * precondition:
     * This method requires that the maximum length of the parameter array is 16.
     *
     * @param theBits an array representing the bits in a 2's complement number
     * @throws IllegalArgumentException if the length of the parameter array > 16
     * @return the decimal equivalent of the 2's complement parameter
     */
    public static int convert2sCompToDecimal(final char[] theBits) {
        /*
         * Write code here to implement this function.
         * Add comments, to explain any complex parts of your algorithm.
         */
        if (theBits.length > 16) {
            throw new IllegalArgumentException("Length of the array of bits is larger than 16. Length: "
                    + theBits.length);
        }
        int bitAmount = theBits.length;
        int decValue = 2;
        int newDecValue = 0;
        char[] theFinalBits = theBits;
        boolean isNegative = theBits[0] == '1';
        // Check if binary string is negative.
        if (isNegative) {
            // The iterator is used to iterate over the bits
            // in search of the first '1' bit from right to left.
            int iterator = theBits.length - 1;
            // The iterateNext is used to iterate over the
            // bits after the first '1' bit, so they can be flipped.
            int iterateNext = iterator;
            // Saves the 2's complement to a variable called theFinalBits.
            theFinalBits = bitFlipper(theBits, iterator, iterateNext);
        }
        // Iterates over the bits from right to left
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            // If char is 0 don't add converted value. If 1, do summation and power
            if (theFinalBits[bitIndex] == '1') {
                newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
            }
        }
        if (isNegative) {
            return -newDecValue;
        }
        return newDecValue;
    }

    /**
     * Accepts a decimal parameter and returns an array of characters
     * representing the bits in the 16 bit two's complement equivalent.
     *
     * precondition:
     * This method requires that the two's complement equivalent
     * won't require more than 16 bits
     *
     * @param theDecimal the decimal number to convert to 2's complement
     * @throws IllegalArgumentException if the parameter cannot be represented in 16 bits
     * @return a 16 bit two's complement equivalent of the decimal parameter
     */
    public static char[] convertDecimalTo2sComp(final int theDecimal) {
        /*
         * Write code here to implement this function.
         * Add comments, to explain any complex parts of your algorithm.
         */
        char[] theBits = new char[16];
        int bitAmount = theBits.length;
        int dividend = 0;
        int magnitudeOfTheDecimal = Math.abs(theDecimal);
        // Iterates over the string of bits from right to left.
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            int value;
            // In the first iteration, ensure the 'value' is positive.
            // Taking the mod of the 'value' and '2' should not produce negative
            // remainders. All remainders should be positive to produce positive bits.
            if (bitIndex == bitAmount - 1) {
                value = magnitudeOfTheDecimal;
                // After the first iteration, each dividend will be positive and
                // should be saved to the value for the current pass on the algorithm.
            } else {
                value = dividend;
            }

            // Check if value is divisible by 2.
            if (value % 2 == 0) {
                // If value is divisible by 2, divide value by 2.
                dividend = value / 2;
                // Assign the remainder '0' as a bit at the current index from right to left.
                theBits[bitIndex] = '0';
                // Check if the value divided by 2 produces a remainder of 1.
            } else if (value % 2 == 1) {
                // Subtract 1 from the decimal to ensure that the value
                // that is used is rounded to the floor which integer division should already do in java.
                dividend = (value - 1) / 2;
                // Assign the remainder '1' as a bit at the current index from right to left.
                theBits[bitIndex] = '1';
            }
        }
        // In the case of a negative decimal. Takes 2's complement.
        if (theDecimal < 0) {
            // The iterator is used to iterate over the bits
            // in search of the first '1' bit from right to left.
            int iterator = theBits.length - 1;
            // The iterateNext is used to iterate over the
            // bits after the first '1' bit, so they can be flipped.
            int iterateNext = iterator;
            // Assigns the 2's complement to 'theBits' variable.
            theBits = bitFlipper(theBits, iterator, iterateNext);
        }

        // Checks if the decimal is representable in 16 bits by
        // converting the string of bits back to decimal. If they are not
        // equal then the exception is thrown. During this process, 'theBits'
        // 2's complement is taken in 'convert2sCompToDecimal' if the value is negative
        // which reverts the conversion of 2's complement previously
        // done above. The 2's complement must be taken again after
        // this check if 'theDecimal' is negative.
        if (theDecimal != convert2sCompToDecimal(theBits)) {
            throw new IllegalArgumentException("The decimal value cannot be represented in 16 bits.");
        }

        // The block of code below is used before and after the exception is thrown
        // out of necessity because the check above takes the 2's complement of
        // 'theBits' if it needs too which reverts the previous 2's complement conversion.

        // In the case of a negative decimal. Takes 2's complement.
        if (theDecimal < 0) {
            // The iterator is used to iterate over the bits
            // in search of the first '1' bit from right to left.
            int iterator = theBits.length - 1;
            // The iterateNext is used to iterate over the
            // bits after the first '1' bit, so they can be flipped.
            int iterateNext = iterator;
            // Saves the 2's complement to 'theBits' variable.
            theBits = bitFlipper(theBits, iterator, iterateNext);
        }
        return theBits;
    }

    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */
    private static char[] bitFlipper(char[] theBits, int iterator, int iterateNext) {
        // Base case
        if (iterateNext != 0) {
            // Iterate over the next index of the bits from right to left.
            iterateNext--;
            // find the first 1 from the right
            if (theBits[iterator] == '1') {
                // Check for '1' and flip bit
                if (theBits[iterateNext] == '1') {
                    theBits[iterateNext] = '0';
                    // Repeat a check for the next bit and flip it.
                    return bitFlipper(theBits, iterator, iterateNext);
                } else {
                    // Flip bit
                    theBits[iterateNext] = '1';
                    // Repeat a check for the next bit and flip it.
                    return bitFlipper(theBits, iterator, iterateNext);
                }
            } else {
                // Found a zero, iterate and look for the next '1'.
                iterator--;
                return bitFlipper(theBits, iterator, iterateNext);
            }
        }
        return theBits;
    }
}
