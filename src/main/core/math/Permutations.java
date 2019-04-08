package main.core.math;

import java.math.BigInteger;

/**
 * Максимальное число шагов - это размещение (комбинаторика) A^m_n = n!/(n-m)!
 */
public class Permutations {
    public static BigInteger get(int n, int m) {

        return Factorial.factorial(BigInteger.valueOf(n)).divide(Factorial.factorial(BigInteger.valueOf(n - m)));

    }
}