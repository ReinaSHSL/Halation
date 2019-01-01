package HalationCode.patches;

import HalationCode.relics.ddlc.Thesaurus;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;

@SpirePatch(
        clz = ShopScreen.class,
        method = "update"
)
public class SuperUnnecessaryThesaurusPatch {
    public static SpireReturn Prefix(ShopScreen __instance) {
        if (AbstractDungeon.player.hasRelic(Thesaurus.ID) && Thesaurus.relicScreenOpen) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }

}
