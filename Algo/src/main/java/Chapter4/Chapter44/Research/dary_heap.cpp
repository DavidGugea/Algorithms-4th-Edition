#include <iostream>
#include <vector>

using namespace std;

class DaryHeap {
private:
    vector<int> heap;
    int d;

    int parent(int i) {
        return (i - 1) / d ;
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

    // helper function to find the index of the child with the maximum value
    int maxChild(int i) {
        int maxChild = kthChild(i, 1);
        int k = 2;

        int currentChild = kthChild(i, k);

        while (currentChild < heap.size() && k <= d) {
            if (heap[currentChild] > heap[maxChild]) {
                maxChild = currentChild;
            }

            k++;
            currentChild = kthChild(i, k);
        }

        return maxChild;
    }

public:
    DaryHeap(int d) {
        this->d = d;
    }

    void insert(int value) {
        heap.push_back(value);
        percolateUp(heap.size() - 1);
    }

    int removeMax() {
        int maxVal = heap[0];
        heap[0] = heap.back();
        heap.pop_back();
        percolateDown(0);
        return maxVal;
    }

    void printHeap() {
        for (int i = 0 ; i < heap.size() ; ++i) {
            cout << heap[i] << " ";
        }

        cout << endl;
    }
};

int main() {
    DaryHeap* heap = new DaryHeap(3);

    heap->insert(10);
    heap->insert(20);
    heap->insert(30);
    heap->insert(40);
    heap->insert(50);

    heap->printHeap();

    int maxVal = heap->removeMax();
    cout << "Removed maximum value: " << maxVal << endl;

    heap->printHeap();

    return 0;
}