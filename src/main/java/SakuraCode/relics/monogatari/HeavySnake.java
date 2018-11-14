package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class HeavySnake extends CustomRelic {
    public static final String ID = "sakura:HeavySnake";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/HeavySnake.png");
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
        ArrayList<AbstractCard> unupgradedCards = new ArrayList<>();
        for (AbstractCard c : p.masterDeck.group) {
            if (!c.upgraded) {
                unupgradedCards.add(c);
            }
        }
        for (int i = 0; i < 3; i++) {
            int r = AbstractDungeon.cardRng.random(unupgradedCards.size()-1);
            unupgradedCards.get(r).upgrade();
        }
    }

}
