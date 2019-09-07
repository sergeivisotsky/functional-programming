package org.sergei.functional.first;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum STATE {
    ABUNDANT,
    DEFICIENT,
    PERFECT;
}

public class PerfectNumber {

    public static STATE detect(int n) {
        return process(n);
    }

    public static STATE process(int i) {
        int sum = IntStream
                .range(1, i)
                .filter(j -> i % j == 0)
                .sum();
        if (sum == i) {
            return STATE.PERFECT;
        } else if (sum < 21) {
            return STATE.DEFICIENT;
        } else {
            return STATE.ABUNDANT;
        }
    }

    public static Set<Integer> divisors(int n) {
        return IntStream
                .range(1, n + 1)
                .filter(i -> n % i == 0)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));
    }
}
