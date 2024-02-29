package blokowanie_drobnoziarniste;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Grain {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//        FineGrainedList<Integer> list = new FineGrainedList<>();
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//
//        for (int i = 0; i < 10; ++i) {
//            pool.submit(new TaskForThread(list, rounds));
//        }
//
//        pool.shutdown();
        SimulationFineGrainedList simulationFineGrainedList = new SimulationFineGrainedList();
    }
}
