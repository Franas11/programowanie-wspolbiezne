package faworyzowanie_pisarzy;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class FavoringWriters {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//        SimulationForWriters simulationForWriters = new SimulationForWriters();
        int readersNumber = 6;     //liczba czytelnikow
        int writersNumber = 4;     //liczba pisarzy
        int rounds = 2;

        Semaphore semaphore = new Semaphore(1);
        ReadingRoom readingRoom = new ReadingRoom(
                writersNumber * rounds);

        ExecutorService pool =
                Executors.newFixedThreadPool(
                        readersNumber + writersNumber);
        for (int i = 0; i < readersNumber; ++i) {
            pool.submit(new Reader(readingRoom, semaphore, rounds));
        }

        for (int i = 0; i < writersNumber; ++i) {
            pool.submit(new Writer(readingRoom, semaphore, rounds));
        }

        pool.shutdown();
    }
}
