package SakuraCode.powers;

import SakuraCode.tools.TextureLoader;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;

public class NoSkillsPower extends AbstractPower{
    public static final String POWER_ID = "sakura:NoSkillsPower";
    private static final PowerStrings  powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;;

    public NoSkillsPower(final AbstractCreature owner) {
        this.owner = owner;
        this.amount = 1;
        this.updateDescription();
        this.img = TextureLoader.getTexture("SakuraImages/powers/NoSkillsPower");
        this.isTurnBased = true;
        this.type = AbstractPower.PowerType.DEBUFF;
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_ENTANGLED", 0.05f);
    }

    @Override
    public void updateDescription() {
        this.description = EntanglePower.DESCRIPTIONS[0];
    }
}
