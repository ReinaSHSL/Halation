package SakuraCode;

import SakuraCode.events.shrines.Contract;
import SakuraCode.events.shrines.FieldOfFlowers;
import SakuraCode.relics.hibike.Trompette;
import SakuraCode.relics.hibike.Yufonium;
import SakuraCode.relics.hsl.BottledLove;
import SakuraCode.relics.madeinabyss.EternalFortune;
import SakuraCode.relics.madeinabyss.StarCompass;
import SakuraCode.relics.madoka.*;
import SakuraCode.relics.monogatari.IncompleteEncyclopedia;
import SakuraCode.relics.monogatari.TwoAmDonut;
import SakuraCode.relics.monogatari.WeightlessCrab;
import SakuraCode.relics.steinsgate.Convergence;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

@SpireInitializer
public class SakuraModInitializer implements EditRelicsSubscriber, EditStringsSubscriber, PostInitializeSubscriber, StartActSubscriber,
        PostUpdateSubscriber, PostDrawSubscriber {
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
        BaseMod.addRelic(new Yufonium(), RelicType.SHARED);
        BaseMod.addRelic(new Trompette(), RelicType.SHARED);
        BaseMod.addRelic(new StarCompass(), RelicType.SHARED);
        BaseMod.addRelic(new EternalFortune(), RelicType.SHARED);
        BaseMod.addRelic(new WeightlessCrab(), RelicType.SHARED);
        BaseMod.addRelic(new TwoAmDonut(), RelicType.SHARED);
        BaseMod.addRelic(new IncompleteEncyclopedia(), RelicType.SHARED);
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
        BaseMod.addEvent(FieldOfFlowers.ID, FieldOfFlowers.class);
    }

    @Override
    public void receiveStartAct() {
        Convergence.updateStats();
    }

    @Override
    public void receivePostUpdate() {
        Convergence.relicBullshit();
        StarCompass.relicBullshit();
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        if (AbstractDungeon.player.hasRelic(Yufonium.ID)) {
            Yufonium.onDraw(abstractCard);
        }
        if (AbstractDungeon.player.hasRelic(WeightlessCrab.ID)) {
            WeightlessCrab.onDraw(abstractCard);
        }
    }
}
