package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Prosthetics extends CustomRelic implements OnReceivePowerRelic {
    public static final String ID = "halation:Prosthetics";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Prosthetics.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public Prosthetics() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Prosthetics();
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth(25);
    }

    @Override
    public boolean onReceivePower(AbstractPower p, AbstractCreature m) {
        if (p.type == AbstractPower.PowerType.DEBUFF) {
            this.flash();
            if (m instanceof AbstractMonster) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, p, p.amount));
            }
            return false;
        }
        return true;
    }
}
