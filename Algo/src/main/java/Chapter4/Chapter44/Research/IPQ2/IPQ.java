package Chapter4.Chapter44.Research.IPQ2;

public class IPQ {
    private final int capacity;
    int n = 0;
    public int[] keys;
    public int[] pq;
    public int[] qp;

    public IPQ(int capacity) {
        this.capacity = capacity;

        // All arrays are one based.
        keys = new int[capacity + 1];
        pq = new int[capacity + 1];
        /*
            parent -> k / 2
            left child -> k * 2 + 1
            right child -> k * 2 + 2
        */
        qp = new int[capacity + 1];
    }

    public void exch(int i, int j) {
        int swap = pq[j];
        pq[j] = pq[i];
        pq[i] = swap;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public boolean greater(int i, int j) {
        if (keys[pq[i]] > keys[pq[j]]) {
            return true;
        } else {
            return false;
        }
    }

    public void insert(int i, int key) {
        keys[i] = key;
        pq[n] = i;
        qp[i] = n;

        swim(n);

        n++;
    }

    public int minIndex() {
        return pq[1];
    }

    public int minKey() {
        return keys[pq[1]];
    }

    public void swim(int k) {
        while (k / 2 > 0 && greater(k, k / 2)) {
            exch(k, k/2);
            k /= 2;
        }
    }

    public void sink(int k) {
        while (k * 2  < n) {
            int j = k * 2;

            if (greater(j, j+1)) j++;
            if (!greater(k, j)) break;

            exch(k, j);
            k = j;
        }
    }

    public int delMin() {
        int min = pq[1]; // index of min key element in keys array.

        // Exchange the last element with the root inside the pq
        exch(1, n--);
        // Sink it to stabilize heap.
        sink(1);

        // Delete the key
        qp[min] = -1; // Deletes the connection that the key had from the keys to pq through qp
        keys[min] = -1; // Deletes it from the keys array
        pq[n+1] = -1; // Delete the last element from pq (it got swapped, that's why we use n + 1 and not 1)

        return min;
    }

    public int delMin2() {
        int min = pq[1];

        exch(1, n--);
        sink(1);

        qp[min] = -1;
        keys[min] = -1;
        pq[n+1] = -1;

        return min;
    }

    public void decreaseKey(int i, int key) {
        keys[i] = key;
        swim(qp[i]);
    }

    public void increaseKey(int i, int key) {
        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);

        keys[i] = -1;
        qp[i] = -1;
    }
}
