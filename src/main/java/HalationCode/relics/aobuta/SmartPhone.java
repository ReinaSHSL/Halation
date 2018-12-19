package HalationCode.relics.aobuta;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SmartPhone extends CustomRelic {
    public static final String ID = "halation:SmartPhone";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/SmartPhone.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public SmartPhone() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SmartPhone();
    }

    @Override
    public void onEquip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        ++energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        --energy.energyMaster;
    }

}
