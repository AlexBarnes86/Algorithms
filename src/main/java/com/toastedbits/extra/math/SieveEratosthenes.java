package com.toastedbits.algs.extra.math;

import java.util.ArrayList;
import java.util.List;

public class SieveEratosthenes {
    private static boolean divisibleByAny(int n, List<Integer> divisors) {
        for(Integer div : divisors) {
            if(n % div == 0) {
                return true;
            }
            if(div > Math.sqrt(n)) {
                break;
            }
        }
        return false;
    }

    public static List<Integer> findPrimes(int max) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        for(int i = 3; i <= max; i += 2) {
            if(!divisibleByAny(i, primes)) {
                primes.add(i);
            }
        }

        return primes;
    }
}
