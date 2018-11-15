package HalationCode.patches;

import HalationCode.interfaces.OnRemoveCardFromMasterDeckRelic;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(clz = CardGroup.class, method = "removeCard")
public class OnRemoveCardFromMasterDeckPatch {
    public static void Postfix(CardGroup __instance, AbstractCard c) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof OnRemoveCardFromMasterDeckRelic) {
                ((OnRemoveCardFromMasterDeckRelic) r).onRemoveCardFromMasterDeck(c);
            }
        }
    }
}
