package HalationCode.relics.mawarupenguindrum;

import HalationCode.cards.DiaryChoiceCard;
import HalationCode.tools.TextureLoader;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Diary extends CustomRelic {
    public static final String ID = "halation:Diary";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Diary.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static boolean pickBoss = true;

    public Diary() {
        super(ID, IMG, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Diary();
    }

    @Override
    public void onEquip() {
        flash();
        pickBoss = true;
        CardGroup bossList = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (String s : BaseMod.getBossIDs(AbstractDungeon.id)) {
           bossList.addToTop(new DiaryChoiceCard(s));
        }
        switch (AbstractDungeon.id) {
            case Exordium.ID:
                bossList.addToTop(new DiaryChoiceCard("The Guardian"));
                bossList.addToTop(new DiaryChoiceCard("Hexaghost"));
                bossList.addToTop(new DiaryChoiceCard("Slime Boss"));
                break;
            case TheCity.ID:
                bossList.addToTop(new DiaryChoiceCard("Collector"));
                bossList.addToTop(new DiaryChoiceCard("Champ"));
                bossList.addToTop(new DiaryChoiceCard("Automaton"));
                break;
            case TheBeyond.ID:
                bossList.addToTop(new DiaryChoiceCard("Time Eater"));
                bossList.addToTop(new DiaryChoiceCard("Donu and Deca"));
                bossList.addToTop(new DiaryChoiceCard("Awakened One"));
                break;
            case TheEnding.ID:
                bossList.addToTop(new DiaryChoiceCard("The Heart"));
        }
        AbstractDungeon.gridSelectScreen.open(bossList, 1, "", false);
    }

    @Override
    public void update() {
        super.update();
        if (pickBoss && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            try {
                pickBoss = false;
                DiaryChoiceCard selected = (DiaryChoiceCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.bossKey = selected.name;
                Method setBoss = AbstractDungeon.class.getDeclaredMethod("setBoss", String.class);
                setBoss.setAccessible(true);
                setBoss.invoke(CardCrawlGame.dungeon, AbstractDungeon.bossKey);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getPrice() {
        return 100;
    }

    public static void newAct() {
        AbstractDungeon.player.getRelic(Diary.ID).onEquip();
    }
}
