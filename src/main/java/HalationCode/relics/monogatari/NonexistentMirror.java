package HalationCode.relics.monogatari;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class NonexistentMirror extends CustomRelic {
    public static final String ID = "halation:NonexistentMirror";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/NonExistentMirror.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static ArrayList<AbstractCard> cardToAdd = new ArrayList<>();
    private static boolean addCard = false;
    private static ArrayList<AbstractCard> addedCards = new ArrayList<>();
    private static final int COPY_AMT = 2;

    public NonexistentMirror() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + COPY_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new NonexistentMirror();
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (!this.usedUp && addedCards.size() > 0 && this.counter > 0)  {
            if (c != addedCards.get(0)) {
                cardToAdd.add(c.makeStatEquivalentCopy());
                addCard = true;
                this.counter--;
            }
        } else if (!this.usedUp) {
            cardToAdd.add(c.makeStatEquivalentCopy());
            addCard = true;
            this.counter--;
        }
        if (this.counter == 0) {
            this.counter--;
            this.usedUp = true;
        }
    }

    @Override
    public void onEquip() {
        this.counter = COPY_AMT;
    }

    public static void cardEffects() {
        if (addCard) {
            addCard = false;
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(cardToAdd.get(0), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            addedCards.add(cardToAdd.get(0));
            cardToAdd.clear();
        }
    }
}
