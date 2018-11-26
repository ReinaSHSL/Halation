package HalationCode.patches;

import HalationCode.relics.katawashoujo.PrussianBluePaint;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = CardGroup.class,
        method = "getUpgradableCards"
)
public class PrussianBluePaintPatch {
    @SpireInsertPatch (
            rloc = 7,
            localvars = {"retVal"}
    )
    public static CardGroup Insert (CardGroup __instance, CardGroup retVal) {
        if (AbstractDungeon.player.hasRelic(PrussianBluePaint.ID)) {
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (c.color == AbstractCard.CardColor.CURSE || c.type == AbstractCard.CardType.CURSE) {
                    retVal.addToBottom(c);
                }
            }
            return retVal;
        }
        return retVal;
    }
}
