package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
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

    public MonkeysPaw() {
        super(ID, IMG, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MonkeysPaw();
    }

    public void onUseCard(AbstractCard c, UseCardAction a) {
        System.out.println("SHIT OVER HERE " + c);
        AbstractCard tmp = c;
        tmp.damage = c.damage/2;
        tmp.magicNumber = c.magicNumber/2; //TODO CHAOS_NEGATIVE_MAGIC
        tmp.misc = c.misc/2;
        tmp.block = c.block/2;
        tmp.cost = c.cost/2;
        am.addToBottom(new MakeTempCardInDrawPileAction(tmp, 2, false, false, true));
        
    }
    //TODO FIX THIS
}
