package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnSmithRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PrussianBluePaint extends CustomRelic implements BetterOnSmithRelic {
    public static final String ID = "halation:PrussianBluePaint";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/PrussianBluePaint.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public PrussianBluePaint() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PrussianBluePaint();
    }

    @Override
    public void betterOnSmith(AbstractCard c) {
        if (!c.cardID.equals(AscendersBane.ID) && c.type == AbstractCard.CardType.CURSE || c.color == AbstractCard.CardColor.CURSE) {
            p.masterDeck.removeCard(c);
        }
    }
}
