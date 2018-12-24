package HalationCode.ui;

import HalationCode.relics.ddlc.SimulatedSpire;
import HalationCode.tools.TextureLoader;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SimulatedSpireButton extends TopPanelItem {
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/ui/SimulatedSpireButton.png");
    public static final String ID = "halation:SimulatedSpireButton";
    private static CardGroup temporaryDeck = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public SimulatedSpireButton() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            swapDecks();
        }
    }

    private static void swapDecks() {
        if (AbstractDungeon.player.hasRelic(SimulatedSpire.ID)) {
            SimulatedSpire r = (SimulatedSpire)AbstractDungeon.player.getRelic(SimulatedSpire.ID);
            ArrayList<AbstractCard> tempDeck = AbstractDungeon.player.masterDeck.group;
            AbstractDungeon.player.masterDeck.group = r.secondDeck.group;
            r.secondDeck.group = tempDeck;
        }
    }


}
