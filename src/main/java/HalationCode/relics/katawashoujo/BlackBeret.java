package HalationCode.relics.katawashoujo;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class BlackBeret extends CustomRelic {
    public static final String ID = "halation:BlackBeret";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/BlackBeret.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static int maxHealthLimit;

    public BlackBeret() {
        super(ID, IMG, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackBeret();
    }

    @Override
    public void onEquip() {
        maxHealthLimit = AbstractDungeon.player.maxHealth / 3;
        this.counter = maxHealthLimit;
    }

    public static void maxHPChange(int i) {
        maxHealthLimit = (AbstractDungeon.player.maxHealth + i) / 3;
    }

    @Override
    public void atBattleStart() {
        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
            if (AbstractDungeon.player.currentHealth <= maxHealthLimit) {
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, 3), 3));
            }
        }
    }
}
