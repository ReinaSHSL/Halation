package HalationCode.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ObtainRelicLater extends AbstractGameEffect {
    private String relic;

    public ObtainRelicLater(String relic) {
        this.relic = relic;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractDungeon.player.getRelic(relic);
        isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
