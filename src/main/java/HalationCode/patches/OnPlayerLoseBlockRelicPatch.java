package HalationCode.patches;

import HalationCode.interfaces.OnPlayerLoseBlockRelic;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(clz = AbstractPlayer.class, method = "damage")
public class OnPlayerLoseBlockRelicPatch {
    @SpireInsertPatch(rloc=121)
    public static void Insert(AbstractPlayer __instance, DamageInfo i) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof OnPlayerLoseBlockRelic) {
                ((OnPlayerLoseBlockRelic)r).onPlayerLoseBlock(i);
            }
        }
    }
}
