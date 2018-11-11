package SakuraCode.powers;

import SakuraCode.relics.madoka.PurpleSoulGem;
import SakuraCode.tools.TextureLoader;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RestartDamagePower extends AbstractPower {
    public static final String POWER_ID = "sakura:RestartDamagePower";
    private static final PowerStrings  powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;;

    public RestartDamagePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.owner = owner;
        this.updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.img = TextureLoader.getTexture("SakuraImages/powers/RestartDamagePower.png");
        this.amount = amount;
    }

    public void updateDescription() {
       this.description = DESCRIPTIONS[0] + this.amount * 1.20F + DESCRIPTIONS[1];
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float modifier = 1.20F;
        if (AbstractDungeon.player.hasRelic(PurpleSoulGem.ID)) {
            modifier = 1.20F * (float)this.amount;
        }
        return type == DamageInfo.DamageType.NORMAL ? damage * modifier : damage;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
}
