package HalationCode.actions;

import HalationCode.cards.MainDeck;
import HalationCode.cards.SecondDeck;
import HalationCode.relics.ddlc.SimulatedSpire;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PickDeckAction extends AbstractGameAction {
    public static boolean isPickingDeck = false;
    public static CardGroup tmp = null;

    public PickDeckAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            isPickingDeck = true;
            tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            tmp.addToBottom(new SecondDeck());
            tmp.addToBottom(new MainDeck());
            AbstractDungeon.gridSelectScreen.open(tmp, 1, "Pick a deck for combat.", false, false);
            tickDuration();
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                if (AbstractDungeon.gridSelectScreen.selectedCards.get(0).cardID.equals(SecondDeck.ID)) {
                    SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
                    AbstractDungeon.player.drawPile.clear();
                    for (AbstractCard c : r.secondDeck.group) {
                        AbstractDungeon.player.drawPile.addToRandomSpot(c.makeSameInstanceOf());
                    }
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                isPickingDeck = false;
                this.isDone = true;
            }
        }
    }

}
