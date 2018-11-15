package HalationCode.relics.persona;

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

    public PapillonHeart() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PapillonHeart();
    }

    @Override
    public void atTurnStart() {
        if (this.counter < 0) {
            this.counter = 0;
        }
        counter++;
        if (this.counter >= 4) {
            this.counter = 0;
            am.addToBottom(new OrgiaModeAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null,true, AbstractDungeon.monsterRng)));
        }
    }
}
