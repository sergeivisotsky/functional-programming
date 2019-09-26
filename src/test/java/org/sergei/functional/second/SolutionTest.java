package org.sergei.functional.second;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Sergei Visotsky
 */
public class SolutionTest {

    /**
     * Test for {@link Solution#palindrome(String)}
     */
    @Test
    public void isPalindromeTest() {
        String token = "argentinamanitnegra";
        assertTrue(Solution.palindrome(token));
    }

    /**
     * Test for {@link Solution#palindrome(String)}
     */
    @Test
    public void notPalindromeTest() {
        String token = "stirna";
        assertFalse(Solution.palindrome(token));
    }

    /**
     * Test for {@link Solution#superDigit(long)}
     */
    @Test
    public void superDigitTest() {
        long number = 1998L;
        long expected = 27L;
        assertEquals(expected, Solution.superDigit(number));
    }

}
