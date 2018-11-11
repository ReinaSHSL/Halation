package SakuraCode;

import SakuraCode.events.shrines.Contract;
import SakuraCode.relics.hsl.BottledLove;
import SakuraCode.relics.madoka.*;
import SakuraCode.relics.steinsgate.Convergence;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.StartActSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

@SpireInitializer
public class SakuraModInitializer implements EditRelicsSubscriber, EditStringsSubscriber, PostInitializeSubscriber {
    private static final String MODNAME = "Sakura";
    private static final String AUTHOR = "Reina";
    private static final String DESCRIPTION = "Relics I guess.";
    public static int startActHealth;
    public static CardGroup startActDeck;
    public static ArrayList<AbstractRelic> startActRelics;

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
        BaseMod.loadCustomStrings(EventStrings.class, "localization/SakuraEventStrings.json");
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(Contract.ID, Contract.class);
    }
}
