package HalationCode.relics.lovelive;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class LoveArrow extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:LoveArrow";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/LoveArrow.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public LoveArrow() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LoveArrow();
    }


}
