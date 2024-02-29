package blokada;

public class Fil5mon {
    public static void main(String[] args) {
        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
        }

        Philosopher[] philosophers = {
                new Philosopher("Sokrates", forks[0], forks[1]),
                new Philosopher("Leibniz", forks[1], forks[2]),
                new Philosopher("Machiavelli", forks[2], forks[3]),
                new Philosopher("Marks", forks[3], forks[4]),
                new Philosopher("Nietzsche", forks[4], forks[0])
        };

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }
}
