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

    @SpirePatch(
            clz = EventHelper.class,
            method = "roll"
    )
    public static class NoMoreEvents {
        public static EventHelper.RoomResult Postfix(EventHelper.RoomResult __result) {
            if (__result == EventHelper.RoomResult.EVENT) {
                int rng = AbstractDungeon.eventRng.random(1);
                switch(rng) {
                    case 0:
                        __result = EventHelper.RoomResult.MONSTER;
                        if (AbstractDungeon.player.hasRelic(JuzuBracelet.ID)) {
                            __result = EventHelper.RoomResult.SHOP;
                        }
                        return __result;
                    case 1:
                        __result = EventHelper.RoomResult.SHOP;
                        return __result;
                }
            }
            return __result;
        }
    }

}
