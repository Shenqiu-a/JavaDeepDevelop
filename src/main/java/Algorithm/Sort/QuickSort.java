package Algorithm.Sort;

import org.apache.commons.lang3.RandomUtils;

import java.util.*;

import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/7/711:14
 */

public class QuickSort {
    static int k = 0;

    public static void main(String[] args) {
        int[] arr = {6, 3, 7, 0, 1, 4, 5, 6};
        System.out.println("排序前: " + Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);

        System.out.println("排序后: " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition3(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);

            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if(arr[j] < arr[high]) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, high);
        return i+1;
    }

    private static int partition2(int[] arr, int low, int high) {
        int i = low;

        for (int j = low; j < high; j++) {
            if(arr[j] < arr[high]) {
                swap(arr, i, j);
                i++;
                System.out.println("第"+ k++ +"次排序后:" + Arrays.toString(arr) + "\n");
            }
        }
        swap(arr, i, high);
        System.out.println("第"+ k++ +"次排序后:" + Arrays.toString(arr) + "i = " + (i) + "\n");
        return i;
    }

    private static int partition3(int[] arr, int low, int high) {
        int i = low;
        swap(arr, high, RandomUtils.nextInt(low, high));
        System.out.println("random element is : " + arr[high]);
        for (int j = low; j < high; j++) {
            if(arr[j] < arr[high]) {
                swap(arr, i, j);
                i++;
                System.out.println("第"+ k++ +"次排序后:" + Arrays.toString(arr) + "\n");
            }
        }
        swap(arr, high, i);
        System.out.println("第"+ k++ +"次排序后:" + Arrays.toString(arr) + "i = " + (i) + "\n");
        return i;
    }
}
