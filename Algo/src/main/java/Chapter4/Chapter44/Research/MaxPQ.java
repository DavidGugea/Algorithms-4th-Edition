package Chapter4.Chapter44.Research;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        // The heap is violated because a node's key becomes smaller than one or both of that node's children's keys, then we can make progress toward fixing the violating by exchanging the node with the larger of its two children.

        while (2 * k <= N) {
            // While k has kids

            // j -> left child
            int j = 2 * k;

            // if right child < left child => j -> right child
            if (j < N && less(j, j + 1)) j++;

            // if k is bigger than j -> break
            if (!less(k, j)) break;

            // Exchange k with j (heap values, not index)
            exch(k, j);

            // swap k and j ( k goes down )
            k = j;
        }
    }
}
