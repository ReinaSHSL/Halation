package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;

public class HeavySnake extends CustomRelic {
    public static final String ID = "halation:HeavySnake";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/HeavySnake.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public HeavySnake() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HeavySnake();
    }

    @Override
    public void atBattleStartPreDraw() {
        for (int i = 0; i < 3; i++) {
            AbstractCard c = AbstractDungeon.returnRandomCurse();
            am.addToBottom(new MakeTempCardInDrawPileAction(c, 1, false, false, true));
        }
    }

    public void onSmith() {
        this.flash();
        for (int i = 0; i < 3; i++) {
            if (p.masterDeck.getUpgradableCards().isEmpty()) {
                return;
            }
            AbstractCard c = p.masterDeck.getUpgradableCards().getRandomCard(true);
            c.upgrade();
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
        }
    }

}
