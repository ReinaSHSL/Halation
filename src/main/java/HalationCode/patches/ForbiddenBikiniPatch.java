package HalationCode.patches;

import HalationCode.relics.aobuta.ForbiddenBikini;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.relics.JuzuBracelet;
import com.megacrit.cardcrawl.shop.Merchant;

public class ForbiddenBikiniPatch {
    @SpirePatch(
            clz = Merchant.class,
            method = "update"
    )
    public static class StopClickMerchant {
        public static SpireReturn Prefix(Merchant __instance) {
            if (AbstractDungeon.player.hasRelic(ForbiddenBikini.ID)) {
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }
}
