import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first; // first node of deque
    private Node last; // last node of deque
    private int size; // size of deque

    private class Node {
        private Item item;
        private Node next;
    }

    public RandomizedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item");
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
        }

        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        int randomIndex = StdRandom.uniformInt(size) + 1;

        if (randomIndex == 1) {
            return dequeueFirst();
        }
        else if (randomIndex == size) {
            return dequeueLast();
        }
        else {
            Node current = first;
            for (int i = 1; i < randomIndex - 1; i++) {
                current = current.next;
            }
            Item item = current.next.item;
            current.next = current.next.next;
            size--;
            return item;
        }
    }

    private Item dequeueFirst() {
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    private Item dequeueLast() {
        Item item = last.item;
        Node current = first;
        while (current.next != last) {
            current = current.next;
        }
        current.next = null;
        last = current;
        size--;
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        int randomIndex = StdRandom.uniformInt(size) + 1;
        Node current = first;
        for (int i = 1; i < randomIndex; i++) {
            current = current.next;
        }
        return current.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        private int[] randomOrder = StdRandom.permutation(size);
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < randomOrder.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            currentIndex++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);

        for (int i : rq) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Sample: " + rq.sample());

        System.out.println("Dequeue: " + rq.dequeue());
        
    }
}
