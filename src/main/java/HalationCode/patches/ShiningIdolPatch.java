package HalationCode.patches;

import HalationCode.events.buttons.RelicDialogOptionButton;
import HalationCode.relics.lovelive.ShiningIdol;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.ForgottenAltar;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.GoldenIdol;

public class ShiningIdolPatch {
    public static final String ID = "halation:ShiningIdol";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;


    @SpirePatch(
            clz = ForgottenAltar.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class AltarCtor {
        public static void Postfix(ForgottenAltar __instance) {
            if (AbstractDungeon.player.hasRelic(GoldenIdol.ID)) {
                __instance.imageEventText.optionList.add(new RelicDialogOptionButton(3, OPTIONS[0], new ShiningIdol(), false));
            }
        }
    }

    @SpirePatch(
            clz = ForgottenAltar.class,
            method = "buttonEffect"
    )
    public static class ButtonEffectPatch {
        public static SpireReturn Prefix(ForgottenAltar __instance, @ByRef int[] buttonPressed) {
            if (buttonPressed[0] == 3) {
                AbstractRelic goldenIdol = AbstractDungeon.player.getRelic(GoldenIdol.ID);
                int relicIndex = AbstractDungeon.player.relics.indexOf(goldenIdol);
                AbstractDungeon.player.loseRelic(GoldenIdol.ID);
                AbstractRelic shiningIdol = new ShiningIdol();
                shiningIdol.instantObtain(AbstractDungeon.player, relicIndex, false);
                __instance.showProceedScreen(DESCRIPTIONS[0]);
                ReflectionHacks.setPrivate(__instance, ForgottenAltar.class, "screenNum", 1);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
