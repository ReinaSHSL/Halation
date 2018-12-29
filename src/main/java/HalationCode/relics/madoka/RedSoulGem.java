package HalationCode.relics.madoka;

import HalationCode.powers.NoSkillsPower;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RedSoulGem extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:RedSoulGem";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/RedSoulGem.png");
    private boolean used = false;

    public RedSoulGem() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RedSoulGem();
    }

    @Override
    public void atBattleStart() {
        this.used = false;
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && GameActionManager.turn == 1 && !this.used) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, 6), 6));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new NoSkillsPower(AbstractDungeon.player, false)));
            this.used = true;
        }
    }
}
