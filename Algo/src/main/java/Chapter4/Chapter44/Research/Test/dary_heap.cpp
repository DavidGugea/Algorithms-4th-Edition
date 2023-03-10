#include <iostream>
#include <vector>

// D-ary min heap
class DaryMinHeap {
private:
    vector<int> heap;
    int d;

public:
    int parent(int i) {
        return (i - 1) / d;
    }

    int kthChidl(int i, int k) {
        return i * d + k;
    }

    DaryMinHeap(int d) {
        this->d = d;
    }

    int smallestChild(int i) {
        int smallest = i * d + 1;
        int k = 2;

        while (k <= d && (i*d+k) <= heap.size()) {
            int value = heap[i * d + k];
            if (value < heap[smallest]) {
                smallest = i * d + k;
            }
        }

        return smallest;
    }

    void swap(int* x, int* y) {
        int temp = *x;
        *x = *y;
        *y = temp;
    }

    void percolateUp(int i) {
        while (i < heap.size() - 1 && heap[i] < heap[parent(i)]) {
            swap(&i, &parent(i));
            i = parent(i);
        }
    }

    void insert(int new_value) {
        heap.push_back(new_value);
        this->percolateUp(heap.size() - 1);
    }

    void decreaseKey(int i, int new_value) {
        heap[i] = new_value;
        this->percolateUp(i);
    }

    int extractMin() {
        int minVal = heap[0];

        heap[0] = heap.back();
        heap.pop_back();

        minHeapify(0);

        return minVal;
    }

    void minHeapify(int i) {
        int smallestChildIndex = this->smallestChild(i);

        if (heap[i] > heap[smallestChildIndex]) {
            int temp = heap[i];
            heap[i] = heap[smallestChildIndex];
            heap[smallestChildIndex] = temp;

            minHeapify(smallestChildIndex);
        }
    }

    void printHeap() {
        for (int i = 0 ; i < heap.size(); ++i) {
            cout << heap[i] << " ";
        }

        cout << endl;
    }
}