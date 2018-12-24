package HalationCode.patches;

import HalationCode.relics.ddlc.SimulatedSpire;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CardRewardScreen;


public class SimulatedSpirePatch {
    @SpirePatch(
            clz = AbstractRoom.class,
            method = "addCardToRewards"
    )
    public static class AddSimulatedSpireCards {
        public static void Prefix(AbstractRoom __instance) {
            RewardItem simulatedSpireRewards = new RewardItem();
            if (simulatedSpireRewards.cards.size() == 0) return;
            for (AbstractCard c : simulatedSpireRewards.cards) {
                IsSimulatedSpireField.isSimulatedSpireReward.set(c, true);
            }
            SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
            simulatedSpireRewards.text = r.rewardScreenText;
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
}
