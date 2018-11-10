import basemod.BaseMod;
import basemod.interfaces.EditRelicsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class SakuraModInitializer implements EditRelicsSubscriber {
    private static final String MODNAME = "Sakura";
    private static final String AUTHOR = "Reina";
    private static final String DESCRIPTION = "Relics I guess.";

    public SakuraModInitializer() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        SakuraModInitializer mod = new SakuraModInitializer();
    }

    @Override
    public void receiveEditRelics() {
    }

}
