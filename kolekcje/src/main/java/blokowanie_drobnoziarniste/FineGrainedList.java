package blokowanie_drobnoziarniste;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedList<E> {
    private volatile Node<E> head;  //first Node in list
    private volatile Node<E> tail;  //last Node in list

    public FineGrainedList() {
    }

    private static class Node<E> {
        private final E object;
        private Node<E> nextNode;
        private final Lock lock;

        private Node(E object) {
            this.object = object;
            nextNode = null;
            lock = new ReentrantLock();
        }

        public void setNextNode(Node<E> nextNode) {
            lock.lock();
            try {
                this.nextNode = nextNode;
            } finally {
                lock.unlock();
            }
        }

        public E getObject() {
            lock.lock();
            try {
                return object;
            } finally {
                lock.unlock();
            }
        }

        public Node<E> getNextNode() {
            lock.lock();
            try {
                return nextNode;
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean add(E object) {
        if (object == null) {
            return false;
        }

        Node<E> toInsert = new Node<>(object);

        if (head == null) {
            head = toInsert;
            tail = toInsert;
            return true;
        }

        tail.setNextNode(toInsert);
        tail = toInsert;
        return true;
    }

    public boolean contains(E objectToCompare) {
        if (objectToCompare == null) {
            return false;
        }

        Node<E> tempNode = head;
        while (!objectToCompare.equals(tempNode.getObject())) {
            if (tempNode.getNextNode() == null) {
                return false;
            }
            tempNode = tempNode.getNextNode();
        }
        return true;
    }

    public boolean remove(E objectToRemove) {
        if (objectToRemove == null || head == null) {
            return false;
        }

        Node<E> previousNode = head;
        Node<E> tempNode = head;
        while (!objectToRemove.equals(tempNode.getObject())) {
            if (tempNode.getNextNode() == null) {
                return false;
            }
            previousNode = tempNode;
            tempNode = tempNode.getNextNode();
        }

        if (tempNode == head) {
            head = tempNode.getNextNode();
            tempNode.setNextNode(null);
            return true;
        }

        if (tempNode == tail) {
            previousNode.setNextNode(null);
            tail = previousNode;
            tempNode.setNextNode(null);
            return true;
        }

        previousNode.setNextNode(tempNode.getNextNode());
        tempNode.setNextNode(null);
        return true;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        Node<E> temp = head;
        result.append("[");
        while (temp != null) {
            result.append(temp.getObject());
            if (temp != tail) {
                result.append(", ");
            }
            temp = temp.getNextNode();
        }
        result.append("]");
        return result.toString();
    }
}
