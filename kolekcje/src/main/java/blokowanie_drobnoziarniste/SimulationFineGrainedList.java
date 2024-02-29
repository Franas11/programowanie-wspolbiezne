package blokowanie_drobnoziarniste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationFineGrainedList {
    PrintWriter printWriter = new PrintWriter(new File("blokowanieziarniste.csv"));

    public SimulationFineGrainedList() throws FileNotFoundException, InterruptedException {
        printWriter.println("ilosc_watkow,czas_wykonania(ms)");
        int rounds = 200_000;

        for (int i = 10; i <= 100; ++i) {
            FineGrainedList<Integer> list = new FineGrainedList<>();
            ExecutorService pool = Executors.newFixedThreadPool(i);
            long startTime = System.nanoTime(), endTime;
            for (int j = 0; j < i; ++j) {
                pool.submit(new TaskForThread(list, rounds));
            }
            pool.shutdown();
            while (!pool.awaitTermination(24L, TimeUnit.HOURS)) {}
            endTime = System.nanoTime();
            long writeTime = (long) ((endTime-startTime) / 1_000_000.0);
            if (writeTime > 1000) {
                printWriter.println(i + "," + writeTime);
                System.out.println("Tura: " + i);
            } else {
                --i;
            }

        }
        printWriter.close();
    }
}
