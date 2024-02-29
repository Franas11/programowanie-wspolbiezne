package blokowanie_drobnoziarniste;

import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.sleep;

public class TaskForThread implements Runnable {
    private final FineGrainedList<Integer> list;
    private final int rounds;

    public TaskForThread(FineGrainedList<Integer> list, int rounds) {
        this.list = list;
        this.rounds = rounds;
    }

    @Override
    public void run() {

        for (int i = 0; i < rounds; ++i) {
//            try {
//                sleep(ThreadLocalRandom.current().nextInt(111, 1111));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            int taskNumber = ThreadLocalRandom.current().nextInt(0, 3);
            switch (taskNumber) {
                case 0 -> addObject();
                case 1 -> checkIfIsInTheList();
                case 2 -> removeObject();
//                case 3 -> printList();
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

