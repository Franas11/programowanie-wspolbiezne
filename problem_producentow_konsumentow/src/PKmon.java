public class PKmon {
    public static void main(String[] args) {
        Bufor bufor = new Bufor(1000, (int) (Math.random() * 1000));
        System.out.println("\nStan poczatkowy bufora: "
                + bufor.getCurrentQuantity());
        Konsument konsument = new Konsument(bufor, "Konsument");
        Producent producent = new Producent(bufor, "Producent");

        konsument.start();
        producent.start();
    }
}
