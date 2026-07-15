package Algorithm.Sort;

import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {6, 3, 7, 0, 1, 4, 5, 6};
        System.out.println("排序前: " + Arrays.toString(arr));
        mergeSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
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
            int mid = low + (high - low) / 2; // 防止溢出
            sort(arr, low, mid, temp); // 排序左半部分
            sort(arr, mid + 1, high, temp); // 排序右半部分
            merge(arr, low, mid, high, temp); // 合并两个有序部分
        }
    }

    private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        int i = low; // 左子数组起始索引
        int j = mid + 1; // 右子数组起始索引
        int k = 0; // 临时数组起始索引

        // 比较合并
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 复制剩余元素
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= high) temp[k++] = arr[j++];

        // 将临时数组复制回原数组
        System.arraycopy(temp, 0, arr, low, k);
    }
}
