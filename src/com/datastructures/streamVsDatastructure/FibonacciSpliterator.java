package com.datastructures.streamVsDatastructure;

import java.math.BigInteger;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class FibonacciSpliterator implements Spliterator<BigInteger> {

    private final int fence;
    private int index;
    private BigInteger a, b;

    public FibonacciSpliterator(int fence) {
        this(0, fence);
    }

    protected FibonacciSpliterator(int start, int fence) {
        this.index = start;
        this.fence = fence;
        recalculateNumbers(start);
    }

    private void recalculateNumbers(int start) {
        a = fastFibonacciDoubling(start);
        b = fastFibonacciDoubling(start + 1);
    }

    @Override
    public boolean tryAdvance(Consumer<? super BigInteger> action) {
        if (index >= fence) {
            return false;
        }
        action.accept(a);
        BigInteger c = a.add(b);
        a = b;
        b = c;
        index++;
        return true;
    }

    @Override
    public FibonacciSpliterator trySplit() {
        int lo = index;
        int mid = (lo + fence) >>> 1;
        if (lo >= mid) {
            return null;
        }
        index = mid;
        recalculateNumbers(mid);
        return new FibonacciSpliterator(lo, mid);
    }

    @Override
    public long estimateSize() {
        return fence - index;
    }

    @Override
    public int characteristics() {
        int distinct = (index >= 2) ? DISTINCT : 0;
        return ORDERED | distinct | SIZED | SUBSIZED | IMMUTABLE | NONNULL;
    }

    /*
     * https://www.nayuki.io/page/fast-fibonacci-algorithms
     */
    public static BigInteger fastFibonacciDoubling(int n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
            BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
            BigInteger e = a.multiply(a).add(b.multiply(b));
            a = d;
            b = e;
            if ((n & bit) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
            }
        }
        return a;
    }

    public static void main(String[] args) {
      StreamSupport.stream(new FibonacciSpliterator(100), true)
                .count();
       ;


    }
}
