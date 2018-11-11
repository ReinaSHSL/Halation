package SakuraCode.relics.madoka;

import SakuraCode.relics.AbstractSakuraRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.BetterOnLoseHpRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class PinkSoulGem extends AbstractSakuraRelic implements ClickableRelic, BetterOnLoseHpRelic {
    public static final String ID = "sakura:PinkSoulGem";

    public PinkSoulGem() {
        super(ID, "PinkSoulGem.png", RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new PinkSoulGem();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player, this.counter)));
            this.counter = 0;
        }
    }

    @Override
    public int betterOnLoseHp(DamageInfo info, int damageAmount) {
        this.counter = damageAmount * 2;
        return damageAmount;
    }
}