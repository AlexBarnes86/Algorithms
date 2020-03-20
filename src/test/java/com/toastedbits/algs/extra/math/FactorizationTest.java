package com.toastedbits.algs.extra.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class FactorizationTest {
    @Test
    public void factorizationTest() {
        final List<Integer> primes = SieveEratosthenes.findPrimes(1_000_000);

        //Test against some random numbers that we have pre-computed the prime factorizations for
        assertThat(PrimeFactorization.factorize(2, primes).asList(), equalTo(List.of(2)));
        assertThat(PrimeFactorization.factorize(987650, primes).asList(), equalTo(List.of(2, 5, 5, 19753)));
        assertThat(PrimeFactorization.factorize(111114, primes).asList(), equalTo(List.of(2, 3, 3, 6173)));
    }
}
