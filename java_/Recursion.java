import java.util.Arrays;

public class Recursion {
    private static int maximum(int[] arr, int max, int i) {
        if (i > arr.length - 1) {
            // reached end of array
            return max;
        }
        max = Math.max(max, arr[i]);
        return maximum(arr, max, i + 1);
    }

    public static int maximum(int[] arr) {
        return maximum(arr, arr[0], 1);
    }

    private static boolean isPalindrome(String string, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (string.charAt(left) != string.charAt(right)) {
            return false;
        }
        return isPalindrome(string, left + 1, right - 1);
    }

    public static boolean isPalindrome(String string) {
        return isPalindrome(string, 0, string.length() - 1);
    }

    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right || left > arr.length - 1) {
            return -1; // finished: not found
        }

        int middle = (left + right) / 2;

        if (arr[middle] > target) {
            // try left-half subarray since target is smaller than middle
            return binarySearch(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            // try right-half subarray since target is greater than middle
            return binarySearch(arr, target, middle + 1, right);
        } else {
            // exactly equal: found
            return middle;
        }
    }

    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8, 2, 5, 3, 10};
        System.out.println(maximum(arr));

        String string = "1123211";
        System.out.println(isPalindrome(string));
        String string2 = "11231211";
        System.out.println(isPalindrome(string2));

        int n = 7;
        System.out.println(fibonacci(n));

        int x = 5;
        System.out.println(factorial(x));

        Arrays.sort(arr);
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 100));
    }
}