package HalationCode.screens;

import HalationCode.patches.SimulatedSpirePatch;
import HalationCode.relics.ddlc.SimulatedSpire;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SecondMasterDeckViewScreen {

    public void open() {
        SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
        AbstractDungeon.screen = SimulatedSpirePatch.SECOND_MASTER_DECK;
        AbstractDungeon.gridSelectScreen.open(r.secondDeck, 999, "Purchase previously owned Persona", false);

    }

    public void close() {
        AbstractDungeon.overlayMenu.cancelButton.hide();
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("MAP_OPEN", 0.1f);
        } else {
            CardCrawlGame.sound.play("MAP_OPEN_2", 0.1f);
        }
    }

}
