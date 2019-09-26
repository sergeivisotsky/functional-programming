package org.sergei.functional.second;

/**
 * @author Sergei Visotsky
 */
class Solution {

    /**
     * Pethod to define wheather a token is a palindrome or not
     * <p>
     * A palindrome is a string which reads the same from both directions. For example:
     * <p>
     * - abcba
     * - argentinamanitnegra
     * - Sapalsarītadēdatīraslapas
     * are palindromes, while:
     * - abccb
     * - stirna
     * are not.
     *
     * @param token palindromic word
     * @return true/false
     */
    static boolean palindrome(String token) {
        if (token.length() == 0 || token.length() == 1) {
            return true;
        }
        if (token.charAt(0) == token.charAt(token.length() - 1)) {
            return palindrome(token.substring(1, token.length() - 1));
        }
        return false;
    }

    /**
     * SuperDigit solution
     * <p>
     * We define super digit of an integer using the following rules:
     * Given an integer, we need to find the super digit of the integer.
     * - If x has only 1 digit, then its super digit is x.
     * - Otherwise, the super digit of x is equal to the super digit of the sum of the digits of x.
     * For example, the super digit of 9875 will be calculated as:
     * <p>
     * superDigit(9875) 9+8+7+5 = 29
     * superDigit(29) 2 + 9 = 11
     * superDigit(11) 1 + 1 = 2
     * superDigit(2) = 2
     *
     * @param number number to calculate sum of digits
     * @return total sum
     */
    static long superDigit(long number) {
        if (number == 0) {
            return 0;
        }
        return (number % 10 + superDigit(number / 10));
    }

}
