package test.java.murraco;

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

import java.util.Arrays;

public class Group20Test {

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

  //Replaced integer addition with subtraction
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

  //Changed increment from 1 to -1
  @Test
  public void testMergeSortWithReverseSortedArray() {
    Integer[] array = {5, 4, 3, 2, 1};
    Integer[] expected = {1, 2, 3, 4, 5};

    MergeSort.mergeSort(array);

    assertEquals(java.util.Arrays.toString(expected), java.util.Arrays.toString(array));
  }
    
}