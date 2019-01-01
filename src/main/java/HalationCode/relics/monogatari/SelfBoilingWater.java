package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnRemoveCardFromMasterDeckRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnSkipCardRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class SelfBoilingWater extends CustomRelic implements OnRemoveCardFromMasterDeckRelic, OnSkipCardRelic {
    public static final String ID = "halation:SelfBoilingWater";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/SelfBoilingWater.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static final int STR_AMT = 1;
    private static final int LIMIT = 3;

    public SelfBoilingWater() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + STR_AMT + DESCRIPTIONS[1] + LIMIT + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SelfBoilingWater();
    }

    @Override
    public void onRemoveCardFromMasterDeck(AbstractCard c)
    {
        this.counter = 0;
    }

    @Override
    public void onObtainCard(final AbstractCard card) {
       if (this.counter < 0) {
           this.counter = 0;
       }
       if (this.counter < LIMIT) {
           this.counter += STR_AMT;
       }
    }

    @Override
    public void onSkipSingingBowl(RewardItem r) {
        this.flash();
        this.counter = 0;
    }

    @Override
    public void onSkipCard(RewardItem r) {
        this.flash();
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        if (this.counter > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.counter), this.counter));
        }
    }
}
