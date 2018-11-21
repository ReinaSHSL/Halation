package HalationCode.relics.discord;

import HalationCode.tools.TextureLoader;
import HalationCode.tools.tetris.TetrisGame;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class LBlock extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:LBlock";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/LBlock.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public LBlock() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[1] + DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LBlock();
    }

    @Override
    public void onRightClick() {
        TetrisGame tetris = new TetrisGame();
        tetris.create();

    }
}
