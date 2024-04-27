import java.util.Comparator;

public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;
    private Comparator<T> comparator;

    // Constructor
    public MyMinHeap() {
        this(null);
    }

    // Constructor with custom comparator
    public MyMinHeap(Comparator<T> comparator) {
        this.heap = new MyArrayList<>();
        this.comparator = comparator;
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return heap.size() == 0;
    }

    // Get the size of the heap
    public int size() {
        return heap.size();
    }

    // Get the minimum element (root of the heap)
    public T getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // Extract the minimum element from the heap
    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = getMin();
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify(0);
        return min;
    }

    // Insert an element into the heap
    public void insert(T element) {
        heap.add(element);
        traverseUp(heap.size() - 1);
    }

    // Maintain heap property from the given index downward
    private void heapify(int index) {
        int left = leftChildOf(index);
        int right = rightChildOf(index);
        int smallest = index;

        if (left < heap.size() && compare(heap.get(left), heap.get(index)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Maintain heap property from the given index upward
    private void traverseUp(int index) {
        while (index != 0 && compare(heap.get(index), heap.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    // Get the index of the left child of the given index
    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    // Get the index of the right child of the given index
    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    // Get the index of the parent of the given index
    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    // Swap elements at two indices in the heap
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // Compare two elements using comparator if available, else use natural ordering
    private int compare(T first, T second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        } else {
            return first.compareTo(second);
        }
    }
}
