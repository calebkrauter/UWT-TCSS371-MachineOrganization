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
 * @author Your name here
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
        if (theBits.length > 16) {
            throw new IllegalArgumentException("Length of the array of bits is larger than 16. Length: " + theBits.length);
        }
        int bitAmount = theBits.length;
        int decValue = 2;
        int newDecValue = 0;
        char[] theFinalBits = theBits;
        boolean isNegative = false;
        if (theBits[0] == '1') {
            isNegative = true;
        }
        if (isNegative) {
            int iterator = theBits.length - 1;
            int iterateNext = iterator;

            theFinalBits = bitFlipper(theBits, iterator, iterateNext);
        }

        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            // if char is 0 don't add converted value. If 1, do summation and power
            if (theFinalBits[bitIndex] == '1') {
                newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
            }
        }
        if (isNegative) {
            return - newDecValue;
        }
        return newDecValue;
        /*
         * Write code here to implement this function.
         * Add comments, to explain any complex parts of your algorithm.
         */

       // return 0; // Replace the zero return value with a correct return value.
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
        char[] theBits = new char[16];
        int bitAmount = theBits.length;
        int dividend = 0;
        int magnitudeOfTheDecimal = Math.abs(theDecimal);
        char[] flippedBits;

        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            int value;

            if (bitIndex == bitAmount - 1) {
                value = magnitudeOfTheDecimal;
            } else {
                value = dividend;
            }

            if (value % 2 == 0) {
                dividend = value / 2;
                theBits[bitIndex] = '0';

            } else if (value % 2 == 1) {
                // Subtract 1 from the decimal to ensure that the value that is used is rounded to the floor which integer division should already do in java.
                dividend = (value - 1) / 2;
                theBits[bitIndex] = '1';
            }
        }
        if (theDecimal < 0) {
            int iterator = theBits.length - 1;
            int iterateNext = iterator;

            flippedBits = bitFlipper(theBits, iterator, iterateNext);
            theBits = flippedBits;
        }

        return theBits;

        // TODO - throw exception for decimal that is unrepresentable in 16 bits
        // TODO - deal with bit extention for 1s or 0s as necessary.
//        for (int bitIndex = 0; bitIndex < 16; bitIndex++) {
//            if (arrayOfBits[bitIndex].equals(null)) {
//                arrayOfBits[bitIndex] = 0;
//            }
//        }


        /*
         * Write code here to implement this function.
         * Add comments, to explain any complex parts of your algorithm.
         */
        
      //  return null;  // Replace the null return value with a correct return value.
    }
    
    
    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */
    private static char[] bitFlipper(char[] theBits, int iterator, int iterateNext) {
        // Base case
        if (iterateNext != 0) {
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
                    return bitFlipper(theBits, iterator, iterateNext);
                }
            } else {
                // Found a zero, iterate and look for the next 1
                iterator--; // i = 2
                return bitFlipper(theBits, iterator, iterateNext);
            }
        }
        return theBits;
    }
}
