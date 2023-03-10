#include <iostream>
#include <vector>

using namespace std;

class DaryHeap {
private:
    vector<int> heap;
    int d;

    int parent(int i) {
        return (i - 1) / d;
    }

    int kthChild(int i, int k) {
        return d * i + k;
    }

    void percolateUp(int i) {
        int temp = heap[i];

        while (i > 0 && temp > heap[parent(i)]) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }

        heap[i] = temp;
    }

    void percolateDown(int i) {
        int child;
        int temp = heap[i];

        while (kthChild(i, 1) < heap.size()) {
            child = maxChild(i);
            if (temp < heap[child]) {
                heap[i] = heap[child];
            } else {
                break;
            }

            i = child;
        }

        heap[i] = temp;
    }
}