import java.util.*;

public class AlgorithmTempClass {
    public static void main(String[] args) {
        int[] arr = {1,3,7,4,24,12,54,23,4,5,7};
        System.out.println("排序前数组为 : " + Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("排序后数组为 : " + Arrays.toString(arr));
    }

   public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
   }

   private static void sort(int[] arr, int low, int high, int[] temp) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            sort(arr, low, mid, temp);
            sort(arr, mid + 1, high, temp);
            merge(arr, low, mid, high, temp);
        }
   }

   private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= high) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, low, k);

   }

}












