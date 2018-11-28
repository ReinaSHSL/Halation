package HalationCode.powers;

import HalationCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NoSkillsPower extends AbstractPower{
    public static final String POWER_ID = "halation:NoSkillsPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean turnBased;


    public NoSkillsPower(AbstractCreature owner, boolean isTurnBased) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.turnBased = isTurnBased;
        updateDescription();
        this.img = TextureLoader.getTexture("HalationImages/powers/NoSkillsPower.png");
        this.canGoNegative = false;
    }

    @Override
    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer && this.turnBased) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }


    @Override
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0]);
    }
}
