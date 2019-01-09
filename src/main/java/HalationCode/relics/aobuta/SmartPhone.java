package HalationCode.relics.aobuta;

import HalationCode.patches.SmartPhonePatch;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnSkipCardRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SmartPhone extends CustomRelic implements OnSkipCardRelic {
    public static final String ID = "halation:SmartPhone";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/SmartPhone.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static boolean doTheThing = false;
    private static final int DMG_AMT = 10;
    private static boolean isCombat = false;

    public SmartPhone() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DMG_AMT + DESCRIPTIONS[1];
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
        if (!SmartPhonePatch.smartBowl) {
            doTheThing = true;
        }
        SmartPhonePatch.smartCard = null;
    }

    @Override
    public void onSkipCard(RewardItem rewardItem) {
        if (!SmartPhonePatch.smartSkip) {
            doTheThing = true;
        }
        SmartPhonePatch.smartCard = null;
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (SmartPhonePatch.smartSkip || SmartPhonePatch.smartBowl && isCombat) {
            doTheThing = true;
        }
        if (!SmartPhonePatch.smartSkip && !SmartPhonePatch.smartBowl && isCombat) {
            if (!c.cardID.equals(SmartPhonePatch.smartCard.cardID)) {
                doTheThing = true;
            }
            SmartPhonePatch.smartCard = null;
        }
    }

    @Override
    public void atPreBattle() {
        isCombat = true;
    }

    @Override
    public void onEnterRoom(AbstractRoom r) {
        this.flash();
        isCombat = false;
    }

    public static void morePostUpdateBullshit() {
        if (doTheThing) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
            CardCrawlGame.sound.play("BLUNT_FAST");
            AbstractDungeon.player.damage(new DamageInfo(null, AbstractDungeon.player.maxHealth/DMG_AMT));
            SmartPhonePatch.smartCard = null;
            doTheThing = false;
        }
    }
}
