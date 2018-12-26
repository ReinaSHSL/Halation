package HalationCode.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ActualSurroundedWtfPower extends AbstractPower {
    public static final String POWER_ID = "halation:ActualSurroundedPower";
    private static final PowerStrings powerStrings =  CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =  powerStrings.NAME;;
    public static final String[] DESCRIPTIONS =  powerStrings.DESCRIPTIONS;

    public ActualSurroundedWtfPower(AbstractCreature owner) {
        this.name = NAME;
        this.owner = owner;
        this.ID = POWER_ID;
        this.amount = -1;
        this.updateDescription();
        this.loadRegion("surrounded");
    }

    @Override
    public float atDamageReceive(float dmg, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) return dmg * 1.5F;
        return dmg;
    }
}
