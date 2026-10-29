class MyStack<T> {
    private Object[] arr;
    private int top;

    public MyStack(int size) {
        arr = new Object[size];
        top = -1;
    }

    public void push(T value) {
        if (top == arr.length - 1) {
            System.out.println("Stack is full");
            return;
        }

        arr[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }

        T value = (T) arr[top];
        arr[top] = null;
        top--;

        return value;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class Main13_3 {
    public static void main(String[] args) {
        MyStack<Integer> numbers = new MyStack<>(5);

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        System.out.println("Integer stack:");
        System.out.println(numbers.pop());
        System.out.println(numbers.pop());

        MyStack<String> names = new MyStack<>(5);

        names.push("Bhargavi");
        names.push("Rutvi");

        System.out.println("String stack:");
        System.out.println(names.pop());
        System.out.println(names.pop());

        System.out.println("Empty stack result: "
            + names.pop());
    }
}
