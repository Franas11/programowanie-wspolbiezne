public class Bufor {
    private final int sizeLimit;
    private int currentQuantity;

    public Bufor(int sizeLimit, int currentQuantity) {
        this.sizeLimit = sizeLimit;
        this.currentQuantity = currentQuantity;
    }

    public void put(int i) throws InterruptedException {
        if (currentQuantity == sizeLimit) {
            System.out.println("Producent -> BUFOR PELNY - " +
                    "budze konsumenta i usypiam");
            notifyAll();
            while(currentQuantity == sizeLimit) {
                wait();
            }
        } else if (currentQuantity + i > sizeLimit) {
            System.out.println("Producent produkuje: " +
                    (sizeLimit - currentQuantity));
            currentQuantity = sizeLimit;
            notifyAll();
        } else {
            System.out.println("Producent produkuje: " + i);
            currentQuantity += i;
            notifyAll();
        }
    }

    public void get(int i) throws InterruptedException {
        if (currentQuantity == 0) {
            System.out.println("Konsument -> BUFOR PUSTY:" +
                    " budze producenta i usypiam");
            notifyAll();
            while(currentQuantity == 0) {
                wait();
            }
        } else if (currentQuantity - i < 0) {
            System.out.println("Konsument konsumuje: " + currentQuantity);
            currentQuantity = 0;
            notifyAll();
        } else {
            System.out.println("Konsument konsumuje: " + i);
            currentQuantity -= i;
            notifyAll();
        }
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }
}