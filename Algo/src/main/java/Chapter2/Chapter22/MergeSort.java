package Chapter2.Chapter22;

public class MergeSort {
    public static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two sub-arrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temporary arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }

        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        // Initial indexes of first and second subarray
        int i = 0, j = 0;

        // Initial indexes of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                ++i;
            } else {
                arr[k] = R[j];
                ++j;
            }

            ++k;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            ++i;
            ++k;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            ++j;
            ++k;
        }
    }

    public static void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void printLn(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        printLn(arr);
        sort(arr, 0, arr.length - 1);
        printLn(arr);
    }
}
