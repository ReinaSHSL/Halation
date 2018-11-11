package SakuraCode.relics.steinsgate;

import SakuraCode.SakuraModInitializer;
import SakuraCode.relics.AbstractSakuraRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Convergence extends AbstractSakuraRelic implements ClickableRelic {
    public static final String ID = "sakura:Convergence";

    public Convergence() {
        super(ID, "Convergence.png", AbstractRelic.RelicTier.BOSS, LandingSound.FLAT);
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
        ArrayList<String> emptyList = new ArrayList<String>();
        switch (AbstractDungeon.id) {
            case Exordium.ID:
                new Exordium(AbstractDungeon.player, emptyList);
                AbstractDungeon.player.currentHealth = SakuraModInitializer.startActHealth;
                AbstractDungeon.player.masterDeck = SakuraModInitializer.startActDeck;
                AbstractDungeon.player.relics = SakuraModInitializer.startActRelics;
                AbstractDungeon.player.maxHealth -= 20;
                break;
            case TheCity.ID:
                new TheCity(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                AbstractDungeon.player.currentHealth = SakuraModInitializer.startActHealth;
                AbstractDungeon.player.masterDeck = SakuraModInitializer.startActDeck;
                AbstractDungeon.player.relics = SakuraModInitializer.startActRelics;
                AbstractDungeon.player.maxHealth -= 20;
                break;
            case TheBeyond.ID:
                new TheBeyond(AbstractDungeon.player, AbstractDungeon.specialOneTimeEventList);
                AbstractDungeon.player.currentHealth = SakuraModInitializer.startActHealth;
                AbstractDungeon.player.masterDeck = SakuraModInitializer.startActDeck;
                AbstractDungeon.player.relics = SakuraModInitializer.startActRelics;
                AbstractDungeon.player.maxHealth -= 20;
                break;
            default:
                break;
        }
    }
}
