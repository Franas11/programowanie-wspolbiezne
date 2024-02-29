package blokada;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
    private long counter;
    private final Fork rightFork;
    private final Fork leftFork;

    public Philosopher(String name, Fork rightFork, Fork leftFork) {
        super(name);
        counter = 0;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                think();    //myslenie
                eat();      //jedzenie
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void eat() throws InterruptedException {
        takeForks();
        ++counter;
        sleep(ThreadLocalRandom.current().nextInt(1, 6));
        if (counter % 100 == 0) {
            System.out.println("Filozof: "
                    + Thread.currentThread().getName()
                    + " jadlem " + counter + " razy");
        }
        putTheForksDown();
    }

    private void takeForks() {
        boolean pickUpTheRightFork = rightFork.pickUpTheFork();
        boolean pickUpTheLeftFork = leftFork.pickUpTheFork();

        /*
        W petli ponizej sprawdzamy, czy obydwa widelce zostaly podniesione przez filozofa.
        Jezeli tak sie nie stalo, w petli watek bedzie sie caly czas staral podniesc brakujace widelce.
        Jezeli np. brakuje prawego, to filozof bedzie czekaj az inny mu zwolni prawy widelec.
        Jezeli brakuje obydwoch, to filozof w petli bedzie sie staral o podniesienie obydwoch.
        Gdy zostana podniesione obydwa widelce, program moze przejsc dalej, do jedzenia filozofa.
    */

        while (!pickUpTheRightFork || !pickUpTheLeftFork) {
            if (pickUpTheRightFork) {
                pickUpTheLeftFork = leftFork.pickUpTheFork();
            } else if (pickUpTheLeftFork) {
                pickUpTheRightFork = rightFork.pickUpTheFork();
            } else {
                pickUpTheRightFork = rightFork.pickUpTheFork();
                pickUpTheLeftFork = leftFork.pickUpTheFork();
            }
        }
    }

    private void putTheForksDown() {
        rightFork.putTheForkDown();
        leftFork.putTheForkDown();
    }

    private void think() throws InterruptedException {
        sleep(ThreadLocalRandom.current().nextInt(1, 34));
    }
}
