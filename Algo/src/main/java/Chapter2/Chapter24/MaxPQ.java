package Chapter2.Chapter24;

public class MaxPQ {
    private int[] pq;
    private int N = 0;

    private void swim(int k) {
        while (k > 1 && pq[k / 2] < pq[k]) {
            int temp = pq[k / 2];
            pq[k / 2] = pq[k];
            pq[k] = temp;

            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2*k; // left child

            if (j <= N && pq[j] < pq[j+1]) ++j;
            if (pq[k] > pq[j]) break;

            int temp = pq[k];
            pq[k] = pq[j+1];
            pq[j+1] = temp;

            k = j;
        }
    }

    public MaxPQ(int maxN) {
        pq = new int[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int v) {
        pq[++N] = v;
        swim(N);
    }

    public int delMax() {
        int max = pq[1];

        int new_root_value = pq[N-1];
        pq[N-1] = 0;
        N--;
        pq[1] = new_root_value;

        sink(1);

        return max;
    }
}
