package HalationCode.powers;

import HalationCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReallySexyPower extends AbstractPower {
    public static final String POWER_ID = "halation:AttractivePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean turnBased;


    public ReallySexyPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = TextureLoader.getTexture("HalationImages/powers/AttractivePower.png");
        this.canGoNegative = false;
    }

    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public float atDamageGive(float dmg, DamageInfo.DamageType type) {
        return dmg * 1.5F;
    }

    @Override
    public void onDeath() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m.hasPower(ActualSurroundedWtfPower.POWER_ID)) {
                m.flipHorizontal = false;
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(m, m, ActualSurroundedWtfPower.POWER_ID));
            }
        }
    }
}
