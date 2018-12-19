package HalationCode.relics.aobuta;

import HalationCode.patches.SmartPhonePatch;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnSkipCardRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class SmartPhone extends CustomRelic implements OnSkipCardRelic {
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

    @Override
    public void onSkipSingingBowl(RewardItem rewardItem) {
        if (SmartPhonePatch.smartBowl) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
            CardCrawlGame.sound.play("BLUNT_FAST");
            AbstractDungeon.player.damage(new DamageInfo(null, AbstractDungeon.player.maxHealth/10));
            SmartPhonePatch.smartBowl = false;
        }
        SmartPhonePatch.notSmartCards.clear();
    }

    @Override
    public void onSkipCard(RewardItem rewardItem) {
        if (SmartPhonePatch.smartSkip) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
            CardCrawlGame.sound.play("BLUNT_FAST");
            AbstractDungeon.player.damage(new DamageInfo(null, AbstractDungeon.player.maxHealth/10));
            SmartPhonePatch.smartSkip = false;
        }
        SmartPhonePatch.notSmartCards.clear();
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (!SmartPhonePatch.smartSkip && !SmartPhonePatch.smartBowl) {
            for (AbstractCard ca : SmartPhonePatch.notSmartCards) {
                if (c == ca) {
                    return;
                } else {
                    CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
                    CardCrawlGame.sound.play("BLUNT_FAST");
                    AbstractDungeon.player.damage(new DamageInfo(null, AbstractDungeon.player.maxHealth/10));
                }
            }
        }
        SmartPhonePatch.notSmartCards.clear();
    }

    @Override
    public void onVictory() {
        this.flash();
    }
}
