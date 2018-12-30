package HalationCode.relics.lovelive;

import HalationCode.actions.TargetAction;
import HalationCode.powers.ActualSurroundedWtfPower;
import HalationCode.powers.ReallySexyPower;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class LoveArrow extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:LoveArrow";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/LoveArrow.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean usedThisCombat = false;

    public LoveArrow() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LoveArrow();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.actionManager.turnHasEnded && !this.usedThisCombat) {
            new TargetAction(this);
            this.usedThisCombat = true;
        }
    }

    public void arrowShoot(AbstractCreature m) {
        if (m.id.equals(SpireSpear.ID) || m.id.equals(SpireShield.ID)) return;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new ReallySexyPower(m)));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo == m) return;
            mo.flipHorizontal = true;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new ActualSurroundedWtfPower(mo)));
        }
    }

    @Override
    public void onVictory() {
        this.usedThisCombat = false;
    }
}
