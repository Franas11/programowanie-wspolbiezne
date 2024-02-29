public class RaceSolved {
    public static void main(String[] args) throws InterruptedException {
        Counter cnt = new Counter(0);
        Semafor semafor = new Semafor(false, 0);
        IThread iThread = new IThread(cnt, semafor);
        DThread dThread = new DThread(cnt, semafor);

        dThread.start();
        iThread.start();
        dThread.join();
        iThread.join();

        System.out.println("value = " + cnt.value());
    }
}
