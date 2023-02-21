package Chapter2.Chapter21;

public class InsertionSort3 {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j -= 1;
            }

            arr[j + 1] = key;
        }
    }

    public static void PrintArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        PrintArray(arr);
        sort(arr);
        PrintArray(arr);
    }
}
