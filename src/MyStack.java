public class MyStack<T> {
    private MyArrayList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    public void push(T item) {
        list.addElement(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.removeElement(list.getSize() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.getElement(list.getSize() - 1);
    }

    public boolean isEmpty() {
        return list.getSize() == 0;
    }
}
