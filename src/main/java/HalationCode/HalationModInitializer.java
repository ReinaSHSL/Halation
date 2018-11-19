package HalationCode;

import HalationCode.events.beyond.AntiShadowMachine;
import HalationCode.events.shrines.Contract;
import HalationCode.events.shrines.FieldOfFlowers;
import HalationCode.relics.nonnonbiyori.Komachan;
import HalationCode.relics.hibike.Trompette;
import HalationCode.relics.hibike.Yufonium;
import HalationCode.relics.hsl.BottledLove;
import HalationCode.relics.madeinabyss.EternalFortune;
import HalationCode.relics.madeinabyss.StarCompass;
import HalationCode.relics.madoka.*;
import HalationCode.relics.mawarupenguindrum.Diary;
import HalationCode.relics.mawarupenguindrum.PenguinHat;
import HalationCode.relics.monogatari.*;
import HalationCode.relics.nonnonbiyori.Sonsunser;
import HalationCode.relics.persona.PapillonHeart;
import HalationCode.relics.railgun.Gekota;
import HalationCode.relics.steinsgate.Convergence;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

@SpireInitializer
public class HalationModInitializer implements EditRelicsSubscriber, EditStringsSubscriber, PostInitializeSubscriber, StartActSubscriber,
        PostUpdateSubscriber {
    private static final String MODNAME = "Halation";
    private static final String AUTHOR = "Reina";
    private static final String DESCRIPTION = "Relics I guess.";

    public HalationModInitializer() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        HalationModInitializer mod = new HalationModInitializer();
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
        BaseMod.addRelic(new HeavySnake(), RelicType.SHARED);
        BaseMod.addRelic(new MonkeysPaw(), RelicType.SHARED);
        BaseMod.addRelic(new LostSnail(), RelicType.SHARED);
        BaseMod.addRelic(new NonexistentMirror(), RelicType.SHARED);
        BaseMod.addRelic(new PeacePiece(), RelicType.SHARED);
        BaseMod.addRelic(new PlatinumPhoenix(), RelicType.SHARED);
        BaseMod.addRelic(new TrafficLight(), RelicType.SHARED);
        BaseMod.addRelic(new SelfBoilingWater(), RelicType.SHARED);
        BaseMod.addRelic(new RainbowClouds(), RelicType.SHARED);
        BaseMod.addRelic(new Toothbrush(), RelicType.SHARED);
        BaseMod.addRelic(new PapillonHeart(), RelicType.SHARED);
        BaseMod.addRelic(new Diary(), RelicType.SHARED);
        BaseMod.addRelic(new PenguinHat(), RelicType.SHARED);
        BaseMod.addRelic(new Komachan(), RelicType.SHARED);
        BaseMod.addRelic(new Sonsunser(), RelicType.SHARED);
        BaseMod.addRelic(new Gekota(), RelicType.BLUE);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/HalationPowerStrings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/HalationRelicStrings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "localization/HalationEventStrings.json");
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addEvent(Contract.ID, Contract.class);
        BaseMod.addEvent(FieldOfFlowers.ID, FieldOfFlowers.class);
        BaseMod.addEvent(AntiShadowMachine.ID, AntiShadowMachine.class, TheBeyond.ID);}

    @Override
    public void receiveStartAct() {
        Convergence.updateStats();
        if (AbstractDungeon.player.hasRelic(Diary.ID)) {
            Diary.newAct();
        }
    }

    @Override
    public void receivePostUpdate() {
        Convergence.relicBullshit();
        StarCompass.relicBullshit();
        HeavySnake.cardEffects();
        if (CardCrawlGame.dungeon != null) {
            System.out.println("WAIT TIMER " + AbstractDungeon.getCurrRoom().waitTimer);
            System.out.println("PHASE: " + AbstractDungeon.getCurrRoom().phase);
            System.out.println("BATTLE OVER?: " + AbstractDungeon.getCurrRoom().isBattleOver);
            System.out.println("SCREEN: " + AbstractDungeon.screen);
            System.out.println("ENDING: " + AbstractDungeon.player.isEndingTurn);
            System.out.println("ACTION: " + AbstractDungeon.actionManager.currentAction);
            System.out.println("ENEMY TURN END: " + AbstractDungeon.actionManager.turnHasEnded);
        }
    }

}
