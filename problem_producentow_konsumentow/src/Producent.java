import java.util.concurrent.ThreadLocalRandom;

public class Producent extends Thread{
    private final Bufor bufor;

    public Producent(Bufor bufor, String name) {
        super(name);
        this.bufor = bufor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            try {
                synchronized (bufor) {
                    bufor.put(ThreadLocalRandom.current()
                            .nextInt(1, 1001));
                }
                sleep(ThreadLocalRandom.current().nextInt(1, 101));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}