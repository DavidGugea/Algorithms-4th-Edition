package Chapter4.Chapter44.Research.IPQ3;

import Chapter4.Chapter44.Research.IPQ.IndexMinPQ;

public class IndexedMinPQ {
    public int maxN;
    public int n = 1;

    public int keys[];
    public int pq[];
    public int qp[];

    public void IndexMinPQ(int maxN) {
        this.maxN = 0;

        keys = new int[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];

        for (int i = 0; i <= maxN; ++i) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public int size() {
        return n;
    }

    public int minIndex() {
        return pq[1];
    }

    public int minKey() {
        return keys[pq[1]];
    }

    public int delMin() {
        int min = pq[1];

        // Exchange the first with the last element
        exch(1, n--);

        // Sink the root to stabilize heap
        sink(1);

        keys[min] = -1;
        qp[min] = -1;
        pq[n + 1] = -1;

        return min;
    }

    public void decreaseKey(int i, int key) {
        keys[i] = key;
        sink(qp[i]);
    }

    public void increaseKey(int i, int key) {
        keys[i] = key;
        swim(qp[i]);
    }

    public boolean greater(int i, int j) {
        return keys[pq[i]] > keys[pq[j]];
    }

    public void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public void swim(int k) {
        while (k > 1 && greater(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (2 * k < n) {
            int j = 2 * k;

            if (greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(j, k);

            k = j;
        }
    }
}
