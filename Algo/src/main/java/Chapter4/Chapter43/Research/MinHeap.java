package Chapter4.Chapter43.Research;

public class MinHeap {
    private int currentSize = 0;
    private int capacity;
    private int[] heap;

    public MinHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity of the heap must be greater than 0.");
        }

        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    private static int parent(int i) {
        return i / 2;
    }

    private static int leftChild(int i) {
        return 2 * i;
    }

    private static int rightChild(int i) {
        return 2 * i + 1;
    }

    public void insert(int data) {
        if (this.currentSize == capacity) {
            throw new IllegalStateException("You have reached the capacity of the heap. You can't add anymore values.");
        }

        heap[currentSize] = data;

        // Swap the item until everything is in the right order.
        int i = currentSize;
        while (i != 0 && heap[MinHeap.parent(i)] > heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;

            i = parent(i);
        }

        this.currentSize += 1;
    }

    public void printHeap() {
        for (int i : heap) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(20);
        int[] values = new int[]{1, 3, 6, 5, 9, 8};

        for (int i : values) {
            heap.insert(i);
        }

        heap.printHeap();
    }
}
