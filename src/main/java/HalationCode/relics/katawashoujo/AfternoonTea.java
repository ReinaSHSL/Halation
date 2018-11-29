package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AfternoonTea extends CustomRelic {
    public static final String ID = "halation:AfternoonTea";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/AfternoonTea.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean firstTurn;

    public AfternoonTea() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.FLAT);
        this.firstTurn = true;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AfternoonTea();
    }

    @Override
    public void onRest() {
        if (this.counter != -2) {
            this.counter = -2;
            this.pulse = true;
        }
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            if (this.counter == -2) {
                this.pulse = false;
                this.counter = -1;
                this.flash();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RegenPower(AbstractDungeon.player, 5), 5));
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            }
            this.firstTurn = false;
        }
    }
}
