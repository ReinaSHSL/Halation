package HalationCode.ui;

import HalationCode.HalationModInitializer;
import HalationCode.actions.PickDeckAction;
import HalationCode.patches.SimulatedSpirePatch;
import HalationCode.relics.ddlc.SimulatedSpire;
import HalationCode.tools.TextureLoader;
import basemod.ReflectionHacks;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimulatedSpireButton extends TopPanelItem {
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/ui/SimulatedSpireButton.png");
    public static final String ID = "halation:SimulatedSpireButton";

    public SimulatedSpireButton() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
        SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            if (PickDeckAction.isPickingDeck) {
                ReflectionHacks.setPrivate(AbstractDungeon.gridSelectScreen, GridCardSelectScreen.class, "targetGroup", r.secondDeck);
                AbstractDungeon.overlayMenu.cancelButton.show("Return");
                return;
            }
            AbstractDungeon.closeCurrentScreen();
            return;
        }

        AbstractDungeon.gridSelectScreen.open(r.secondDeck, 999, "Second Deck", false, false, false, false);
        AbstractDungeon.overlayMenu.cancelButton.show("Return");
    }

    @Override
    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player.hasRelic(SimulatedSpire.ID)) {
            SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
            super.render(sb);
            FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont, Integer.toString(r.secondDeck.size()), this.x + 58.0f * Settings.scale, this.y + 25.0f * Settings.scale, Color.WHITE.cpy());
        }
    }



}
