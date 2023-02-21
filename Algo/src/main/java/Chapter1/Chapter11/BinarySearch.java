package Chapter1.Chapter11;

public class BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int rankRecursive(int key, int[] a, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (key < a[mid]) {
                return rankRecursive(key, a, lo, mid - 1);
            } else if (key > a[mid]) {
                return rankRecursive(key, a, mid + 1, hi);
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] whiteList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i : whiteList) {
            System.out.println(rankRecursive(i, whiteList, 0, whiteList.length));
        }
    }
}
