package HalationCode;

import HalationCode.events.beyond.AntiShadowMachine;
import HalationCode.events.shrines.Contract;
import HalationCode.events.shrines.FieldOfFlowers;
import HalationCode.relics.aobuta.QuantumPhysicsTextbook;
import HalationCode.relics.aobuta.SmartPhone;
import HalationCode.relics.generalweebreferences.OrihimeAndHikoboshiSamaAndTears;
import HalationCode.relics.hibike.Trompette;
import HalationCode.relics.hibike.Yufonium;
import HalationCode.relics.hsl.BottledLove;
import HalationCode.relics.katawashoujo.*;
import HalationCode.relics.madeinabyss.EternalFortune;
import HalationCode.relics.madeinabyss.StarCompass;
import HalationCode.relics.madoka.*;
import HalationCode.relics.mawarupenguindrum.Diary;
import HalationCode.relics.mawarupenguindrum.PenguinHat;
import HalationCode.relics.monogatari.*;
import HalationCode.relics.nonnonbiyori.Komachan;
import HalationCode.relics.nonnonbiyori.Sonsunser;
import HalationCode.relics.nonnonbiyori.SwordOfLegend;
import HalationCode.relics.nonnonbiyori.VictoryRuler;
import HalationCode.relics.persona3.PapillonHeart;
import HalationCode.relics.persona3.PinkAlligatorPlush;
import HalationCode.relics.railgun.Gekota;
import HalationCode.relics.steinsgate.Convergence;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
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
        BaseMod.addRelic(new PinkAlligatorPlush(), RelicType.SHARED);
        BaseMod.addRelic(new Diary(), RelicType.SHARED);
        BaseMod.addRelic(new PenguinHat(), RelicType.SHARED);
        BaseMod.addRelic(new Komachan(), RelicType.SHARED);
        BaseMod.addRelic(new Sonsunser(), RelicType.SHARED);
        BaseMod.addRelic(new Gekota(), RelicType.BLUE);
        BaseMod.addRelic(new SwordOfLegend(), RelicType.SHARED);
        BaseMod.addRelic(new VictoryRuler(), RelicType.SHARED);
        //BaseMod.addRelic(new LBlock(), RelicType.SHARED);
        BaseMod.addRelic(new PrussianBluePaint(), RelicType.SHARED);
        BaseMod.addRelic(new Prosthetics(), RelicType.SHARED);
        BaseMod.addRelic(new BlackBeret(), RelicType.SHARED);
        BaseMod.addRelic(new Risk(), RelicType.SHARED);
        BaseMod.addRelic(new AfternoonTea(), RelicType.SHARED);
        BaseMod.addRelic(new IllGainedPizza(), RelicType.SHARED);
        BaseMod.addRelic(new Jellyphish(), RelicType.SHARED);
        BaseMod.addRelic(new OrihimeAndHikoboshiSamaAndTears(), RelicType.SHARED);
        BaseMod.addRelic(new QuantumPhysicsTextbook(), RelicType.SHARED);
        BaseMod.addRelic(new SmartPhone(), RelicType.SHARED);
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
        if (AbstractDungeon.id.equals(TheBeyond.ID)) {
            AbstractDungeon.rareRelicPool.remove(OrihimeAndHikoboshiSamaAndTears.ID);
        }
    }

    @Override
    public void receivePostUpdate() {
        Convergence.relicBullshit();
        StarCompass.relicBullshit();
        HeavySnake.cardEffects();
        NonexistentMirror.cardEffects();
        PrussianBluePaint.cardEffects();
        Jellyphish.fuckmeintheASS();
        SmartPhone.morePostUpdateBullshit();
    }

}
