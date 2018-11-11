package SakuraCode.relics.madoka;

import SakuraCode.relics.AbstractSakuraRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class PinkSoulGem extends AbstractSakuraRelic implements ClickableRelic {
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

        }
    }
}
