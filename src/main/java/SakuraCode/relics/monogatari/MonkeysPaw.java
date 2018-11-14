package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MonkeysPaw extends CustomRelic {
    public static final String ID = "sakura:MonkeysPaw";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/MonkeysPaw.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public MonkeysPaw() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MonkeysPaw();
    }
}
