package Chapter4.Chapter44.Research.PQS;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MinPQ<Key> implements Iterable<Key> {
    @Override
    public Iterator<Key> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Key> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Key> spliterator() {
        return Iterable.super.spliterator();
    }

    private Key[] pq; // stores items at indices 1 to n
    private int n;
    private Comparator<Key> comparator;

    public MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MinPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length - 1];
        for (int i = 0; i < n; ++i)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= k; k--)
            sink(k);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public void resize(int capacity) {
        assert capacity > n;

        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; ++i) {
            temp[i] = pq[i];
        }

        pq = temp;
    }

    public void insert(Key x) {
        if (n == pq.length - 1) resize(2 * pq.length);

        pq[++n] = x;
        swim(n);
    }

    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;

        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);

        return min;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null)
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        else
            return comparator.compare(pq[i], pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
}
