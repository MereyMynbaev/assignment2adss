public class Main {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("apple");
        list.add("banana");
        list.add("Qazaq");
        list.add("Samat");
        list.sort();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        list.addLast("Elim");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // Pushing elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Peeking the top element
        System.out.println("Top element: " + stack.peek());

        // Popping elements from the stack
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());

        // Checking if the stack is empty
        System.out.println("Is stack empty? " + stack.empty());

        // Getting the size of the stack
        System.out.println("Size of stack: " + stack.size());
    }
}
