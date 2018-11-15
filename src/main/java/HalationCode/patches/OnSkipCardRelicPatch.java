package HalationCode.patches;

import HalationCode.interfaces.OnSkipCardRelic;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.SingingBowl;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;

public class OnSkipCardRelicPatch {
    @SpirePatch(clz = SingingBowlButton.class, method = "onClick")
    public static class SingingBowlSkipPatch {
        public static void Prefix(SingingBowlButton __instance) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof OnSkipCardRelic) {
                    if (AbstractDungeon.player.hasRelic(SingingBowl.ID)) {
                        ((OnSkipCardRelic)r).onSkipSingingBowl();
                    }
                }
            }
        }
    }

    @SpirePatch(clz = CardRewardScreen.class, method = "skippedCards")
    public static class OnSkipCardPatch {
        public static void Prefix(CardRewardScreen __instance) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof OnSkipCardRelic) {
                    ((OnSkipCardRelic)r).onSkipCard();
                }
            }
        }
    }
}
