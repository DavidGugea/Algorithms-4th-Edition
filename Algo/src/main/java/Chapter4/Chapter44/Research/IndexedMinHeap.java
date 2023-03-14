package Chapter4.Chapter44.Research;

import java.util.HashMap;

public class IndexedMinHeap {
    private int[] heap;
    private int capacity;
    private int currentIndex = 0;

    // positionMap[value] = index of value in heap
    private HashMap<Integer, Integer> positionMap = new HashMap<>();

    public IndexedMinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
    }

    public boolean contains(int value) {
        return this.positionMap.containsKey(value);
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i) {
        return i * 2 + 1;
    }

    public int right(int i) {
        return i * 2 + 2;
    }

    public void swap(int i, int j) {
        // i, j -> indexes
        int valueI = heap[i];
        int valueJ = heap[j];

        // Swap indexes in the position map
        positionMap.replace(valueI, j);
        positionMap.replace(valueJ, i);

        // Swap values in the heap
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void swim(int i) {
        while (i != 0 && heap[i] < parent(i)) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void sink(int i) {
        if (i * 2 + 1 >= (this.capacity - 1)) {
            // If there are no kids, return
            return;
        }

        // Get the smallest child of heap[i];
        int smallest = i*2+1;

        if (heap[i*2+1] < heap[i*2+2]) {
            smallest += 1;
        }

        // Swap heap values with the smallest child
        swap(i, smallest);

        // Sink at the smallest index
        sink(smallest);
    }

    public void insert(int value) {
        this.heap[currentIndex] = value;
        this.positionMap.put(value, currentIndex);

        currentIndex++;
        swim(currentIndex);
    }

    public int extractMin() {
        // Swap root with the last element and delete the root
        int min = this.heap[0];
        this.positionMap.remove(min);

        this.heap[0] = this.heap[currentIndex];
        this.heap[currentIndex] = -1;
        currentIndex--;

        // Sink the new root
        sink(0);

        return min;
    }

    public void decreaseKey(int value, int newValue) {
        int index = positionMap.get(value);
        swim(index);
    }
}
