package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TwoAmDonut extends CustomRelic {
    public static final String ID = "halation:TwoAmDonut";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/TwoAmDonut.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public TwoAmDonut() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TwoAmDonut();
    }

    @Override
    public void atPreBattle()
    {
        flash();
        if (!pulse) {
            beginLongPulse();
        }
    }

    @Override
    public void atTurnStart() {
        flash();
        if (!pulse) {
            beginLongPulse();
        }
    }

    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            stopPulse();
        }
    }

    public void onPlayerEndTurn() {
        if (pulse) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        }
    }
}
