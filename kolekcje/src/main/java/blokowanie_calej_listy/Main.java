package blokowanie_calej_listy;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//        ThreadSafeList<Integer> list = new ThreadSafeList<>();
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//
//        for (int i = 0; i < 10; ++i) {
//            pool.submit(new TaskForThread(1, 2, 1000_000, list));
//        }
//
//        pool.shutdown();
        SimulationForBlockAllList simulationForBlockAllList = new SimulationForBlockAllList();
    }
}
