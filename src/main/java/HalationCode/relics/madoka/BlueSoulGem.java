package HalationCode.relics.madoka;

import HalationCode.powers.InevitabilityPower;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BlueSoulGem extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:BlueSoulGem";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/BlueSoulGem.png");

    public BlueSoulGem() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlueSoulGem();
    }

    @Override
    public void onEquip() {
        this.counter = 2;
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.counter != 0) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (m.type == AbstractMonster.EnemyType.BOSS) {
                    return;
                }
            }
            flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,
                    AbstractDungeon.player, new InevitabilityPower(AbstractDungeon.player, 1), 1));
            this.counter--;
        }
    }
}
