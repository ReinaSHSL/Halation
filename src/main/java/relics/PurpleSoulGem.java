package relics;

import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
import powers.RestartDamagePower;

public class PurpleSoulGem extends CustomRelic implements ClickableRelic {
    public static final String ID = "sakura:PurpleSoulGem";

    public PurpleSoulGem()
    {
        super(ID, "purpleSoulGem.png", AbstractRelic.RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.counter++;
            flash();
            AbstractDungeon.actionManager.addToBottom(new PurpleSoulGemReviveAction());
        }
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }


    private class PurpleSoulGemReviveAction extends AbstractGameAction
    {
        @Override
        public void update()
        {
            flash();
            restartCombat();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, PurpleSoulGem.this));
            isDone = true;
        }
    }


    private void restartCombat()
    {
        System.out.println("Remaking combat vs " + AbstractDungeon.lastCombatMetricKey);
        AbstractRoom room = AbstractDungeon.getCurrRoom();

        AbstractDungeon.fadeIn();
        AbstractDungeon.player.resetControllerValues();
        AbstractDungeon.effectList.clear();
        AbstractDungeon.topLevelEffects.clear();
        AbstractDungeon.topLevelEffectsQueue.clear();
        AbstractDungeon.effectsQueue.clear();
        AbstractDungeon.dungeonMapScreen.dismissable = true;
        AbstractDungeon.dungeonMapScreen.map.legend.isLegendHighlighted = false;

        AbstractDungeon.player.orbs.clear();
        AbstractDungeon.player.animX = 0.0f;
        AbstractDungeon.player.animY = 0.0f;
        AbstractDungeon.player.hideHealthBar();
        AbstractDungeon.player.hand.clear();
        AbstractDungeon.player.powers.clear();
        AbstractDungeon.player.drawPile.clear();
        AbstractDungeon.player.discardPile.clear();
        AbstractDungeon.player.exhaustPile.clear();
        AbstractDungeon.player.limbo.clear();
        AbstractDungeon.player.loseBlock(true);
        AbstractDungeon.player.damagedThisCombat = 0;
        GameActionManager.turn = 1;

        AbstractDungeon.actionManager.monsterQueue.clear();
        AbstractDungeon.actionManager.monsterAttacksQueued = true;


        for (AbstractRelic r : AbstractDungeon.player.relics) {
            r.onEnterRoom(room);
        }

        AbstractDungeon.actionManager.clear();

        room.monsters = MonsterHelper.getEncounter(AbstractDungeon.lastCombatMetricKey);
        room.monsters.init();

        // Prepare monsters
        for (AbstractMonster m : room.monsters.monsters) {
            m.showHealthBar();
            m.usePreBattleAction();
            m.useUniversalPreBattleAction();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new RestartDamagePower(m, 1), 1));
        }

        AbstractDungeon.player.preBattlePrep();

        // From AbstractRoom.update(). Most of what happens at start of combat
        AbstractDungeon.actionManager.turnHasEnded = true;
        AbstractDungeon.topLevelEffects.add(new BattleStartEffect(false));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAndEnableControlsAction(AbstractDungeon.player.energy.energyMaster));

        AbstractDungeon.player.applyStartOfCombatPreDrawLogic();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, AbstractDungeon.player.gameHandSize));

        AbstractDungeon.actionManager.addToBottom(new EnableEndTurnButtonAction());
        AbstractDungeon.overlayMenu.showCombatPanels();
        AbstractDungeon.player.applyStartOfCombatLogic();
        AbstractDungeon.player.applyStartOfTurnRelics();
        AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
        AbstractDungeon.player.applyStartOfTurnCards();
        AbstractDungeon.player.applyStartOfTurnPowers();
        AbstractDungeon.player.applyStartOfTurnOrbs();
    }

    @Override
    public void onVictory()
    {
        flash();
        this.counter = 0;

    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new PurpleSoulGem();
    }
}
