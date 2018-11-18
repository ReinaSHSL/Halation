package HalationCode.relics.nonnonbiyori;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Sonsunser extends CustomRelic {
    public static final String ID = "halation:Sonsunser";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Sonsunser.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Sonsunser() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Sonsunser();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            if (this.counter < 0) {
                this.counter += 2;
            }
            this.counter++;
            if (this.counter >= 8) {
                ArrayList<AbstractCard> attacksPlayedThisTurn = new ArrayList<>();
                for (AbstractCard ca : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                    if (ca.type == AbstractCard.CardType.ATTACK) {
                        attacksPlayedThisTurn.add(ca);
                    }
                }
                for (AbstractCard ca : attacksPlayedThisTurn) {
                    if (ca.target == AbstractCard.CardTarget.ENEMY) {
                        ca.use(p, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.monsterRng));
                    } else {
                        c.use(p, null);
                    }
                }
            }
        }
    }

    @Override
    public void onPlayerEndTurn() {
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        this.counter = 0;
    }
}
