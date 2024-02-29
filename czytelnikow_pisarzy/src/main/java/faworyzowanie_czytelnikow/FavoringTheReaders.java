package faworyzowanie_czytelnikow;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class FavoringTheReaders {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
//        SimulationSemaforReaders simulationSemaforReaders = new SimulationSemaforReaders();

        int readersNumber = 566;     //liczba czytelnikow
        int writersNumber = 353;     //liczba pisarzy
        int rounds = 463;            //liczba prob pisania/czytania

        ReadingRoom readingRoom = new ReadingRoom(readersNumber * rounds);
        Semaphore semaphore = new Semaphore(0);

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
