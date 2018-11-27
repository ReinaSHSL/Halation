package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Risk extends CustomRelic {
    public static final String ID = "halation:Risk";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Risk.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Risk() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Risk();
    }
}
