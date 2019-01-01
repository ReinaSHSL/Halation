package HalationCode.relics.persona3;

import HalationCode.actions.OrgiaModeAction;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PapillonHeart extends CustomRelic {
    public static final String ID = "halation:PapillonHeart";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/PapillonHeart.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static final int TURNS = 4;

    public PapillonHeart() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TURNS + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PapillonHeart();
    }

    @Override
    public void atTurnStartPostDraw() {
        if (this.counter < 0) {
            this.counter = 0;
        }
        counter++;
        if (this.counter >= TURNS) {
            this.counter = 0;
            AbstractDungeon.actionManager.addToBottom(new OrgiaModeAction());
        }
    }

}
