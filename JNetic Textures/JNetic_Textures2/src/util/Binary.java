package util;


/**
 * This class holds functions which can be used to convert decimal values to and 
 * from binary.
 *
 * @author Steve Bergen - Brock University
 * @version 1.0.0 - 25/04/2009
 */

public class Binary {

    
    /**
     * Computes the integer represented by the binary data array supplied.
     *
     * @param  index    The starting index to be parsed
     * @param  n        The number of bits to be parsed
     * @param  data     The binary stream to be parsed
     * @return The integer value of the binary data stream
    */
    
    public static int getValue ( int index, int n, boolean data[] ) {
        
        String s = "";
        
        for ( int i = index; i < index + n; i++ ) {
            s = s + ((data[i])?1:0);
        }
        
        return Integer.parseInt(s, 2);
        
    };
    
    
    /**
     * Returns a string representation of a binary array.
     *
     * @param  data     The binary array to be converted
     * @return A string representation of the array
    */ 
    
    public static String getString ( boolean data[] ) {
        
        String s = "";
        
        for ( int i = 0; i < data.length; i++ ) {
            s = s + ((data[i])?1:0);
        }
        
        return s;
        
    };
    
    
    /**
     * Converts an integer to a binary array.
     *
     * @param  num          The number to be converted
     * @param  bits         The number of bits
     * @return Array of boolean values as binary
    */
    
    public static boolean[] toBinary ( int num, int bits ) {
        
       return leadZeros(Integer.toBinaryString(num), bits);
        
    };
    
    
    /**
     * Converts a string of 0/1 to an n-bit array of boolean.
     *
     * @param  s            The number to be converted
     * @param  n            The number of bits
     * @return Array of boolean values as binary
    */
    
    private static boolean[] leadZeros ( String s, int n ) {
        
        boolean number[] = new boolean[n];
        
        // Add zeros
        int zeros = n - s.length();
        for ( int i = 0; i < zeros; i++ ) s = "0" + s;
        
        // Convert string to boolean array
        for ( int i = 0; i < n; i++ ) {
            if ( s.charAt(i) == '0' ) number[i] = false;
            else number[i] = true;
        }
        
        return number;
        
    };
    
    
};