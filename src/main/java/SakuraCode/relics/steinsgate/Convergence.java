package SakuraCode.relics.steinsgate;

import SakuraCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RingOfTheSerpent;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Convergence extends CustomRelic implements ClickableRelic{
    public static final String ID = "sakura:Convergence";
    private static final Texture IMG = TextureLoader.getTexture("SakuraImages/relics/Convergence.png");
    private static int startHp = 0;
    private static CardGroup startDeck = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private static ArrayList<AbstractRelic> startRelics = new ArrayList<>();
    private static int startGold = 0;
    private static ArrayList<AbstractPotion> startPotions = new ArrayList<>();
    private static boolean relicChange = false;
    private static boolean normalActChange = true;

    public Convergence() {
        super(ID, IMG, AbstractRelic.RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Convergence();
    }

    @Override
    public void onRightClick() {
        normalActChange = false;
        setPlayerStats();
        ArrayList<String> emptyList = new ArrayList<String>();
        switch (AbstractDungeon.id) {
            case TheCity.ID:
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                AbstractDungeon.scene.fadeOutAmbiance();
                new TheCity(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                AbstractDungeon.dungeonMapScreen.open(false);
                break;
            case TheBeyond.ID:
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                AbstractDungeon.scene.fadeOutAmbiance();
                new TheBeyond(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                AbstractDungeon.dungeonMapScreen.open(false);
                break;
            default:
                break;
        }
    }

    public static void updateStats() {
        if (normalActChange) {
            startRelics.clear();
            startDeck.clear();
            startHp = Integer.valueOf(AbstractDungeon.player.currentHealth);
            startGold = Integer.valueOf(AbstractDungeon.player.gold);
            startRelics.addAll(AbstractDungeon.player.relics);
            for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                startDeck.addToTop(c.makeStatEquivalentCopy());
            }
        }
    }

    private static void setPlayerStats() {
        AbstractDungeon.player.masterDeck.clear();
        AbstractDungeon.player.currentHealth = startHp;
        AbstractDungeon.player.gold = startGold;
        relicChange = true;
        for (AbstractCard c : startDeck.group) {
            AbstractDungeon.player.masterDeck.addToBottom(c);
        }
        AbstractDungeon.player.decreaseMaxHealth(20);
    }

    public static void relicBullshit() {
        if (relicChange) {
            AbstractDungeon.player.relics.clear();
            for (AbstractRelic r : startRelics) {
                r.obtain();
            }
            relicChange = false;
            normalActChange = true;
            updateStats();
        }
    }

//    @Override
//    public HashMap<String, Object> onSave() {
//        HashMap<String, Object> statsSaved = new HashMap<>();
//        statsSaved.clear();
//        statsSaved.put("hp", startHp);
//        statsSaved.put("relics", startRelics);
//        statsSaved.put("deck", startDeck);
//        System.out.println("SHIT'S HAPPENING HERE " + statsSaved + statsSaved.get("hp") + statsSaved.get("relics") + statsSaved.get("deck"));
//        return statsSaved;
//    }
//
//    @Override
//    public void onLoad(HashMap<String, Object> li) {
//        startHp = (int)li.get("hp");
//        startRelics = (ArrayList)li.get("relics");
//        startDeck = (CardGroup)li.get("deck");
//    }
}
