package Chapter4.Chapter44.Research;

public class IndexedMinHeap {
    private int[] heap;
    private int[] index;  // maps object indices to heap indices
    private int[] reverseIndex; // maps heap indices to object indices

    private int size;
    private int capacity;

    public IndexedMinHeap(int capacity) {
        this.capacity = capacity;
        size = 0;
        heap = new int[capacity + 1];
        index = new int[capacity + 1];
        reverseIndex = new int[capacity + 1];

        for (int i = 0 ; i <= capacity; i++) {
            reverseIndex[i] = -1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(int i) {
        return reverseIndex[i] != -1;
    }

    public int size() {
        return size;
    }

    public void insert(int i, int key) {
        if (size >= capacity)
            throw new IllegalStateException("Heap size exceeded.");

        
    }
}
