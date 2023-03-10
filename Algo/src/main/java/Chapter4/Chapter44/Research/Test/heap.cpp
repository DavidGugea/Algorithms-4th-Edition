#include <iostream>
#include <vector>

// Binary Min Heap
class BinaryHeap {
private:
    vector<int> heap;

public:
    int parent(int i) {
        return (i - 1) / 2;
    }
    
    int left(int i) {
        return i * 2 + 1;
    }

    int right(int i) {
        return i * 2 + 2;
    }

    void swap(int* x, int* y) {
        int temp = *x;
        *x = *y;
        *y = temp;
    }

    void insert(int value) {
        heap.push_back(value);
        percolateUp(heap.size() - 1);
    }

    void decreaseKey(int i, int new_value) {
        heap[i] = new_value;
        this->percolateUp(i);
    }

    void extractMin() {
        int min_item = heap[0];

        heap[0] = heap[heap.size() - 1]
        heap.pop_back();

        this->min_heapify(0);

        return min_item;
    }

    void min_heapify(int i) {
        int left = left(i);
        int right = right(i);

        int smallest = i;

        if (heap[left] < heap[smallest]) {
            smallest = left;
        }
        
        if (heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            min_heapify(smallest);
        }
    }

    void percolateUp(int i) {
        while (heap[i] < heap[parent(i)]) {
            swap(&i, &parent(i));
            i = parent(i);
        }
    }

    void printHeap() {
        for (int i = 0 ; i < heap.size(); ++i) {
            cout << heap[i] << " ";
        }
        
        cout << endl;
    }
}