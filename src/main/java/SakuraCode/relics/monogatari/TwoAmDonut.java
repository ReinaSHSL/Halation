package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
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
    public static final String ID = "sakura:TwoAmDonut";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/TwoAmDonut.png");
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
            beginPulse();
            pulse = true;
        }
    }

    @Override
    public void atTurnStart() {
        flash();
        if (!pulse) {
            beginPulse();
            pulse = true;
        }
    }

    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            pulse = false;
        }
    }

    public void onPlayerEndTurn() {
        if (pulse) {
            am.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        }
    }
}
