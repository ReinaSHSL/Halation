package HalationCode.patches;

import HalationCode.relics.aobuta.SmartPhone;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.SingingBowl;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;

@SpirePatch(
        clz = RewardItem.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {}
)
public class SmartPhonePatch {
    public static boolean smartSkip = false;
    public static boolean smartBowl = false;
    public static AbstractCard smartCard;
    private static int rng;

    public static void Postfix(RewardItem __instance) {
        if (AbstractDungeon.player.hasRelic(SmartPhone.ID)) {
            if (AbstractDungeon.player.hasRelic(SingingBowl.ID)) {
                rng = AbstractDungeon.cardRng.random(__instance.cards.size()) + 1;
            }
            rng = AbstractDungeon.cardRng.random(__instance.cards.size());
            if (rng == __instance.cards.size()) {
                smartSkip = true;
            } else if (rng == __instance.cards.size() + 1) {
                smartBowl = true;
            } else {
                smartCard = __instance.cards.get(rng);
            }
        }
    }
}