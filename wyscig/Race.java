import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class Race {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Counter cnt = new Counter(0);

        DThread[] dThreads = new DThread[100];
        IThread[] iThreads = new IThread[100];

        Map<Integer, Integer> forHistogram = new TreeMap<>();

        for (int i = 0; i < 100; ++i) {
            dThreads[i] = new DThread(cnt);
            iThreads[i] = new IThread(cnt);

            dThreads[i].start();
            iThreads[i].start();
            dThreads[i].join();
            iThreads[i].join();

            if (!forHistogram.containsKey(cnt.value())) {
                forHistogram.put(cnt.value(), 1);
            } else {
                forHistogram.replace(cnt.value(), forHistogram.get(cnt.value()) + 1);
            }
            cnt.set_val(0);
        }

        PrintWriter printWriter = new PrintWriter("dane.dat");
        forHistogram.forEach((k, v) ->
                printWriter.println(k + " " + v));
        printWriter.close();
    }
}
