package SakuraCode.patches;

import SakuraCode.powers.NoSkillsPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = AbstractCard.class,
        method = "hasEnoughEnergy"
)
public class NoSkillsPowerPatch {
    public static SpireReturn<Boolean> Prefix(AbstractCard __instance) {
        if (AbstractDungeon.player.hasPower(NoSkillsPower.POWER_ID) && __instance.type == AbstractCard.CardType.SKILL) {
            return SpireReturn.Return(false);
        }
        return SpireReturn.Continue();
    }
}
