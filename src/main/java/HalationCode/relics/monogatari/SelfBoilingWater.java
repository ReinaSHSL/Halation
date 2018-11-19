package HalationCode.relics.monogatari;

import HalationCode.interfaces.OnRemoveCardFromMasterDeckRelic;
import HalationCode.interfaces.OnSkipCardRelic;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SelfBoilingWater extends CustomRelic implements OnRemoveCardFromMasterDeckRelic, OnSkipCardRelic {
    public static final String ID = "halation:SelfBoilingWater";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/SelfBoilingWater.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public SelfBoilingWater() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SelfBoilingWater();
    }

    @Override
    public void onRemoveCardFromMasterDeck(AbstractCard c)
    {
        System.out.println("Master deck fuck");
        this.counter = 0;
    }

    @Override
    public void onObtainCard(final AbstractCard card) {
       if (this.counter < 0) {
           this.counter = 0;
       }
       if (this.counter < 3) {
           this.counter++;
       }
    }

    @Override
    public void onSkipSingingBowl() {
        System.out.println("Singing Bowl Fuck");
        this.flash();
        this.counter = 0;
    }

    @Override
    public void onSkipCard() {
        System.out.println("Skip fuck");
        this.flash();
        this.counter = 0;
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.counter), this.counter));
    }
}
