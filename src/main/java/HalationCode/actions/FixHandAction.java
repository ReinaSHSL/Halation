package HalationCode.actions;

import HalationCode.relics.monogatari.RainbowClouds;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FixHandAction extends AbstractGameAction {
    public static final String[] TEXT = new String[]{"Pick ", " Cards to add to your hand."};
    private AbstractPlayer p;

    public FixHandAction(final int amount) {
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (this.duration != Settings.ACTION_DUR_FASTER) {
            final CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (final AbstractCard c2 : this.p.drawPile.group) {
                tmp.addToRandomSpot(c2);
            }
            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }
            if (tmp.size() == 1) {
                final AbstractCard card = tmp.getTopCard();
                if (this.p.hand.size() == 10) {
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0f);
                    card.drawScale = 0.12f;
                    card.targetDrawScale = 0.75f;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    this.p.drawPile.removeCard(card);
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }
                this.isDone = true;
                return;
            }
            if (tmp.size() <= this.amount) {
                for (int i = 0; i < tmp.size(); ++i) {
                    final AbstractCard card2 = tmp.getNCardFromTop(i);
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card2);
                        this.p.createHandIsFullDialog();
                    } else {
                        card2.unhover();
                        card2.lighten(true);
                        card2.setAngle(0.0f);
                        card2.drawScale = 0.12f;
                        card2.targetDrawScale = 0.75f;
                        card2.current_x = CardGroup.DRAW_PILE_X;
                        card2.current_y = CardGroup.DRAW_PILE_Y;
                        this.p.drawPile.removeCard(card2);
                        AbstractDungeon.player.hand.addToTop(card2);
                        AbstractDungeon.player.hand.refreshHandLayout();
                        AbstractDungeon.player.hand.applyPowers();
                    }
                }
            }
            AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0] + RainbowClouds.handSize + TEXT[1], false);
            this.tickDuration();
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (final AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    c.unhover();
                    if (this.p.hand.size() == BaseMod.DEFAULT_MAX_HAND_SIZE) {
                        this.p.drawPile.moveToDiscardPile(c);
                        this.p.createHandIsFullDialog();
                    } else {
                        this.p.drawPile.removeCard(c);
                        this.p.hand.addToTop(c);
                    }
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }
            this.isDone = true;
        }
    }
}