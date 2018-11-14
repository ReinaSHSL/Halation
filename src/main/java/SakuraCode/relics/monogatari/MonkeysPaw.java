package SakuraCode.relics.monogatari;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardAtBottomOfDeckAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MonkeysPaw extends CustomRelic {
    public static final String ID = "sakura:MonkeysPaw";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/MonkeysPaw.png");
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
        c.damage = c.damage/2;
        c.magicNumber = c.magicNumber/2; //TODO CHAOS_NEGATIVE_MAGIC
        c.misc = c.misc/2;
        c.block = c.block/2;
        am.addToBottom(new MakeTempCardInDrawPileAction(c, 2, false, false, true));
    }
}
