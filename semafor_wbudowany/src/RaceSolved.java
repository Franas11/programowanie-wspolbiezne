public class RaceSolved {
    public static void main(String[] args) throws InterruptedException {
        Counter cnt = new Counter(0);
        DThread dThread = new DThread(cnt);
        IThread iThread = new IThread(cnt);

        dThread.start();
        iThread.start();
        dThread.join();
        iThread.join();

        System.out.println("stan = " + cnt.value());
    }
}
