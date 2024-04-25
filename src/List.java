public interface List<T> extends Iterable<T> {
    void addElement(T item);
    void set(int index, T item);
    void add(int index, T item);
    void addFirst(T item);
    void addLast(T item);
    T getElement(int index);
    T getFirst();
    T getLast();
    void remove(int index);
    void removeFirst();
    void removeLast();
    void sort();
    int indexOf(Object object);
    int lastIndexOf(Object object);
    boolean exists(Object object);
    Object[] toArray();
    void clear();
    int size();
}
