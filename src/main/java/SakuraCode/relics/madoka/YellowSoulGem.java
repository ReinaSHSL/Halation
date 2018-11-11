package SakuraCode.relics.madoka;

import SakuraCode.relics.AbstractSakuraRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YellowSoulGem extends AbstractSakuraRelic implements ClickableRelic {
    public static final String ID = "sakura:YellowSoulGem";

    public YellowSoulGem() {
        super(ID, "YellowSoulGem.png", RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new YellowSoulGem();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !this.usedUp) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player, 15,
                        DamageInfo.DamageType.NORMAL)));
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new VulnerablePower(AbstractDungeon.player, 99, false)));
            this.counter++;
            counterCheck();
        }
    }

    public void counterCheck() {
        if (this.counter >= 8) {
            this.usedUp = true;
        }
    }
}
