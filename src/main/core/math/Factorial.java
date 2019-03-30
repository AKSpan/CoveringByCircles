package main.core.math;

import java.math.BigInteger;

public class Factorial {
    public static BigInteger factorial(BigInteger n) {
        if (n.equals(BigInteger.ZERO)) return BigInteger.ONE;
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
}
