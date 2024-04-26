import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements List<T> {

    static class Node<T> {
        T item;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("No more elements in the list");
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    @Override
    public Object set(int index, T item) {
        checkElementIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T res = current.item;
        current.item = item;
        return res;
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        checkPositionIndex(index);

        if (index == size) {
            addLast(item);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(item, current.next);
            current.next = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item, head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public T getFirst() {
        if (head == null)
            throw new NoSuchElementException("List is empty");
        return head.item;
    }

    @Override
    public T getLast() {
        if (tail == null)
            throw new NoSuchElementException("List is empty");
        return tail.item;
    }

    @Override
    public void remove(int index) {
        checkElementIndex(index);
        if (index == 0) {
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        if (index == size - 1) {
            tail = current;
        }
        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null)
            throw new NoSuchElementException("List is empty");
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
    }

    @Override
    public void removeLast() {
        if (tail == null)
            throw new NoSuchElementException("List is empty");
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;
    }

    @Override
    public void sort() {
        for (Node<T> i = head; i != null; i = i.next) {
            for (Node<T> j = i.next; j != null; j = j.next) {
                Comparable<T> itemI = (Comparable<T>) i.item;
                Comparable<T> itemJ = (Comparable<T>) j.item;
                if (itemI.compareTo((T)itemJ) > 0) {
                    T temp = i.item;
                    i.item = j.item;
                    j.item = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        Node<T> current = head;
        for (int i = 0; current != null; i++, current = current.next) {
            if (object == null && current.item == null || object != null && object.equals(current.item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        Node<T> current = head;
        int lastIndex = -1;
        for (int i = 0; current != null; i++, current = current.next) {
            if (object == null && current.item == null || object != null && object.equals(current.item)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public boolean exists(Object object) {
        Node<T> current = head;
        while (current != null) {
            if (object == null && current.item == null || object != null && object.equals(current.item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.item;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid Index: " + index + ", Size: " + size);
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid Index: " + index + ", Size: " + size);
    }
}
