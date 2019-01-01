package HalationCode.relics.steinsgate;

import HalationCode.tools.TextureLoader;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardSave;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Convergence extends CustomRelic implements ClickableRelic {
    public static final String ID = "halation:Convergence";
    private static final Texture IMG = TextureLoader.getTexture("HalationImages/relics/Convergence.png");
    private static int startHp = 0;
    private static CardGroup startDeck = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private static ArrayList<AbstractRelic> startRelics = new ArrayList<>();
    private static int startGold = 0;
    private static ArrayList<AbstractPotion> startPotions = new ArrayList<>();
    private static boolean relicChange = false;
    private static boolean normalActChange = true;
    private static final int HP_LOSS = 20;

    public Convergence() {
        super(ID, IMG, AbstractRelic.RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HP_LOSS + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Convergence();
    }

    //TODO SUPPORT NEOW ROOM AND SAVE AND LOAD LOGIC!

    @Override
    public void onRightClick() {
        normalActChange = false;
        setPlayerStats();
        ArrayList<String> emptyList = new ArrayList<String>();
        switch (AbstractDungeon.id) {
            case Exordium.ID:
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                AbstractDungeon.scene.fadeOutAmbiance();
                new Exordium(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                AbstractDungeon.dungeonMapScreen.open(false);
                break;
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
            case TheEnding.ID:
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                AbstractDungeon.scene.fadeOutAmbiance();
                new TheEnding(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
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
        AbstractDungeon.player.decreaseMaxHealth(HP_LOSS);
    }

    public static void relicBullshit() {
        if (relicChange) {
            int index = 0;
            AbstractDungeon.player.relics.clear();
            for (AbstractRelic r : startRelics) {
                r.instantObtain(AbstractDungeon.player, index, false);
                index++;
            }
            relicChange = false;
            normalActChange = true;
            updateStats();
        }
    }

//    @Override
//    public HashMap<String, Object> onSave() {
//        HashMap<String, Object> statsSaved = new HashMap<>();
//        ArrayList<CardSave> cardsInDeck = new ArrayList<>();
//        for (AbstractCard c : startDeck.group) {
//            cardsInDeck.add(new CardSave(c.cardID, c.timesUpgraded, c.misc));
//        }
//        statsSaved.clear();
//        statsSaved.put("hp", startHp);
//        statsSaved.put("relics", startRelics);
//        statsSaved.put("deck", cardsInDeck);
//        statsSaved.put("gold", startGold);
//        statsSaved.put("potions", startPotions);
//        return statsSaved;
//    }
//
//    @Override
//    public void onLoad(HashMap<String, Object> li) {
//        startHp = (int)li.get("hp");
//        startRelics = (ArrayList)li.get("relics");
//        for (CardSave c : (ArrayList<CardSave>)li.get("deck")) {
//            startDeck.addToBottom(CardLibrary.getCopy(c.id, c.upgrades, c.misc));
//        }
//        startGold = (int)li.get("gold");
//        startPotions = (ArrayList<AbstractPotion>)li.get("potions");
//    }
}
