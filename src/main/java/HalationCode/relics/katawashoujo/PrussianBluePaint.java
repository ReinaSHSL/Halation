package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnSmithRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

public class PrussianBluePaint extends CustomRelic implements BetterOnSmithRelic {
    public static final String ID = "halation:PrussianBluePaint";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/PrussianBluePaint.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static ArrayList<AbstractCard> cardsToShow = new ArrayList<>();

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
        if (c.type == AbstractCard.CardType.CURSE || c.color == AbstractCard.CardColor.CURSE) {
            p.masterDeck.removeCard(c);
            AbstractCard ca = p.masterDeck.getUpgradableCards().getRandomCard(true);
            ca.upgrade();
            cardsToShow.add(ca);
        }
    }

    public static void cardEffects() {
        for (AbstractCard c : cardsToShow) {
            final float x = MathUtils.random(0.4f, 0.9f) * Settings.WIDTH;
            final float y = MathUtils.random(0.6f, 0.8f) * Settings.HEIGHT;
            AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), x, y));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
        }
        cardsToShow.clear();
    }
}
