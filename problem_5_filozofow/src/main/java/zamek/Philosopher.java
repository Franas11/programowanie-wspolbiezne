package zamek;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
    private final Semaphore butler;
    private long counter;
    private final Fork rightFork;
    private final Fork leftFork;

    public Philosopher(String name, Semaphore butler, Fork rightFork,
                       Fork leftFork) {
        super(name);
        this.butler = butler;
        counter = 0;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                think();    //myslenie
                butler.acquire();
                eat();      //jedzenie
                butler.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter == 1_000_000) {
                break;
            }
        }
    }

    private void eat() throws InterruptedException {
        takeForks();
        ++counter;
        //sleep(ThreadLocalRandom.current().nextInt(1, 6));
        if (counter % 100 == 0) {
//            System.out.println("Filozof: "
//                    + Thread.currentThread().getName()
//                    + " jadlem " + counter + " razy");
        }
        putTheForksDown();
    }

    private void takeForks() {
        rightFork.pickUpTheFork();
        leftFork.pickUpTheFork();
    }

    private void putTheForksDown() {
        rightFork.putTheForkDown();
        leftFork.putTheForkDown();
    }

    private void think() throws InterruptedException {
        //sleep(ThreadLocalRandom.current().nextInt(1, 34));
    }
}
