import java.util.concurrent.ThreadLocalRandom;

public class Konsument extends Thread{
    private final Bufor bufor;

    public Konsument(Bufor bufor, String nameOfThread) {
        super(nameOfThread);
        this.bufor = bufor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            try {
                synchronized (bufor) {
                    bufor.get(ThreadLocalRandom.current()
                            .nextInt(1, 1001));
                }
                sleep(ThreadLocalRandom.current().nextInt(1, 101));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}