package HalationCode.ui;

import HalationCode.relics.ddlc.SimulatedSpire;
import HalationCode.tools.TextureLoader;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class SimulatedSpireButton extends TopPanelItem {
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/ui/SimulatedSpireButton.png");
    public static final String ID = "halation:SimulatedSpireButton";

    public SimulatedSpireButton() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
            AbstractDungeon.gridSelectScreen.open(r.secondDeck, 999, "Second Deck", false, false, false, false);
            AbstractDungeon.overlayMenu.cancelButton.show("Return");
        }
    }



}
