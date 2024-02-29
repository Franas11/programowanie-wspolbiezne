package fifo;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FifoSolution {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        //SimulationForFifo simulationForFifo = new SimulationForFifo();
        int readersNumber = 10;      //liczba czytelnikow
        int writersNumber = 10;      //liczba pisarzy
        int rounds = 2;             //liczba prob czytania/pisania

        ReadingRoom readingRoom =
                new ReadingRoom(readersNumber);
        ExecutorService pool = Executors
                .newFixedThreadPool(readersNumber + writersNumber);

        for (int i = 0; i < readersNumber; ++i) {
            pool.submit(new Reader(readingRoom, rounds));
        }

        for (int i = 0; i < writersNumber; ++i) {
            pool.submit(new Writer(readingRoom, rounds));
        }

        pool.shutdown();
    }
}
