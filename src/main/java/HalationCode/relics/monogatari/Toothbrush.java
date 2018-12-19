package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnRemoveCardFromMasterDeckRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Toothbrush extends CustomRelic implements OnRemoveCardFromMasterDeckRelic {
    public static final String ID = "halation:Toothbrush";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Toothbrush.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Toothbrush() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Toothbrush();
    }

    @Override
    public void onRemoveCardFromMasterDeck(AbstractCard c) {
        p.increaseMaxHp(3, false);
    }
}
