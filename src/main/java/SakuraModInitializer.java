import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import relics.BlueSoulGem;
import relics.BottledLove;
import relics.PurpleSoulGem;

@SpireInitializer
public class SakuraModInitializer implements EditRelicsSubscriber, EditStringsSubscriber {
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
        BaseMod.addRelic(new BottledLove(), RelicType.SHARED);
        BaseMod.addRelic(new PurpleSoulGem(), RelicType.SHARED);
        BaseMod.addRelic(new BlueSoulGem(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/SakuraPowerStrings.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/SakuraRelicStrings.json");
    }
}
