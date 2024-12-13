package murraco;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import murraco.BubbleSort;
import murraco.Heapsort;
import murraco.InsertionSort;
import murraco.MergeSort;
import murraco.Quicksort;
import murraco.SelectionSort;

public class SortingAlgorithmsTest {

  @Test
  public void testBubbleSort() {
    final Integer[] data = {4, 3, 0, 11, 7, 5, 15, 12, 99, 1};
    BubbleSort.bubbleSort(data);
    assertEquals("[0, 1, 3, 4, 5, 7, 11, 12, 15, 99]", Arrays.toString(data));
  }

  private static class ReferenceEqualString implements Comparable<ReferenceEqualString>{
    private final String value;

    public ReferenceEqualString(String value) {
      this.value = value;
    }

    @Override
    public int compareTo(ReferenceEqualString o) {
      return this.value.compareTo(o.value);
    }
  }

  private static class CompareToCountingInteger implements Comparable<CompareToCountingInteger>{
    private final Integer value;
    private int counter;

    public CompareToCountingInteger(Integer value){
      this.value = value;
    }

    @Override
    public int compareTo(CompareToCountingInteger o) {
      counter++;
      return this.value.compareTo(o.value);
    }

    public int getCounter() {
      return this.counter;
    }
  }

  @Test
  public void testBubbleSortStability() {
    ReferenceEqualString a = new ReferenceEqualString("a");
    ReferenceEqualString b = new ReferenceEqualString("a");

    assertEquals(0, a.compareTo(b));
    assertNotEquals(a.hashCode(), b.hashCode());
    
    final ReferenceEqualString[] data = {a, b};
    final ReferenceEqualString[] sortedData = Arrays.copyOf(data, 2);
    BubbleSort.bubbleSort(sortedData);
    assertArrayEquals(data, sortedData);
  }

  @Test
  public void testBubbleSortNumberOfComparisons() {
    CompareToCountingInteger a = new CompareToCountingInteger(3);
    CompareToCountingInteger b = new CompareToCountingInteger(1);
    CompareToCountingInteger c = new CompareToCountingInteger(2);

    final CompareToCountingInteger[] data = {a,b,c};
    BubbleSort.bubbleSort(data);
    assertEquals(1, a.getCounter());
    assertEquals(1, b.getCounter());
    assertEquals(2, c.getCounter());
  }

  @Test
  public void testInsertionSort() {
    final Integer[] data = {4, 3, 0, 11, 7, 5, 15, 12, 99, 1};
    InsertionSort.insertionSort(data);
    assertEquals("[0, 1, 3, 4, 5, 7, 11, 12, 15, 99]", Arrays.toString(data));
  }

  @Test
  public void testSelectionSort() {
    final Integer[] data = {4, 3, 0, 11, 7, 5, 15, 12, 99, 1};
    SelectionSort.selectionSort(data);
    assertEquals("[0, 1, 3, 4, 5, 7, 11, 12, 15, 99]", Arrays.toString(data));
  }

  @Test
  public void testMergeSort() {
    final Integer[] data = {4, 3, 0, 11, 7, 5, 15, 12, 99, 1};
    MergeSort.mergeSort(data);
    assertEquals("[0, 1, 3, 4, 5, 7, 11, 12, 15, 99]", Arrays.toString(data));
  }

  @Test
  public void testHeapsort() {
    final Integer[] data = {4, 3, 0, 11, 7, 5, 15, 12, 99, 1};
    Heapsort.heapSort(data);
    assertEquals("[0, 1, 3, 4, 5, 7, 11, 12, 15, 99]", Arrays.toString(data));
  }

  @Test
  public void testQuicksort() {
    final Integer[] data = {4, 3, 0, 11, 7, 5, 15, 12, 99, 1};
    Quicksort.quickSort(data);
    assertEquals("[0, 1, 3, 4, 5, 7, 11, 12, 15, 99]", Arrays.toString(data));
  }

  @Test
  public void testHeapsortWithEdgeCases() {
    // Test empty array
    Integer[] emptyData = {};
    Heapsort.heapSort(emptyData);
    assertEquals("[]", Arrays.toString(emptyData));

    // Test single-element array
    Integer[] singleData = {42};
    Heapsort.heapSort(singleData);
    assertEquals("[42]", Arrays.toString(singleData));

    // Test already sorted array
    Integer[] sortedData = {1, 2, 3, 4, 5};
    Heapsort.heapSort(sortedData);
    assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(sortedData));

    // Test reverse-sorted array
    Integer[] reverseData = {5, 4, 3, 2, 1};
    Heapsort.heapSort(reverseData);
    assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(reverseData));

    // Test array with duplicates
    Integer[] duplicatesData = {3, 3, 1, 2, 2, 1};
    Heapsort.heapSort(duplicatesData);
    assertEquals("[1, 1, 2, 2, 3, 3]", Arrays.toString(duplicatesData));
  }

  @Test
  public void testBuildMaxHeap() {
    Integer[] data = {4, 3, 0, 11, 7, 5};
    Heapsort.buildMaxHeap(data);
    // Verify the max-heap property
    for (int i = 0; i < data.length / 2; i++) {
      int leftChild = 2 * i + 1;
      int rightChild = 2 * i + 2;
      if (leftChild < data.length) {
        assertTrue(data[i] >= data[leftChild]);
      }
      if (rightChild < data.length) {
        assertTrue(data[i] >= data[rightChild]);
      }
    }
  }

  @Test
  public void testMaxHeapifyWithRightChildDominance() {
    // Arrange: Create a specific scenario where the right child is the largest
    Integer[] data = {10, 20, 30, 5, 15, 25};
    int n = data.length - 1;

    // Act: Call maxHeapify directly to verify behavior
    Heapsort.maxHeapify(data, 0, n);

    // Assert: The max-heap property should hold
    assertEquals("[30, 20, 25, 5, 15, 10]", Arrays.toString(data));
  }

}
