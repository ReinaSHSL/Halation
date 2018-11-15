package HalationCode.relics.hibike;

import HalationCode.powers.HalveDamagePower;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Yufonium extends CustomRelic {
    public static final String ID = "halation:Yufonium";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Yufonium.png");

    public Yufonium() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Yufonium();
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new HalveDamagePower(AbstractDungeon.player)));
        this.counter = 0;
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        if (c.type == AbstractCard.CardType.ATTACK && c.cost > 0) {
            AbstractDungeon.actionManager.addToBottom(new ReduceCostAction(c.uuid, 1));
        }
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void onPlayCard(final AbstractCard card, final AbstractMonster m) {
        if (this.counter < 20 && card.type != AbstractCard.CardType.CURSE) {
            ++this.counter;
            if (this.counter >= 20) {
                this.flash();
            }
        }
    }

    @Override
    public boolean canPlay(final AbstractCard card) {
        if (this.counter >= 20 && card.type != AbstractCard.CardType.CURSE) {
            return false;
        }
        return true;
    }
}
