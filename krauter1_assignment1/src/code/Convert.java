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
        int bitAmount = theBits.length;
        int decValue = 2;
        int newDecValue = 0;
        System.out.println(Arrays.toString(theBits));
        boolean isNegative = false;
        if (theBits[0] == '1') {
            isNegative = true;
        }
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            // if char is 0 don't add converted value. If 1, do summation and power
            if (theBits[bitIndex] == '1') {
                newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
            }

            // Convert to 2's comp and get negative decimal
            // Convert to negative by multiplying decimal by two and subtracting from initial decimal

            // Checks if the first bit is '1' and if it is then it takes 2's complement.

            if (theBits[0] == '1') {

                // checks from right to left for a bit equalling '1'
                // TODO - either figure out how to stop doing this check when '1' is found or use the second method
                //        being flip all bits and add '1'.
                int flipperIndex = bitIndex;

                // Finds the first '1' closest to the right
                if (theBits[bitIndex] == '1') {
                    while (flipperIndex != 0) {

                        // Checks for the next bit after the first '1' found.
                        if (theBits[flipperIndex - 1] == '1') {
                            flipperIndex--;
                            // Flips every 1 bit immediately after the first 1 to 0
                            theBits[flipperIndex] = '0';

                            // If the bit is '0'
                        } else {
                            flipperIndex--;
                            theBits[flipperIndex] = '1';
                        }
                    }
                }

                if (theBits[bitIndex] == '1') {
                    newDecValue = 0;
                    newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
                }
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
        // TODO ensure that overflow is impossible.
        // Idea- convert binary back to dec and if the dec does not equal the converted binary then it can't be represented in 16 bits.

        char[] arrayOfBits = new char[16];
        int bitAmount = arrayOfBits.length;
        int dividend = 0;


        // TODO - store the dividend at each step and reuse the next dividend
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            int value;

            if (bitIndex == bitAmount - 1) {
                value = theDecimal;
            } else {
                value = dividend;
            }

            if (value % 2 == 0){
                dividend = value / 2;
                arrayOfBits[bitIndex] = '0';

            } else if (value % 2 == 1){
                // Subtract 1 from theDecimal to ensure that the value that is used is rounded to the floor which integer division should already do in java.
                dividend = (value - 1) / 2;
                arrayOfBits[bitIndex] = '1';
            }

        }

        // TODO - check if this is where exception should be thrown.
        if (theDecimal != convert2sCompToDecimal(arrayOfBits)) {
            throw new IllegalArgumentException("Decimal converted to 2's complement is "
                    + theDecimal + ". Using 16 bit binary, the decimal converted back to decimal is recorded as "
                    + convert2sCompToDecimal(arrayOfBits) + ".\nThe initial decimal does not fit in 16 bits.");
        }



        return arrayOfBits;
        /*
         * Write code here to implement this function.
         * Add comments, to explain any complex parts of your algorithm.
         */
        
      //  return null;  // Replace the null return value with a correct return value.
    }
    
    
    /*
     * NOTE: If you wish, you may also include private helper methods in this class.
     */

}
