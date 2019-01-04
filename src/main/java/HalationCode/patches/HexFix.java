package HalationCode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.HexPower;

@SpirePatch(
        clz = HexPower.class,
        method = "onUseCard"
)
public class HexFix {
    public static SpireReturn Prefix(HexPower __instance, AbstractCard c, UseCardAction a) {
        System.out.println(__instance.owner instanceof AbstractPlayer);
        if (!(__instance.owner instanceof AbstractPlayer)) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
