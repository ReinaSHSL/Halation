package HalationCode.patches;

import HalationCode.relics.mawarupenguindrum.PenguinHat;
import HalationCode.relics.persona.PinkAlligatorPlush;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;

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
                int masterHandSize = AbstractDungeon.player.masterHandSize;
                int gameHandSize = AbstractDungeon.player.gameHandSize;
                int potionSlots = AbstractDungeon.player.potionSlots;
                boolean inspectMode = AbstractDungeon.player.inspectMode;
                int damagedThisCombat = AbstractDungeon.player.damagedThisCombat;
                int cardsPlayedThisTurn = AbstractDungeon.player.cardsPlayedThisTurn;
                int masterMaxOrb = AbstractDungeon.player.masterMaxOrbs;
                int maxOrbs = AbstractDungeon.player.maxOrbs;
                ArrayList<AbstractOrb> playerOrbs = new ArrayList<>(AbstractDungeon.player.orbs);
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
                AbstractDungeon.player = CardCrawlGame.characterManager.recreateCharacter(charToBecome.chosenClass);
                CardCrawlGame.dungeon.initializeCardPools();
                AbstractDungeon.player.masterHandSize = masterHandSize;
                AbstractDungeon.player.gameHandSize = gameHandSize;
                AbstractDungeon.player.potionSlots = potionSlots;
                AbstractDungeon.player.inspectMode = inspectMode;
                AbstractDungeon.player.damagedThisCombat = damagedThisCombat;
                AbstractDungeon.player.cardsPlayedThisTurn = cardsPlayedThisTurn;
                AbstractDungeon.player.masterMaxOrbs = masterMaxOrb;
                AbstractDungeon.player.maxOrbs = maxOrbs;
                AbstractDungeon.player.hand.clear();
                AbstractDungeon.topPanel.setPlayerName();
                AbstractDungeon.player.maxHealth = currentMaxHp;
                AbstractDungeon.player.currentHealth = currentHp;
                AbstractDungeon.player.gold = currentGold;
                AbstractDungeon.player.energy.prep();
                AbstractDungeon.player.showHealthBar();
                AbstractDungeon.player.healthBarRevivedEvent();
                AbstractDungeon.player.chosenClass = charToBecome.chosenClass;
                EnergyPanel.addEnergy(currentEnergy);
                AbstractDungeon.player.loseRandomRelics(1);
                int relicIndex = 0;
                for (AbstractOrb o : playerOrbs) {
                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(o));
                }
                for (AbstractCard c : playerDeck) {
                    AbstractDungeon.player.masterDeck.addToTop(c);
                }
                for (AbstractRelic relic : playerRelics) {
                    relic.instantObtain(AbstractDungeon.player, relicIndex, false);
                    relicIndex++;
                }
                for (AbstractPotion po : playerPotions) {
                    AbstractDungeon.player.obtainPotion(po);
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
                    AbstractDungeon.player.hand.addToHand(c);
                    AbstractDungeon.player.hand.refreshHandLayout();
                }
                ReflectionHacks.setPrivate(AbstractDungeon.overlayMenu, OverlayMenu.class, "player", AbstractDungeon.player);
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
