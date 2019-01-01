package HalationCode.relics.madeinabyss;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EternalFortune extends CustomRelic {
    public static final String ID = "halation:EternalFortune";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/EternalFortune.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static final int HP_GAIN = 3;

    public EternalFortune() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HP_GAIN + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EternalFortune();
    }

    @Override
    public void onEquip() {
        p.increaseMaxHp(3, true);
    }

    public void increment() {
        if (this.counter < 0) {
            this.counter = 0;
        }
        this.counter++;
        p.increaseMaxHp(HP_GAIN, true);
    }

    @Override
    public void onVictory() {
        int roll = AbstractDungeon.relicRng.random(0, 99);
        if (roll < 9) {
            AbstractDungeon.getCurrRoom().addRelicToRewards(new EternalFortune());
        }
    }

    @Override
    public void instantObtain()
    {
        if (AbstractDungeon.player.hasRelic(EternalFortune.ID)) {
            EternalFortune EternalFortune = (EternalFortune) AbstractDungeon.player.getRelic(ID);
            EternalFortune.increment();
            EternalFortune.flash();
        } else {
            super.instantObtain();
        }
    }

    @Override
    public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip)
    {
        if (AbstractDungeon.player.hasRelic(EternalFortune.ID)) {
            EternalFortune EternalFortune = (EternalFortune) AbstractDungeon.player.getRelic(ID);
            EternalFortune.increment();
            EternalFortune.flash();

            isDone = true;
            isObtained = true;
            discarded = true;
        } else {
            super.instantObtain(p, slot, callOnEquip);
        }
    }

    @Override
    public void obtain()
    {
        if (AbstractDungeon.player.hasRelic(EternalFortune.ID)) {
            EternalFortune EternalFortune = (EternalFortune) AbstractDungeon.player.getRelic(ID);
            EternalFortune.increment();
            EternalFortune.flash();
        } else {
            super.obtain();
        }
    }
}
