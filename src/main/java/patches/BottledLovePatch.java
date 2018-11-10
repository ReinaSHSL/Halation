package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz= AbstractCard.class,
        method=SpirePatch.CLASS
)
public class BottledLovePatch
{
    public static SpireField<Boolean> inBottledLove = new SpireField<>(() -> false);

    @SpirePatch(
            clz = AbstractCard.class,
            method="makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy
    {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance)
        {
            inBottledLove.set(__result, inBottledLove.get(__instance));
            return __result;
        }
    }
}
