package HalationCode.ui;

import HalationCode.relics.ddlc.SimulatedSpire;
import HalationCode.tools.TextureLoader;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class SimulatedSpireButton extends TopPanelItem {
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/ui/SimulatedSpireButton.png");
    public static final String ID = "halation:SimulatedSpireButton";

    public SimulatedSpireButton() {
        super(IMG, ID);
    }

    @Overrideg
    protected void onClick() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
            AbstractDungeon.gridSelectScreen.open(r.secondDeck, 999, "Second Deck", false, false, false, false);
            AbstractDungeon.overlayMenu.cancelButton.show("Return");
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
        super.render(sb);
        FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont, Integer.toString(r.secondDeck.size()), this.x + 58.0f * Settings.scale, this.y + 25.0f * Settings.scale, Color.WHITE.cpy());
    }



}
