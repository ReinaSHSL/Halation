package HalationCode.patches;

import HalationCode.relics.ddlc.SimulatedSpire;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.neow.NeowReward.NeowRewardDef;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;

import java.util.ArrayList;


public class SimulatedSpirePatch {
    @SpirePatch(
            clz = CombatRewardScreen.class,
            method = "setupItemReward"
    )
    public static class AddSimulatedSpireCards {
        @SpireInsertPatch(
                rloc = 36
        )
        public static void Insert(CombatRewardScreen __instance) {
            RewardItem simulatedSpireRewards = new RewardItem();
            if (simulatedSpireRewards.cards.size() == 0) return;
            for (AbstractCard c : simulatedSpireRewards.cards) {
                IsSimulatedSpireField.isSimulatedSpireReward.set(c, true);
            }
            simulatedSpireRewards.text = "Simulated Spire Card";
            __instance.rewards.add(simulatedSpireRewards);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class IsSimulatedSpireField {
        public static SpireField<Boolean> isSimulatedSpireReward = new SpireField<>(() -> false);
    }

    @SpirePatch(
            clz = CardRewardScreen.class,
            method = "acquireCard"
    )
    public static class AddToSimulatedSpireDeck {
        public static SpireReturn Prefix(CardRewardScreen __instance, AbstractCard c) {
            if (IsSimulatedSpireField.isSimulatedSpireReward.get(c)) {
                if (AbstractDungeon.player.hasRelic(SimulatedSpire.ID)) {
                    SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
                    r.secondDeck.addToBottom(c);
                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = NeowReward.class,
            method = "getRewardOptions"
    )
    public static class AddToNeow {
        public static ArrayList<NeowRewardDef> Postfix(ArrayList<NeowRewardDef> __result, NeowReward __instance, final int category) {
                if (category == 3) {
                    __result.add(new NeowRewardDef(SIMULATED_SPIRE, "#rLose #ryour #ystarter #yRelic. #gObtain #ySimulated #ySpire"));
                }
                return __result;
        }
    }

    @SpirePatch(
            clz = NeowReward.class,
            method = "activate"
    )
    public static class ActivatePatch {
        public static void PostFix(NeowReward __instance) {
            if (__instance.type == SIMULATED_SPIRE) {
                AbstractRelic r = AbstractDungeon.player.relics.get(0);
                AbstractDungeon.player.loseRelic(r.relicId);
                AbstractDungeon.player.getRelic(SimulatedSpire.ID);
            }
        }
    }

    @SpireEnum
    public static NeowReward.NeowRewardType SIMULATED_SPIRE;
}
