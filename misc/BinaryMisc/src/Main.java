import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Make a function to generate random binary numbers.
        char[] binaryValue1 = new char[]{'1', '0', '1', '0'};
        char[] binaryValue2 = new char[]{'1', '1', '1', '0'};
        char[] binaryValue3 = new char[]{'1', '1', '1', '0', '0', '1', '1', '1'};
        char[] binaryValue4 = new char[]{'1', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1'};
        System.out.println(binaryToDec(binaryValue1));
        System.out.println(binaryToDec(binaryValue2));
        System.out.println(binaryToDec(binaryValue3));
        System.out.println(binaryToDec(binaryValue4));
    }

    public static int binaryToDec(char[] binaryValue) {
        int bitAmount = binaryValue.length;
        int decValue = 2;
        int newDecValue = 0;

        //
        for (int bitIndex = bitAmount - 1; bitIndex >= 0; bitIndex--) {
            //System.out.println("Binary digit " + binaryValue[bitIndex]);



                // if char is 0 don't add converted value. If 1, do summation and power
                if (binaryValue[bitIndex] == '1') {
                    //for (int bitIndex = 0; bitIndex < bitAmount; bitIndex++) {
                    newDecValue += (int) Math.pow(decValue, bitAmount - bitIndex - 1);
//                    int exponent = bitAmount - bitIndex - 1;
//                    System.out.println(" decimal " + decValue + " exponent " + exponent + " = " + newDecValue);
                }
            //}

        }
        return newDecValue;
    }
}