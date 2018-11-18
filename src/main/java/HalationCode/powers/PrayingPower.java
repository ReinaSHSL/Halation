package HalationCode.powers;

import HalationCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PrayingPower extends AbstractPower {
    public static final String POWER_ID = "halation:PrayingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int healAmount;


    public PrayingPower(AbstractCreature owner, int healAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        this.img = TextureLoader.getTexture("HalationImages/powers/PrayingPower.png");
        this.canGoNegative = false;
        this.healAmount = healAmount;
    }

    @Override
    public void atStartOfTurn() {
       AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, this.owner, this.healAmount));
       for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
           AbstractDungeon.actionManager.addToBottom(new HealAction(m, this.owner, this.healAmount));
       }
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new PrayingPower(this.owner, this.healAmount)));
    }

    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.healAmount + DESCRIPTIONS[1]);
    }
}
