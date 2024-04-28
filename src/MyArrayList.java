import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements List<T> {
    private int size = 0;
    private T[] array;

    // Constructors
    public MyArrayList() {
        this(1);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0)
            throw new IllegalArgumentException("Capacity must be positive: " + initialCapacity);
        array = (T[]) new Object[initialCapacity];
    }

    // Returns the size of the array
    @Override
    public int size() {
        return size;
    }

    // Checks if memory size is sufficient, if not extends the size of the array
    private void ensureCapacity() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[size * 2 + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    // Adds an item to the end of the list
    @Override
    public void add(T item) {
        ensureCapacity();
        array[size++] = item;
    }

    // Adds an item to the beginning of the list
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    // Adds an item to the end of the list
    @Override
    public void addLast(T item) {
        add(item);
    }

    // Adds an item at the specified index
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    // Removes the item at the specified index
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null; // Prevent memory leak
    }

    // Removes the first item
    @Override
    public void removeFirst() {
        remove(0);
    }

    // Removes the last item
    @Override
    public void removeLast() {
        if (size == 0)
            throw new NoSuchElementException("List is empty");
        array[--size] = null; // Prevent memory leak
    }

    // Obtains the item at the specified index
    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return array[index];
    }

    // Obtains the first item
    @Override
    public T getFirst() {
        if (size == 0)
            throw new NoSuchElementException("List is empty");
        return array[0];
    }

    // Obtains the last item
    @Override
    public T getLast() {
        if (size == 0)
            throw new NoSuchElementException("List is empty");
        return array[size - 1];
    }

    // Clears the list
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // Converts the list to an array
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    // Iterator for iterating over the elements of the list
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
                if (!hasNext())
                    throw new NoSuchElementException();
                return array[currentIndex++];
            }
        };
    }

    // Sets the item at the specified index
    @Override
    public T set(int index, T item) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        T oldValue = array[index];
        array[index] = item;
        return oldValue;
    }

    // Checks if an item exists in the list
    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null && array[i] == null || object != null && object.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    // Sorts the list
    @Override
    public void sort() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (((Comparable<T>) array[i]).compareTo(array[j]) > 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // Finds the index of the first occurrence of an item
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == null && array[i] == null || object != null && object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    // Finds the index of the last occurrence of an item
    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object == null && array[i] == null || object != null && object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }
}
