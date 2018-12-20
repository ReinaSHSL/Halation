package HalationCode.patches;

import HalationCode.relics.monogatari.TwoAmDonut;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.BloodVial;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class VampiresLoveDonutsPatch {
    public static final String ID = "halation:VampireDonuts";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;

    @SpirePatch(clz = Vampires.class, method = SpirePatch.CONSTRUCTOR)
    public static class Constructor {
        public static void Postfix(Vampires __instance) {
            if (AbstractDungeon.player.hasRelic(TwoAmDonut.ID)) {
                if (AbstractDungeon.player.hasRelic(BloodVial.ID)) {
                    __instance.imageEventText.updateDialogOption(2, OPTIONS[0]);
                    __instance.imageEventText.setDialogOption(Vampires.OPTIONS[2]);
                } else {
                    __instance.imageEventText.updateDialogOption(1, OPTIONS[0]);
                    __instance.imageEventText.setDialogOption(Vampires.OPTIONS[2]);
                }
            }
        }
    }

    @SpirePatch(clz = Vampires.class, method = "buttonEffect")
    public static class ButtonEffect {
        public static SpireReturn Prefix(Vampires __instance, @ByRef int[] buttonPressed) {
            if ((int) ReflectionHacks.getPrivate(__instance, Vampires.class, "screenNum") == 0 && AbstractDungeon.player.hasRelic(TwoAmDonut.ID)) {
                if (AbstractDungeon.player.hasRelic(BloodVial.ID)) {
                    if (buttonPressed[0] == 2) {
                        replaceAttacks();
                        AbstractDungeon.player.loseRelic(TwoAmDonut.ID);
                        UPDATEBODYTEXT(__instance);
                        ReflectionHacks.setPrivate(__instance, Vampires.class, "screenNum", 1);
                        return SpireReturn.Return(null);
                    } else if (buttonPressed[0] == 3) {
                        buttonPressed[0] = 2;
                    }
                } else {
                    if (buttonPressed[0] == 1) {
                        replaceAttacks();
                        AbstractDungeon.player.loseRelic(TwoAmDonut.ID);
                        UPDATEBODYTEXT(__instance);
                        ReflectionHacks.setPrivate(__instance, Vampires.class, "screenNum", 1);
                        return SpireReturn.Return(null);
                    } else if (buttonPressed[0] == 2) {
                        buttonPressed[0] = 1;
                    }
                }
            }
            return SpireReturn.Continue();
        }

        private static void UPDATEBODYTEXT(Vampires __instance) {
            __instance.imageEventText.updateBodyText(DESCRIPTIONS[0]);
            __instance.imageEventText.updateDialogOption(0, OPTIONS[1]);
            __instance.imageEventText.clearRemainingOptions();
        }

        private static void replaceAttacks() {
            int cardsToReplace = 0;
            Iterator it = AbstractDungeon.player.masterDeck.group.iterator();
            while(it.hasNext()) {
                AbstractCard c = (AbstractCard) it.next();
                if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
                    it.remove();
                    cardsToReplace++;
                }
            }
            for (int i = 0; i < cardsToReplace; i++) {
                AbstractCard bite = new Bite();
                bite.upgrade();
                AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(bite, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            }
        }
    }
}
