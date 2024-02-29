package fifo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationForFifo {
    PrintWriter printWriter = new PrintWriter("fifo.dat");
    private final int rounds = 1000;

    public SimulationForFifo() throws FileNotFoundException, InterruptedException {
        for (int i = 1; i <= 10; ++i) {
            for (int j = 10; j <= 100; ++j) {
                ReadingRoom readingRoom = new ReadingRoom(100);
                ExecutorService pool =
                        Executors.newFixedThreadPool(i + j);

                long startTime = System.nanoTime(), endTime;

                int c = 0;
                int rest = 0;
                boolean con = false;
                if (j % i == 0) {
                    c = j / i;
                    con = true;
                } else {
                    rest = j % i;
                    c = j/i ;
                }

                for (int k = 0; k < j; ++k) {
                    pool.submit(new Reader(readingRoom, rounds));
                }
                for (int k = 0; k < i; ++k) {
                    if (con) {
                        pool.submit(new Writer(readingRoom, c * rounds));
                    } else {
                        if (k < i - 1) {
                            pool.submit(new Writer(readingRoom, c * rounds));
                        } else {
                            pool.submit(new Writer(readingRoom, (c +rest) * rounds));
                        }
                    }
                }
                pool.shutdown();
                while (!pool.awaitTermination(24L, TimeUnit.HOURS)) {
                }
                endTime = System.nanoTime();
                double writeTime = (endTime - startTime) / 1_000_000.0;
                printWriter.println(i + " " + j + " " + writeTime);
            }
        }
        printWriter.close();
    }
}
