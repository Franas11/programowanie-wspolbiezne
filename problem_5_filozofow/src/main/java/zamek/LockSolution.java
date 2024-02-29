package zamek;

import java.util.concurrent.Semaphore;

public class LockSolution {
    public static void main(String[] args) throws InterruptedException {
        Semaphore butler = new Semaphore(4);

        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        Philosopher[] philosophers = {
                new Philosopher("Sokrates", butler, forks[0], forks[1]),
                new Philosopher("Leibniz", butler, forks[1], forks[2]),
                new Philosopher("Machiavelli", butler, forks[2], forks[3]),
                new Philosopher("Marks", butler, forks[3], forks[4]),
                new Philosopher("Nietzsche", butler, forks[4], forks[0])
        };

        long startTime = System.nanoTime(), endTime;


        for (zamek.Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        for (zamek.Philosopher philosopher : philosophers) {
            philosopher.join();
        }
        endTime = System.nanoTime();
        System.out.println("Time = " + (endTime-startTime));
    }
}
