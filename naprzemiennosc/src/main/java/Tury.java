public class Tury {
    public static void main(String[] args) {
        Sync sync = new Sync();
        T1 t1 = new T1(sync);
        T2 t2 = new T2(sync);

        t1.start();
        t2.start();
    }
}
