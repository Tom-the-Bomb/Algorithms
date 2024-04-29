package java_;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Sorts {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean linearSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int middle = 0;
        int right = arr.length - 1;

        while (left <= right) {
            middle = (left + right) / 2;

            if (target > arr[middle]) {
                left = middle + 1;
            } else if (target < arr[middle]) {
                right = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // last `i` elements will be sorted each iteration
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // first `i` elements will be sorted each iteration
            // find minimum element from arr[i -> end]
            // and swap it with the element after the sorted subarray
            int minI = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minI]) {
                    minI = j;
                }
            }
            swap(arr, i, minI);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // first `i` elements will be sorted each iteration
            for (int j = i; j >= 0; j--) {
                // take i-th element and find a spot in the sorted subarray we have
                // loop backwards through sorted subarray until the `i-th` element is smaller than an element (place it before) [2]
                // place at start if not found [1]
                //
                // swap pairs of elements to slowly push the `i-th` element backwards in the subarray to the start [1] or where it belongs [2]
                if (arr[j + 1] > arr[j]) {
                    break;
                }
                swap(arr, j, j + 1);
            }
        }
    }

    public static void insertionSort2(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int element = arr.remove(i + 1);
            int j = i;
            while (j > 0 && element < arr.get(j)) {
                j--;
            }
            arr.add(j + 1, element);
        }
    }

    public static void insertionSort3(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            a: {
                for (int j = i; j >= 0; j--) {
                    if (arr.get(i + 1) >= arr.get(j)) {
                        arr.add(j + 1, arr.remove(i + 1));
                        break a;
                    }
                }
                arr.add(0, arr.remove(i + 1));
            }
        }
    }

    /* not in place */
    public static ArrayList<Integer> mergeSort(List<Integer> arr) {
        // finished dividing
        if (arr.size() == 1) {
            return new ArrayList<Integer>(arr);
        }
        int mid = arr.size() / 2;

        // divide (in half)
        ArrayList<Integer> left = mergeSort(arr.subList(0, mid));
        ArrayList<Integer> right = mergeSort(arr.subList(mid, arr.size()));

        ArrayList<Integer> newArr = new ArrayList<>();
        // compare first elements until either one is exhausted, then -> [1]
        while (left.size() > 0 && right.size() > 0) {
            if (left.get(0) < right.get(0)) {
                // add smaller first element from left vs. right divided subarrays
                newArr.add(left.remove(0));
            } else {
                newArr.add(right.remove(0));
            }
        }
        // [1]: add potentially leftover elements
        newArr.addAll(left);
        newArr.addAll(right);
        return newArr;
    }

    public static void main(String[] args) {
        int[] testArr = {1, 9, 7, 0, 21, 2, 5, 7, 3};
        bubbleSort(testArr);
        System.out.println(Arrays.toString(testArr));

        testArr = new int[] {1, 9, 7, 0, 21, 2, 5, 7, 3};
        selectionSort(testArr);
        System.out.println(Arrays.toString(testArr));

        testArr = new int[] {1, 9, 7, 0, 21, 2, 5, 7, 3};
        insertionSort(testArr);
        System.out.println(Arrays.toString(testArr));

        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(7);
        testList.add(5);
        testList.add(4);
        testList.add(6);
        ArrayList<Integer> testListCopy = new ArrayList<>(testList);
        ArrayList<Integer> testListCopy2 = new ArrayList<>(testList);

        insertionSort2(testList);
        System.out.println(testList);
        insertionSort3(testListCopy);
        System.out.println(testListCopy);
        testListCopy2 = mergeSort(testListCopy2);
        System.out.println(testListCopy2);

        System.out.println(binarySearch(testArr, 5));
        System.out.println(binarySearch(testArr, 6));
    }
}