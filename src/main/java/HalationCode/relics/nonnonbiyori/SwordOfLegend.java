package HalationCode.relics.nonnonbiyori;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

public class SwordOfLegend extends CustomRelic {
    public static final String ID = "halation:SwordOfLegend";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/SwordOfLegend.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static final int HEAL_AMT = 3;

    public SwordOfLegend() {
        super(ID, IMG, RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HEAL_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SwordOfLegend();
    }

    @Override
    public void onEnterRoom(AbstractRoom r) {
        if (r instanceof MonsterRoom) {
            if (this.counter < 0) {
                this.counter = HEAL_AMT;
            } else {
                this.counter += HEAL_AMT;
            }
        }
        if (r instanceof RestRoom) {
            AbstractDungeon.player.heal(this.counter);
            this.counter = 0;
        }
    }
}
