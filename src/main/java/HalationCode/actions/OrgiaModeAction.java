package HalationCode.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OrgiaModeAction extends AbstractGameAction {

    public OrgiaModeAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (!AbstractDungeon.player.hand.isEmpty()) {
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    AbstractDungeon.getCurrRoom().souls.remove(card);
                    card.freeToPlayOnce = true;
                    AbstractDungeon.player.limbo.group.add(card);
                    AbstractMonster target = AbstractDungeon.getRandomMonster();
                    card.applyPowers();
                    AbstractDungeon.actionManager.addToTop(new QueueCardAction(card, target));
                    AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.limbo, true));
                    if (!Settings.FAST_MODE) {
                        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    } else {
                        AbstractDungeon.actionManager.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                    }
                }
            }
            this.isDone = true;
        }
    }
}
