package fifo;

public class Writer implements Runnable {
    private final ReadingRoom readingRoom;
    private final int writings;

    public Writer(ReadingRoom readingRoom, int writings) {
        this.readingRoom = readingRoom;
        this.writings = writings;
    }

    @Override
    public void run() {
        for (int i = 0; i < writings; ++i) {
            try {
                readingRoom.writeData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
