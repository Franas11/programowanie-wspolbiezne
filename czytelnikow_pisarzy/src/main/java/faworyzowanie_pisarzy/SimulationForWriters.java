package faworyzowanie_pisarzy;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SimulationForWriters {
    PrintWriter printWriter = new PrintWriter("faworyzowaniePisarzy.dat");
    private final int rounds = 100_000;

    public SimulationForWriters() throws FileNotFoundException, InterruptedException {
        for (int i = 1; i <= 10; ++i) {
            for (int j = 10; j <= 100; ++j) {
                ReadingRoom readingRoom = new ReadingRoom(i*rounds);
                Semaphore semaphore = new Semaphore(1);
                ExecutorService pool =
                        Executors.newFixedThreadPool(i+j);

                long startTime = System.nanoTime(), endTime;
                for (int k = 0; k < j; ++k) {
                    pool.submit(new Reader(readingRoom, semaphore, rounds));
                }
                for (int k = 0; k < i; ++k) {
                    pool.submit(new Writer(readingRoom, semaphore, rounds));
                }
                pool.shutdown();
                while (!pool.awaitTermination(24L, TimeUnit.HOURS)) {}
                endTime = System.nanoTime();
                double writeTime = (endTime-startTime) / 1_000_000.0;
                printWriter.println(i + " " + j + " " + writeTime);
            }
        }
        printWriter.close();
    }
}
