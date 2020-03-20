package com.toastedbits.algs.extra.math;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SieveTest {
    @Test
    public void test100() {
        List<Integer> primes = ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        assertThat(SieveEratosthenes.findPrimes(100), equalTo(primes));
    }

    @Test
    public void testLarge() {
        int max = 1_000_000;
        List<Integer> primes = SieveEratosthenes.findPrimes(max);
        primes.forEach(prime -> Assertions.assertTrue(BigInteger.valueOf(prime).isProbablePrime(100)));
    }
}
