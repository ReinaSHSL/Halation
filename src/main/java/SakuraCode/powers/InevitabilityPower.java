package SakuraCode.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import SakuraCode.tools.TextureLoader;

public class InevitabilityPower extends AbstractPower {
    public static final String POWER_ID = "sakura:InevitabilityPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public InevitabilityPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.img = getInevitabilityPowerTexture();
        this.canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        this.owner.maxHealth = this.owner.maxHealth/10;
    }
    @Override
    public float atDamageReceive(final float damage, final DamageInfo.DamageType damageType) {
       return 0;
    }

    @Override
    public void updateDescription()
    {
       this.description = DESCRIPTIONS[0];
    }


    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    private static Texture getInevitabilityPowerTexture() {
        return TextureLoader.getTexture("SakuraImages/powers/InevitabilityPower.png");
    }
}

