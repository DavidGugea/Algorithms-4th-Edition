#include <iostream>
#include <climits>

using namespace std;

void swap(int* x, int* y) {
    int temp = *x;
    *x = *y;
    *y = temp;
};

class MinHeap {
private:
    int *heap_array;
    int capacity;
    int heap_size;
public:
    MinHeap(int capacity);

    void MinHeapify(int i);

    int parent(int i) {
        return i / 2;
    }

    int left(int i) {
        return 2 * i;
    }

    int right(int i) {
        return 2 * i + 1;
    }

    int extractMin();

    void decreaseKey(int i, int new_val);

    int getMin() {
        return this->heap_array[0];
    }

    void deleteKey(int i);

    void insertKey(int k);
};

MinHeap::MinHeap(int capacity) {
    this->heap_size = 0;
    this->capacity = capacity;
    this->heap_array = new int[this->capacity];
}

void MinHeap::insertKey(int k) {
    if (this->heap_size == this->capacity) {
        cout << "The heap has reached its given capacity\n";
        return;
    }

    this->heap_size++;
    int i = heap_size - 1;
    heap_array[i] = k;

    while (i != 0 && heap_array[parent(i)] > heap_array[i]) {
        swap(&heap_array[i], &heap_array[parent(i)]);
        i = parent(i);
    }
}

void MinHeap::decreaseKey(int i, int new_val) {
    heap_array[i] = new_val;

    while (i != 0 && heap_array[parent(i)] > heap_array[i]) {
        swap(&heap_array[i], &heap_array[parent(i)]);
        i = parent(i);
    }
}

int MinHeap::extractMin() {
    if (heap_size <= 0) {
        cout << "The heap is empty.\n";
        return INT_MAX;
    }

    if (heap_size == 1) {
        heap_size--;
        return heap_array[0];
    }

    int root = heap_array[0];
    heap_array[0] = heap_array[heap_size-1];
    heap_size--;
    MinHeapify(0);

    return root;
}

void MinHeap::deleteKey(int i) {
    decreaseKey(i, INT_MIN);
    extractMin();
}

void MinHeap::MinHeapify(int i) {
    int l = left(i);
    int r = right(i);

    int smallest = i;

    if (l < heap_size && heap_array[l] < heap_array[i])
        smallest = l;
    if (r < heap_size && heap_array[r] < heap_array[i])
        smallest = r;
    if (smallest != i) {
        swap(&heap_array[i], &heap_array[smallest]);
        MinHeapify(smallest);
    }
}

int main() {
    MinHeap h(11);
    h.insertKey(3);
    h.insertKey(2);
    h.deleteKey(1);
    h.insertKey(15);
    h.insertKey(5);
    h.insertKey(4);
    h.insertKey(45);

    cout << h.extractMin() << " ";
    cout << h.getMin() << " ";

    h.decreaseKey(2, 1);
    cout << h.getMin();

    cout << " " << endl;

    // Output : 2 4 1
    return 0;
}