# Task description

Solution is supposed to use recursion and loops are restricted. Ifs are allowed :)

0) Determine if a String is a palindrome

A palindrome is a string which reads the same from both directions. For example:
- abcba
- argentinamanitnegra
- Sapalsarītadēdatīraslapas

are palindromes, while:
- abccb 
- stirna

are not.
```java
public class Solution {
    static boolean palindrome(String s) {
        // TODO
    }
}
```

1) Recursive Digit Sum

We define super digit of an integer using the following rules:
Given an integer, we need to find the super digit of the integer.
- If x has only 1 digit, then its super digit is x.
- Otherwise, the super digit of x is equal to the super digit of the sum of the digits of x.
For example, the super digit of 9875 will be calculated as:

superDigit(9875) 9+8+7+5 = 29
superDigit(29) 2 + 9 = 11
superDigit(11) 1 + 1 = 2
superDigit(2) = 2

```java
public class Solution {
    static long superDigit(long x) {
        // TODO
    }
}
```

