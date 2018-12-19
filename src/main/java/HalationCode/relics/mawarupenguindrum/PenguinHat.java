package HalationCode.relics.mawarupenguindrum;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PenguinHat extends CustomRelic {
    public static final String ID = "halation:PenguinHat";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/PenguinHat.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    public static AbstractPlayer revivedClass = null;

    public PenguinHat() {
        super(ID, IMG, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PenguinHat();
    }
}
