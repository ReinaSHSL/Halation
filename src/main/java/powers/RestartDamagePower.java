package powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import relics.PurpleSoulGem;

public class RestartDamagePower extends AbstractPower {
    public static final String POWER_ID = "sakura:RestartDamagePower";
    private static final PowerStrings  powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;;
    private boolean justApplied = false;

    public RestartDamagePower(AbstractCreature owner) {
        this.name = NAME;
        this.owner = owner;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
    }

    public void updateDescription() {
       this.description = DESCRIPTIONS[0];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float modifier = 1.20F;
        if (AbstractDungeon.player.hasRelic(PurpleSoulGem.ID)) {
            modifier = 1.20F * (float)AbstractDungeon.player.getRelic(PurpleSoulGem.ID).counter;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * modifier : damage;
    }

    static {

    }
}
