package blokowanie_calej_listy;

import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.sleep;

public class TaskForThread implements Runnable {
    private final int origin;
    private final int bound;
    private final int rounds;
    private final ThreadSafeList<Integer> list;

    public TaskForThread(int origin, int bound, int rounds, ThreadSafeList<Integer> list) {
        this.origin = origin;
        this.bound = bound;
        this.rounds = rounds;
        this.list = list;
    }

    @Override
    public void run() {

        for (int i = 0; i < rounds; ++i) {
//            try {
//                sleep(ThreadLocalRandom.current().nextInt(origin, bound));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            int taskNumber = ThreadLocalRandom.current().nextInt(0, 3);
            switch (taskNumber) {
                case 0 -> addObject();
                case 1 -> checkIfIsInTheList();
                case 2 -> removeObject();
                //case 3 -> printList();
            }
        }
    }

    private void addObject() {
        int objectToAdd = ThreadLocalRandom.current().nextInt(1, 11);
        if (list.add(objectToAdd)) {
//            System.out.println(
//                    Thread.currentThread().getName() + " adds an item "
//                            + objectToAdd
//            );
        } else {
//            System.out.println("Failed to add object " + objectToAdd);
        }
    }

    private void checkIfIsInTheList() {
        int objectToCheck = ThreadLocalRandom.current().nextInt(1, 11);
        if (list.contains(objectToCheck)) {
//            System.out.println(Thread.currentThread().getName() +
//                    " list contains " + objectToCheck);
        } else {
//            System.out.println(Thread.currentThread().getName() +
//                    " the list doesn't contain "
//                    + objectToCheck);
        }
    }

    private void removeObject() {
        int objectToRemove = ThreadLocalRandom.current().nextInt(1, 11);
        if (list.remove(objectToRemove)) {
//            System.out.println(Thread.currentThread().getName() +
//                    " object " + objectToRemove
//                    + " removed from list");
        } else {
//            System.out.println(Thread.currentThread().getName() +
//                    " cannot delete object " + objectToRemove
//                    + " because list did not contain it");
        }
    }

//    private void printList() {
//        System.out.println(Thread.currentThread().getName() +
//                " printout of the list: " + list);
//    }
}
