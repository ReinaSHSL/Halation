package HalationCode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RandomEtherealCardToHand extends AbstractGameAction {
    private boolean isRare = false;
    private boolean isCurse = false;

    public RandomEtherealCardToHand(boolean isRare, boolean isCurse) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.isRare = isRare;
        this.isCurse = isCurse;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.isRare) {
                AbstractCard c = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, AbstractDungeon.cardRng).makeStatEquivalentCopy();
                c.isEthereal = true;
                c.exhaust = true;
                c.rawDescription = c.rawDescription + " NL Ethereal. NL Exhaust.";
                c.initializeDescription();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
                this.tickDuration();
            } else if (this.isCurse) {
                AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.CURSE).makeStatEquivalentCopy();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
                this.tickDuration();
            }
            else {
                AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeStatEquivalentCopy();
                c.isEthereal = true;
                c.exhaust = true;
                c.rawDescription = c.rawDescription + " NL Ethereal. NL Exhaust.";
                c.initializeDescription();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
                System.out.println("EXHAUST: " + c.exhaust);
                System.out.println("ETHEREAL: " + c.isEthereal);
                System.out.println("DESC: " + c.rawDescription);
                System.out.println("c " + c);
                this.tickDuration();
            }
        }

        this.isDone = true;
    }
}
