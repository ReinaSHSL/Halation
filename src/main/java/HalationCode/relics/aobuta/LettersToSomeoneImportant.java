package HalationCode.relics.aobuta;

import HalationCode.cards.LetterOfAdmiration;
import HalationCode.cards.LetterOfLove;
import HalationCode.cards.LetterOfRespect;
import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class LettersToSomeoneImportant extends CustomRelic {
    public static final String ID = "halation:LettersToHer";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/LettersToSomeoneImportant.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static ArrayList<AbstractCard> letterCards = new ArrayList<>();
    private static final int LETTERS_AMT = 1;

    public LettersToSomeoneImportant() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.MAGICAL);
        if (letterCards.size() == 0) {
            letterCards.add(new LetterOfAdmiration());
            letterCards.add(new LetterOfLove());
            letterCards.add(new LetterOfRespect());
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + LETTERS_AMT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new LettersToSomeoneImportant();
    }

    @Override
    public void atBattleStartPreDraw() {
        int rng = AbstractDungeon.cardRng.random(letterCards.size() - 1);
        AbstractCard c = letterCards.get(rng);
        for (int i = 0; i < this.counter; i++) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c.makeCopy(), LETTERS_AMT, true, false, false));
        }
    }

    @Override
    public void onEquip() {
        if (this.counter < 0) {
            this.counter = 1;
        }
    }

    @Override
    public void onChestOpen(boolean bossChest) {
        if (!bossChest) {
            AbstractDungeon.getCurrRoom().addRelicToRewards(new LettersToSomeoneImportant());
        }
    }

    @Override
    public void instantObtain()
    {
        if (AbstractDungeon.player.hasRelic(LettersToSomeoneImportant.ID)) {
            LettersToSomeoneImportant LettersToSomeoneImportant = (LettersToSomeoneImportant) AbstractDungeon.player.getRelic(ID);
            LettersToSomeoneImportant.increment();
            LettersToSomeoneImportant.flash();
        } else {
            super.instantObtain();
        }
    }

    @Override
    public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip)
    {
        if (AbstractDungeon.player.hasRelic(LettersToSomeoneImportant.ID)) {
            LettersToSomeoneImportant LettersToSomeoneImportant = (LettersToSomeoneImportant) AbstractDungeon.player.getRelic(ID);
            LettersToSomeoneImportant.increment();
            LettersToSomeoneImportant.flash();

            isDone = true;
            isObtained = true;
            discarded = true;
        } else {
            super.instantObtain(p, slot, callOnEquip);
        }
    }

    @Override
    public void obtain()
    {
        if (AbstractDungeon.player.hasRelic(LettersToSomeoneImportant.ID)) {
            LettersToSomeoneImportant LettersToSomeoneImportant = (LettersToSomeoneImportant) AbstractDungeon.player.getRelic(ID);
            LettersToSomeoneImportant.increment();
            LettersToSomeoneImportant.flash();
        } else {
            super.obtain();
        }
    }

    public void increment() {
        if (this.counter < 0) {
            this.counter = 0;
        }
        this.counter ++;
    }
}
