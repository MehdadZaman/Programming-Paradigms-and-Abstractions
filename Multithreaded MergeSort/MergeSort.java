import java.util.Arrays;
import java.util.Random;

public class MergeSort extends Thread{

    private static final Random RNG    = new Random(10982755L);
    private static final int    LENGTH = 524288;

    public static void main(String... args) {
        int[] arr = randomIntArray();
        long start = System.currentTimeMillis();
        concurrentMergeSort(arr);
        long end = System.currentTimeMillis();
        if (!sorted(arr)) {
            System.err.println("The final array is not sorted");
            System.exit(0);
        }
        System.out.printf("%10d numbers: %6d ms%n", LENGTH, end - start);
    }

    private static int[] randomIntArray() {
        int[] arr = new int[LENGTH];
        for (int i = 0; i < arr.length; i++)
            arr[i] = RNG.nextInt(LENGTH * 10);
        return arr;
    }

    public static boolean sorted(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++)
        {
            if(!(arr[i] <= arr[i+1]))
            {
                return false;
            }
        }
        return true;
    }

    public static void concurrentMergeSort(int[] arr)
    {
        concurrentMergeSort(arr, Runtime.getRuntime().availableProcessors());
    }

    public static void concurrentMergeSort(int[] arr, int threadCount)
    {
        if (threadCount <= 1)
        {
            mergeSort(arr);
        }
        else if (arr.length >= 2)
        {
            int[] left  = Arrays.copyOfRange(arr, 0, (arr.length / 2));
            int[] right = Arrays.copyOfRange(arr, (arr.length / 2), arr.length);

            Thread leftThread = new Thread(new Sorting(left,  threadCount / 2));
            Thread rightThread = new Thread(new Sorting(right, threadCount / 2));

            leftThread.start();
            rightThread.start();

            try
            {
                leftThread.join();
                rightThread.join();
            }
            catch (InterruptedException ie) {}

            merge(left, right, arr);
        }
    }

    public static void mergeSort(int[] arr)
    {
        if (arr.length >= 2)
        {
            int[] left  = Arrays.copyOfRange(arr, 0, (arr.length / 2));
            int[] right = Arrays.copyOfRange(arr, (arr.length / 2), arr.length);

            mergeSort(left);
            mergeSort(right);
            merge(left, right, arr);
        }
    }

    public static void merge(int[] left, int[] right, int[] merged)
    {
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 0; i < merged.length; i++)
        {
            if (rightIndex >= right.length || (leftIndex < left.length && left[leftIndex] < right[rightIndex]))
            {
                merged[i] = left[leftIndex];
                leftIndex++;
            }
            else {
                merged[i] = right[rightIndex];
                rightIndex++;
            }
        }
    }
}