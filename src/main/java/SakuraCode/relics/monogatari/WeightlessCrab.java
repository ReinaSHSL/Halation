package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class WeightlessCrab extends CustomRelic {
    public static final String ID = "sakura:WeightlessCrab";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/WeightlessCrab.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;

    public WeightlessCrab() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WeightlessCrab();
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
    }

    public static void onDraw(AbstractCard c) {
        AbstractRelic r = AbstractDungeon.player.getRelic(WeightlessCrab.ID);
        if (c.type == AbstractCard.CardType.CURSE) {
            r.counter++;
        }
        if (r.counter >= 3) {
            r.counter = 0;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePower(AbstractDungeon.player, 1), 1));
        }
    }
}
