package com.toastedbits.algs.extra.math;

import com.google.common.collect.ImmutableMap;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public class PrimeFactorization {
    private final int number;
    private final Map<Integer, Integer> factors;

    public static PrimeFactorization factorize(int number, @NonNull List<Integer> primes) {
        return new PrimeFactorization(number, findPrimeFactorsMap(number, primes));
    }

    public boolean contains(int factor) {
        return factors.containsKey(factor) && factors.get(factor) > 0;
    }

    public int getCount(int factor) {
        return factors.getOrDefault(factor, 0);
    }

    public Set<Integer> uniqueFactors() {
        return factors.keySet();
    }

    public List<Integer> asList() {
        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : factors.entrySet()) {
            int factor = entry.getKey();
            for(int i = 0; i < entry.getValue(); ++i) {
                list.add(factor);
            }
        }
        Collections.sort(list);
        return list;
    }

    public Map<Integer, Integer> asMap() {
        return Map.copyOf(factors);
    }

    public int getNumber() {
        return number;
    }

    private PrimeFactorization(int number, Map<Integer, Integer> factors) {
        this.number = number;
        this.factors = factors;
    }

    private static Map<Integer, Integer> findPrimeFactorsMap(int n, @NonNull final List<Integer> primes) {
        checkArgument(Math.sqrt(n)+1 < primes.get(primes.size()-1), "Need larger primes");

        final Map<Integer, Integer> factors = new HashMap<>();
        for(int i = 0; i < primes.size(); ++i) {
            final int prime = primes.get(i);
            while(n % prime == 0) {
                n /= prime;
                if(!factors.containsKey(prime)) {
                    factors.put(prime, 0);
                }
                factors.put(prime, factors.get(prime)+1);
            }
        }

        return ImmutableMap.copyOf(factors);
    }
}
