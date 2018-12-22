package HalationCode.relics.ddlc;

import HalationCode.patches.BottledHappinessPatch;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.function.Predicate;

public class BottledHappiness extends CustomRelic implements CustomBottleRelic, CustomSavable<ArrayList<Integer>> {
    public static final String ID = "halation:BottledHappiness";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/BottledHappiness.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean cardSelected = true;
    private ArrayList<AbstractCard> cards = new ArrayList<>();

    public BottledHappiness() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BottledHappiness();
    }

    @Override
    public Predicate<AbstractCard> isOnCard()
    {
        return BottledHappinessPatch.inBottledHappiness::get;
    }

    @Override
    public ArrayList<Integer> onSave()
    {
        ArrayList<Integer> cardIndexes = new ArrayList<>();
        for (AbstractCard c : cards) {
            cardIndexes.add(AbstractDungeon.player.masterDeck.group.indexOf(c));
        }
        return cardIndexes;
    }

    @Override
    public void onLoad(ArrayList<Integer> cardIndexes)
    {
        if (cardIndexes == null || cardIndexes.size() == 0) {
            return;
        }
        for (int i = 0; i < cardIndexes.size(); i++) {
            this.cards.add(AbstractDungeon.player.masterDeck.group.get(i));
        }
        for (AbstractCard c : this.cards) {
            BottledHappinessPatch.inBottledHappiness.set(c, true);
        }
        if (cards != null && cards.size() != 0) {
            setDescriptionAfterLoading();
        }
    }

    @Override
    public void onEquip()
    {
        if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck).size() != 0) {
            cardSelected = false;
            if (AbstractDungeon.isScreenUp) {
                AbstractDungeon.dynamicBanner.hide();
                AbstractDungeon.overlayMenu.cancelButton.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.screen;
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck),
                    1, DESCRIPTIONS[1] + name + ".",
                    false, false, false, false);
        }
    }

    @Override
    public void onUnequip()
    {
        if (this.cards != null && this.cards.size() > 0) {
            for (AbstractCard c : this.cards) {
                AbstractCard cardInDeck = AbstractDungeon.player.masterDeck.getSpecificCard(c);
                if (cardInDeck != null) {
                    BottledHappinessPatch.inBottledHappiness.set(cardInDeck, false);
                }
            }


        }
    }

    @Override
    public void update()
    {
        super.update();

        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            cardSelected = true;
            cards.add(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
            BottledHappinessPatch.inBottledHappiness.set(AbstractDungeon.gridSelectScreen.selectedCards.get(0), true);
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            setDescriptionAfterLoading();
        }
    }

    private void setDescriptionAfterLoading()
    {
        StringBuilder s = new StringBuilder();
        for (AbstractCard c : this.cards) {
            if (this.cards.indexOf(c) != this.cards.size() - 1) {
                s.append(FontHelper.colorString(c.name, "y")).append(", ");
            } else {
                s.append("and ").append(FontHelper.colorString(c.name, "y"));
            }
        }
        String finalString = s.toString();
        description = finalString + DESCRIPTIONS[2];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        if (this.cards != null && this.cards.size() > 0) {
            for (AbstractCard ca : this.cards) {
                if (ca == c) {
                    AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                    AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
                }
            }
        }
    }

    @Override
    public void onVictory() {
        this.onEquip();
    }
}
