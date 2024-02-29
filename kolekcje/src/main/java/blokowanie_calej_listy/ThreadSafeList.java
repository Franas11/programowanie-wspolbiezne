package blokowanie_calej_listy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadSafeList<E> {
    private volatile Node<E> head;  //first Node in list
    private volatile Node<E> tail;  //last Node in list
    private final Lock lock = new ReentrantLock();

    public ThreadSafeList() {
    }

    private static class Node<E> {
        private final E object;
        private Node<E> nextNode;

        public Node(E object) {
            this.object = object;
            nextNode = null;
        }
    }

    public boolean add(E object) {
        lock.lock();
        try {
            if (object == null) {
                return false;
            }

            Node<E> toInsert = new Node<>(object);

            if (head == null) {
                head = toInsert;
                tail = toInsert;
                return true;
            }

            tail.nextNode = toInsert;
            tail = toInsert;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(E objectToCompare) {
        lock.lock();
        try {
            if (objectToCompare == null || head == null) {
                return false;
            }

            Node<E> tempNode = head;
            while (!objectToCompare.equals(tempNode.object)) {
                if (tempNode.nextNode == null) {
                    return false;
                }
                tempNode = tempNode.nextNode;
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(E objectToRemove) {
        lock.lock();
        try {
            if (objectToRemove == null || head == null) {
                return false;
            }

            Node<E> previousNode = head;
            Node<E> tempNode = head;
            while (!objectToRemove.equals(tempNode.object)) {
                if (tempNode.nextNode == null) {
                    return false;
                }
                previousNode = tempNode;
                tempNode = tempNode.nextNode;
            }

            if (tempNode == head) {
                head = tempNode.nextNode;
                tempNode.nextNode = null;
                return true;
            }

            if (tempNode == tail) {
                previousNode.nextNode = null;
                tail = previousNode;
                tempNode.nextNode = null;
                return true;
            }

            previousNode.nextNode = tempNode.nextNode;
            tempNode.nextNode = null;
            return true;

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            StringBuffer result = new StringBuffer();
            Node<E> temp = head;
            result.append("[");
            while (temp != null) {
                result.append(temp.object);
                if (temp != tail) {
                    result.append(", ");
                }
                temp = temp.nextNode;
            }
            result.append("]");
            return result.toString();
        } finally {
            lock.unlock();
        }
    }
}
