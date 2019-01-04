package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WeightlessCrab extends CustomRelic {
    public static final String ID = "halation:WeightlessCrab";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/WeightlessCrab.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static int CURSE_DRAW_AMT = 3;
    private static int INTANGIBLE_AMT = 1;

    public WeightlessCrab() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + CURSE_DRAW_AMT + DESCRIPTIONS[1] + INTANGIBLE_AMT + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WeightlessCrab();
    }

    @Override
    public void atBattleStart() {
        if (this.counter < 0) {
            this.counter = 0;
        }
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        if (c.type == AbstractCard.CardType.CURSE) {
            this.counter++;
        }
        if (this.counter >= CURSE_DRAW_AMT) {
            this.counter = 0;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, INTANGIBLE_AMT), INTANGIBLE_AMT));
        }
    }
}
