package SakuraCode.relics.monogatari;

import SakuraCode.powers.NoDamagePower;
import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PeacePiece extends CustomRelic {
    public static final String ID = "sakura:PeacePiece";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/PeacePiece.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public PeacePiece() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PeacePiece();
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStartPostDraw() {
        this.counter++;
        if (this.counter >= 3) {
            am.addToBottom(new ApplyPowerAction(p, p, new NoDamagePower(p)));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                am.addToBottom(new ApplyPowerAction(m, m, new NoDamagePower(m)));
            }
            this.counter = 0;
        }
    }
}
