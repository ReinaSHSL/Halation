package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MonkeysPaw extends CustomRelic {
    public static final String ID = "halation:MonkeysPaw";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/MonkeysPaw.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static int COPY_AMT = 2;

    public MonkeysPaw() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + COPY_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MonkeysPaw();
    }

    public void onUseCard(AbstractCard c, UseCardAction a) {
        AbstractCard tmp = c;
        tmp.baseDamage = c.baseDamage/2;
        tmp.baseMagicNumber = c.baseMagicNumber/2; //TODO CHAOS_NEGATIVE_MAGIC
        tmp.misc = c.misc/2;
        tmp.baseBlock = c.baseBlock/2;
        AbstractDungeon.actionManager.addToBottom(new ReduceCostAction(tmp.uuid, tmp.cost/2));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardActionAction(tmp, COPY_AMT));
    }
}
