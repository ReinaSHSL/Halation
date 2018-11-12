package SakuraCode;

import SakuraCode.events.shrines.Contract;
import SakuraCode.relics.hsl.BottledLove;
import SakuraCode.relics.madoka.*;
import SakuraCode.relics.steinsgate.Convergence;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

@SpireInitializer
public class SakuraModInitializer implements EditRelicsSubscriber, EditStringsSubscriber, PostInitializeSubscriber, StartActSubscriber,
        PostUpdateSubscriber {
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
        BaseMod.addRelic(new RedSoulGem(), RelicType.SHARED);
        BaseMod.addRelic(new YellowSoulGem(), RelicType.SHARED);
        BaseMod.addRelic(new PinkSoulGem(), RelicType.SHARED);
        BaseMod.addRelic(new Convergence(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/SakuraPowerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/SakuraRelicStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "localization/SakuraEventStrings.json");
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(Contract.ID, Contract.class);
    }

    @Override
    public void receiveStartAct() {
        Convergence.updateStats();
    }

    @Override
    public void receivePostUpdate() {
        Convergence.relicBullshit();
    }
}
