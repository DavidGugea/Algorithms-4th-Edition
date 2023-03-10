package Chapter4.Chapter44.Research;

public class PriorityQueue {
    private MaxHeap heap;

    PriorityQueue(int capacity) {
        heap = new MaxHeap(capacity);
    }

    void print() {
        this.heap.printArray();
    }

    void enqueue(int value) {
        this.heap.add(value);
    }

    int dequeue() {
        return this.heap.getMax();
    }
}
