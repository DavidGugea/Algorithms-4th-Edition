package Chapter4.Chapter44;

public class MaxHeap {
    private int[] array;
    private int size;
    private int capacity;

    MaxHeap(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    void swap(int parent, int i) {
        int temp = array[parent];
        array[parent] = array[i];
        array[i] = temp;
    }

    int parent(int i) {
        return i / 2;
    }

    int left(int i) {
        return i * 2;
    }

    int right(int i) {
        return i * 2 + 1;
    }

    void add(int value) {
        if (size == capacity) {
            throw new RuntimeException("The heap has reached its capacity.");
        }

        int i = size - 1;
        this.array[i] = value;

        while (parent(i) < array[i]) {
            swap(parent(i), i);
            i = parent(i);
        }

        size++;
    }

    int getMax() {
        if (size == 1) {
            size--;
            array[0] = 0;
            return array[0];
        }

        int root = array[0];

        array[0] = array[size-1];
        size--;

        maxHeapify(0);

        return root;
    }

    void maxHeapify(int i) {
        int largest = i;
        int left = left(i);
        int right = right(i);

        if (array[left] > largest) {
            largest = left;
        } else if (array[right] > largest) {
            largest = right;
        }

        if (largest != i) {
            swap(parent(largest), largest);
            maxHeapify(largest);
        }
    }

    void printArray() {
        for (int i : array) {
            System.out.print(i + " - ");
        }

        System.out.println("\n");
    }
}
