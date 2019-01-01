package HalationCode.relics.hibike;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Trompette extends CustomRelic {
    public static final String ID = "halation:Trompette";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Trompette.png");
    private static final int HEAL_AMT = 10;

    public Trompette() {
        super(ID, IMG, RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEAL_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Trompette();
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

    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (c.type != AbstractCard.CardType.ATTACK) {
            pulse = false;
        }
    }

    @Override
    public void onVictory()
    {
        if (pulse) {
            AbstractDungeon.player.heal(HEAL_AMT);
        }
        pulse = false;
    }
}
