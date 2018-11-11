package SakuraCode.relics.hibike;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Yufonium extends CustomRelic {
    public static final String ID = "sakura:Yufonium";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/Yufonium.png");

    public Yufonium() {
        super(ID, IMG, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Yufonium();
    }
}
