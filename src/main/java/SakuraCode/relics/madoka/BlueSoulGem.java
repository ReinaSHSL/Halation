package SakuraCode.relics.madoka;

import SakuraCode.powers.InevitabilityPower;
import SakuraCode.relics.AbstractSakuraRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

public class BlueSoulGem extends AbstractSakuraRelic implements ClickableRelic {
    public static final String ID = "sakura:BlueSoulGem";

    public BlueSoulGem() {
        super(ID, "BlueSoulGem.png", RelicTier.SPECIAL, LandingSound.MAGICAL);
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
                && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) && this.counter != 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,
                    AbstractDungeon.player, new InevitabilityPower(AbstractDungeon.player, 1), 1));
            this.counter--;
        }
    }
}
