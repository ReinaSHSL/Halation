package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IllGainedPizza extends CustomRelic {
    public static final String ID = "halation:IllGainedPizza";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/IllGainedPizza.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public IllGainedPizza() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new IllGainedPizza();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractDungeon.player.gainGold(1);
        return damageAmount;
    }

}
