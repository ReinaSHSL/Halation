package SakuraCode.relics.hibike;

public class Trompette {
    private static Trompette ourInstance = new Trompette();

    public static Trompette getInstance() {
        return ourInstance;
    }

    private Trompette() {
    }
}
