package HalationCode.powers;

import HalationCode.tools.TextureLoader;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RestartDamagePower extends AbstractPower {
    public static final String POWER_ID = "halation:RestartDamagePower";
    private static final PowerStrings  powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RestartDamagePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.owner = owner;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.img = TextureLoader.getTexture("HalationImages/powers/RestartDamagePower.png");
        this.amount = amount;
    }

    public void updateDescription() {
       this.description = DESCRIPTIONS[0] + this.amount * 20 + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float modifier = 1;
        if (type == DamageInfo.DamageType.NORMAL) {
            modifier = 1.20F * this.amount;
        }
        return damage * modifier;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
}
