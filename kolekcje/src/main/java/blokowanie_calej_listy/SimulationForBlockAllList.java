package blokowanie_calej_listy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationForBlockAllList {
    PrintWriter printWriter = new PrintWriter(new File("blokowanielisty.csv"));

    public SimulationForBlockAllList() throws FileNotFoundException, InterruptedException {
        printWriter.println("ilosc_watkow,czas_wykonania(ms)");
        int origin = 1;
        int bound = 3;
        int rounds = 10_000;

        for (int i = 10; i <= 100; ++i) {
            ThreadSafeList<Integer> list = new ThreadSafeList<>();
            ExecutorService pool = Executors.newFixedThreadPool(i);
            long startTime = System.nanoTime(), endTime;
            for (int j = 0; j < i; ++j) {
                pool.submit(new TaskForThread(origin, bound, rounds, list));
            }
            pool.shutdown();
            while (!pool.awaitTermination(24L, TimeUnit.HOURS)) {}
            endTime = System.nanoTime();
            long writeTime = (long) ((endTime-startTime) / 1_000_000.0);
            printWriter.println(i + "," + writeTime);
        }
        printWriter.close();
    }
}
