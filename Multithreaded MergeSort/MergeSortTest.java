import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    public void testSorted()
    {
        assertTrue(MergeSort.sorted(new int[]{14, 88, 96, 124, 132}));
        assertTrue(MergeSort.sorted(new int[]{1, 2, 3}));
        assertTrue(MergeSort.sorted(new int[]{1, 2}));
        assertTrue(MergeSort.sorted(new int[]{-3,-1, 0, 48}));
    }

    @Test
    public void testUnsorted()
    {
        assertFalse(MergeSort.sorted(new int[]{1, 0, -1}));
        assertFalse(MergeSort.sorted(new int[]{3, 2, 1}));
        assertFalse(MergeSort.sorted(new int[]{10, 23, 9, 12, 1}));
        assertFalse(MergeSort.sorted(new int[]{137, 3}));
    }

    @Test
    public void testOneElement()
    {
        assertTrue(MergeSort.sorted(new int[]{1}));
        assertTrue(MergeSort.sorted(new int[]{-432}));
    }

    @Test
    public void testEmpty()
    {
        assertTrue(MergeSort.sorted(new int[]{}));
    }
}