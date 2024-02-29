public class Semafor {
    private boolean _stan;
    private int _czeka;

    public Semafor(boolean _stan, int _czeka) {
        this._stan = _stan;
        this._czeka = _czeka;
    }

    public synchronized void P() {
        if(_stan) {
            _stan = false;
            _czeka--;
            notifyAll();
        }
    }

    public synchronized void V() throws InterruptedException {
        while (_stan) {
            wait();
        }
        _stan = true;
        _czeka++;
    }
}
