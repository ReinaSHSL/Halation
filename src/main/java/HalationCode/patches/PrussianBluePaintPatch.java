package HalationCode.patches;

import HalationCode.relics.katawashoujo.PrussianBluePaint;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.RestRoom;

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
                if (!c.cardID.equals(AscendersBane.ID) && !SoulboundField.soulbound.get(c) && AbstractDungeon.getCurrRoom() instanceof RestRoom && c.color == AbstractCard.CardColor.CURSE || c.type == AbstractCard.CardType.CURSE) {
                    retVal.addToBottom(c);
                }
            }
            return retVal;
        }
        return retVal;
    }
}
