public class MyArrayList<T> implements List<T> {
    private T[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 5;

    public MyArrayList() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void addElement(T item) {
        if (size >= arr.length) {
            increaseBuffer();
        }
        arr[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        arr[index] = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index);
        if (size >= arr.length) {
            increaseBuffer();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(size, item);
    }

    @Override
    public T getElement(int index) {
        checkIndex(index);
        return arr[index];
    }

    @Override
    public T getFirst() {
        return getElement(0);
    }

    @Override
    public T getLast() {
        return getElement(size - 1);
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        Arrays.sort(arr, 0, size);
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public void clear() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void increaseBuffer() {
        T[] newArr = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[currentIndex++];
            }
        };
    }
}
