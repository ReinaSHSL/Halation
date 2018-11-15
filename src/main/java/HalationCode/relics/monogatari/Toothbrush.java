package HalationCode.relics.monogatari;

import HalationCode.interfaces.OnPlayerLoseBlockRelic;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Toothbrush extends CustomRelic implements OnPlayerLoseBlockRelic {
    public static final String ID = "halation:Toothbrush";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Toothbrush.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Toothbrush() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK);
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
    public void onBlockBroken(AbstractCreature m) {

    }

    @Override
    public DamageInfo onPlayerLoseBlock(DamageInfo i) {
        if (p.currentBlock == 0) {
            if (am.turnHasEnded) {
                am.addToBottom(new ApplyPowerAction(p, p, new WeakPower(i.owner, 1, true), 1));
            } else {
                am.addToBottom(new ApplyPowerAction(p, p, new WeakPower(i.owner, 1, false), 1));
            }
        }
        return i;
    }
}
