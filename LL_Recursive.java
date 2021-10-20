public class LL_Recursive<T>
{
    private Node<T> head;
    
    public LL_Recursive() {
        head = null;
    }
    
    public void insertAtFront(T data) {
        head = new Node<T>(data, head);
    }
    
    public boolean contains(T data) {
        return search(data) != null;
    }
    
    public LL_Recursive(LL_Recursive<T> other) {
        constructorHelper(other.head);
    }
    
    private void constructorHelper(Node<T> curr) {
        if (curr == null) {
            return;
        }
        insertAtTail(curr.data);
        constructorHelper(curr.next);
    }
    
    public void insertAtTail(T data) {
        if (head == null) {
            insertAtFront(data);
            return;
        }
        this.insertAtTailHelper(head, data);
    }
    
    private void insertAtTailHelper(Node<T> curr, T data) {
        if (curr.next == null) {
            curr.next = new Node<T>(data, null);
            return;
        }

        insertAtTailHelper(curr.next, data);
    }
    
    public int size() {
        return sizeHelper(head);
    }
    
    private int sizeHelper(Node<T> curr) {
        if (curr == null) return 0;

        return 1 + sizeHelper(curr.next);
    }
    
    public String toString() {
        return toString(head);
    }
    
    private String toString(Node<T> curr) {
        if (curr.next == null) return curr.data + "";
 
        return curr.data + " -> " + toString(curr.next);
    }
    
    public Node<T> search(T key) {
        return searchHelper(head, key);
    }
    
    private Node<T> searchHelper(Node<T> curr, T key) {
        if (curr == null) {
            return null;
        }
        if (key.equals(curr.data)) {
            return curr;
        }
        return searchHelper(curr.next, key);
    }
}