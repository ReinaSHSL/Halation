package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IncompleteEncyclopedia extends CustomRelic {
    public static final String ID = "sakura:IncompleteEncyclopedia";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/IncompleteEncyclopedia.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public IncompleteEncyclopedia() {
        super(ID, IMG, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new IncompleteEncyclopedia();
    }



}
