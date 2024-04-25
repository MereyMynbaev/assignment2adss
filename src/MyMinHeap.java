public class MyMinHeap {
    private MyArrayList<Integer> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public void insert(int item) {
        heap.addElement(item);
        heapifyUp(heap.getSize() - 1);
    }

    public int deleteMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        int min = heap.getElement(0);
        int lastItem = heap.removeElement(heap.getSize() - 1);
        if (!isEmpty()) {
            heap.setElement(0, lastItem);
            heapifyDown(0);
        }
        return min;
    }

    public int getMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap.getElement(0);
    }

    public boolean isEmpty() {
        return heap.getSize() == 0;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.getElement(index) < heap.getElement(parentIndex)) {
            heap.swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex;
        int rightChildIndex;
        int smallerChildIndex;

        while (index < heap.getSize() / 2) {
            leftChildIndex = 2 * index + 1;
            rightChildIndex = 2 * index + 2;

            if (rightChildIndex < heap.getSize() &&
                    heap.getElement(rightChildIndex) < heap.getElement(leftChildIndex)) {
                smallerChildIndex = rightChildIndex;
            } else {
                smallerChildIndex = leftChildIndex;
            }

            if (heap.getElement(index) <= heap.getElement(smallerChildIndex)) {
                break;
            }

            heap.swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }
}
