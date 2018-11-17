package HalationCode.patches;

import HalationCode.relics.mawarupenguindrum.PenguinHat;
import HalationCode.relics.persona.PinkAlligatorPlush;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;

import javax.smartcardio.Card;
import java.lang.reflect.Array;
import java.util.ArrayList;

@SpirePatch(
        clz=AbstractPlayer.class,
        method="damage",
        paramtypez={DamageInfo.class}
)
public class PinkAlligatorPlushPatch
{
    @SpireInsertPatch(
            locator=Locator.class
    )
    public static SpireReturn Insert(AbstractPlayer __instance, DamageInfo info)
    {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !__instance.hasRelic(MarkOfTheBloom.ID)) {
            if (__instance.hasRelic(PinkAlligatorPlush.ID)) {
                PinkAlligatorPlush.onDeath();
                return SpireReturn.Return(null);
            } else if (__instance.hasRelic(PenguinHat.ID)) {
                ArrayList< AbstractPlayer > charPool = new ArrayList<>();
                int currentMaxHp = AbstractDungeon.player.maxHealth;
                int currentHp = AbstractDungeon.player.maxHealth/10;
                int currentGold = AbstractDungeon.player.gold;
                ArrayList<AbstractCard> playerDeck = new ArrayList<>(AbstractDungeon.player.masterDeck.group);
                ArrayList<AbstractRelic> playerRelics = new ArrayList<>(AbstractDungeon.player.relics);
                ArrayList<AbstractPotion> playerPotions = new ArrayList<>(AbstractDungeon.player.potions);
                ArrayList<AbstractCard> currentDrawPile = new ArrayList<>(AbstractDungeon.player.drawPile.group);
                ArrayList<AbstractCard> currentDiscardPile = new ArrayList<>(AbstractDungeon.player.discardPile.group);
                ArrayList<AbstractCard> currentExhaustPile = new ArrayList<>(AbstractDungeon.player.exhaustPile.group);
                ArrayList<AbstractCard> currentHand = new ArrayList<>(AbstractDungeon.player.hand.group);
                int currentEnergy = EnergyPanel.totalCount;
                for (AbstractPlayer p : CardCrawlGame.characterManager.getAllCharacters()) {
                    if (p.chosenClass != AbstractDungeon.player.chosenClass) {
                        charPool.add(p);
                    }
                }
                int r = AbstractDungeon.eventRng.random(charPool.size()-1);
                AbstractPlayer charToBecome = charPool.get(r);
                PenguinHat.revivedClass = charToBecome;
                AbstractDungeon.player = charToBecome;
                CardCrawlGame.dungeon.initializeCardPools();
                AbstractDungeon.player.hand.clear();
                AbstractDungeon.topPanel.setPlayerName();
                AbstractDungeon.player.maxHealth = currentMaxHp;
                AbstractDungeon.player.currentHealth = currentHp;
                AbstractDungeon.player.gold = currentGold;
                AbstractDungeon.player.energy.prep();
                AbstractDungeon.player.showHealthBar();
                AbstractDungeon.player.healthBarRevivedEvent();
                EnergyPanel.addEnergy(currentEnergy);
                AbstractDungeon.player.loseRandomRelics(1);
                int relicIndex = 0;
                int potionIndex = 0;
                for (AbstractCard c : playerDeck) {
                    AbstractDungeon.player.masterDeck.addToTop(c);
                }
                for (AbstractRelic relic : playerRelics) {
                    relicIndex++;
                    relic.instantObtain(AbstractDungeon.player, relicIndex, false);
                }
                for (AbstractPotion po : playerPotions) {
                    po.setAsObtained(potionIndex);
                    potionIndex++;
                }
                for (AbstractCard c : currentDrawPile) {
                    AbstractDungeon.player.drawPile.addToBottom(c);
                }
                for (AbstractCard c : currentDiscardPile) {
                    AbstractDungeon.player.discardPile.addToBottom(c);
                }
                for (AbstractCard c : currentExhaustPile) {
                    AbstractDungeon.player.exhaustPile.addToBottom(c);
                }
                for (AbstractCard c : currentHand) {
                    AbstractDungeon.player.hand.addToTop(c);
                }
                return SpireReturn.Return(null);
            }
        }
        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator
    {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception
        {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "isDead");

            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
