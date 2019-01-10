package HalationCode.relics.ddlc;

import HalationCode.tools.TextureLoader;
import HalationCode.ui.SimulatedSpireButton;
import basemod.BaseMod;
import basemod.TopPanelItem;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

public class SimulatedSpire extends CustomRelic implements CustomSavable<List<CardSave>> {
    public static final String ID = "halation:SimulatedSpire";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/SimulatedSpire.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    public CardGroup secondDeck = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    public static SimulatedSpireButton simulatedSpireButton = new SimulatedSpireButton();

    public SimulatedSpire() {
        super(ID, IMG, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SimulatedSpire();
    }

    @Override
    public void onEquip() {
        BaseMod.addTopPanelItem(
                new SimulatedSpireButton()
        );
        ArrayList<String> starterDeck = AbstractDungeon.player.getStartingDeck();
        for (String s : starterDeck) {
            this.secondDeck.addToTop(CardLibrary.getCard(AbstractDungeon.player.chosenClass, s).makeCopy());
        }
        if (AbstractDungeon.ascensionLevel > 9) {
            this.secondDeck.addToBottom(new AscendersBane());
        }
    }

    @Override
    public List<CardSave> onSave() {
        ArrayList<CardSave> retVal = new ArrayList<>();
        for (AbstractCard card : this.secondDeck.group) {
            retVal.add(new CardSave(card.cardID, card.timesUpgraded, card.misc));
        }
        return retVal;
    }

    @Override
    public void onLoad(List<CardSave> cardSaves) {
        BaseMod.addTopPanelItem(simulatedSpireButton);
        for (CardSave s : cardSaves) {
            this.secondDeck.addToBottom(CardLibrary.getCopy(s.id, s.upgrades, s.misc));
        }
    }

    @Override
    public void onUnequip() {
        BaseMod.removeTopPanelItem(simulatedSpireButton);
    }
}
