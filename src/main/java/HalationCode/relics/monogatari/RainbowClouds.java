package HalationCode.relics.monogatari;

import HalationCode.actions.FixHandAction;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RainbowClouds extends CustomRelic {
    public static final String ID = "halation:RainbowClouds";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/RainbowClouds.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    public static int handSize = 0;

    public RainbowClouds() {
        super(ID, IMG, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RainbowClouds();
    }

    @Override
    public void onEnterRoom(AbstractRoom r) {
        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
            handSize = AbstractDungeon.player.masterHandSize;
            AbstractDungeon.player.masterHandSize = 0;
        }
    }

    @Override
    public void  atBattleStartPreDraw() {
        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new NoDrawPower(p)));
            AbstractDungeon.actionManager.addToBottom(new FixHandAction(handSize));
        }

    }

    @Override
    public void onVictory() {
        AbstractDungeon.player.masterHandSize = handSize;
    }
}
