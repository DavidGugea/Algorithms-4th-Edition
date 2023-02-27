package Chapter3.Chapter34;

public class LinearProbingHashST<Key, Value> {
    private int N; // number of key-value pairs in the table
    private int M = 16; // Size of linear-probing table
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>();
        t.M = cap;

        for (int i = 0; i < M; ++i)
            t.put(keys[i], values[i]);

        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }

        keys[i] = key;
        values[i] = value;
        ++N;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return values[i];

        return null;
    }
}
