package HalationCode.relics.ddlc;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Thesaurus extends CustomRelic {
    public static final String ID = "halation:Thesaurus";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Thesaurus.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private boolean cardsSelected;

    public Thesaurus() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Thesaurus();
    }

    @Override
    public void onEquip() {
        this.cardsSelected = false;
        final CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (final AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (!card.cardID.equals(Necronomicurse.ID) && !card.cardID.equals(AscendersBane.ID)) {
                tmp.addToTop(card);
            }
        }
        if (tmp.group.isEmpty()) {
            this.cardsSelected = true;
            return;
        }
        if (tmp.group.size() <= 3) {
            AbstractDungeon.player.masterDeck.group.clear();
            for (final AbstractCard card : tmp.group) {
                ArrayList<AbstractCard.CardRarity> rarities = new ArrayList<>();
                ArrayList<AbstractCard.CardRarity> newRarities = new ArrayList<>();
                rarities.add(card.rarity);
                for (AbstractCard.CardRarity r : rarities) {
                    switch (r) {
                        case COMMON:
                            newRarities.add(AbstractCard.CardRarity.UNCOMMON);
                        case UNCOMMON:
                            newRarities.add(AbstractCard.CardRarity.RARE);
                        case RARE:
                            newRarities.add(AbstractCard.CardRarity.COMMON);
                        case BASIC:
                            newRarities.add(AbstractCard.CardRarity.UNCOMMON);
                        default:
                            newRarities.add(AbstractCard.CardRarity.UNCOMMON);
                    }
                }
                AbstractDungeon.player.masterDeck.removeCard(card);
                for (AbstractCard.CardRarity r : newRarities) {
                    AbstractCard c = AbstractDungeon.getCard(r);
                    AbstractDungeon.player.masterDeck.addToBottom(c);
                }
            }
            this.cardsSelected = true;
        }
        else if (!AbstractDungeon.isScreenUp) {
            AbstractDungeon.gridSelectScreen.open(tmp, 3, this.DESCRIPTIONS[1], false, false, false, false);
        }
        else {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
            AbstractDungeon.gridSelectScreen.open(tmp, 3, this.DESCRIPTIONS[1], false, false, false, false);
        }
    }
}
